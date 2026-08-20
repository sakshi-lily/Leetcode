class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
      
        int[] firstArray = new int[n];
        int[] secondArray = new int[n];
      
        firstArray[0] = nums[0];
        secondArray[0] = nums[1];
      
        int lastIndexFirst = 0;
        int lastIndexSecond = 0;
      
        for (int currentIndex = 2; currentIndex < n; currentIndex++) {
            if (firstArray[lastIndexFirst] > secondArray[lastIndexSecond]) {
                lastIndexFirst++;
                firstArray[lastIndexFirst] = nums[currentIndex];
            } else {
                lastIndexSecond++;
                secondArray[lastIndexSecond] = nums[currentIndex];
            }
        }
      
        for (int index = 0; index <= lastIndexSecond; index++) {
            lastIndexFirst++;
            firstArray[lastIndexFirst] = secondArray[index];
        }
      
        return firstArray;
    }
}
