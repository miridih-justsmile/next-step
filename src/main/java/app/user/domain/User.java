package app.user.domain;

import app.user.infra.UserDto;

public class User {
    private final UserDto userDto;

    private User() {
        userDto = new UserDto();
    }

    User(final UserDto userDto) {
        this.userDto = userDto;
    }

    public String getNickNameId() {
        return String.format("%s #%s", userDto.getNickName(), userDto.getNickNameIdx());
    }

    public Long getNickNameIdx() {
        return userDto.getNickNameIdx();
    }

    public String getNickName() {
        return userDto.getNickName();
    }

    public String getPassword() {
        return userDto.getPassword();
    }

    public String getName() {
        return userDto.getName();
    }

    public String getEmail() {
        return userDto.getEmail();
    }

    @Override
    public String toString() {
        return userDto.toString();
    }
}
