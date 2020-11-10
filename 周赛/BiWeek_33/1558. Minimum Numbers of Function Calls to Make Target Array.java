// LC_1558: https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array/

// 解题思路:
// 1.就是典型的递归咯，我们想尽可能的nums[]去divide by 2, 如果可以divide by 2, 那么divide by 2后得到一新Array后
// return res = cur + recursion(newArray)就可以了； 就是之前一直说的recursion中的nexRes的概念
// 2.最后处理好当前corner case就可以了，也就是当前每个num都不能divide by 2, 此时算一个值可以直接返回

class Solution {
  public int minOperations(int[] nums) {
      int res = 0;
      if(!canDivideTwo(nums)) {
          return calculate(nums);
      }
      
      // Make it all even
      for(int i = 0; i < nums.length; i++) {
          if(nums[i] % 2 != 0) {
              nums[i] -= 1;
              res += 1;
          }
      }
      
      // Divide all by 2 
      for(int i = 0; i < nums.length; i++) {
          nums[i] /= 2;
      }
      
      return res + 1 + minOperations(nums);
  }
  
  private boolean canDivideTwo(int[] nums) {
      for(int num : nums) {
          if(num >= 2) {
              return true;
          }
      }
      return false;
  }
  
  private int calculate(int[] nums) {
      int res = 0;
      for(int num: nums) {
          res += num;
      }
      return res;
  }
}