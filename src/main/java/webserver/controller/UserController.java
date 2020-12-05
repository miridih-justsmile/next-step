package webserver.controller;

import webserver.servlet.Controller;
import webserver.servlet.GET;

@Controller(path = "/user")
public class UserController extends DefaultServletController {

    public UserController() {}

    @GET(path = "/create")
    public String create() {

        return "";
    }
}
