package ioandstream.objectstream_5;

import org.junit.Test;

import java.io.*;

/**
 * ClassName: ObjectInputOutputStream
 * Package: ioandstream.objectstream_5
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/18 21:35
 * @Version 1.0
 */
public class ObjectInputOutputStream {

    // 序列化过程：使用ObjectOutputStream流实现。将内存中的Java对象保存在文件中或通过网络传输出去

    @Test
    public void test1() throws IOException {
        File file = new File("object.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);


        String str = "beautiful 江山如此多娇，引无数英雄竞折腰！";
        objectOutputStream.writeUTF(str);
        objectOutputStream.flush();

        objectOutputStream.writeObject("slightly 轻轻的我走了，正如我轻轻的来！");
        objectOutputStream.flush();

        objectOutputStream.close();
    }


    // 反序列化过程：使用ObjectInputSteam流实现。将文件中的数据或网络传输过来的数据还原为内存中的Java对象
    @Test
    public void test2() throws IOException, ClassNotFoundException {
        File file = new File("object.txt");
        FileInputStream fos = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fos);

        String str1 = objectInputStream.readUTF();
        System.out.println(str1);

        String str2 = (String) objectInputStream.readObject();
        System.out.println(str2);

        objectInputStream.close();
    }

}
