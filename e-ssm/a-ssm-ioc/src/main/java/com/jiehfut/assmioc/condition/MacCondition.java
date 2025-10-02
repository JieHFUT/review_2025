package com.jiehfut.assmioc.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 返回 false 代表条件不匹配，返回 true 代表提交匹配
        return  context.getEnvironment().getProperty("OS").contains("Mac");

    }
}
