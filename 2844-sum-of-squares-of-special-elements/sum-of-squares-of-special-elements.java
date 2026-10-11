class Solution {
    public int sumOfSquares(int[] nums) {
        int arrayLength = nums.length;
        int sumOfSquares = 0;
        for (int position = 1; position <= arrayLength; ++position) {
            if (arrayLength % position == 0) {
                int currentElement = nums[position - 1];
                sumOfSquares += currentElement * currentElement;
            }
        }
        return sumOfSquares;
    }
}
