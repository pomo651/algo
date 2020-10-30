/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
  // corner
  if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    return false;
  }
  // initial
  let m = matrix.length;
  let n = matrix[0].length;
  var x = m - 1;
  var y = 0;

  // left bottom to right top
  while (x >= 0 && y <= n - 1) {
    if (matrix[x][y] == target) {
      return true;
    } else if (matrix[x][y] > target) {
      x--;
    } else {
      y++;
    }
  }
  return false;
};
