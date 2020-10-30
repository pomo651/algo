class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // corner case
        if(matrix == null || matrix.length == 0 ||  matrix[0].length== 0) {
            return false;
        }
        // initial
        int m = matrix.length;
        int n = matrix[0].length;
        int y = 0;
        int x = m - 1;
        //loop from left bottom to  right top
        while(x >= 0 && y < n) {
            if(matrix[x][y] == target) {
                return true;
            }else if(matrix[x][y] < target) {
                y++;
            }
            //matrix[x][y] > target
            else{
                x--;
            }
        }
        return false;    
    }
}