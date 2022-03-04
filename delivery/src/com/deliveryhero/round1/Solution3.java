package com.deliveryhero;

import java.util.Stack;


/**
 * @author sanray on 3/1/2022
 */
public class Solution3 {

    public static void main(String[] args) {
        Solution3 sol3 = new Solution3();
        System.out.println(sol3.solution3("ABAD"));
        //System.out.println(sol3.solution2("ABAD"));
        //System.out.println(sol3.solution("ACCD"));
        System.out.println(sol3.solution3("ACCD"));
        System.out.println(sol3.solution3("ACD"));
        System.out.println(sol3.solution3("ACBD"));
    }
    public int solution3(String S) {
        // write your code in Java 8
        if (S.length() <= 1) {
            return S.length();
        }

        Stack<Character> stack = new Stack<>();
        for (int i = S.length() - 1; i >= 0; i--) {
            Character currFish = S.charAt(i);

            Character stackFish;
            if (stack.size() > 0) {
                stackFish = stack.peek();
                if (currFish == 'A' && (stackFish == 'B' || stackFish == 'C')) {
                    stack.pop();
                } else if (currFish == 'B' && (stackFish == 'C' || stackFish == 'D')) {
                    stack.pop();
                } else if (currFish == 'C' && stackFish == 'D') {
                    stack.pop();
                }
            }
            stack.push(currFish);
        }

        return stack.size();
    }
    public int solution(String S) {
        // write your code in Java 8
        if (S.length() <= 1) {
            return S.length();
        }
        StringBuilder buf = new StringBuilder();
        buf.append(S.charAt(0));
        for (int i = 1; i < S.length(); i++) {
            if (buf.charAt(buf.length() - 1) == 'A' && (S.charAt(i) == 'B' || S.charAt(i) == 'C')) {
                continue;
            } else if (buf.charAt(buf.length() - 1) == 'B' && (S.charAt(i) == 'C' || S.charAt(i) == 'D')) {
                continue;
            } else if (buf.charAt(buf.length() - 1) == 'C' && (S.charAt(i) == 'D')) {
                continue;
            } else {
                buf.append(S.charAt(i));
            }
        }
        System.out.println(buf);
        return buf.length();
    }

    public int solution2(String S) {
        // write your code in Java 8
        if (S.length() <= 1) {
            return S.length();
        }
        StringBuilder buf = new StringBuilder();
        buf.append(S.charAt(S.length() - 1));
        for (int i = S.length() - 2; i >= 0; ) {
            boolean eaten = false;
            if ((S.charAt(i) == 'A') && (buf.charAt(buf.length() - 1) == 'B' || buf.charAt(buf.length() - 1) == 'C')) {
                buf.deleteCharAt(buf.length() - 1);
                buf.append(S.charAt(i));
                eaten = true;
            } else if ((S.charAt(i) == 'B') && (buf.charAt(buf.length() - 1) == 'C'
                    || buf.charAt(buf.length() - 1) == 'D')) {

                buf.deleteCharAt(buf.length() - 1);
                buf.append(S.charAt(i));
                eaten = true;
            } else if ((S.charAt(i) == 'C') && buf.charAt(buf.length() - 1) == 'D') {
                buf.deleteCharAt(buf.length() - 1);
                buf.append(S.charAt(i));
                eaten = true;
            }
            if (!eaten) {
                buf.append(S.charAt(i));
            } else {
                i--;
            }
        }
        System.out.println(buf.reverse());
        return buf.length();
    }


}
