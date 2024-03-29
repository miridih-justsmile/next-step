package app.user.ui;

import app.user.domain.FromSite;
import app.user.infra.UserDto;
import lombok.Getter;
import webserver.request.RequestWrapper;

@Getter
public class JoinUserVo {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private final FromSite fromSite;

    public JoinUserVo(final RequestWrapper requestWrapper) {
        this.userId = requestWrapper.getParameter("nickName");
        this.password = requestWrapper.getParameter("password");
        this.name = requestWrapper.getParameter("name");
        this.email = requestWrapper.getParameter("email");
        this.fromSite = FromSite.findSite(requestWrapper.getParameter("fromSite"));
    }

    public UserDto convertDto() {
        final UserDto userDto = new UserDto();
        userDto.setNickName(userId);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setFromSite(fromSite);
        return userDto;
    }
}
