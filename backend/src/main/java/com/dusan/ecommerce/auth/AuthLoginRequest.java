package com.dusan.ecommerce.auth;

import jakarta.validation.constraints.NotBlank;

public class AuthLoginRequest {
    @NotBlank(message = "Email can not be empty")
    private String email;

    @NotBlank(message = "Password can not be empty")
    private String password;

    public AuthLoginRequest(){
    }

    public AuthLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
