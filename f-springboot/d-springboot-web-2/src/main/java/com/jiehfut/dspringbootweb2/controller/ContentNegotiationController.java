package com.jiehfut.dspringbootweb2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.jiehfut.dspringbootweb2.bean.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容协商测试
 * 一套系统适配多端数据返回
 */

@RestController
public class ContentNegotiationController {

    /**
     * 1.spring 默认支持把对象写成 JSON 对象发送给前端，spring 默认导入了 jackson 依赖
     * 2.jackson 也支持将数据写成 xml，但是需要导入依赖 jackson-dataformat-xml
     *   在一个 bean 上添加注解 @JacksonXmlRootElement，就可以将对象以 xml 格式发送
     * 这样就可以设置请求头中的 Accept: 属性
     * Accept: application/json  =>  得到 json 相应
     * Accept: application/xml   =>  得到 xml 相应
     *
     * 基于请求头进行内容协商是默认开启的，如果想要根据请求参数进行内容协商需要自己开启
     *
     * @return
     */

    @GetMapping("/person")
    public Person person() {
        Person person = new Person();
        person.setId(1);
        person.setUserName("zhangsan");
        person.setAge(18);
        person.setEmail("489563812@qq.com");

        return person;
    }


    /**
     * 内容协商的原理
     * HttpMessageConverter 怎么工作完成内容协商
     *
     * 编写下面的底层功能即可，修改 configureMessageConverters
     * public interface WebMvcConfigurer {
     *     default void configureMessageConverters(List<HttpMessageConverter<?>> converters) {}
     * }
     * controller
     *   => @RestController
     *     => @ReseponseBody
     *     请求先到 DispatherServlet类中的 doDispather()方法
     *
     *
     * 标注了@ResponseBody的返回值 将会由支持它的 HttpMessageConverter写给浏览器
     * 1. 如果controller方法的返回值标注了 @ResponseBody 注解
     *   1.1. 请求进来先来到DispatcherServlet的doDispatch()进行处理
     *   1.2. 找到一个 HandlerAdapter 适配器。利用适配器执行目标方法
     *   1.3. RequestMappingHandlerAdapter来执行，调用invokeHandlerMethod（）来执行目标方法
     *   1.4. 目标方法执行之前，准备好两个东西
     *     1.4.1. HandlerMethodArgumentResolver：参数解析器，确定目标方法每个参数值
     *     1.4.2. HandlerMethodReturnValueHandler：返回值处理器，确定目标方法的返回值改怎么处理
     *   1.5. RequestMappingHandlerAdapter 里面的invokeAndHandle()真正执行目标方法
     *   1.6. 目标方法执行完成，会返回返回值对象
     *   1.7. 找到一个合适的返回值处理器 HandlerMethodReturnValueHandler
     *   1.8. 最终找到 RequestResponseBodyMethodProcessor能处理 标注了 @ResponseBody注解的方法
     *   1.9. RequestResponseBodyMethodProcessor 调用writeWithMessageConverters ,利用MessageConverter把返回值写出去*
     */


    /**
     * 支持 yaml 的格式返回
     * 引入相关依赖
     */
    @GetMapping("/personyaml")
    public void personYaml() throws JsonProcessingException {
        Person person = new Person();
        person.setId(1);
        person.setUserName("zhangsan");
        person.setAge(18);
        person.setEmail("489563812@qq.com");

        // 禁用开始标志 reponse-yaml = ---
        YAMLFactory factory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(factory);
        String response = mapper.writeValueAsString(person);
        System.out.println("reponse-yaml = " + response);
        // id: 1
        // userName: "zhangsan"
        // email: "489563812@qq.com"
        // age: 18

    }



}
