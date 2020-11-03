// 参考: * https://www.youtube.com/watch?v=pVfj6mxhdMw 【必看，讲的特别清楚】

// 参考 - 当前: https://mrfzh.github.io/2020/03/06/Dijkstra%EF%BC%88%E8%BF%AA%E6%9D%B0%E6%96%AF%E7%89%B9%E6%8B%89%EF%BC%89%E7%AE%97%E6%B3%95%E7%9A%84-java-%E5%AE%9E%E7%8E%B0/
// 参考 - 2: https://blog.csdn.net/mayifan_blog/article/details/85684916
// 参考 - 为什么遍历 n - 1次: https://www.cnblogs.com/jason2003/p/7222182.html
// 参考 - 打印路径: http://www.jiajiajia.club/official/weblog/28
// 参考 - 使用 PriorityQueue: https://www.youtube.com/watch?v=qCka7w93QJc


// 本质: 一种贪心算法，每次找res[i]最小的一个点，res[i]表示开始点到i的最短路径
// 限制:
// 时间复杂度: N^2, 如果用 PriorityQueue能优化到 NlogN 
// 内容就2个点:
// STEP_1.将点分成2个阵营，已经遍历过的合集S[]和尚未遍历的合集U[]
// STEP_2.每次从S[]中找到一个上次用的点，然后去U里面找一圈最短路径点p; 找到P后，p放进S[], 然后U[]中剩余点更新 res[i]的值

public class Test {
  public static void main(String[] args) {
      int MAX = Integer.MAX_VALUE;    // 无法到达时距离设为 Integer.MAX_VALUE
      int[][] weight={ // 用2D Matrix 来表示一个 Graph 常见用法
              {0,1,12,MAX,MAX,MAX},
              {MAX,0,9,3,MAX,MAX},
              {MAX,MAX,0,MAX,5,MAX},
              {MAX,MAX,4,0,13,15},
              {MAX,MAX,MAX,MAX,0,4},
              {MAX,MAX,MAX,MAX,MAX,0}
      };
      int start = 0;  // 选择出发点
      System.out.println(Arrays.toString(solution(weight,start)));
  }

  private static int[] solution(int[][] weight, int start) {
      boolean[] visit = new boolean[weight.length]; // 标记某节点是否被访问过
      int[] res = new int[weight.length];     // 记录 start 点到各点的最短路径长度
      for (int i = 0; i < res.length; i++) {
          res[i] = weight[start][i];
      }

      // 查找 n - 1 次（n 为节点个数），每次确定一个节点
      // 因为一次遍历出的不一定都是最优解: https://www.cnblogs.com/jason2003/p/7222182.html 里面有说明
      for(int i = 1; i < weight.length; i++) {
          int min = Integer.MAX_VALUE;
          int p = 0;
          // 找出一个未标记的离出发点最近的节点: => 即上面 STEP_1
          for(int j = 0; j < weight.length; j++){
              if(j != start && !visit[j] && res[j] < min){
                  min = res[j];
                  p = j;
              }
          }
          // 标记该节点为已经访问过
          visit[p] = true;

          // 找到next点P后，此刻立刻更新 U[] 剩余点到开始点的距离的值
          for (int j = 0; j < weight.length; j++){
              if (j == p || weight[p][j] == Integer.MAX_VALUE) {  // p 点不能到达 j
                  continue;
              }
              if (res[p] + weight[p][j] < res[j]){
                  res[j] = res[p] + weight[p][j];  //更新最短路径
              }
          }
      }

      return res;
  }
}