package com.jiehfut.domain.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RequestDomainController {


    /**
     * 通过 ServletRequest 的原生 API 来测试请求域参数
     * @param request 请求中的 ruquest 对象
     * @return
     */
    @RequestMapping("/testRequestByServletAPI")
    public String testRequestByServletAPI(HttpServletRequest request) {
        request.setAttribute("requestDomainKeya", "requestDomainValuea");
        return "success";
    }


    /**
     * 设置返回值为 ModelAndView 对象给前端控制器，实现请求域的数据共享
     * 在控制器方法中创建 ModelAndView 对象，在控制器方法中返回该对象实现数据共享
     * view="success" model={key=value, ...}
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        // 处理模型数据，即为向请求域中 request 中共享数据
        // 向请求域中放数据，等同于 setArrtibude
        modelAndView.addObject("requestDomainKeyb", "requestDomainValueb");
        // 设置视图名称，等同于 return "success"
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /**
     * 设置 Model 对象，实现请求域的数据共享
     * Model 对象实际上是一个{key1=value1,ley2=value2... , Model}
     * @param model
     * @return
     */
    @RequestMapping("/testModel")
    public String testModel(Model model) {
        model.addAttribute("requestDomainKeyc", "requestDomainValuec");
        return "success";
    }


    /**
     * 设置 Map 对象，实现请求域的数据共享
     * Map 对象实际上是一个{key1=value1,ley2=value2... , Map}
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        map.put("requestDomainKeyd", "requestDomainValued");
        return "success";
    }



    /**
     * 设置 ModelMap 对象，实现请求域的数据共享
     * 在控制器方法中创建形参，形参具有向请求域中共享数据的功能
     * ModelMap 对象实际上是一个{key1=value1,ley2=value2... , ModelMap}
     * @return
     */
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("requestDomainKeye", "requestDomainValuee");
        return "success";
    }

    /**
     * 以上五种方式都可以在请求域中实现数据共享
     * Model && Map 是一个接口，ModelMap 是一个类
     * 实际上这三个类在实现（实例化）的时候使用的是同一个（实现类或者继承类：BindingAwareModelMap 类）
     *
     * Model 是一个顶层接口
     *
     * BindingAwareModelMap 继承了 ExtendedModelMap，
     * ExtendedModelMap 继承了 ModelMap 并且实现了 Model 接口
     *
     * ModelMap 继承了 LinkedHashMap
     *
     * 总结就是：Model 接口 && Map 接口都间接的被 BindingAwareModelMap 实现了
     */




}
