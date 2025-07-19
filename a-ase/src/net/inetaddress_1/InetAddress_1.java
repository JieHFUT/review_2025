package net.inetaddress_1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 5. InetAddress的使用
 5.1 作用
 InetAddress类的一个实例就代表一个具体的ip地址。
 5.2 实例化方式
 InetAddress getByName(String host):获取指定ip对应的InetAddress的实例
 InetAddress getLocalHost():获取本地ip对应的InetAddress的实例
 5.3 常用方法
 getHostName()
 getHostAddress()
 */
public class InetAddress_1 {
    public static void main(String[] args) {

        try {
            //1. 实例化
            InetAddress address = InetAddress.getByName("192.168.83.39");
            System.out.println(address);// /192.168.83.39
            InetAddress inet4 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet4);// /127.0.0.1


            //2. 求指定网址ip
            InetAddress baiduAddress = InetAddress.getByName("baidu.com");
            System.out.println(baiduAddress);// baidu.com/39.156.66.10

            //3. 获取本地ip对应的 InetAddress 实体
            InetAddress selfAdress = InetAddress.getLocalHost();
            System.out.println(selfAdress);// jieHFUT/192.168.83.39


            // 常用方法
            System.out.println(baiduAddress.getHostName());// baidu.com
            System.out.println(baiduAddress.getHostAddress());// [B@2401f4c3


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
