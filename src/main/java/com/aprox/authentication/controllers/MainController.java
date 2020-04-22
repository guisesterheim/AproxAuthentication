package com.aprox.authentication.controllers;

import com.aprox.authentication.model.FrontAuthentication;
import com.aprox.authentication.model.User;
import com.aprox.authentication.service.UserDetailService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/healthCheck")
    public String index(){
        return "OK";
    }

    @RequestMapping(value = "/api/**")
    public ResponseEntity apiHome(@RequestBody (required = false) String body, HttpMethod method, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        URI otherMicroservices = new URI("http",
                                    null,
                                    "localhost",
                                    8081,
                                        request.getRequestURI(),
                                        request.getQueryString(),
                                null);

        ResponseEntity resp =
                restTemplate.exchange(otherMicroservices, method, new HttpEntity<String>(body), String.class);

        return resp;
    }

    @PostMapping("/authenticate")
    public ResponseEntity login(@RequestBody FrontAuthentication frontAuthentication){
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                                                            frontAuthentication.getUsername(),
                                                            frontAuthentication.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        return ResponseEntity.accepted().build();
    }
}