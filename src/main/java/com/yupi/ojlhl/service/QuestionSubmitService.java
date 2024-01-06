package com.yupi.ojlhl.service;


import com.yupi.ojlhl.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.ojlhl.model.entity.User;

/**
* @author lhl
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-01-05 19:13:10
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    long doQuestionSubmit(com.yupi.ojlhl.model.dto.questionsubmit.QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
}
