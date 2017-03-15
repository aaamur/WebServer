package accounts;

public class UserProfile {
    private String login;
    private String password;
    private String email;
    private Boolean isEnable;

    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isEnable = true;
    }

    public UserProfile(String login) {
        this.login = login;
        this.password = login;
        this.email = login;
        this.isEnable = true;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

}
