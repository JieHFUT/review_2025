package net.tcpudp_2;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接。
public class TCP {
    
    @Test
    public void testClient() throws Exception {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 2312;
        // 客户端指明服务器的 ip 和服务器的 端口，同时得到 socket
        Socket socket = new Socket(address, port);
        // 默认bind捆绑自己的ip和端口号
        // socket.bind(SocketAdress bindPoint);

        File file = new File("girl.webp");
        FileInputStream fis = new FileInputStream(file);

        // 获取socket中的输出流
        OutputStream os = socket.getOutputStream();

        // 开始传输
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            // 将获得的文件的字节流通过 socket 的输出流发给服务器
            os.write(buffer, 0, len);
        }
        System.out.println("data translate success");
        // 已经将图片发送过去
        socket.shutdownOutput();


        // 客户端接收服务器的数据
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer1 = new byte[1024];
        int len1;
        // 读取服务器发来的数据
        while ((len1 = inputStream.read(buffer1)) != -1) {
            byteArrayOutputStream.write(buffer1, 0, len1);
        }
        System.out.println(byteArrayOutputStream.toString());


        byteArrayOutputStream.close();
        inputStream.close();
        os.close();
        fis.close();
        socket.close();

    }




    @Test
    public void testServer() throws IOException {
        // 创建欢迎 socket
        int port = 2312;
        ServerSocket welcomeSocket = new ServerSocket(port);
        // 默认绑定自己的 sad(ip port)
        // welcomeSocket.bind(SocketAddress);

        // 去接收客户端的连接，接收一个连接就返回一个 socket
        System.out.println("等待客户端发送数据...");
        Socket clientSocket = welcomeSocket.accept();
        // 获取客户端发送过来的输入流
        InputStream inputStream = clientSocket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        // 服务器将文件保存在当前目录的 girl1.webp
        File file = new File("girl1.webp");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int len;
        byte[] buffer = new byte[1024];
        while ((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        // 图片放置完毕后，返回一个数据给客户端
        String str = "picture is beautiful";
        OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write(str.getBytes());


        outputStream.close();
        bos.close();
        fos.close();
        bis.close();
        inputStream.close();
        clientSocket.close();
        welcomeSocket.close();
    }

}
