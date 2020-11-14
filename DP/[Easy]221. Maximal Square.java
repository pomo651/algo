// LC221.https://leetcode.com/problems/maximal-square/

// 解题思路:
// 1.简单的DP,当前位置dp[i][j] 是由上，左，上左3个值决定的: dp[i][j] = fn(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
// 2.故意做成int[][] dp = new int[m+1][n+1];给左和上左一个padding

class Solution {
  public int maximalSquare(char[][] matrix) {
      if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
      
      int m = matrix.length;
      int n = matrix[0].length;
      int[][] dp = new int[m + 1][n + 1];
      int res = 0;
      
      for(int i = 1; i <= m; i++) {
          for(int j = 1; j <= n; j++) {
              if(matrix[i - 1][j - 1] == '0') {
                  dp[i][j] = 0;
              } else {
                  dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                  res = Math.max(res, dp[i][j] * dp[i][j]);
              }
          }
      }
      
      return res;
  }
}