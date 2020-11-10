// Solution: https://leetcode.com/problems/delete-and-earn/

// 解题思路:
// 本题用到2种元题思路就很容易出来了:
// 1.桶排序，先用桶排序思想把nums[]给初始化处理下，得到每个元素的累加和sum[];
// 2.然后基本就是和house robber一个意思，选择当前sum, 就不能选择邻近的，于是2个dp[]就可以解决

class Solution {
  public int deleteAndEarn(int[] nums) {
      int N = 10000;
      int[] sum = new int[N + 1];
      
      for(int num: nums) {
          sum[num] += num;
      }
      
      int[] p = new int[N + 1]; // 包含当前cur的最大值，那么不能包含cur - 1;
      int[] q = new int[N + 1]; // 不包含当前cur的最大值，那么可以包含或者不包含cur - 1;
      p[1] = sum[1];
      
      for(int i = 2; i <= N; i++) {
          p[i] = q[i - 1] + sum[i]; // 包含当前i;
          q[i] = Math.max(q[i - 1], p[i - 1]); // 不包含当前i;
      }
      
      return Math.max(p[N], q[N]);
  }
}