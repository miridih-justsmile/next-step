package webserver.response.resolver;

import com.oracle.webservices.internal.api.message.ContentType;
import javassist.NotFoundException;
import webserver.request.RequestHeader;

import java.io.File;
import java.nio.charset.StandardCharsets;
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
    public ContentType responseContentType() {
        try {
            return new ContentType.Builder()
                    .contentType(Files.probeContentType(this.responseFile.toPath()))
                    .charset(StandardCharsets.UTF_8.name())
                    .build();
        } catch (final Exception e) {
            return super.responseContentType();
        }
    }
}
