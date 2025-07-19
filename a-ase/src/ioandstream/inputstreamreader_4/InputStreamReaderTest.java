package ioandstream.inputstreamreader_4;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: InputStreamReader
 * Package: ioandstream.inputstreamreader_4
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/18 20:59
 * @Version 1.0
 */
public class InputStreamReaderTest {
    // 3. 转换流：
    // ① 作用：实现字节与字符之间的转换
    //
    // ② API:
    // nputStreamReader:将一个输入型的字节流转换为输入型的字符流。
    // OutputStreamWriter:将一个输出型的字符流转换为输出型的字节流。
    //
    // 4. 关于字符集的理解
    // 4.1 在存储的文件中的字符：
    // ascii:主要用来存储a、b、c等英文字符和1、2、3、常用的标点符号。每个字符占用1个字节。
    //
    // iso-8859-1:了解，每个字符占用1个字节。向下兼容ascii。
    //
    // gbk:用来存储中文简体繁体、a、b、c等英文字符和1、2、3、常用的标点符号等字符。
    //    中文字符使用2个字节存储的。向下兼容ascii，意味着英文字符、1、2、3、标点符号仍使用1个字节。
    //
    // utf-8:可以用来存储世界范围内主要的语言的所有的字符。使用1-4个不等的字节表示一个字符。
    //    中文字符使用3个字节存储的。向下兼容ascii，意味着英文字符、1、2、3、标点符号仍使用1个字节。
    //
    //
    // .2 在内存中的字符：
    //
    // 个字符(char)占用2个字节。在内存中使用的字符集称为Unicode字符集。



    // 将一个 utf8 编码的文件拷贝为 gbk 格式
    @Test
    public void test() {
        File srcFile = new File("utf8_file.txt");
        File destFile = new File("gbk_file.txt");

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            inputStreamReader = new InputStreamReader(fileInputStream, "utf8");
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");

            // 读写过程
            char[] buffer = new char[1024];
            int len;
            while ((len = inputStreamReader.read(buffer)) != -1) {
                outputStreamWriter.write(buffer, 0, len);
            }
            System.out.println("转码完成");

        } catch (Exception e) {
            e.printStackTrace();












        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




















    

}
