package webserver.controller;

import app.user.domain.User;
import app.user.domain.UserService;
import app.user.ui.JoinUserVo;
import webserver.request.RequestWrapper;
import webserver.servlet.GET;
import webserver.servlet.POST;
import webserver.servlet.RestController;

@RestController(path = "/user")
public class UserController extends DefaultServletController {
    
    @GET(path = "/create")
    public String create(final RequestWrapper requestWrapper) {
        UserService.joinUser(new JoinUserVo(requestWrapper));;
        StringBuilder builder = new StringBuilder();
        builder.append("========== 닉네임 아이디 목록 ==========<br>");
        for (User user : UserService.findAll()) {
            builder.append(String.format("%s<br>", user.getNickNameId()));
        }
        return builder.toString();
    }

    @POST(path = "create")
    public String createPost(final RequestWrapper requestWrapper) {
        return create(requestWrapper);
    }

    @GET(path = "/test")
    public String test(final RequestWrapper requestWrapper) {
        return "test";
    }
}
