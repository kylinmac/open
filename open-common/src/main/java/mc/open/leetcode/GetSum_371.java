package mc.open.leetcode;

/**
 * @author macheng
 * @date 2021/9/26 10:17
 */
public class GetSum_371 {

    public static void main(String[] args) {
        System.out.println(-1 << 1);
        System.out.println(-1 >> 1);
        System.out.println(-1 & 1);
        System.out.println(-1 | 1);

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).length());
    }

    public int getSum(int a, int b) {

        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);

    }
}
