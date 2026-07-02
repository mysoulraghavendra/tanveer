package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.comp.JwtUtil;
import com.app.entity.Users;
import com.app.repo.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,
                               HttpServletResponse response) {

        Users user = userRepository.findByUsername(username).orElse(null);

        if (user != null && user.getPassword().equals(password)) {

            String token = jwtUtil.generateToken(username);

            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);

            return "redirect:/dashboard";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "redirect:/login";
    }
}