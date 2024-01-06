package com.yupi.ojlhl.model.dto.question;

import lombok.Data;

/*题目配置*/
@Data
public class JudgeConfig {

    /*时间限制ms*/
    private Long timeLimit;
    /*空间限制kb*/
    private Long memoryLimit;
    /*堆栈先知kb*/
    private Long stackLimit;
}
