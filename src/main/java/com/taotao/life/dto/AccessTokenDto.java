package com.taotao.life.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccessTokenDto {

    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String state;

}
