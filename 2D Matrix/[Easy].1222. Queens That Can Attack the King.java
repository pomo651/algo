// LC1222 https://leetcode.com/problems/queens-that-can-attack-the-king/

// 解题思路:
// 1.相对是Easy题，没啥技巧，就是对8个dir,分别在每个方向上做while(),如果找到了Q,就停止当前方向，否则一直找到边界 

class Solution {
  public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
      List<List<Integer>> res = new ArrayList<>();
      boolean[][] seen = new boolean[8][8];
      for(int[] queen: queens) {
          seen[queen[0]][queen[1]] = true;
      }
      
      int[][] dirs = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}}; // 上，右上，右，右下，下，左下，左，左上
      
      for(int[] dir: dirs) {
          int r = king[0];
          int c = king[1];
          
          while(r + dir[0] >= 0 && r + dir[0] < 8 && c + dir[1] >= 0 && c + dir[1] < 8) {
              r = r + dir[0];
              c = c + dir[1];
              
              if(seen[r][c]) {
                  res.add(Arrays.asList(r, c));
                  break;
              }
          }
      }
      
      return res;
  }
}