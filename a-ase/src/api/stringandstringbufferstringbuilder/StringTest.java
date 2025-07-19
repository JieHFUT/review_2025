package api.stringandstringbufferstringbuilder;

import java.io.UnsupportedEncodingException;

public class StringTest {
    public static void main(String[] args) {

        // 通过使用平台的**默认字符集**解码当前参数中的字节数组来构造新的String
        String str = new String(new byte[]{'a','b','c'});
        System.out.println(str);

        try {
            // 通过使用指定的字符集解码当前参数中的字节数组来构造新的String
            String str1 = new String(new byte[]{'+','f','c'}, "GBK");
            System.out.println(str1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
    }

}
