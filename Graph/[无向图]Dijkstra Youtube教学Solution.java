// 参考: * https://www.youtube.com/watch?v=pVfj6mxhdMw 【必看，讲的特别清楚】
// *** 上面这个教程很清楚了，然后需要的能力是将视频里面的思路转换成Code

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static int[] parent;
  public static void main(String[] args) {
    int MAX = Integer.MAX_VALUE;
    int[][] weight={
              {0,6,MAX,1,MAX},
              {6,0,5,2,2},
              {MAX,5,0,MAX,5},
              {1,2,MAX,0,1},
              {MAX,2,5,1,0},
    };
    parent = new int[weight.length];
    int start = 0;
    System.out.println(Arrays.toString(solution(weight, start)));
    System.out.println(Arrays.toString(parent));
  };
       
  private static int[] solution(int[][] weight, int start) {
    boolean[] seen = new boolean[weight.length];
    int[] res = new int[weight.length];
    
    for(int i = 0; i < weight.length; i++) {
      res[i] = weight[start][i];
    }
    
    seen[start] = true;
    
    for(int i = 1; i < weight.length; i++) {
      
      // 从 U[] 中找到和当前相邻，并且shortest的路径的点
      int min = Integer.MAX_VALUE;
      int p = 0;
      
      for(int j = 0; j < weight.length; j++) {
        if(j != start && !seen[j] && res[j] < min) {
          min = res[j];
          p = j;
        }
      }
      
      // 然后找到了点p, 然后更新U[k] 所有的res[k]
      seen[p] = true;
      for(int j = 0; j < weight.length; j++) {
        if( j == p || weight[p][j] == Integer.MAX_VALUE) continue;
        if(res[p] + weight[p][j] < res[j]) {
          res[j] = res[p] + weight[p][j];
          parent[j] = p;
        }
      }
    }
    
    return res;
  }                    
}
