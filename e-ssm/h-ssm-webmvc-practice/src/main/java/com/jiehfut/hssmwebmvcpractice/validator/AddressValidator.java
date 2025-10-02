package com.jiehfut.hssmwebmvcpractice.validator;

import com.jiehfut.hssmwebmvcpractice.annotation.Address;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 校验器：支持地址校验注解的校验规则设置
 * 1.实现 ConstraintValidator 接口，在泛型中声明校验的接口和属性类型
 * 2.
 * 3.
 */
public class AddressValidator implements ConstraintValidator<Address, String> {

    /**
     * 是否校验通过
     * 1.安徽省芜湖市
     * 2.上海市浦东新区
     * 3.江苏省南京市
     * @param value 请求中传递的需要校验的值
     * @param constraintValidatorContext 校验上下文
     * @return true：校验通过
     *         false：校验不通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value.equals("安徽省芜湖市") || value.equals("上海市浦东新区") || value.equals("江苏省南京市")) {
            // 校验成功
            return true;
        }
        return false;
    }
}
