package com.coder520.jxc.leetcode;

/**
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 * 是否是回文，例如121
 * @author liugang
 * @create 2018/12/19 23:59
 **/
public class PalindromeNumber {

    //思路，可以弄成char数组
    public static boolean isPalindromeNumber(int x){
        if (x<0){
            return false;
        }
        String str_x = x+"";
        char[] s = str_x.toCharArray();
        int left = 0,right = str_x.length()-1;
        while (left<right){
            //判断相等，不相等返回false，哈哈
            if (s[right] != s[left]){
                return false;
            }else {
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(isPalindromeNumber(121));
    }

}
