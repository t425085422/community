package com.taotao.life.taotao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUser {
    public String ge;
    private String name;
    private Long id;
    private String bio;

    @Override
    public String toString() {
        return "GithubUser{" +
                "ge='" + ge + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
