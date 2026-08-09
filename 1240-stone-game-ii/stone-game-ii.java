class Solution {
    private int[] prefixSum;
    private Integer[][] dp;
    private int n;
    public int stoneGameII(int[] piles) {
        n = piles.length;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + piles[i];
        }
        dp = new Integer[n][n + 1];
        return dfs(0, 1);
    }
    /**
     * Calculate the maximum stones the current player can get
     * @param index Current starting index in piles array
     * @param M Current value of M (player can take 1 to 2*M piles)
     * @return Maximum stones the current player can collect from this state
     */
    private int dfs(int index, int M) {
        if (2 * M >= n - index) {
            return prefixSum[n] - prefixSum[index];
        }
        if (dp[index][M] != null) {
            return dp[index][M];
        }   
        int maxStones = 0;
        for (int X = 1; X <= 2 * M; X++) {
            int currentPlayerStones = prefixSum[n] - prefixSum[index] - 
                                      dfs(index + X, Math.max(M, X));
            maxStones = Math.max(maxStones, currentPlayerStones);
        }
            dp[index][M] = maxStones;
        return maxStones;
    }
}