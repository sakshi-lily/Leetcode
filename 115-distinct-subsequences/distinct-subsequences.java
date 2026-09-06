class Solution {
    public int numDistinct(String s, String t) {
        int sourceLength = s.length();
        int targetLength = t.length();
        int[][] dp = new int[sourceLength + 1][targetLength + 1];
        for (int i = 0; i <= sourceLength; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= sourceLength; i++) {
            for (int j = 1; j <= targetLength; j++) {
                dp[i][j] = dp[i - 1][j];              
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[sourceLength][targetLength];
    }
}
