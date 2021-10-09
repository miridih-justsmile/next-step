package webserver.resolver;

import javassist.NotFoundException;
import webserver.request.RequestHeader;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

class FileResolver extends ViewResolverDefault {
    private final File responseFile;

    FileResolver(RequestHeader requestHeader) {
        super(requestHeader);
        this.responseFile = new File("./webapp" + requestHeader.getHttpHeader().getUri().getUrlString());
    }

    @Override
    public byte[] getBodyByte() {
        try {
            if (responseFile != null) {
                final List<String> stringList = Files.readAllLines(this.responseFile.toPath());
                final StringBuilder resultBuilder = new StringBuilder();
                for (final String str : stringList) {
                    resultBuilder.append(str);
                }
                return resultBuilder.toString().getBytes();
            }
            throw new NotFoundException("path를 찾을 수 없음");
        } catch (Exception e) {
            return e.getMessage().getBytes();
        }
    }

    @Override
    public String responseContentType() {
        try {
            return Files.probeContentType(this.responseFile.toPath());
        } catch (final Exception e) {
            return "text/html";
        }
    }
}
