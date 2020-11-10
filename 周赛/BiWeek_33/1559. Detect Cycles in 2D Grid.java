// Leetcode_1559: https://leetcode.com/problems/detect-cycles-in-2d-grid/

// 解题思路:
// 1.典型的DFS，我们思路是 dfs如果能void就void, 不行就boolean, 不行再返回其他值
// 2.反正就是DFS想四周扩散，只要下一次遍历的值和当前值相同，就一直尝试扩散下去
// 3.只需要注意一个点，这里需要一个(pr, pc)的参考，表示我在当前的时候，不会向上一次过来的点的方向走，相当于每个点只能朝3个方向扩散

class Solution {
  private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 上 右 下 左
  private boolean found;
  public boolean containsCycle(char[][] grid) {
      if(grid.length == 0 || grid[0].length == 0) return false;
      
      int m = grid.length;
      int n = grid[0].length;
      boolean[][] seen = new boolean[m][n];
      found = false;    
      
      for(int i = 0; i < m; i++) {
          for(int j = 0; j < n; j++) {
              if(!seen[i][j] && !found) {
                  dfs(grid, seen, i, j, -1, -1);
              }
          }
      }
      
      return found;
  }
  
  private void dfs(char[][] grid, boolean[][] seen, int r, int c, int pr, int pc) {
      int m = grid.length;
      int n = grid[0].length;
      
      if(seen[r][c]) {
          found = true;
          return;
      }
      
      seen[r][c] = true;
      
      for(int[] dir: dirs) {
          int nr = r + dir[0];
          int nc = c + dir[1];
          
          if(nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == grid[r][c]) {
              if (nr != pr || nc != pc) { // 保证只能朝3个方向扩散
                  dfs(grid, seen, nr, nc, r, c);    
              }
          }
      }
  }
}