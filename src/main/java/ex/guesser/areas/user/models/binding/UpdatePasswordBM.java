package ex.guesser.areas.user.models.binding;

public class UpdatePasswordBM  extends UserPassword{
    private static final String PSW_NOT_EMPTY_MSG = "Password cannot be empty";
    private static final String PSW_LENGHT_MSG = "Password must be at least 5 symbols";
    private static final String PASSWORD_MUST_CONT_NUMS = "Password must contains at least one number";

    private String oldPassword;

    private String id;

    public String getPassword() {
        return super.password;
    }

    public void setPassword(String password) {
        super.password = password;
    }

    public String getConfirmPassword() {
        return super.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        super.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


