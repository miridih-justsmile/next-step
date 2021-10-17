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

    @Override
    public String toString() {
        return userDto.toString();
    }
}
