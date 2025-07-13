package com.dusan.ecommerce.auth;

import jakarta.validation.constraints.NotBlank;

public class AuthRegisterRequest {

    @NotBlank(message = "First name can not be empty")
    private String firstName;

    @NotBlank(message = "Last name can not be empty")
    private String lastName;

    @NotBlank(message = "Email can not be empty")
    private String email;

    @NotBlank(message = "Username can not be empty")
    private String username;

    @NotBlank(message = "Password can not be empty")
    private String password;

    public @NotBlank(message = "First name can not be empty") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name can not be empty") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last name can not be empty") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name can not be empty") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Email can not be empty") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email can not be empty") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Username can not be empty") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username can not be empty") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password can not be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password can not be empty") String password) {
        this.password = password;
    }
}
