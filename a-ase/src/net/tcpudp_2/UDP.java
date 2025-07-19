package net.tcpudp_2;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;


public class UDP {

    @Test
    public void testClient() throws IOException {
        // 客户端直接发送数据
        DatagramSocket socket = new DatagramSocket();
        // 将目的 ip，目的port，发送的数据全部分装在 DatagramPacket 数据报文中
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        int port = 9090;
        byte[] bytes = "我是发送端".getBytes("utf-8");
        DatagramPacket packet =  new DatagramPacket(bytes,0,bytes.length,inetAddress,port);

        //发送数据
        socket.send(packet);

        socket.close();


    }
    
    
    @Test
    public void testServer() throws IOException {
        // 服务器启动
        int port = 32421;
        // 指定服务器的端口信息
        DatagramSocket socket = new DatagramSocket(port);
        // 创建服务器接收数据数据包的大小
        byte[] buf = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
        // 开始等待客户端发送数据
        socket.receive(packet);

        // 获得数据
        String msg = new String(packet.getData(),0,packet.getLength());
        System.out.println(msg);
        socket.close();
    }
}
