package com.aprox.authentication.controllers;

import com.aprox.authentication.model.User;
import com.aprox.authentication.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
public class MainController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/api")
    public String apiHome(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Authenticated OK!";
    }
    
}