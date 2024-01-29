package com.yupi.ojlhl.judge.codesandbox.impl;

import com.yupi.ojlhl.judge.codesandbox.CodeSandbox;
import com.yupi.ojlhl.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.ojlhl.judge.codesandbox.model.ExecuteCodeResponse;

//远程代码沙箱
public class RemoteCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
