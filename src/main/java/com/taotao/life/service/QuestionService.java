package com.taotao.life.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.life.mapper.PublicQuestionMapper;
import com.taotao.life.mapper.UserMapper;
import com.taotao.life.model.PublicQuestion;
import com.taotao.life.model.User;
import com.taotao.life.model.vo.PublicQuestionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    private PublicQuestionMapper publicQuestionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    public PageInfo<PublicQuestionVo> list(Integer pageNum) {
        Page page=  PageHelper.startPage(pageNum, 5);
        List<PublicQuestion> question = publicQuestionMapper.selectList();
        ArrayList<PublicQuestionVo> list = new ArrayList<PublicQuestionVo>();
        for(PublicQuestion publicQuestion : question){
            User user = userMapper.selectByPrimaryKey(publicQuestion.getCreator());
            PublicQuestionVo publicQuestionVo = new PublicQuestionVo();
            publicQuestionVo.setUser(user);
            BeanUtils.copyProperties(publicQuestion,publicQuestionVo);
            list.add(publicQuestionVo);
        }
        PageInfo<PublicQuestionVo > list1 = new PageInfo<>(page.getResult());
        return list1;
    }

    public PageInfo<PublicQuestionVo> selectListById(Integer id, Integer pageNum) {
        Page page=  PageHelper.startPage(pageNum, 5);
        List<PublicQuestion> question = publicQuestionMapper.selectListById(id);
        PageInfo<PublicQuestionVo > list = new PageInfo<>(page.getResult());
        return list;
    }
}
