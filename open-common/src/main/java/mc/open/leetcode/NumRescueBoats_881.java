package mc.open.leetcode;

import java.util.Arrays;

/**
 * @author macheng
 * @date 2021/8/26 8:36
 */
public class NumRescueBoats_881 {

    public static int  numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);
        int result=0;
         int left=0;
         int right=people.length-1;

         while (left<right){
             if ((people[left]+people[right])<=limit){
                 result++;
                 right--;
                 left++;
             }else {
                 result++;
                 right--;
             }
         }

         if (left==right){
             result++;
         }
         return result;

    }
}
