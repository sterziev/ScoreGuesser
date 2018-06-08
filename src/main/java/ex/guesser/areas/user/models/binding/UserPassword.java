package ex.guesser.areas.user.models.binding;

import ex.guesser.areas.errorHandling.validation.annotaitons.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@PasswordMatches()
public abstract class UserPassword {
    private static final String PSW_NOT_EMPTY_MSG = "Password cannot be empty";
    private static final String PSW_LENGHT_MSG = "Password must be at least 5 symbols";
    private static final String PASSWORD_MUST_CONT_NUMS = "Password must contains at least one number";
    @NotEmpty(message = PSW_NOT_EMPTY_MSG)
    @Size(min = 3, max = 50, message = PSW_LENGHT_MSG)
    @Pattern(regexp = ".*[0-9].*", message = PASSWORD_MUST_CONT_NUMS)
    protected String password;

    protected String confirmPassword;

    public abstract String getPassword();
    public abstract void setPassword(String password);
    public abstract String getConfirmPassword();
    abstract void setConfirmPassword(String confirmPassword);
}
