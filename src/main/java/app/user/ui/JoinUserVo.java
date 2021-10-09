package app.user.ui;

import app.user.infra.UserDto;
import webserver.request.RequestWrapper;

public class JoinUserVo {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public JoinUserVo(final RequestWrapper requestWrapper) {
        this.userId = requestWrapper.getParameter("nickName");
        this.password = requestWrapper.getParameter("password");
        this.name = requestWrapper.getParameter("name");
        this.email = requestWrapper.getParameter("email");
    }

    public UserDto convertDto() {
        final UserDto userDto = new UserDto();
        userDto.setNickName(userId);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setEmail(email);
        return userDto;
    }
}
