package com.jiehfut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: TestController
 * Package: com.jiehfut.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/8 15:41
 * @Version 1.0
 */
@Controller
public class TestController {



    @RequestMapping("/testInterceptor")
    public String testInterceptor() {
        return "success";
    }

    /**
     DispacherServlet 类中处理拦截器的过程

     拦截器在控制器方法执行前执行，mappedHandler是处理器，即处理执行链，包含了当前的控制器方法 & 涉及到的拦截器（包括 springMVC 自己创建的一个拦截器）
     mappedHandler 中包括 handler（控制器方法） interceptorList（存放的是拦截器集合），在 applyPreHandle 中按照配置的顺序进行执行
     if (!mappedHandler.applyPreHandle(processedRequest, response)) {
         return;
     }

     如果上面 applyPreHandle返回的是 true，即为放行，向下执行控制器方法，统一返回 ModelAndView
     mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
     if (asyncManager.isConcurrentHandlingStarted()) {
        return;
     }

     拦截器在控制器放行执行后执行，applyPostHandle 就对下标标注的拦截器进行反序执行
     this.applyDefaultViewName(processedRequest, mv);
     mappedHandler.applyPostHandle(processedRequest, response, mv);

     渲染视图完毕后执行
     this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
     */












    /**
     * 对 interceptorList 进行遍历，如果每个拦截器都放行，interceptorIndex 设置最大的拦截器索引，然后返回 true 放行
     * 如果某一个拦截器没有放行，就将 interceptorIndex 设置为上一个拦截器的下标，然后返回 false 禁止
     * 如果中间某个拦截器将请求拦截了，在其之前（包括该拦截的拦截器）的拦截器的 preHandle 可以执行，其他的拦截器的 preHandle 不能执行，所有人的 postHandle不执行，已经放行的拦截器的 afterCompletion 方法执行
     boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
         for(int i = 0; i < this.interceptorList.size(); i++) {
            HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
            if (!interceptor.preHandle(request, response, this.handler)) {
                如果某一个拦截器返回 false 将请求给拦截了，调用的下面 triggerAfterCompletion 去反序执行所有已放行的拦截器的 afterCompletion 方法
                this.triggerAfterCompletion(request, response, (Exception)null);
                return false;
            }
            this.interceptorIndex = i;
        }
        return true;
     }



     对控制器执行前 applyPreHandle 中放行的拦截器反序执行其 applyPostHandle 方法
     void applyPostHandle(HttpServletRequest request, HttpServletResponse response, @Nullable ModelAndView mv) throws Exception {
         for(int i = this.interceptorList.size() - 1; i >= 0; --i) {
            HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
            interceptor.postHandle(request, response, this.handler, mv);
        }
     }




     渲染视图完毕后执行，在 processDispatchResult 中，如果执行链不为空就会去执行
     if (mappedHandler != null) {
        mappedHandler.triggerAfterCompletion(request, response, (Exception)null);
     }
     所有拦截器都通过，在 triggerAfterCompletion 中，也去反序执行
     void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, @Nullable Exception ex) {
         for(int i = this.interceptorIndex; i >= 0; --i) {
             HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
             interceptor.afterCompletion(request, response, this.handler, ex);
         }
     }


     */



}
