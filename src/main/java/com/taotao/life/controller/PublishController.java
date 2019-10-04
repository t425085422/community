package com.taotao.life.controller;

import com.taotao.life.model.PublicQuestion;
import com.taotao.life.model.User;
import com.taotao.life.service.PublicService;
import com.taotao.life.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired(required = false)
    private PublicService publicService;
    @Autowired(required = false)
    private HelloController helloController;
    @Autowired(required = false)
    private MapUtils mapUtils;

    @RequestMapping("/publish")
    public String publish(){
        return "Publish";
    }


    @RequestMapping("/publish1")
    public String doPublish(@RequestParam(name="title") String title,
                            @RequestParam(name="description") String description,
                            @RequestParam(name="tag") String tag,
                            HttpServletRequest request ,Model model){
        User user = helloController.UserCookie(request);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        String notEmptyBatch = mapUtils.isNotEmptyBatch(title, description, tag);
        if(notEmptyBatch != null && notEmptyBatch !=""){
            model.addAttribute("error",notEmptyBatch);
            return "publish";
        }
        if (user != null){
            PublicQuestion publicQuestion = new PublicQuestion();
            publicQuestion.setTitle(title);
            publicQuestion.setDescription(description);
            publicQuestion.setTag(tag);
            publicQuestion.setCreateTime(new Date());
            publicQuestion.setCreator(user.getId());
            publicService.insert(publicQuestion);
            return "redirect:/";
        } else {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
    }
}
