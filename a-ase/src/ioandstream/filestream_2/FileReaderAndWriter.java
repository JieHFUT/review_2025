package ioandstream.filestream_2;

import org.junit.Test;

import java.io.*;

public class FileReaderAndWriter {
    /*
     * 需求：读取hello.txt中的内容，显示在控制台上。
     * 异常使用throws的方式处理，不太合适。见 test2()
     * */
    @Test
    public void test() throws IOException {
        //1. 创建File类对象，对应文件
        File file = new File("hello.txt");
        //2. 创建输入形的字符流，用于读取数据
        FileReader fileReader = new FileReader(file);
        //3. 读取数据并显示在控制台上
        int ret = fileReader.read();
        while (ret != -1) {
            System.out.print((char) ret);
            ret = fileReader.read();
        }
        // you never know
        // 谁知道呢!
        // 另外一种输出字符的操作
        int res;
        while ((res = fileReader.read()) != -1) {
            System.out.print((char) res);
        }

        //4. 关闭流资源
        fileReader.close();
    }


    /*
     * 需求：读取hello.txt中的内容，显示在控制台上。
     * 对test()进行优化，每次读取多个字符存放到字符数组中，减少了与磁盘交互的次数，提升效率。
     * */
    @Test
    public void test1(){
        File file = new File("hello.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);

            // 创建缓冲数组
            char[] cbuffer = new char[4];
            // 读入：read(char[] cbuffer)
            // 写出：write(String str) / write(char[] cbuffer,0,len)
            // 开始打印在控制台上
            int ret;
            while ((ret = fileReader.read(cbuffer)) != -1) {
                for (int i = 0; i < ret; i++) {
                    System.out.print(cbuffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 需求：将内存中的数据写出到指定的文件中
     * */
    @Test
    public void test2(){
        File file = new File("write.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("you never konw!");
            fileWriter.write("谁知道呢！");
            System.out.println("输出内存数据到文件成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 需求：复制一份hello.txt文件，命名为hello_copy.txt
     * */
    @Test
    public void test3(){
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try{
            File srcFile = new File("hello.txt");
            File destFile = new File("hello_copy.txt");

            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(destFile);

            char[] cbuffer = new char[4];
            int ret;
            while ((ret = fileReader.read(cbuffer)) != -1) {
                fileWriter.write(cbuffer, 0, ret);
            }
            System.out.println("copy success");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
     * 需求：复制一份girl.webp文件，命名为girl_copy.webp
     * 复制失败！因为字符流不适合用来处理非文本文件。
     * */
    @Test
    public void test4() {
        FileReader fileReader = null;
        FileWriter fileWriter = null;

        File srcFile = new File("girl.webp");
        File destFile = new File("girl_copy.webp");

        try {
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(destFile);

            char[] cbuffer = new char[4];
            int ret;
            while ((ret = fileReader.read(cbuffer)) != -1) {
                fileWriter.write(cbuffer, 0, ret);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "图片girl拷贝错误");
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    
}
