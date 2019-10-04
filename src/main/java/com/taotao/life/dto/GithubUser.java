package com.taotao.life.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUser {

    private String name;
    private Long id;
    private String bio;

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
