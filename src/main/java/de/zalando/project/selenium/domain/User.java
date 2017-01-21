package de.zalando.project.selenium.domain;

public class User {

    private final String alias;
    private final String password;

    public User(final String alias, final String password) {
        this.alias = alias;
        this.password = password;
    }

    public String getAlias() { return alias; }

    public String getPassword() { return password; }
}
