package com.jiehfut.essmwebmvc.controller;


import com.jiehfut.essmwebmvc.pojo.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class RequestTest {


    /**
     * 要求变量名和参数名称保持一致
     * 包装类型不携带的封装为 null，基本类型不携带数据的为默认值
     * @param username
     * @param password
     * @param cellphone
     * @param agreement
     * @return
     */
    @RequestMapping("/handle01")
    public String handle01(String username,
                           String password,
                           String cellphone,
                           boolean agreement) {
        return "ok";
    }

    /**
     * 如果后端接收参数名称和前端传递参数不一致，可以使用注解声明
     * 但是一但某个参数使用注解，请求中这个参数就必需要携带
     * @RequestParam 可以获取请求体中和 url 后面所有的请求参数
     * @param name
     * @param pwd
     * @param phone
     * @param isAgree
     * @return
     */
    @RequestMapping("/handle02")
    public String handle02(@RequestParam("username") String name,
                           @RequestParam("password")String pwd,
                           @RequestParam("cellphone")String phone,
                           @RequestParam("agreement")boolean isAgree) {
        return "ok";
    }


    /**
     * 如果后端接收参数名称和前端传递参数不一致，可以使用注解声明
     * 但是一但某个参数使用注解，请求中这个参数就必需要携带
     * 如果想让这个参数在请求中可以不携带，可以使用属性进行声明（required = false 声明其为非必需携带）
     * @param name
     * @param pwd
     * @param phone
     * @param isAgree
     * @return
     */
    @RequestMapping("/nucessery")
    public String nucessery(@RequestParam(value = "username", required = false) String name,
                           @RequestParam(value = "password", required = false)String pwd,
                           @RequestParam("cellphone")String phone,
                           @RequestParam("agreement")boolean isAgree) {
        return "ok";
    }

    /**
     * 如果后端接收参数名称和前端传递参数不一致，可以使用注解声明
     * 但是一但某个参数使用注解，请求中这个参数就必需要携带
     * 如果想让这个参数在请求中可以不携带，可以使用属性进行声明（required = false 声明其为非必需携带）
     *
     * 如果声明了默认值 defaultValue，就不用写 required 属性，声明默认值，请求中可带可不带该参数
     * @param name
     * @param pwd
     * @param phone
     * @param isAgree
     * @return
     */
    @RequestMapping("/default")
    public String defaultValue(@RequestParam(value = "username", required = false) String name,
                               @RequestParam(value = "password", required = false)String pwd,
                               @RequestParam(value = "cellphone", defaultValue = "18888888888")String phone,
                               @RequestParam("agreement")boolean isAgree) {
        return "ok";
    }


    /**
     * 1.请求中的参数就会自动注入该对象的属性中，名称不匹配的属性为 null
     * 2.如果请求中没有携带某个属性，该对象中的该属性也为 null
     * 3.如果想让这个对象拥有默认值，可以直接在创建类的时候赋值
     * 原理是利用反射给对象赋值：request.getParameter("username")...
     * @param person
     * @return
     */
    @RequestMapping(value = "/handle03")
    public String handle03(Person person) {
        return "ok";
    }


    /**
     * 获取请求头，不区分大小写
     * @param person
     * @param host
     * @return
     */
    @RequestMapping("/handle04")
    public String handle04(Person person,
                           @RequestHeader(value = "host", required = false) String host,
                           @RequestHeader(value = "user-agent", defaultValue = "") String userAgent) {
        return "ok";
    }


    /**
     * 获取 cookie
     * @param keya
     * @return
     */
    @RequestMapping("/handle05")
    public String handle5(@CookieValue("keya") String keya,
                          @CookieValue(value = "keyb", required = false) String keyb) {
        return "ok";
    }




    /**'
     * 级联属性封装赋值
     *      http://localhost:8080/handle06?username=jieHFUT&password=123456&cellphone=18895693047
     *      &address.province=安徽省&address.city=蚌埠市&address.Area=怀远县&sex=男
     *      &hobby=篮球&hobby=足球&grade=二年级&agreement=on
     * @param person
     * @return
     */
    @RequestMapping("/handle06")
    public String handler06(Person person) {
        return "ok";
    }


    /**
     * 请求体数据为 json 格式 {"":"","":"","":""}
     * 接收请求体中的 json（字符串）格式的数据
     * 1.注解 @ResponseBody
     * 2.使用对象接收
     * @param person
     * @return
     */

    @RequestMapping("/handle07")
    public String handler07(@RequestBody Person person){
        System.out.println(person.toString());
        // Person(username=jiehfut, password=959452, cellphone=18895693047, agreement=true,
        // address=Address(province=anhui, city=wuhu, area=xiushuijiangnan), sex=male,
        // hobby=[basketball, football], grade=collage)
        return "ok";
    }

    // 请求中的 json 数据以 json 字符串展示
    @RequestMapping("/string-json")
    public String handler08(@RequestBody String json){
        System.out.println(json);
        return "ok";
        /**
         {
             "username":"jiehfut",
             "password":"959452",
             "cellphone":"18895693047",
             "agreement":true,
             "address":{
                 "province":"anhui",
                 "city":"wuhu",
                 "area":"xiushuijiangnan"
             },
             "sex":"male",
             "hobby":["basketball", "football"],
             "grade":"collage"
         }
         */
    }


    /**
     * 接收文件，使用 spring 封装的用于接收文件的类
     * 接收使用 @RequestParam || @RequestPart
     * 1.@RequestParam("headerImg") 取出对应的文件项（单个就用 MultipartFile，多个就用 MultipartFile[]）
     * 2.注意文件大小限制配置
     *
     * @param person
     * @param file 单个文件 - 头像
     * @param files 多个文件 - 生活照片
     * @return
     */
    @RequestMapping("/handle08")
    public String handle08(Person person,
                           @RequestParam("headerImg") MultipartFile file,
                           @RequestParam("lifeImg") MultipartFile[] files) throws IOException {
        // 1.获取原始文件
        String originalFilename = file.getOriginalFilename();
        // 2.获取文件大小
        long size = file.getSize();
        // 3.获取文件流
        InputStream inputStream = file.getInputStream();
        // 4.进行文件保存
        file.transferTo(new File("E:\\code\\ssm-parent\\e-ssm-webmvc\\src" +
                "\\main\\java\\com\\jiehfut\\essmwebmvc\\controller\\img\\" + originalFilename));


        // 1.遍历多个文件（可以在保存之前判断有无该路径，无路径就创建）
        if (files.length > 0) {
            for (MultipartFile afile : files) {
                afile.transferTo(new File("E:\\code\\ssm-parent\\e-ssm-webmvc\\src" +
                        "\\main\\java\\com\\jiehfut\\essmwebmvc\\controller\\img\\" + afile.getOriginalFilename()));
            }
        }
        /**
         * 注意 spring 限制了单个文件上传的大小为 1 MB
         * 可以在配置文件中设置有关信息
         * application.properties - spring.servlet.multipart.max-file-size=100MB
         *
         * 注意 spring 还设置了单次请求设置的文件限制大小为 10MB
         * 可以在配置文件中设置有关信息
         * application.properties - spring.servlet.multipart.max-request-size=1GB
         *
         */
        System.out.println("person = " + person);
        return "ok";
    }


    /**
     * 获取某一次请求的全部信息
     * 1.使用 HttpEntity<String> 去接收，代表找整个请求（请求行，请求头，请求体）
     * 2.泛形里面的就是请求体里面的类型（例如请求体中是 JSON 字符串，泛型里面是 String 就如下
     *                                                    泛型里面是 Person 就会自动将 json 字符串转换为 Person 对象）
     *
     * @param requestEntity
     * @return
     */
    @RequestMapping("/handle09")
    public String handle09(HttpEntity<String> requestEntity) {
        // 1.获取请求头
        HttpHeaders httpHeaders = requestEntity.getHeaders();
        // 2.获取请求体
        String body = requestEntity.getBody();

        System.out.println("httpHeaders = " + httpHeaders + "\nhttpBody = " + body);
        return "ok";

        /**
         *
         * http://localhost:8080/handle09?paramsa=params-valuea

         httpHeaders = [headera:"headera-valuea", user-agent:"PostmanRuntime/7.29.0", accept:"...", postman-token:"5e5e498a-b9e6-4d07-ac1b-cb829481289d", host:"localhost:8080", accept-encoding:"gzip, deflate, br", connection:"keep-alive", content-length:"307", Content-Type:"application/json;charset=UTF-8"]
         httpBody = {
             "username":"jiehfut",
             "password":"959452",
             "cellphone":"18895693047",
             "agreement":true,
             "address":{
             "province":"anhui",
                 "city":"wuhu",
                 "area":"xiushuijiangnan"
             },
             "sex":"male",
             "hobby":["basketball", "football"],
             "grade":"collage"
         }
         */
    }
















}
