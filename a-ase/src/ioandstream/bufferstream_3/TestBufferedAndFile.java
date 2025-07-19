package ioandstream.bufferstream_3;

import org.junit.Test;

import java.io.*;

public class TestBufferedAndFile {
    // 测试缓冲流和节点流之间的效率
    @Test
    public void testFileInputStream() throws IOException {
        // 测试节点流 拷贝一张图片到F盘  45ms
        File srcFile = new File("girl.webp");
        File destFile = new File("F:\\file.webp");

        FileInputStream fis = null;
        FileOutputStream fos = null;

        fis = new FileInputStream(srcFile);
        fos = new FileOutputStream(destFile);

        long start = System.currentTimeMillis();
        byte[] buffer = new byte[10];
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        long end = System.currentTimeMillis();

        fos.close();
        fis.close();

        System.out.println("节点流 fileinputstream 花费时间" + (end - start) + "ms");
    }


    @Test
    public void testBufferedInputStream() throws IOException {
        // 测试节点流 拷贝一张图片到F盘  1ms
        File srcFile = new File("girl.webp");
        File destFile = new File("F:\\buffer.webp");

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        bis = new BufferedInputStream(fis);
        bos = new BufferedOutputStream(fos);

        long start = System.currentTimeMillis();
        byte[] buffer = new byte[10];
        int len = 0;
        while ((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        long end = System.currentTimeMillis();

        bos.close();
        bis.close();
        fis.close();
        fos.close();

        System.out.println("节点流 fileinputstream 花费时间" + (end - start) + "ms");
    }


}
