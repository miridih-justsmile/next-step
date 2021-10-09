package webserver.servlet;

public enum Config {
    DEFAULT_SERVLET_PATH("api"),
    WEB_SERVER_PACKAGE_NAME("webserver");

    private final String value;
    Config(String value) {
        this.value = value;
    }

    public boolean equals(final String str) {
        return this.value.equals(str);
    }

    public String value(){
        return this.value;
    }

    @Override
    public String toString() {
        return this.value();
    }
}
