package webserver.web;

public interface ContentType {
    String getContentType();
    String getCharset();

    class Builder {
        private String contentType;
        private String charset;

        public Builder() {}

        public ContentType.Builder contentType(String s) {
            this.contentType = s;
            return this;
        }

        public ContentType.Builder charset(String s) {
            this.charset = s;
            return this;
        }

        public ContentType build() {
            return new ContentType() {
                private final String contentTypeStr = contentType;
                private final String charSetStr = charset;

                @Override
                public String getContentType() {
                    return contentTypeStr;
                }

                @Override
                public String getCharset() {
                    return charSetStr;
                }
            };
        }
    }
}
