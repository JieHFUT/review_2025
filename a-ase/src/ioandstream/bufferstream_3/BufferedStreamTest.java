package ioandstream.bufferstream_3;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: BufferedStreamTest
 * Package: ioandstream.bufferstream_3
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/18 20:22
 * @Version 1.0
 */
public class BufferedStreamTest {

    /*
     * 需求：使用BufferedInputStream \ BufferedOutputStream复制一个图片
     *
     * 注意：如下的操作应该使用try-catch-finally处理异常。
     * */
    @Test
    public void test() throws IOException {
        File srcFile = new File("girl.webp");
        File destFile = new File("girl_copy2.webp");

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        FileInputStream fileInputStream = new FileInputStream(srcFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);

        bufferedInputStream = new BufferedInputStream(fileInputStream);
        bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer, 0, len);
        }
        System.out.println("复制成功");

        bufferedInputStream.close();
        bufferedOutputStream.close();

        fileInputStream.close();
        fileOutputStream.close();
    }
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
