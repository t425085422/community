package com.taotao.life.taotao.controller;

import com.taotao.life.taotao.dto.AccessTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taotao.life.taotao.provider.GithubProvider;


@Controller
public class AuthorizeController {

   @Autowired(required = false)
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name = "state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClientId("675c188385ae6c8c9e5e");
        accessTokenDto.setClientSecret("359b9764a13d983d68287d2a87653a0a317b7f94");
        accessTokenDto.setRedirectUri("http://localhost:8080/callback");
            accessTokenDto.setState(state);
        githubProvider.GithubProvider(accessTokenDto);
        return  "index";
    }
}
