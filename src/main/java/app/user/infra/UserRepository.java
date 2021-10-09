package app.user.infra;

import app.user.domain.User;

import java.util.Collection;

public interface UserRepository {
    void addUser(UserDto userDto);

    User findUserByIdx(long userIdx);

    Collection<User> findAll();
}
