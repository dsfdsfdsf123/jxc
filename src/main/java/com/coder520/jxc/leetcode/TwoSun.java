package com.coder520.jxc.leetcode;

/**
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * @author liugang
 * @create 2018/12/19 23:47
 **/
public class TwoSun {

    public int[] twoSum(int[] nums,int target){
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]==target-nums[i]){
                    return new int[] {i,j};
                }
            }
        }
        throw  new IllegalArgumentException("No two sum solution");
    }
}
