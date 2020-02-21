package com.edu.domain.model.admin;

import javax.validation.constraints.NotNull;

public class UserUpdateForm {
    @NotNull
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String country;

    @NotNull
    private String state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
