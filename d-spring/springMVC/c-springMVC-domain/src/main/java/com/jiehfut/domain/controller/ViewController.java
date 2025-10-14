package com.jiehfut.domain.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    /**
     视图名称没有任何前缀，当前视图为 thymeleaf view，会被配置的 ThymeleafViewResolver 解析
     被视图解析器解析，所以返回一个单视图名称 => 找到视图文件

     ============ 在 DisPatcherServlet 类中 =================

     protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        该解析器会在该方法中调用控制器中的方法，返回一个 ModelAndView 对象
        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
        拦截器中的方法
        mappedHandler.applyPostHandle(processedRequest, response, mv);
        处理我们封装的模型数据 ModelAndView 对象和视图信息的方法（执行转发结果）
        this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
     }


     private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,...
         if (mv != null && !mv.wasCleared()) {
            如果 ModelAndView 不为空 => 处理，执行，渲染...  处理我们最终的结果，处理我们封装的 ModelAndView 对象，并且请求对象和响应对象
            this.render(mv, request, response);...


     protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
        response.setLocale(locale); // 进行本地化
        String viewName = mv.getViewName(); // 获得 ModelAndView 名称：success
        if (viewName != null) { // 如果视图名称不为空，根据视图名称，返回的 Model 对象，本地化，请求对象，即可通过解析视图名称，获得视图对象
            view = this.resolveViewName(viewName, mv.getModelInternal(), locale, request);
     }
     *
     * @return
     */
    @RequestMapping("/testThymeleafViewa")
    public String testThymeleafViewa(){
        // 直接返回视图名称，没有任何前缀，是 ThymeleafView 视图，直接转发到该视图
        return "success";
    }


    /**
     * 在 DisPatcherServlet 类中

     protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
         该解析器会在该方法中调用控制器中的方法，返回一个 ModelAndView 对象
         mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
         拦截器中的方法
         mappedHandler.applyPostHandle(processedRequest, response, mv);
         处理我们封装的模型数据 ModelAndView 对象和视图信息的方法（执行转发结果）
         this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
     }


     // 这个是上面的最后一个封装的方法
     private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,...
        if (mv != null && !mv.wasCleared()) {
             如果 ModelAndView 不为空 => 处理，执行，渲染...  处理我们最终的结果，处理我们封装的 ModelAndView 对象，并且请求对象和响应对象
             this.render(mv, request, response);...



     protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
         response.setLocale(locale); // 进行本地化
         String viewName = mv.getViewName(); // 获得 ModelAndView 名称："forward:/testThymeleafViewa"
         if (viewName != null) { // 如果视图名称不为空，根据视图名称，返回的 Model 对象，本地化，请求对象，
            即可通过解析视图名称，获得视图对象（根据视图名称有没有前缀，有哪种前缀，就创建哪一种视图对象）
            view = this.resolveViewName(viewName, mv.getModelInternal(), locale, request); => internalResourceView
                                                  这个就是 mv 中封装的 model 对象
     }



     // 对于 "/testThymeleafViewa" 再执行一遍上述流程到 success 视图，执行一遍得到 ThymeleafView

     第一次是进行转发，转发到某一个请求中，第二次该请求再去创建视图

     * @return
     */
    @RequestMapping("/testForWord")
    public String testForWord(){
        // 转发
        // 当控制器方法中所设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，
        // 此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，而是会将前缀"forward:"去掉，剩余部分作为最终路径通过转发的方式实现跳转

        // 直接转发到 /testThymeleafViewa 请求中，创建两次视图：转发视图（internalResourceView）&& （ThymeleafView）
        return "forward:/testThymeleafViewa";
    }





    @RequestMapping("/testRedirect")
    public String testRedirect(){
        // 重定向
        // 当控制器方法中所设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，
        // 而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转

        // 创建两次视图：重定向视图（RedirectView）&&（ThymeleafView）
        return "redirect:/testThymeleafViewa";
    }



    /**
     * 视图控制器
     * 在 springMVC 中进行控制配置
     *
     */








}
