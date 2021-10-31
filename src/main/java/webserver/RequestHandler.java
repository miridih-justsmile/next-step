package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.request.Request;
import webserver.response.resolver.ViewResolver;
import webserver.response.resolver.ViewResolverFactory;
import webserver.web.ContentType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

public class RequestHandler extends Thread {
    private final static Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private final Socket socket;

    public RequestHandler(final Socket connectionSocket) {
        this.socket = connectionSocket;
    }

    public void run() {
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            final Request request = new Request(bufferedReader);
            final ViewResolver viewResolver = ViewResolverFactory.create(request);
            final byte[] body = viewResolver.getBodyByte();
            log.debug("IP : {}, Port : {}, Method : {}, URL : {}",
                    socket.getInetAddress(),
                    socket.getPort(),
                    request.getHttpHeader().getMethod(),
                    request.getHttpHeader().getUrl()
            );

            response200Header(dataOutputStream, viewResolver.getCharset(), body.length, viewResolver.responseContentType());
            responseBody(dataOutputStream, body);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void response200Header(final DataOutputStream dos, final Charset charset, final int lengthOfBodyContent, final ContentType contentType) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: " + contentType.getContentType() + ";charset=" + charset + "\r\n");
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
}
