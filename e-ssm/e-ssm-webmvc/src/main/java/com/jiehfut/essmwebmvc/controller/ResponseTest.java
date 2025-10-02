package com.jiehfut.essmwebmvc.controller;

import com.jiehfut.essmwebmvc.pojo.Address;
import com.jiehfut.essmwebmvc.pojo.Person;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class ResponseTest {

    /**
     * 使用 @ResponseBody 注解
     * 直接把响应的对象转换为 json 字符串给前端
     * @return
     */
    @RequestMapping("/resp01")
    public Person resp01() {
        return new Person("zhangsan", "123456", "18895693047", true,
                new Address("anhui", "bengbu", "huaiyuan"),
                "male", new String[]{"basketball", "football"}, "grade-one");
    }


    /**
     * 文件下载;
     *     1. HttpEntity：拿到整个请求（请求头，请求体）
     *     2. ResponseEntity：拿到整个响应（响应头、响应体、响应状态码）
     * @return
     */
    @RequestMapping("/download")
    // public ResponseEntity<byte[]> download() throws IOException {
    public ResponseEntity<InputStreamResource> download() throws IOException {
        /**
         * ResponseEntity.ok() || ResponseEntity.ok(T body)
         * 响应成功的时候，如果什么都不放 => 200
         *           T - 任意类型数据 => 如果放置一个对象，这个对象将会抓换为 json 字符串
         *                             如果放置一个字节流 byte[], 前端下载文件
         * 如果直接给前端返回一个字节流还不行，需要在响应中指明响应的类型，让前端按照这个类型去解析字节流，让他下载
         */
        FileInputStream inputStream = new FileInputStream("E:\\code\\ssm-parent\\e-ssm-webmvc\\src\\main\\java\\com\\jiehfut\\essmwebmvc\\controller\\img\\OIP-C.jpg");

        byte[] bytes = inputStream.readAllBytes(); // 一口气读取文件容易内存溢出，可以包装一下
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        /**
         * 存在的问题
         * 1.文件名中文会乱码
         *   进行编码后传入
         * 2.文件太大会 oom（内存溢出）
         *   不要一口气将所有字节流读出去
         *   不要直接返回 byte[]，返回一个 InputStreamResource 类型
         *   然后再将文件包装为 InputStreamResource 类型
         */
        String filename = URLEncoder.encode("头像.jpg", "UTF-8");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // 八位字节流
                // .contentLength(bytes.length)
                .contentLength(inputStream.available())
                // 声明字节流的内容处理方式（附件）- 触发下载操作
                .header("Content-Disposition", "attachment; filename=" + filename)
                .body(inputStreamResource);
    }



}
