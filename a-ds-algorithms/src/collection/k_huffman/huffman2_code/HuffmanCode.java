package collection.k_huffman.huffman2_code;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * ClassName: HuffmanCode
 * Package: collection.k_huffman.huffman2_code
 * Description:
 * 霍夫曼编码（变长编码）（ascii码就是定长编码）
 * 例如一段话：“i like like like java do you like a java”
 * 各个字符出现的次数：d:1 y:1 u:1 j:2  v:2  o:2  l:4  k:4  e:4 i:5  a:5   :9
 * 按照各个字符出现的次数进行编码：0=  ,  1=a, 10=i, 11=e, 100=k, 101=l, 110=o, 111=v, 1000=j, 1001=u, 1010=y, 1011=d
 * 粗略的将上述这段话变为：10010110100...
 *
 * 但是考虑到字符的编码都不能是其他字符编码的前缀，符合此要求的编码叫做前缀编码 => 使用霍夫曼树保证每个字符的编码的独特性
 * 将字符出现的次数作为其权值 array = {d:1 y:1 u:1 j:2  v:2  o:2  l:4  k:4  e:4 i:5  a:5   :9}
 * 按照出现次数的权值构造霍夫曼树，然后每一个叶子节点的路径就是其二进制码（向左为 0，向右为 1）
 *
 * @Author jieHFUT
 * @Create 2025/7/30 13:29
 * @Version 1.0
 */
public class HuffmanCode {
    // 完成数据的压缩 => 解压

    /**
     * 1.将需要压缩的字符串转化为 byte[] 数组
     *        public byte[] turn(String str) {....}
     * 2.统计 byte[] 数组中各个字符出现的次数作为权值，记录 List[node(字符，次数).....。]
     *        public List<Node> statistics(byte[] input) {.....}
     * 3.根据权值集合构建霍夫曼树，返回树的根节点
     *        public Node createHuffmanTree(List<Node> nodes) {...}
     * 4.根据霍夫曼数构建霍夫曼表，返回每一个字符对应的二进制字符串，Map<Byte, String>
     *        public Map<Byte, String> huffmanCode(Node root) {...}
     * 5.获取压缩后需要被传输的 byte[]
     *        public byte[] compress(byte[] input, Map<Byte, String> huffmanCodes) {...}
     *
     * @param args
     */
    public static void main(String[] args) {
        String content =
                "于是分布式版本控制系统（Distributed Version Control System，简称 DVCS）面世了。 在这类系统中，像\n" +
                "于是分布式版本控制系统（Distributed Version Control System，简称 DVCS）面世了。 在这类系统中，像\n" +
                "Git、Mercurial 以及 Darcs 等，客户端并不只提取最新版本的文件快照， 而是把代码仓库完整地镜像下来，包\n" +
                "Git、Mercurial 以及 Darcs 等，客户端并不只提取最新版本的文件快照， 而是把代码仓库完整地镜像下来，包\n" +
                "括完整的历史记录。 这么一来，任何一处协同工作用的服务器发生故障，事后都可以用任何一个镜像出来的本\n" +
                "括完整的历史记录。 这么一来，任何一处协同工作用的服务器发生故障，事后都可以用任何一个镜像出来的本\n" +
                "于是分布式版本控制系统（Distributed Version Control System，简称 DVCS）面世了。 在这类系统中，像\n" +
                "于是分布式版本控制系统（Distributed Version Control System，简称 DVCS）面世了。 在这类系统中，像\n" +
                "Git、Mercurial 以及 Darcs 等，客户端并不只提取最新版本的文件快照， 而是把代码仓库完整地镜像下来，包\n" +
                "Git、Mercurial 以及 Darcs 等，客户端并不只提取最新版本的文件快照， 而是把代码仓库完整地镜像下来，包\n" +
                "括完整的历史记录。 这么一来，任何一处协同工作用的服务器发生故障，事后都可以用任何一个镜像出来的本\n" +
                "括完整的历史记录。 这么一来，任何一处协同工作用的服务器发生故障，事后都可以用任何一个镜像出来的本\n" +
                "地仓库恢复。 因为每一次的克隆操作，实际上都是一次对代码仓库的完整备份。";


    }

    // 将压缩文件的过程整合
    public byte[] zip(byte[] input) {
        // 统计各个字符出现的次数
        List<Node> nodeList = statistics(input);
        // 构建哈夫曼树
        Node root = createHuffmanTree(nodeList);
        // 构建哈夫曼表
        Map<Byte, String> huffmanCodes = huffmanCode(root);
        // 获得压缩后需要传输的 byte[]
        byte[] zipBytes = compress(input, huffmanCodes);
        // 返回
        return zipBytes;
    }



    /**
     * 1.将需要被压缩的字符串转化为 byte[] 数组
     * @param str
     * @return
     */
    public byte[] turn(String str) {
        return str.getBytes();
    }


