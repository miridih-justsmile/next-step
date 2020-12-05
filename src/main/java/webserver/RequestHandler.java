package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import webserver.request.RequestHead;
import webserver.resolver.ViewResolver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private static final String DEFAULT_CHARSET = "utf-8";
    private final Socket socket;

    public RequestHandler(final Socket connectionSocket) {
        this.socket = connectionSocket;
    }

    public void run() {
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            final RequestHead requestHead = createRequestHead(bufferedReader);
            final ViewResolver viewResolver = new ViewResolver(requestHead.getHttpHead().getUrl());
            final byte[] body = viewResolver.getBodyByte();
            log.debug("New Client Connected IP : {}, Port : {}, URL : {}", socket.getInetAddress(), socket.getPort(), requestHead.getHttpHead().getUrl());

            response200Header(dataOutputStream, body.length, viewResolver.responseContentType());
            responseBody(dataOutputStream, body);
        } catch (final IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(final DataOutputStream dos, final int lengthOfBodyContent, final String contentType) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: " + contentType + ";charset=" + DEFAULT_CHARSET + "\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (final IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(final DataOutputStream dos, final byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (final IOException e) {
            log.error(e.getMessage());
        }
    }

    private RequestHead createRequestHead(final BufferedReader bufferedReader) throws IOException {
        final RequestHead requestHead = new RequestHead();
        String line = "firstLine";
        while (!"".equals(line)) {
            if ("firstLine".equals(line)) {
                line = bufferedReader.readLine();
                requestHead.setHead(RequestHead.Title.HTTP, line);
            } else {
                line = bufferedReader.readLine();
                if (!line.isEmpty()) {
                    final HttpRequestUtils.Pair parseHeader = HttpRequestUtils.parseHeader(line);
                    requestHead.setHead(RequestHead.Title.getTitle(parseHeader.getKey()), parseHeader.getValue());
                }
            }
        }
        return requestHead;
    }
}
