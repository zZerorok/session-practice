package project.session.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginForm {
    @NotEmpty(message = "ID를 입력하세요.")
    private String loginId;

    @NotEmpty(message = "패스워드를 입력하세요.")
    private String password;
}
