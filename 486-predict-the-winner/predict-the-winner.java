class Solution {

    int[][] memo;
    int[] nums;

    public boolean predictTheWinner(int[] nums) {

        int n = nums.length;

        this.nums = nums;
        memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], Integer.MIN_VALUE);
        }

        return dfs(0, n - 1) >= 0;
    }

    private int dfs(int left, int right) {

        if (left == right)
            return nums[left];

        if (memo[left][right] != Integer.MIN_VALUE)
            return memo[left][right];

        int pickLeft = nums[left] - dfs(left + 1, right);

        int pickRight = nums[right] - dfs(left, right - 1);

        memo[left][right] = Math.max(pickLeft, pickRight);

        return memo[left][right];
    }
}