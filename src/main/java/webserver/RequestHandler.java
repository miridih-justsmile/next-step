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
            log.debug("IP : {}, Port : {}, Method : {}, URL : {}",
                    socket.getInetAddress(),
                    socket.getPort(),
                    request.getHttpHeader().getMethod(),
                    request.getHttpHeader().getUrl()
            );
            response(dataOutputStream, viewResolver);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void response(final DataOutputStream dos, final ViewResolver viewResolver) {
        try {
            dos.write(viewResolver.getHeaderByte());
            dos.write(viewResolver.getBodyByte(), 0, viewResolver.getBodyByte().length);
            dos.flush();
        } catch (final IOException e) {
            log.error(e.getMessage());
        }
    }
}
