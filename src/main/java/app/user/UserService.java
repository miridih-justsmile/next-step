package app.user;

import app.user.domain.User;
import app.user.domain.UserStaticRepo;
import app.user.infra.UserRepository;
import app.user.ui.JoinUserVo;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final static UserRepository userRepo = UserStaticRepo.create();

    public static void joinUser(final JoinUserVo joinUserVo) {
        userRepo.addUser(joinUserVo.convertUser());
    }

    public static User findUser(final long userIdx) {
        return userRepo.findUserByIdx(userIdx);
    }

    public static List<User> findAll() {
        return (ArrayList<User>) userRepo.findAll();
    }
}
