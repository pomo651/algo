class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    int minSize;
    int maxSize;
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // init maxHeap
        minSize = 0;
        maxSize = 0;
    }
    
    public void addNum(int num) {
        if(minSize == 0) {
            minHeap.offer(num);
            minSize++;
            return;
        }
        if(maxSize == 0) {
            if(num < minHeap.peek()) {
                maxHeap.offer(num);
            } else {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }
            maxSize++;
            return;
        }
        
        if(num < minHeap.peek()) {
            maxHeap.offer(num);
            maxSize++;
        } else {
            minHeap.offer(num);
            minSize++;
        }
        
        balance();
    }
    
    private void balance() {
        if(Math.abs(minSize - maxSize) <= 1) return;
        
        if(maxSize > minSize) {
            minHeap.offer(maxHeap.poll());
            minSize++;
            maxSize--;
        } else {
            maxHeap.offer(minHeap.poll());
            maxSize++;
            minSize--;
        }
    }
     
    public double findMedian() {
        if(maxSize > minSize) return maxHeap.peek();
        if(minSize > maxSize) return minHeap.peek();
        double a = minHeap.peek();
        double b = maxHeap.peek();
        return (a + b) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */