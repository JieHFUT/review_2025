package com.jiehfut.assmioc.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 返回 false 代表条件不匹配，返回 true 代表提交匹配
        // 判断环境变量中的系统
        Environment environment = context.getEnvironment();
        String os = environment.getProperty("OS");
        if (os != null && os.contains("Windows")) {
            return true;
        }
        return false;
    }
}
