package algorithms.e_greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * ClassName: Greedy
 * Package: algorithms.e_greedy
 * Description:
 * 贪婪算法：每一次都拿走最好的结果
 *
 * 例如：有几个广播电视台 k1 k2 k3 k4 k5
 *      每一个广播电视台都有自己可以覆盖的几个城市
 *      如何选取最少的广播电视台，可以覆盖全部地区：依次选取（覆盖未选取地区）最多的那个电视台
 *
 * @Author jieHFUT
 * @Create 2025/8/3 0:14
 * @Version 1.0
 */
public class Greedy {

    public static void main(String[] args) {

        //创建广播电台,放入到 Map    broadcasts：电视台列表
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();

        //将各个电台放入到 broadcasts
        HashSet<String> radio1 = new HashSet<String>();
        radio1.add("北京");
        radio1.add("上海");
        radio1.add("天津");

        HashSet<String> radio2 = new HashSet<String>();
        radio2.add("广州");
        radio2.add("北京");
        radio2.add("深圳");

        HashSet<String> radio3 = new HashSet<String>();
        radio3.add("成都");
        radio3.add("上海");
        radio3.add("杭州");


        HashSet<String> radio4 = new HashSet<String>();
        radio4.add("上海");
        radio4.add("天津");

        HashSet<String> radio5 = new HashSet<String>();
        radio5.add("杭州");
        radio5.add("大连");

        // 各个电台加入到 map
        broadcasts.put("K1", radio1);
        broadcasts.put("K2", radio2);
        broadcasts.put("K3", radio3);
        broadcasts.put("K4", radio4);
        broadcasts.put("K5", radio5);


        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");



        //创建ArrayList, 存放最终选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();

        // 一轮中选中那个电台后，需要从剩下的区域集合中移除的区域
        HashSet<String> toRemove = new HashSet<>();
        HashSet<String> trueRemove = new HashSet<>();

        // 记录某一轮中选中的电台名称
        String thisChoice = null;


        // 当这个集合还有数据，说明还有地区被遗漏
        // 一次循环添加一个广播电视台
        while (allAreas.size() != 0) {
            // allAreas 就是还剩下的区域集合

            // 遍历广播电视台 name：电视台的名称   broadcasts.keySet() = key 的集合
            for (String name: broadcasts.keySet()) {
                // 得到这个广播电视台能够覆盖的区域
                HashSet<String> areas = broadcasts.get(name);
                toRemove.clear();
                trueRemove.clear();
                thisChoice = null;

                // 计算还剩下的区域集合中，这个电台包含了几个 => 取交集
                toRemove.addAll(areas);
                toRemove.retainAll(allAreas);

                // 然后找到交集最大的那个电台，使用 thisChoice 来记录选中的电台名称
                // 如果是第一个电台直接记录，如果是后面的电台需要和前面记录的进行比较
                if (thisChoice != null) {
                    // 进行比较：这次遍历到的电台名称是 name, 之前记录的交集最多的电台名称是 thisChoice
                    // 进行比较：这次遍历到的区域集合是 toRemove, 之前记录的交集最多的区域集合是 broadcasts.get(thisChoice);
                    HashSet maxAreas = broadcasts.get(thisChoice);
                    trueRemove.addAll(maxAreas);
                    trueRemove.retainAll(allAreas);
                }

                if (toRemove.size() > 0 && (thisChoice == null || toRemove.size() > trueRemove.size())) {
                    // 如果这是第一次取交集：thisChoice == null 或者这一次取交集的大小大于之前记录的
                    thisChoice = name;
                }
            }

            // 如果某一轮遍历确实要加入某一个电视台，即 thisChoice != null
            if (thisChoice != null) {
                selects.add(thisChoice);
                allAreas.retainAll(broadcasts.get(thisChoice));
            }

        }







    }
}
