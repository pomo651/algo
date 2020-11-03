// 解题思路:
// 1.主要在于理解Dijkstra的本质: 本题dist[x][y] 可以看做点(x, y) 到(0, 0)的 最短路径
// 2.这里的最短路径不是 edge上的 值的和，而是 Math.max(dist[r][c], Math.abs(heights[nr][nc] - Math.abs[r][c]));
// 3.一旦这样理解这个题目，套上Dijkstra 用 minHeap的模板就很快出来了

class Solution {
  public int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 上 右 下 左
  public int minimumEffortPath(int[][] heights) {
      int m = heights.length;
      int n = heights[0].length;
      int[][] dist = new int[m][n];
      for(int i = 0; i < m; i++) {
          Arrays.fill(dist[i], Integer.MAX_VALUE);
      }
      PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
      
      minHeap.offer(new int[]{0, 0, 0});
      
      while(!minHeap.isEmpty()) {
          int[] cur = minHeap.poll();
          int d = cur[0], r = cur[1], c = cur[2];
          
          // 如果遍历到底
          if(r == m - 1 && c == n - 1) return d;
          
          for(int[] dir: dirs) {
              int nr = r + dir[0];
              int nc = c + dir[1];
              if(nr >= 0 && nr < m && nc >= 0 && nc < n) {
                  int newDist = Math.max(d, Math.abs(heights[r][c] - heights[nr][nc]));
                  if(newDist < dist[nr][nc]) {
                      dist[nr][nc] = newDist;
                      minHeap.offer(new int[]{newDist, nr, nc});    
                  }
              }
          }
      }
      
      return 0;
  }
}