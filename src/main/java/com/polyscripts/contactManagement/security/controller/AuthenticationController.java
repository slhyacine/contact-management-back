package com.polyscripts.contactManagement.security.controller;

import com.polyscripts.contactManagement.models.User;
import com.polyscripts.contactManagement.security.auth.JwtAuthenticationRequest;
import com.polyscripts.contactManagement.security.auth.TokenHelper;
import com.polyscripts.contactManagement.security.dto.UserTokenState;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final TokenHelper tokenHelper;
    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public UserTokenState generateToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        List<String> perms = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String jws = tokenHelper.generateToken(user.getUsername(), perms);
        long expiresIn = tokenHelper.getExpiredIn();

        return new UserTokenState(jws, expiresIn);
    }
}
