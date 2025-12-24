package org.mik.springboot3security.service;

import org.jspecify.annotations.Nullable;
import org.mik.springboot3security.entity.UserInfo;
import org.mik.springboot3security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;
    //private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UserInfoRepository repository) {
        this.repository = repository;
       // this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<UserInfo> userInfo = repository.findByUsername(username);
        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Convert UserInfo to UserDetails (UserInfoDetails)
        UserInfo user = userInfo.get();
        List<GrantedAuthority> authorityList = Arrays.stream(user.getRoles().split(",")).map(a->new GrantedAuthority(){
            @Override
            public @Nullable String getAuthority() {
                return a;
            };
        }).collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), authorityList);

    }
    // Add any additional methods for registering or managing users
    public String addUser(UserInfo userInfo) {
        // Encrypt password before saving
        userInfo.setPassword(passwordEncoder().encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added successfully!";
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
