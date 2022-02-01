package ib;

public class EmployeeAccounts {
    private final String login;
    private final String password;

    public EmployeeAccounts() {
        this.login = "admin";
        this.password = "password";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
