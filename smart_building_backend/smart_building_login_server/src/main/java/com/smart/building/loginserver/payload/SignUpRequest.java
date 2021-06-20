package com.smart.building.loginserver.payload;

import com.smart.building.loginserver.model.AuthProvider;
import com.smart.building.loginserver.model.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotNull
    @Size(min = 1, max = 25)
    private String name;

    @NotNull @NotEmpty
    @Email
    private String email;

    @NotNull
    @Size(min = 1, max = 25)
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
