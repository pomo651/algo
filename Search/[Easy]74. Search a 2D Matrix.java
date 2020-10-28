class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
      // corner case 
      if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
      
      // initials
      int m = matrix.length;
      int n = matrix[0].length;
      int x = 0;
      int y = n - 1;
      
      while(x < m && y >= 0) {
          if(matrix[x][y] == target) {
              return true;
          } else if(matrix[x][y] < target) { // 说明在下边
              x++;
          } else {
              y--;
          }
      }
      
      return false;
  }
}