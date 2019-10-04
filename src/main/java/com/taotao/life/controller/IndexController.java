package com.taotao.life.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.life.model.User;
import com.taotao.life.model.vo.PublicQuestionVo;
import com.taotao.life.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired(required = false)
    private QuestionService questionService;
    @Autowired(required = false)
    private HelloController helloController;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        User user = helloController.UserCookie(request);

        PageInfo <PublicQuestionVo> questionList = questionService.list(pageNum);
        model.addAttribute("pageInfo", questionList);
        return "index";
    }

}
