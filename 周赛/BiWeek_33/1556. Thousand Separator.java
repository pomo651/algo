// LC.1556: https://leetcode.com/problems/thousand-separator/

// 解题思路
// 1.Easy题目，就不多数了，基本就是 n / 1000 和 n % 1000的迭代，然后处理好special case就好了

class Solution {
  public String thousandSeparator(int n) {
      String res = "";
      
      if(n == 0) return "0";
      
      while(n != 0) {
          res = format(n % 1000, n / 1000 == 0) + "." + res;
          n /= 1000;
      }
      
      // remove leading 0 and last "."
      return res.substring(0, res.length() - 1);
  }
  
  private String format(int n, boolean isLeading) {
      if(isLeading) {
          return n + "";
      }
      
      if(n > 100) {
          return n + "";
      } else if(n > 10) {
          return "0" + n;
      } else {
          return "00" + n;
      }
  }
}