package webserver.web;

import java.nio.charset.Charset;

public interface ContentType {
    String getContentType();
    Charset getCharset();

    class Builder {
        private String contentType;
        private Charset charset;

        public Builder() {}

        public ContentType.Builder setContentType(final String s) {
            this.contentType = s;
            return this;
        }

        public ContentType.Builder setCharset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public ContentType build() {
            return new ContentType() {
                private final String contentTypeStr = contentType;
                private final Charset charSetStr = charset;

                @Override
                public String getContentType() {
                    return contentTypeStr;
                }

                @Override
                public Charset getCharset() {
                    return charSetStr;
                }
            };
        }
    }
}
