class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007L;

        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long total = 0;

            for (long x : dp) {
                total = (total + x) % MOD;
            }
            dp[idx] = (total + 1) % MOD;
        }

        long ans = 0;

        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}