package com.jiehfut.hssmwebmvcpractice.controller;


import com.jiehfut.hssmwebmvcpractice.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class ExceptionController {


    @GetMapping("/test-code-exception")
    public R testCodingException() {
        // 编程式异常处理 try - catch
        try {
            int i = 1 / 0;
            // ......

            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }






    @GetMapping("/test-declare-exception")
    public R testDeclareException(@RequestParam(value = "i", defaultValue = "0") Integer i) throws FileNotFoundException {
        // 声明式异常处理 - 异常被捕捉后被统一处理
        int j = 1 / i;
        File file = new File("D:/12312/231412/432/45/325/4/5");
        InputStream stream = new FileInputStream("D:/12312/231412/432/45/325/4/5");
        // ......
        return R.ok();
    }


    /**
     * 处理该 ExceptionController 类中数学异常
     * 如果本类出现异常，就会自动在本类中寻找有没有 @ExceptionHandler 注解的能够接收本异常的方法
     * 找到的话就把异常交给这个方法处理
     * 1.精确异常优先
     * 2.类内处理异常优先
     * @param e
     * @return 出现异常的结果
     */
    @ExceptionHandler(ArithmeticException.class)
    public R arithmeticException(ArithmeticException e) {
        return R.error("捕捉 ExceptionController 类中 ArithmeticException 异常：" + e.getMessage());
    }
    @ExceptionHandler(FileNotFoundException.class)
    public R arithmeticException(FileNotFoundException e) {
        return R.error("捕捉 ExceptionController 类中 FileNotFoundException 异常：" + e.getMessage());
    }
    @ExceptionHandler(IOException.class)
    public R arithmeticException(IOException e) {
        return R.error("捕捉 ExceptionController 类中 IOException 异常：" + e.getMessage());
    }







}
