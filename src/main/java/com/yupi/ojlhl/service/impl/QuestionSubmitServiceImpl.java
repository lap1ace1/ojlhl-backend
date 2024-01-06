package com.yupi.ojlhl.service.impl;
import com.yupi.ojlhl.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.ojlhl.common.ErrorCode;
import com.yupi.ojlhl.exception.BusinessException;
import com.yupi.ojlhl.mapper.QuestionSubmitMapper;
import com.yupi.ojlhl.model.entity.Question;
import com.yupi.ojlhl.model.entity.QuestionSubmit;
import com.yupi.ojlhl.model.entity.User;
import com.yupi.ojlhl.model.enums.QuestionSubmitLanguageEnum;
import com.yupi.ojlhl.model.enums.QuestionSubmitStatusEnum;
import com.yupi.ojlhl.service.QuestionService;
import com.yupi.ojlhl.service.QuestionSubmitService;
import javax.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author lhl
* @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
* @createDate 2024-01-05 19:13:10
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(com.yupi.ojlhl.model.dto.questionsubmit.QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言错误");
        }
        long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交
        long userId = loginUser.getId();
        // 每个用户串行提交
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(questionSubmit.getLanguage());
        //设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }
        return questionSubmit.getId();
    }

}