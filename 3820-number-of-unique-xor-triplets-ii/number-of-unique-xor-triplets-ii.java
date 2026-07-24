class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        final int MAX = 2048;

        // suffixPair[i][x] = true if there exists j,k with
        // i <= j <= k < n and nums[j] ^ nums[k] == x
        boolean[][] suffixPair = new boolean[n + 1][MAX];

        for (int i = n - 1; i >= 0; i--) {
            System.arraycopy(suffixPair[i + 1], 0, suffixPair[i], 0, MAX);

            for (int k = i; k < n; k++) {
                suffixPair[i][nums[i] ^ nums[k]] = true;
            }
        }

        boolean[] seen = new boolean[MAX];

        for (int i = 0; i < n; i++) {
            for (int x = 0; x < MAX; x++) {
                if (suffixPair[i][x]) {
                    seen[nums[i] ^ x] = true;
                }
            }
        }

        int ans = 0;
        for (boolean b : seen) {
            if (b) ans++;
        }

        return ans;
    }
}