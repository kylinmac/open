package mc.open.leetcode;

import java.util.HashSet;

/**
 * @author macheng
 * @date 2021/9/17 9:09
 */
public class IsValidSudoku_36 {


    public boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            int[] nine=new int[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j]!='.'){
                    int curNum = board[i][j] - 48;
                    if (nine[curNum]>0){
                        System.out.println("line");
                        return false;
                    }else {
                        nine[curNum]=1;
                    }
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            int[] nine=new int[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][i]!='.'){
                    int curNum = board[j][i] - 48;
                    if (nine[curNum]>0){
                        return false;
                    }else {
                        nine[curNum]=1;
                    }
                }
            }
        }


        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                int[] nine=new int[9];
                for (int k = i; k < i+3; k++) {
                    for (int l = j; l < j+3; l++) {
                        if (board[k][l]!='.'){
                            int curNum = board[j][i] - 48;
                            if (nine[curNum]>0){
                                return false;
                            }else {
                                nine[curNum]=1;
                            }
                        }
                    }
                }

            }
        }


        return true;

    }

    public static void main(String[] args) {
        System.out.println('1'+0);
    }

}
