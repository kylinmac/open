package mc.open.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author macheng
 * @date 2021/8/30 8:11
 */
public class RandomPickwithWeight_528 {

    int[] pickList;
    int sum;

    /**
     * 超内存了
     * @param w
     */
    public RandomPickwithWeight_528(int[] w) {
        IntStream stream = Arrays.stream(w);
        this.sum = stream.sum();
        this.pickList=new int[w.length];
        int i=0;
        for (int j = 0; j < w.length; j++) {
            for (int k = 0; k < w[j]; k++,i++) {
                pickList[i]=j;
            }
        }
    }

    public int pickIndex() {
        int i = pickList.length - 1;
        if (i==1){
            return pickList[0];
        }
        return pickList[(int)(Math.random()*pickList.length)];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println((int)(Math.random()*4+1));
        }
    }
}
