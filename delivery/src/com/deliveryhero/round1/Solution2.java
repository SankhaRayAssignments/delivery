package com.deliveryhero;

/**
 * @author sanray on 3/1/2022
 */
public class Solution2 {

    public static void main(String[] args) {
        Solution2 sol2 = new Solution2();
        System.out.println(sol2.solution(
                new int[] { 1, 2, 3, 5, 6, 7, 8, 9 }));
        System.out.println(sol2.solution(
                new int[] { 1, 2, 3, 10, 11, 15 }));
        System.out.println(sol2.solution(
                new int[] { 5, 4, 2, 1 }));
        System.out.println(sol2.solution(
                new int[] { 3, 5, 7, 10, 15 }));
        System.out.println(sol2.solution(
                new int[] { 5, -3, -2, -1, 0, 1, 5, 6, 7 }));
        System.out.println(sol2.solution(
                new int[] { 0,1,0,1,0,1,0,-1,-2,-3 }));

    }

    public int solution(int[] A) {
        if (A.length <= 1) {
            return 1;
        }
        int longest_run = 1;
        int max = 0;
        int sign = getSign(A[1], A[0]);
        for (int i = 0; i < A.length - 1; i++) {
            int new_sign = getSign(A[i+1], A[i]);
            if (Math.abs(A[i + 1] - A[i]) == 1 && sign == new_sign) {
                longest_run++;
            } else {
                max = Math.max(longest_run, max);
                longest_run = 1;
                if(Math.abs(A[i + 1] - A[i]) == 1 && sign != new_sign) {
                    longest_run++;
                }
                sign = new_sign;
            }
        }

        max = Math.max(longest_run, max);
        return max;

    }

    public int getSign(int a, int b) {
        return a > b ? 1 : -1;
    }
}
