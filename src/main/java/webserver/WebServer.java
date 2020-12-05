package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ArraysUtil;
import util.IntegerUtil;

import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private static final Logger log = LoggerFactory.getLogger(WebServer.class);
    private static final int DEFAULT_PORT = 8080;

    public static void main(final String[] args) throws Exception {
        // 서버소켓을 생성한다. 웹서버는 기본적으로 8080번 포트를 사용한다.
        final int port = ArraysUtil.isNotEmpty(args) ? IntegerUtil.parseInt(args[0], DEFAULT_PORT) : DEFAULT_PORT;
        try (final ServerSocket listenSocket = new ServerSocket(port)) {
            log.info("Web Application Server started {} port.", port);

            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                new RequestHandler(connection).start();
            }
        }
    }
}
