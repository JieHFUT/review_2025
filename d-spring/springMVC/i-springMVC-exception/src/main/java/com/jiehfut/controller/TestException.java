package com.jiehfut.controller;

import com.jiehfut.exception.SelfException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: TestException
 * Package: com.jiehfut.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/8 18:18
 * @Version 1.0
 */

@Controller
public class TestException {
    // 测试异常
    @RequestMapping("/testException")
    public String testException() {
        // 根据配置，如果出现数学异常就会跳转到设置的 error 视图
        if (true) {
            throw new SelfException("self exception");
        }
        return "success";
    }

}
