class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int ans = 0;
        int cost = 1;
        while (n >= 8) {
            ans += 8 * cost;
            n -= 8;
            cost++;
        }
        ans += n * cost;
        return ans;
    }
}