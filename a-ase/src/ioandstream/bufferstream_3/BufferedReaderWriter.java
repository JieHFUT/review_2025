package ioandstream.bufferstream_3;

/**
 * ClassName: BufferedReaderWriter
 * Package: ioandstream.bufferstream_3
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/18 19:14
 * @Version 1.0
 */

import org.junit.Test;

import java.io.*;

/**
 * 2. 缓冲流的作用：
 * 提升文件读写的效率。
 *
 * 3.
 * 4个缓冲流                   使用的方法
 * 处理非文本文件的字节流：
 * BufferedInputStream        read(byte[] buffer)
 * BufferedOutputStream       write(byte[] buffer,0,len) 、flush()
 *
 * 处理文本文件的字符流：
 * BufferedReader            read(char[] cBuffer) / String readLine()
 * BufferedWriter            write(char[] cBuffer,0,len) / write(String ) 、flush()
 *
 *
 * 3. 实现的步骤
 *
 * 第1步：创建File的对象、流的对象（包括文件流、缓冲流）
 *
 * 第2步：使用缓冲流实现 读取数据 或 写出数据的过程（重点）
 *     读取：int read(char[] cbuf/byte[] buffer) : 每次将数据读入到cbuf/buffer数组中，并返回读入到数组中的字符的个数
 *     写出：void write(String str)/write(char[] cbuf):将str或cbuf写出到文件中
 *          void write(byte[] buffer) 将byte[]写出到文件中
 *
 * 第3步：关闭资源
 */
public class BufferedReaderWriter {
    /*
     * 使用BufferedReader将dbcp_utf-8.txt中的内容显式在控制台上。
     * */
    @Test
    public void test() throws IOException {
        File file = new File("utf8_file.txt");
        // BufferedReader(Reader)
        // BufferedReader(Reader, int)
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        // 读取的过程
        char[] buffers = new char[1024];
        int len = 0;
        while ((len = bufferedReader.read(buffers)) != -1) {
            for (int i = 0; i < len; i++) {
                System.out.print(buffers[i]);
            }
            System.out.println();
            String string = new String(buffers, 0, len);
            System.out.println(string);
        }

        // 方式2：读取一行
        String data;
        while ((data = bufferedReader.readLine()) != null) {
            System.out.println(data);
        }

        bufferedReader.close();
    }




    /*
     * 使用BufferedReader和BufferedWriter实现文本文件的复制
     *
     * 注意：开发中，还是需要使用try-catch-finally来处理流的异常。
     * */
    @Test
    public void test1(){
        File srcFile = new File("utf8_file.txt");
        File destFile = new File("utf8_file_copy.txt");

        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(srcFile));
            bufferedWriter = new BufferedWriter(new FileWriter(destFile));

            String data;
            while ((data = bufferedReader.readLine()) != null) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }























}
