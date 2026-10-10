class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] differences = new int[n];
        long totalSum = 0;
        int maxDifference = 0;
        int totalOperations = k1 + k2;

        for (int i = 0; i < n; i++) {
            differences[i] = Math.abs(nums1[i] - nums2[i]);
            totalSum += differences[i];
            maxDifference = Math.max(maxDifference, differences[i]);
        }

        if (totalSum <= totalOperations) {
            return 0;
        }

        int left = 0;
        int right = maxDifference - 1;
        int firstTrueIndex = maxDifference;  

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long operationsNeeded = 0;

            for (int value : differences) {
                operationsNeeded += Math.max(value - mid, 0);
            }

            if (operationsNeeded <= totalOperations) {
                firstTrueIndex = mid;
                right = mid - 1;  
            } else {
                left = mid + 1;
            }
        }

        int optimalThreshold = firstTrueIndex;

        for (int i = 0; i < n; i++) {
            totalOperations -= Math.max(0, differences[i] - optimalThreshold);
            differences[i] = Math.min(differences[i], optimalThreshold);
        }

        for (int i = 0; i < n && totalOperations > 0; i++) {
            if (differences[i] == optimalThreshold) {
                totalOperations--;
                differences[i]--;
            }
        }

        long sumOfSquares = 0;
        for (int value : differences) {
            sumOfSquares += (long) value * value;
        }

        return sumOfSquares;
    }
}
