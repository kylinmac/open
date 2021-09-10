package mc.open.leetcode;

/**
 * 一个班级里有n个学生，编号为 0到 n - 1。每个学生会依次回答问题，编号为 0的学生先回答，然后是编号为 1的学生，以此类推，直到编号为 n - 1的学生，然后老师会重复这个过程，重新从编号为 0的学生开始回答问题。
 *
 * 给你一个长度为 n且下标从 0开始的整数数组chalk和一个整数k。一开始粉笔盒里总共有k支粉笔。当编号为i的学生回答问题时，他会消耗 chalk[i]支粉笔。如果剩余粉笔数量 严格小于chalk[i]，那么学生 i需要 补充粉笔。
 *
 * 请你返回需要 补充粉笔的学生 编号。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author macheng
 * @date 2021/9/10 16:10
 */
public class ChalkReplacer_1894 {
    public int chalkReplacer(int[] chalk, int k) {
        int cur=0;
        int sum=0;
        while (true){
            k-=chalk[cur];
            if(k<0){
                return cur;
            }
            if (cur==chalk.length-1){
                cur=0;
                break;
            }else {
                sum+=chalk[cur];
                cur++;
            }
        }

        int i = k % sum;
        System.out.println(i);
        while (true){
            i-=chalk[cur];
            if(i<0){
                return cur;
            }
             cur++;
        }

    }
}
