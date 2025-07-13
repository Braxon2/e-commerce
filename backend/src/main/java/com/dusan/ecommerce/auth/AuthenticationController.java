package com.dusan.ecommerce.auth;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody @Valid AuthRegisterRequest request){
        return authenticationService.register(request);
    }



    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody @Valid AuthLoginRequest request){
        return authenticationService.authenticate(request);
    }
}
