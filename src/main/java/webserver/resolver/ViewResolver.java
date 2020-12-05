package webserver.resolver;

import webserver.request.head.HttpHead;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ViewResolver {
    private final File responseFile;

    public ViewResolver(final String url) {
        this.responseFile = new File("./webapp" + url);
    }

    public byte[] getBodyByte() throws IOException {
        return Files.readAllBytes(this.responseFile.toPath());
    }

    public String responseContentType() throws IOException {
        return Files.probeContentType(this.responseFile.toPath());
    }
}
