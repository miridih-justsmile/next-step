package webserver.response.result;

import webserver.response.HTTPStatus;
import webserver.web.ContentType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileResult extends ResponseDefault {
    private final byte[] result;
    private ContentType contentType;

    private FileResult(final String path) throws IOException {
        final File file = new File("./webapp" + path);
        this.result = fileInit(file);
        this.contentType = new ContentType.Builder()
                .setContentType(Files.probeContentType(file.toPath()))
                .setCharset(StandardCharsets.UTF_8)
                .build();
    }

    private byte[] fileInit(final File file) {
        try {
            final List<String> stringList = Files.readAllLines(file.toPath());
            final StringBuilder resultBuilder = new StringBuilder();
            for (final String str : stringList) {
                resultBuilder.append(str);
            }
            return resultBuilder.toString().getBytes();
        } catch (Exception e) {
            this.httpStatus = HTTPStatus.BAD_REQUEST_400;
            return "파일을 찾을 수 없습니다".getBytes();
        }
    }

    public static FileResult init(final String filePath) throws IOException {
        return new FileResult(filePath);
    }

    @Override
    public byte[] getByte() {
        return result;
    }

    @Override
    public ContentType getContentType() {
        return this.contentType;
    }
}
