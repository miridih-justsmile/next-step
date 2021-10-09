package webserver.controller;

import webserver.request.RequestWrapper;
import webserver.servlet.RestController;
import webserver.servlet.GET;

@RestController(path = "/user")
public class UserController extends DefaultServletController {

    public UserController() {}

    @GET(path = "/create")
    public String create(final RequestWrapper requestWrapper) {
        return "Hello!!!\n" + requestWrapper.getRequestHead().getHttpHeader().toString();
    }

    @GET(path = "/test")
    public String test(final RequestWrapper requestWrapper) {
        return "test";
    }
}
