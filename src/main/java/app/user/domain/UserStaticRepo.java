package app.user.domain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import app.user.infra.UserDto;
import app.user.infra.UserRepository;
import com.google.common.collect.Maps;

import app.user.domain.User;

public class UserStaticRepo implements UserRepository {
    private static Map<Long, UserDto> users = Maps.newHashMap();
    private static long userSeq = 1;

    private UserStaticRepo() {}

    public static UserStaticRepo create() {
        return new UserStaticRepo();
    }

    public void addUser(UserDto user) {
        if (user.getUserIdx() == null || user.getUserIdx() == 0) {
            user.setUserIdx(userSeq++);
        }
        users.put(user.getUserIdx(), user);
    }

    public User findUserByIdx(final long userIdx) {
        return new User(users.get(userIdx));
    }

    public Collection<User> findAll() {
        return users.values().stream().map(User::new).collect(Collectors.toList());
    }
}
