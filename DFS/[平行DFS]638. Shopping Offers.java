// 题目: https://leetcode.com/problems/shopping-offers/

// 解题思路:
// 1.典型的平行DFS, 每次碰到一个curSpecial, 你就有2种情况去接下来的DFS
// 情况1: 如果当前curSpecial可以被使用，那么就使用，并且更新needs和expense 
// 情况2: 反正我就不使用当前curSpecial

// 唯一思考情况，是否有必要用 【分治】 思维，那么dfs 需要 return int; 分治肯定是也能做的，但是没有必要，直接平行DFS也可以

class Solution {
  public int min;
  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
      
      min = expenseWithoutSpeical(price, needs);
      
      dfs(price, special, needs, 0, 0);
      
      return min;
  }
  
  private void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos, int expense) {
      if(expense >= min) return;
      
      if(pos == special.size()) {
          if (expense + expenseWithoutSpeical(price, needs) < min ){
              min = expense + expenseWithoutSpeical(price, needs);
          }
          return;
      }
      
      // 看是否能用当前special
      List<Integer> curSpecial = special.get(pos);
      if(canUseSpecial(curSpecial, needs)) {
          List<Integer> newNeeds = new ArrayList<>();
          for(int i = 0; i < needs.size(); i++) {
              newNeeds.add(needs.get(i) - curSpecial.get(i));
          }
          
          dfs(price, special, newNeeds, pos, expense + curSpecial.get(curSpecial.size() - 1));
      }
      // 不管能否用curSpec, 都需要看如果不用当前spec会如何
      dfs(price, special, needs, pos + 1, expense);
  }
  
  private boolean canUseSpecial(List<Integer> spec, List<Integer> needs) {
      for(int i = 0; i < needs.size(); i++) {
          if(spec.get(i) > needs.get(i)) {
              return false;
          }
      }
      return true;
  }
  
  private int expenseWithoutSpeical(List<Integer> price, List<Integer> needs) {
      int res = 0;
      for(int i = 0; i < price.size(); i++) {
          res += price.get(i) * needs.get(i);
      }
      return res;
  }
}