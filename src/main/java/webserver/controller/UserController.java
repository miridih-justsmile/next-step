package webserver.controller;

import app.user.domain.User;
import app.user.domain.UserService;
import app.user.ui.JoinUserVo;
import webserver.request.RequestWrapper;
import webserver.response.result.JsonResult;
import webserver.servlet.GET;
import webserver.servlet.POST;
import webserver.servlet.RestController;

@RestController(path = "/user")
public class UserController extends DefaultServletController {
    
    @GET(path = "/create")
    public String create(final RequestWrapper requestWrapper) {
        UserService.joinUser(new JoinUserVo(requestWrapper));;
        return "회원가입이 완료되었습니다.";
    }

    @POST(path = "/create")
    public String createPost(final RequestWrapper requestWrapper) {
        return create(requestWrapper);
    }

    @GET(path = "/test")
    public String test(final RequestWrapper requestWrapper) {
        return "test";
    }
}
