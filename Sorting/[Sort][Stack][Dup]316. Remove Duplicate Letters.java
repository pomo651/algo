// Question: 316. Remove Duplicate Letters 
// https://leetcode.com/problems/remove-duplicate-letters/

// 解题思路:
// 1.其实还是先分析给的Example: "cbacdcbc": 明显我们需要一个东西知道c出现了几次，可能出现几次还不够，
// 我们需要知道c分别出现在了哪些pos; 于是乎，遍历第一次得到 lastOccurence[] 思路就出来了。
// 2.第二遍遍历，碰到c,就先放那；碰到b, 其实也只能放那，因为b不可能放到c前面；
// 碰到a, 明显需要先和前面的b去比，b可能出现在a后面，于是b肯定要拿走，就是典型的【跳栈】思维；
// 然后c也可能出现在a后面，于是c也要拿走，接连跳栈b和c后，a自然要入栈。通过这个分析，用Stack的思路自然就出来了。

// Solution:
class Solution {
  public String removeDuplicateLetters(String s) {
      if(s == null || s.length() == 0) return s; 
      
      // 先遍历一遍，知道lastOccurance[];
      int[] lastOccurence = new int[26];
      for(int i = 0; i < s.length(); i++) {
          char cur = s.charAt(i);
          lastOccurence[cur - 'a'] = i;
      }
      
      // 通过stack的方式，用当前char去和stack.peek.char比较
      // 只要(while) 当前char 比 stack.peek.char要小，并且后面还会出现，就pop()
      StringBuilder res = new StringBuilder();
      boolean[] used = new boolean[26];
      for(int i = 0; i < s.length(); i++) {
          char cur = s.charAt(i);
          
          // 条件1: 如果碰到用过的，就直接continue了，后面都不会跑的
          if(used[cur - 'a']) continue;
          
          while(res.length() > 0 && 
                cur < res.charAt(res.length() - 1) &&
                lastOccurence[res.charAt(res.length() - 1) - 'a'] > i) {
              // 满足条件，就pop()
              // 跳栈的时候自然更新used 
              used[res.charAt(res.length() - 1) - 'a'] = false;
              res.deleteCharAt(res.length() - 1);
          }
          
          // 入栈
          res.append(cur);
          used[cur - 'a'] = true;
      }
      
      return res.toString();
  }
}