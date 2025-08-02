package algorithms.b_dac;

/**
 * ClassName: Hanoitower
 * Package: algorithms.b_dac
 * Description:
 * 分治算法
 * 汉诺塔问题
 * @Author jieHFUT
 * @Create 2025/8/1 1:31
 * @Version 1.0
 */
public class Hanoitower {

    /**
     * 思路：采用分治算法
     * 想要将所有的层从 tower1 转移到 tower3 上，需要先做：将除去最底下的层的那些转移到 tower2 中，再将最大的层转移到 tower3 上
     *
     * @param level  塔的层数
     * @param tower1 第一座塔的名称
     * @param tower2 第二座塔的名称
     * @param tower3 第三座塔的名称
     */
    public void hanoitower(int level, String tower1, String tower2, String tower3) {

        if (level <= 0) throw new RuntimeException("Invalid level");
        if (level == 1)
            System.out.println("第" + level + "层 => 从" + tower1 + "塔移动到" + tower3 + "塔");
        else {
            // 在这个地方是大于两层的
            // 1.先把上面的 level - 1 层移动到二塔上
            hanoitower(level-1, tower1, tower3, tower2);
            // 2.再把一塔上剩下的最底下的那个移动到三塔上
            System.out.println("第" + level + "层 => 从" + tower1 + "塔移动到" + tower3 + "塔");
            // 3.再把刚才的 level - 1 层从二塔上移动到三塔上
            hanoitower(level-1, tower2, tower1, tower3);
        }

    }


    public static void main(String[] args) {
        Hanoitower h = new Hanoitower();
        h.hanoitower(10, "A", "B", "C");
    }
}
