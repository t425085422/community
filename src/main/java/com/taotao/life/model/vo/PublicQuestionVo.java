package com.taotao.life.model.vo;

import com.github.pagehelper.PageInfo;
import com.taotao.life.model.PublicQuestion;
import com.taotao.life.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PublicQuestionVo extends PublicQuestion  {

    private User user;

}