    /**
     * 2.将传入的 byte[] 数组转换为（每个字符及其出现的次数）List 集合形式
     *
     * @param input ['a','f','5','e','k','f', ......]
     * @return List[每个字符及其出现的次数]
     */
    public List<Node> statistics(byte[] input) {
        // 记录出现的次数
        Map<Byte, Integer> countMap = new HashMap<>();
        // 遍历数组
        for (byte b : input) {
            Integer weight = countMap.get(b);
            if (weight == null) {
                // 这个字符还没有计数
                countMap.put(b, 1);
            } else {
                // 这个字符已经记数，加一
                countMap.put(b, weight + 1);
            }
        }
        // 将 map 中的数据转换为 node 集合
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : countMap.entrySet()) {
            if (entry.getKey() != null) {
                // 有这个字符
                nodes.add(new Node(entry.getKey(), entry.getValue()));
            }
        }
        return nodes;
    }

    /**
     * 3.根据权值集合构建霍夫曼树，返回树的根节点
     * @param nodes
     * @return
     */
    public Node createHuffmanTree(List<Node> nodes) {
        if (nodes == null || nodes.size() == 0) return null;

        while (nodes.size() > 1) {
            // 构建霍夫曼树在前面已经学过(根据权值先排序)
            Collections.sort(nodes);
            // 取出前两个最小的
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            Node parent = new Node(null,left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            nodes.remove(0);
            nodes.remove(0);

            nodes.add(parent);
        }
        // 到这个地方集合中只剩下一个元素就是根
        return nodes.get(0);
    }


    /**
     * 4.根据霍夫曼数构建霍夫曼表，返回每一个字符对应的二进制字符串，Map<Byte, String>
     *
     * @param root
     * @return [['a', 0110], ['8', 001], [...], [...]]
     */
    public Map<Byte, String> huffmanCode(Node root) {
        if (root == null) return null;
        getHuffmanCodes(root, "", route);
        return huffmanCodes;
    }

    private Map<Byte, String> huffmanCodes = new HashMap<>();
    private StringBuilder route = new StringBuilder();

    /**
     * 构建哈夫曼编码表：将传入的 node 节点的所有叶子节点的 01010 编码得到，放到 huffmanCodes 集合中
     * @param node 哈夫曼树的节点
     * @param code 每次往下走一层一个字符添加的一个 左“0” 或者 右“1”
     *             也就是 node 的父亲节点到 node 的是 "0" 还是 "1"
     * @param parentRoute 用来记录从根节点到 node 父亲节点的 0101001 代码
     * @return
     */
    public void getHuffmanCodes(Node node, String code, StringBuilder parentRoute) {
        StringBuilder sb = new StringBuilder(parentRoute);

        sb.append(code);
        if (node.value == null) {
            // 这个节点不是叶子节点
            if (node.left != null)
                getHuffmanCodes(node.left, "0", sb);
            if (node.right != null)
                getHuffmanCodes(node.right, "1", sb);
        } else {
            // 这个节点就是叶子节点
            huffmanCodes.put(node.value, sb.toString());
        }
    }


    /**
     * 5.获取压缩后需要被传输的 byte[]
     * @param input 原始字符串转换成为的 byte[] 数组
     * @param huffmanCodes 构建完成的霍夫曼编码
     * @return "01010010010100100...00010100101" 字符串转化为 byte[] 数组
     */
    public byte[] compress(byte[] input, Map<Byte, String> huffmanCodes) {
        StringBuilder wholeCode = new StringBuilder();
        // 遍历 input 数组，将其对应的 010101 代码追加到 wholeCode‘
        for (byte b : input) {
            wholeCode.append(huffmanCodes.get(b));
        }
        // 现在 wholeCode 变成了 "01010010101001...01001001" 一整个字符串流
        // 需要按照 8 个一组将其分为一个个 byte
        byte[] zip = new byte[(wholeCode.length() + 7) / 8];
        // 记录 zip 的下标
        int index = 0;
        for (int i = 0; i < wholeCode.length(); i += 8) {
            String sub;
            // 一次截取8个
            if (i + 8 < wholeCode.length()) {
                // 可以截取完整
                sub = wholeCode.substring(i, i + 8);
            } else {
                // 最后一段不够 8 个了，剩下的全部截完
                sub = wholeCode.substring(i);
            }
            zip[index++] = (byte) Integer.parseInt(sub, 2);
        }
        return zip;
    }








    // 解码，根据传入的经过编码的 byte[]，将其内信息转化为 010100101...
    // 再根据 01 代码匹配哈夫曼表将其解码为原本的 byte[]
    public byte[] decode(byte[] zip) {
        return decode(zip, huffmanCodes);
    }
    public byte[] decode(byte[] zip, Map<Byte, String> huffmanCodes) {
        // 转化为 01010100...
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zip.length; i++) {
            if (i == zip.length - 1) {
                // 最后一个可能不足 8 位
                str = byteToString(true, zip[i]);
            } else {
                str = byteToString(false, zip[i]);
            }
            stringBuilder.append(str);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////// 这是待传送的 "0101001001..." 流
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //System.out.println(stringBuilder.toString());
        // 此时重新获得 0100101010111010101001010100101001010100100100001010101100010010100...
        // 将这一串 01 代码和哈夫曼表匹配，匹配到的结果放在集合里
        List<Byte> decodeResult = new ArrayList<>();
        Map<String, Byte> toSearchHuffmanCodes = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet())
            toSearchHuffmanCodes.put(entry.getValue(), entry.getKey());

        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            while (flag) {
                // 匹配到了就结束循环
                String toSearch = stringBuilder.substring(i, i + count);
                if (toSearchHuffmanCodes.get(toSearch) == null) {
                    // 没有匹配到
                    count++;
                } else {
                    // 匹配到了
                    flag = false;
                    decodeResult.add(toSearchHuffmanCodes.get(toSearch));
                }
            }
            i = i + count;
        }
        // 已经把解码的 byte[] 放在集合中
        int index = 0;
        byte[] decodedBytes = new byte[decodeResult.size()];
        for (Byte b : decodeResult)
            decodedBytes[index++] = b;
        return decodedBytes;
    }


    @Test
    public void test2(){
        System.out.println(Integer.toBinaryString(1)); // 1
        System.out.println(Integer.toBinaryString(-1)); // 11111111_11111111_11111111_11111111
        System.out.println(Integer.toBinaryString(-1).substring(32 - 8)); // 11111111
        System.out.println(Integer.toBinaryString(1 | 256)); // 1_00000001
        System.out.println(Integer.toBinaryString(1 | 256).substring(9 - 8)); // 00000001
    }
    public static String byteToString(boolean flag, byte codeByte) {
        int code = codeByte;
        if (flag) {
            // 可能不足 8 位
            return Integer.toBinaryString(code);
        } else {
            String str = Integer.toBinaryString(code |= 256); // 256 = 00000000_00000000_00000001_00000000
            return str.substring(str.length() - 8); // 只要后面8位
        }
    }


    @Test
    public void test(){
        System.out.println(Integer.parseInt("01111111", 2)); // 127
        System.out.println(Integer.parseInt("111", 2)); // 7
        System.out.println(Integer.parseInt("11111111", 2));// 255
        System.out.println(Integer.parseInt("01111111111111111111111111111111", 2));// 2147483647]\
        // 111111111111111111111111111111111 => 11111111111111111111111111111110 => 1000000000000000000000000000001
        System.out.println(Integer.parseInt("10000000000000000000000000000000", 2));// NumberFormatException
        System.out.println(Integer.parseInt("10000000000000000000000000000001", 2));// NumberFormatException
        // 1000000000000000000000000000001 => 10000000000000000000000000000000 => 11111111111111111111111111111111 => -2147483647
        System.out.println(Integer.parseInt("11111111111111111111111111111111", 2));// NumberFormatException
    }
    @Test
    public void test1(){
        System.out.println(Integer.parseInt("01010011", 2)); // 83
        System.out.println(Integer.parseInt("10101101", 2)); // 173
        System.out.println(Integer.parseInt("101010011", 2)); // 339
        System.out.println(Integer.parseInt("110101101", 2)); // 429


        System.out.println((byte) Integer.parseInt("01010011", 2)); // 83

        //
        // 10101101 补码：10101101  反码:10101100  原码:11010011     原码=> -83
        System.out.println((byte) Integer.parseInt("10101101", 2)); // -83
        System.out.println((byte) Integer.parseInt("101010011", 2)); // 83
        System.out.println((byte) Integer.parseInt("110101101", 2)); // -83
    }


















    @Test
    public void zip(){
        zip(".//src//huffmancodereview//sun.bmp", ".//src//huffmancodereview//compress.huf");
        System.out.println("压缩完成~");
    }
    @Test
    public void decode(){
        decode(".//src//huffmancodereview//compress.huf", ".//src//huffmancodereview//decode.bmp");
        System.out.println("解压完成~");
    }

    @Test
    public void zipVideo(){
        zip(".//src//huffmancodereview//tocompress.mp4", ".//src//huffmancodereview//compressed.huf");
        System.out.println("压缩完成~");
    }
    @Test
    public void decodeVideo(){
        decode(".//src//huffmancodereview//compressed.huf", ".//src//huffmancodereview//decode.mp4");
        System.out.println("解压完成~");
    }



    // 传入一个原文件，将其压缩输出到目标文件
    public void zip(String src, String dest) {
        File srcFile = new File(src);
        File destDest = new File(dest);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fis = new FileInputStream(srcFile);
            byte[] buffer = new byte[fis.available()];
            // 将文件中的内容以字节流读取到 buffer 中
            fis.read(buffer);
            // 压缩数据得到需要传输的 byte[]

            byte[] toTrans = zip(buffer);

            // 向 dest 文件中写入需要传输的 byte[]，同时传入哈夫曼表以供解码
            fos = new FileOutputStream(destDest);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(toTrans);
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 将压缩的文件解码
    public void decode(String src, String dest) {
        File srcFile = new File(src);
        File destDest = new File(dest);

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcFile);
            ois = new ObjectInputStream(fis);

            byte[] buffer = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            byte[] decoded = decode(buffer, huffmanCodes);

            fos = new FileOutputStream(destDest);
            fos.write(decoded);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                ois.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
























}
