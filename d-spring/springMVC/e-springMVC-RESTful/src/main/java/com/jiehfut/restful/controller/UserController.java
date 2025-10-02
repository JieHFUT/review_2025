package com.jiehfut.restful.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Controller
public class UserController {

    /**
     * 使用 restful 来模拟用户资源的增删改查
     *
     * "/user"    GET    查询所有用户信息
     * "/user/1"  GET    根据用户ID查询用户信息
     * "/user"    POST   添加用户信息
     * "/user/1"  DELETE  删除用户信息
     * "/user"    PUT    修改用户信息
     *
     */


    // @GetMapping("/user")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUser() {
        System.out.println("查询所有用户信息.");
        return "success";
    }



    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById() {
        System.out.println("根据用户ID查询用户信息");
        return "success";
    }



    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String insertUser(String username, String password) {
        System.out.println("添加用户信息：username = " + username + "，password = " + password);
        return "success";
    }


    /**
     * 使用 put 请求 && delete 请求需要使用过滤器：HiddenHttpMethodFilter（web.xml）
     * 需要满足
     * 1.请求方式为 POST
     * 2.携带请求参数："_method"
     * 最终过滤后的请求方式就是 “_method” 的值转换成为大写的结果，没有该参数就不执行请求方式转换
     public class HiddenHttpMethodFilter extends OncePerRequestFilter {

         protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
             HttpServletRequest requestToUse = request; // 获取请求对象
                                       获取请求方式为 POST 往下执行                                 恒成立
             if ("POST".equals(request.getMethod()) && request.getAttribute("jakarta.servlet.error.exception") == null) {
                String paramValue = request.getParameter(this.methodParam); // 获取参数 => 常量："_method" => value
                 if (StringUtils.hasLength(paramValue)) {  // 若不为空
                    String method = paramValue.toUpperCase(Locale.ENGLISH); // 大写转换（PUT DELETE PATCH）
                    if (ALLOWED_METHODS.contains(method)) { // ALLOWED_METHODS 是一个 List<String>：PUT DELETE PATCH
                      requestToUse = new HttpMethodRequestWrapper(request, method); // 创建了一个新的请求对象放行，method 就是大写的 PUT DELETE PATCH
                    }
                 }
             }
        filterChain.doFilter((ServletRequest)requestToUse, response);
        }
     }

     总的来说就是将 POST 请求方式替换成为 "_method" 对应的 value 值并且大写，让俺后重新创建请求对象并且放行

     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(String username, String password) {
        System.out.println("修改用户信息：username = " + username + "，password = " + password);
        return "success";
    }




}
