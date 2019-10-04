package com.taotao.life.service;

import com.taotao.life.mapper.PublicQuestionMapper;
import com.taotao.life.model.PublicQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicService {

    @Autowired(required = false)
    private PublicQuestionMapper publicQuestionMapper;

    public void insert(PublicQuestion publicQuestion) {
        publicQuestionMapper.insert(publicQuestion);
    }
}
