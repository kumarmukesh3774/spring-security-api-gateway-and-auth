package org.mik.springboot3security.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mik.springboot3security.entity.AuthRequest;
import org.mik.springboot3security.entity.UserInfo;
import org.mik.springboot3security.service.JwtService;
import org.mik.springboot3security.service.UserInfoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return  "Welcome this endpoint is not Secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo user){
        return userInfoService.addUser(user);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }
        else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
}

