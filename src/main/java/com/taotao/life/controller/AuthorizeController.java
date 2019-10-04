package com.taotao.life.controller;

import com.taotao.life.dto.AccessTokenDto;
import com.taotao.life.dto.GithubUser;
import com.taotao.life.model.User;
import com.taotao.life.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taotao.life.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Controller
@PropertySource("classpath:application.properties")
public class AuthorizeController {

   @Autowired(required = false)
    private GithubProvider githubProvider;

   @Autowired(required = false)
   private UserService userService;

   @Value("${github.client.id}")
   private String clientId;
   @Value("${github.client.secret}")
   private String clientSecret;
   @Value("${github.redirect.uri}")
   private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name = "state") String state, HttpServletRequest req , HttpServletResponse resp){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClientId(clientId);
        accessTokenDto.setClientSecret(clientSecret);
        accessTokenDto.setRedirectUri(redirectUri);
            accessTokenDto.setState(state);
        String accessToken = githubProvider.GithubProvider(accessTokenDto);
        GithubUser usger = githubProvider.getUsger(accessToken);
        if (usger!=null) {
            User u = userService.selectUserByAccountId(String.valueOf(usger.getId()));
            if (u == null){
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                User user1 = new User();
                user1.setName(usger.getName());
                String token = UUID.randomUUID().toString();
                user1.setToken(token);
                user1.setCreateTime(date);
                user1.setAccountId(String.valueOf(usger.getId()));
                userService.insertUser(user1);
                resp.addCookie(new Cookie("token", token));
            } else {
                resp.addCookie(new Cookie("token", u.getToken()));
            }
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
