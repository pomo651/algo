// Question: 1293. Shortest Path in a Grid with Obstacles Elimination
// https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/

// 解题思路:
// 1.凡是2D Matrix涉及到 最短路径的 问题，坚定用BFS, 肯定是时间复杂度最小的
// 2.回顾下 基本的 2D BFS, 需要一个 boolean[][] seen; 来判断(r, c)是否之前遍历过
//   结合到本题，需要记录额外参数o(当前路径上遇到的Obstacles), 只要变成int[][] seen即可;
// 3.BFS的steps可以通过每层for(i: 0 -> size) { ... }; 后面step++即可知道
// 4.结合本题, 只用看到最后点时候 seen[m - 1][n - 1] <= k 表示能通过eleminate k个obstacles到达

// Solution:
class Solution {
  int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 上，右，下，左
  public int shortestPath(int[][] grid, int k) {
      // initials
      int[][] seen = new int[grid.length][grid[0].length];
      for(int i = 0; i < grid.length; i++) {
          Arrays.fill(seen[i], Integer.MAX_VALUE);
      }
      
      Queue<int[]> queue = new LinkedList<>();
      int step = 0; 
      seen[0][0] = 0;
      queue.offer(new int[]{0, 0, 0});
      
      while(!queue.isEmpty()) {
          // *** 注意 此处必须先拿到 size = queue.size(); ***
          // *** 这种会出错: for(int i = 0; i < queue.size(); i++); ***
          int size = queue.size();
          for(int i = 0; i < size; i++) {
              int[] cur = queue.poll();
              int x = cur[0];
              int y = cur[1];
              
              // 如果满足条件，则找到返回
              if(x == grid.length - 1 && y == grid[0].length - 1) {
                  return step;
              }
              
              // 向4个方向BFS
              for(int[] dir: dirs) {
                  int r = x + dir[0];
                  int c = y + dir[1];
                  
                  // 首先判断是否越界，如果越界，直接continue剪枝
                  if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) continue;
                  
                  // 否则没越界，算到(r, c)遇到的Obstacles的数量 
                  int o = cur[2] + grid[r][c];
                  // 如果到(r, c)比之前碰到的seen[r][c]还大，就没必要走了
                  // 或者此时o 已经 > k,也没必要走了
                  if(o >= seen[r][c] || o > k) continue;
                  
                  // 否则(r,c)就是满足条件的，更新seen, 更新q 
                  seen[r][c] = o;
                  queue.offer(new int[]{r, c, o});
              }
          }
          step++;
      }
      
      return -1;
  }
}