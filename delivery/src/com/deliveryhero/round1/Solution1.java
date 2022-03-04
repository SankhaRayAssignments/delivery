package com.deliveryhero;

/**
 * @author sanray on 2/28/2022
 */
public class Solution1 {
    public static void main(String[] args) {
        Solution1 sol1 = new Solution1();
        //sol1.solution();
    }

    private int[] solution(int X, int Y) {


        int jumbo = (X - 2*Y) / 2;
        int small = (Y - jumbo);
        if(jumbo < 0 || small < 0 || 4 * jumbo + 2 *small != X) {
            return new int[]{-1,-1};
        }
        return new int[]{jumbo, small};

    }
}
