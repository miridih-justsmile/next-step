package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import webserver.request.RequestHeader;
import webserver.resolver.ViewResolver;
import webserver.resolver.ViewResolverFactory;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread {
    private final static Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private final static String DEFAULT_CHARSET = "utf-8";
    private final Socket socket;

    public RequestHandler(final Socket connectionSocket) {
        this.socket = connectionSocket;
    }

    public void run() {
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            final RequestHeader requestHeader = createRequestHead(bufferedReader);
            final ViewResolver viewResolver = ViewResolverFactory.create(requestHeader);
            final byte[] body = viewResolver.getBodyByte();
            log.debug("New Client Connected IP : {}, Port : {}, URL : {}", socket.getInetAddress(), socket.getPort(), requestHeader.getHttpHeader().getUrl());

            response200Header(dataOutputStream, body.length, viewResolver.responseContentType());
            responseBody(dataOutputStream, body);
        } catch (final Exception e) {
            e.printStackTrace();
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

    private RequestHeader createRequestHead(final BufferedReader bufferedReader) throws IOException {
        final RequestHeader requestHeader = new RequestHeader();
        String line = "firstLine";
        while (!"".equals(line)) {
            if ("firstLine".equals(line)) {
                line = bufferedReader.readLine();
                requestHeader.setHeader(RequestHeader.Title.HTTP, line);
            } else {
                line = bufferedReader.readLine();
                if (!line.isEmpty()) {
                    final HttpRequestUtils.Pair parseHeader = HttpRequestUtils.parseHeader(line);
                    requestHeader.setHeader(RequestHeader.Title.getTitle(parseHeader.getKey()), parseHeader.getValue());
                }
            }
        }
        return requestHeader;
    }
}
