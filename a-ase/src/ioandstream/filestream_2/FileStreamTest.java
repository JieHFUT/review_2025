package ioandstream.filestream_2;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class FileStreamTest {
    // 复制一份girl.webp的文件，将其拷贝为girl_new_copy.webp
    @Test
    public void test(){
        File srcFile = new File("girl.webp");
        File destFile = new File("girl_new_copy.webp");

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            System.out.println("图片拷贝完成");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    
    // 实现复制.txt文件，通过字节流
    @Test
    public void test1(){
        File srcFile = new File("hello.txt");
        File destFile = new File("hello_new_copy.txt");

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            byte[] buffer = new byte[5];
            int ret;
            while ((ret = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, ret);
                // 看一下每次取出来的5个字节  byte[] => string
                // System.out.println(Arrays.toString(buffer));
                // [121, 111, 117, 32, 110]
                // [101, 118, 101, 114, 32]
                // [107, 110, 111, 119, 13]
                // [10, -24, -80, -127, -25]
                // [-97, -91, -23, -127, -109]
                // [-27, -111, -94, 33, -109]

                String string = new String(buffer, 0, ret);
                System.out.print(string);
                // you never know
                // 谁���道呢!复制文件成功
            }
            System.out.println("复制文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
