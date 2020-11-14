// LC 1206. https://leetcode.com/problems/design-skiplist/

// 解题思路:
// 这题咋看比较难的,建议看下花花讲的: https://www.youtube.com/watch?v=783qX31AN08

class Skiplist {
  Node head;
  public Skiplist() {
      head = new Node(-1, null, null);
  }
  
  public boolean search(int target) {
      Node node = head; // pointer Node in top left layer 
      while(node != null) { 
          // Move to right in current layer
          while(node.right != null && node.right.value < target) { 
              node = node.right;
          }
          if(node.right != null && node.right.value == target) {
              return true;
          }
          
          // Move to next layer 
          node = node.down;
      }
      return false;
  }
  
  public void add(int num) {
      Stack<Node> stack = new Stack<>();;
      Node node = head;
      
      while(node != null) {
          // Move to right in current layer
          while(node.right != null && node.right.value < num) {
              node = node.right;
          }
          // 找到本层的后，将当前层的node放到nodes[]里面
          stack.push(node);
          
          // Move to next level 
          node = node.down;
      }
      
      boolean insert = true;
      Node down = null;
      // 
      while(insert && !stack.isEmpty()) {
          Node cur = stack.pop();
          // 创建一个新New Node 然后insert在cur level
          cur.right = new Node(num, cur.right, down);
          // 更新down 
          down = cur.right;
          
          insert = Math.random() < 0.25;
      }
  }
  
  public boolean erase(int num) {
      Node node = head;
      boolean found = false;
      
      while(node != null) {
          // Move to right in current layer
          while(node.right != null && node.right.value < num) {
              node = node.right;
          }
          // Check if we should delete found Node 
          if(node.right != null && node.right.value == num) {
              // Delete found node, delete本质就是移动link
              node.right = node.right.right;
              found = true;
          }
          
          // Move to next layer
          node = node.down;
      }
      return found;
  }
  
  public class Node {
      public int value;
      public Node right;
      public Node down;
      
      public Node(int val, Node right, Node down) {
          this.value = val;
          this.right = right;
          this.down = down;
      }
  }
}

/**
* Your Skiplist object will be instantiated and called as such:
* Skiplist obj = new Skiplist();
* boolean param_1 = obj.search(target);
* obj.add(num);
* boolean param_3 = obj.erase(num);
*/