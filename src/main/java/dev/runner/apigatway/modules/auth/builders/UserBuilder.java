package dev.runner.apigatway.modules.auth.builders;

import dev.runner.apigatway.modules.auth.dto.User;


public class UserBuilder {
    private String id;
    private String email;
    private String password;
    private String username;

    public static UserBuilder newUser() {
        return new UserBuilder();
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public User build() {
        return new User(id, email, password, username);
    }
}
