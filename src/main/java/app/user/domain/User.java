package app.user.domain;

import app.user.infra.UserDto;

public class User {
    private final UserDto userDto;

    private User() {
        userDto = new UserDto();
    }

    public User(final UserDto userDto) {
        this.userDto = userDto;
    }

    public String getUserId() {
        return userDto.getUserId();
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
        return "User [userId=" + getUserId() + ", password=" + getPassword() + ", name=" + getName() + ", email=" + getEmail() + "]";
    }
}
