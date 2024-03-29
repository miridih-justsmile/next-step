package app.user.infra;

import app.user.domain.FromSite;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserDto {

    private Long userIdx;
    private Long nickNameIdx;
    private String nickName;
    private String password;
    private String name;
    private String email;
    private FromSite fromSite;
}
