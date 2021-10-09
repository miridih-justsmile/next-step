package webserver.controller;

public abstract class DefaultServletController implements ServletController {
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
