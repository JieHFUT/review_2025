package com.jiehfut.dspringbootweb2.component;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class MyYamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper mapper;

    public MyYamlHttpMessageConverter() {

        // 向父类中传递支持什么类型（application.properties）告诉springboot这个messagehhxonverter支持哪种媒体类型
        super(new MediaType("application", "yaml", Charset.forName("UTF-8")));

        YAMLFactory factory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        mapper = new ObjectMapper(factory);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 只要是对象类型，不是基本类型，都支持
        if (clazz.isPrimitive()) {
            return false;
        }
        return true;
    }

    @Override // @RequestBody
    // 将请求带来的对象数据转换为 Yaml 对象
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }


    /**
     * 将对象写成 Yaml 对象从响应写出去
     * outputMessage: 响应的输出流
     */
    @Override // @ResponseBody
    protected void writeInternal(Object methodReturnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // OutputStream body = outputMessage.getBody();

        // try-with 写法：自动关流
        try(OutputStream outputStream = outputMessage.getBody()) {
            mapper.writeValue(outputStream, methodReturnValue);
        }

    }
}
