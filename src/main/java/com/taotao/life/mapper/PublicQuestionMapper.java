package com.taotao.life.mapper;

import com.taotao.life.model.PublicQuestion;
import com.taotao.life.model.PublicQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PublicQuestionMapper {
    int countByExample(PublicQuestionExample example);

    int deleteByExample(PublicQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PublicQuestion record);

    int insertSelective(PublicQuestion record);

    List<PublicQuestion> selectByExampleWithBLOBs(PublicQuestionExample example);

    List<PublicQuestion> selectByExample(PublicQuestionExample example);

    PublicQuestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PublicQuestion record, @Param("example") PublicQuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") PublicQuestion record, @Param("example") PublicQuestionExample example);

    int updateByExample(@Param("record") PublicQuestion record, @Param("example") PublicQuestionExample example);

    int updateByPrimaryKeySelective(PublicQuestion record);

    int updateByPrimaryKeyWithBLOBs(PublicQuestion record);

    int updateByPrimaryKey(PublicQuestion record);

    List<PublicQuestion> selectList();

    List<PublicQuestion> selectListById(Integer id);
}