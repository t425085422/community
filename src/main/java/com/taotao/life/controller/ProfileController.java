package com.taotao.life.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.life.model.User;
import com.taotao.life.model.vo.PublicQuestionVo;
import com.taotao.life.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired(required = false)
    private QuestionService questionService;
    @Autowired(required = false)
    private HelloController helloController;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        User user = helloController.UserCookie(request);
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PageInfo<PublicQuestionVo> questionList = questionService.selectListById(user.getId(), pageNum);
        model.addAttribute("pagination", questionList);
        return "profile";
    }
}
