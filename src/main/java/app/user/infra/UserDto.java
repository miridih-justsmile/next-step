package app.user.infra;

public class UserDto {

    private Long userIdx;
    private Long nickNameIdx;
    private String nickName;
    private String password;
    private String name;
    private String email;

    public UserDto() {}

    public Long getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(Long userIdx) {
        this.userIdx = userIdx;
    }

    public Long getNickNameIdx() {
        return nickNameIdx;
    }

    public void setNickNameIdx(Long nickNameIdx) {
        this.nickNameIdx = nickNameIdx;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userIdx=" + userIdx +
                ", nickNameIdx=" + nickNameIdx +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
