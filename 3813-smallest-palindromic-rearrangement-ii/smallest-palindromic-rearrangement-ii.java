class Solution {

    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder mid = new StringBuilder();

        int[] half = new int[26];
        int m = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                mid.append((char) ('a' + i));
            }
            half[i] = cnt[i] / 2;
            m += half[i];
        }

        if (countWays(half) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        while (m > 0) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    m--;
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + mid.toString() + right;
    }

    private long countWays(int[] freq) {

        int total = 0;

        for (int x : freq)
            total += x;

        long ans = 1;

        for (int x : freq) {

            if (x == 0)
                continue;

            ans = multiply(ans, comb(total, x));

            if (ans >= LIMIT)
                return LIMIT;

            total -= x;
        }

        return ans;
    }

    private long multiply(long a, long b) {

        if (a == 0 || b == 0)
            return 0;

        if (a >= LIMIT || b >= LIMIT)
            return LIMIT;

        if (a > LIMIT / b)
            return LIMIT;

        long res = a * b;

        return Math.min(res, LIMIT);
    }

    private long comb(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res >= LIMIT)
                return LIMIT;
        }

        return res;
    }
}