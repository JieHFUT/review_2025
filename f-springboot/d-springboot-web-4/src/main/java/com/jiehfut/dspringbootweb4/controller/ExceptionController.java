package com.jiehfut.dspringbootweb4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController {
    /**
     * springboot 的异常的处理机制
     * ErrorMvcAutoConfiguration
     * 这个自动配置类中在容器中注入了很多组件
     *
     * 1.springboot 会自适应处理错误，如果是浏览器端会响应页面，如果是移动端会相应 json 数据
     * 2.SpringMVC 的错误处理机制依然保留，MVC处理不了，才会交给 boot进行处理
     *
     * 3.如果 springmvc 处理不了的异常会转交给 springboot 的 "/error"
     * 4.ErrorMvcAutoConfiguration 中有一个控制器组件
     *   @Controller
     *   @RequestMapping("${server.error.path:${error.path:/error}}") // 可以配置错误路径
     *   public class BasicErrorController extends AbstractErrorController {...}
     * 5.BasicErrorController 有两个内容协商机制
     *   页面：
     *   (1) 优先模板引擎精确码，无模板引擎精确码 => 静态资源文件夹下找精确码
     *   (2) 匹配不到精确码 => 优先模板引擎下的模糊码，没有模板引擎，匹配静态资源文件夹下的模糊码
     *   (3) 如果都没有精确码 && 模糊码 => "/error" 优先 error.html
     *   (4) 如果你自己没有写 error.html 视图，springboot 中有一个默认的 error 视图（ 默认的白页功能 ）
     *
     *   JSON:
     *   Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
     *   return new ResponseEntity<>(body, status);
     */


    /**
     * 1. @ExceptionHandler(<? extends Throwable>[]) 注解来声明这就是处理这个控制类中的错误的
     * 这个控制类中的异常信息会自动被 springmvc 传递给参数 Exception e
     * ===== 但是默认只能处理这个类里面的错误，优先于统一错误处理 =====
     *
     * 2. 可以专门使用 @ControllerAdvice 统一处理所有的错误
     * @return
     */
    @ResponseBody
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String handlerException(Exception e) {

        return "exception，原因：" + e.getMessage();
    }


    @RequestMapping("/testexception")
    public String testException(Model model) {
        model.addAttribute("model", new String("model"));
        int num = 10 / 0;
        return "testexception";
    }





}
