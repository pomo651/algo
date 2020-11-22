class Solution {
  public static void main(String[] args) {
    int[] nums = {9, 7, 3, 6, 4, 5};
    
    sort(nums);
    
    System.out.println(Arrays.toString(nums));
  }
  
  public static int[] sort(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
  }
  
  public static void mergeSort(int[] nums, int lo, int hi) {
    if (lo >= hi) return;
    
    int mid = lo + (hi -lo) / 2;
    
    mergeSort(nums, lo, mid);
    mergeSort(nums, mid + 1, hi);
    
    merge(nums, lo, mid, hi);
    return;
  }
  
  public static void merge(int[] nums, int lo, int mid, int hi) {
    int i = lo;
    int j = mid + 1;
    int index = 0;
    int[] a = new int[hi - lo + 1];
    
    while(i <= mid && j <= hi) {
      if(nums[i] <= nums[j]) {
        a[index++] = nums[i++];
      } else {
        a[index++] = nums[j++];
      }
    }
    
    while(i <= mid) {
      a[index++] = nums[i++]; 
    }
    
    while(j <= hi) {
      a[index++] = nums[j++];
    }
    
    System.arraycopy(a, 0, nums, lo, hi - lo + 1);
  }
}