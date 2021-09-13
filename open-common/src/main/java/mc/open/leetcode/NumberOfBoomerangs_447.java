package mc.open.leetcode;

/**
 * @author macheng
 * @date 2021/9/13 13:32
 */
public class NumberOfBoomerangs_447 {
    public int numberOfBoomerangs(int[][] points) {

        int result=0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i==j){continue;}
                int powj = (points[j][0] - points[i][0])*(points[j][0] - points[i][0])+ (points[j][1] - points[i][1])*(points[j][1] - points[i][1]);
                for (int k = 0; k < points.length; k++) {
                    if (i==k||k==j){continue;}
                    int powk = (points[k][0] - points[i][0])*(points[k][0] - points[i][0])+ (points[k][1] - points[i][1])*(points[k][1] - points[i][1]);
                    if (powj==powk){
                        result++;
                    }
                }
            }
        }
        return result;

    }


}
