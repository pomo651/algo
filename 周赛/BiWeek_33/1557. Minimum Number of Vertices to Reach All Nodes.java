// LC_1557: https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/

// 解题思路:
// 1.其实我都不知道这题想问啥，对于edge<from, to>, 如果一个vertex出现在to的位置上，那么它一定不需要出现在res里面
// 2.那只用简单走一遍，把出现在to位置上的都排除掉，剩下的vertex就是都需要放入res中的了

class Solution {
  public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
      List<Integer> res = new ArrayList<>();
      boolean[] reachable = new boolean[n];
  
      for(List<Integer> edge: edges) {
          reachable[edge.get(1)] = true;
      }
      
      for(int i = 0; i < n; i++) {
          if(!reachable[i]) {
              res.add(i);
          }
      }
      
      return res;
  }
}