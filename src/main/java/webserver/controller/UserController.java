package webserver.controller;

import app.user.domain.User;
import app.user.domain.UserService;
import app.user.ui.JoinUserVo;
import webserver.request.RequestWrapper;
import webserver.servlet.RestController;
import webserver.servlet.GET;

import java.util.stream.Collectors;

@RestController(path = "/user")
public class UserController extends DefaultServletController {

    public UserController() {}

    @GET(path = "/create")
    public String create(final RequestWrapper requestWrapper) {
        UserService.joinUser(new JoinUserVo(requestWrapper));;
        return UserService.findAll().stream().map(User::getNickNameId).collect(Collectors.toList()).toString();
    }

    @GET(path = "/test")
    public String test(final RequestWrapper requestWrapper) {
        return "test";
    }
}
