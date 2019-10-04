package com.taotao.life.controller;

import com.taotao.life.model.User;
import com.taotao.life.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private User user;


    public User UserCookie(HttpServletRequest request) {
        User user = new User();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie1 : cookies) {
                if ("token".equals(cookie1.getName())) {
                    String token = cookie1.getValue();
                    user = userService.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        return user;
    }
}