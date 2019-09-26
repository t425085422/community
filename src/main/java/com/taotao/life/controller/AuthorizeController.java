package com.taotao.life.controller;

import com.taotao.life.dto.AccessTokenDto;
import com.taotao.life.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taotao.life.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;


@Controller
@PropertySource("classpath:application.properties")
public class AuthorizeController {

   @Autowired(required = false)
    private GithubProvider githubProvider;

   @Value("${github.client.id}")
   private String clientId;
   @Value("${github.client.secret}")
   private String clientSecret;
   @Value("${github.redirect.uri}")
   private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name = "state") String state, HttpServletRequest req){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClientId(clientId);
        accessTokenDto.setClientSecret(clientSecret);
        accessTokenDto.setRedirectUri(redirectUri);
            accessTokenDto.setState(state);
        String accessToken = githubProvider.GithubProvider(accessTokenDto);
        GithubUser usger = githubProvider.getUsger(accessToken);
        if (usger!=null) {
            req.getSession().setAttribute("usger",usger);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
