class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int i = 0;

        while (i < target.length()) {
            int x = target.charAt(i) - 'a';

            if (cnt[x] > 0) {
                cnt[x]--;
                i++;
            } else {
                // Try a larger character here
                for (int c = x + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        cnt[c]--;
                        return target.substring(0, i)
                                + (char)('a' + c)
                                + build(cnt);
                    }
                }
                break;
            }
        }

        // No larger character at current position -> backtrack
        for (int p = i - 1; p >= 0; p--) {
            cnt[target.charAt(p) - 'a']++;

            int x = target.charAt(p) - 'a';

            for (int c = x + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    cnt[c]--;
                    return target.substring(0, p)
                            + (char)('a' + c)
                            + build(cnt);
                }
            }
        }

        return "";
    }

    private String build(int[] cnt) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 26; i++)
            while (cnt[i] > 0) {
                sb.append((char)('a' + i));
                cnt[i]--;
            }

        return sb.toString();
    }
}
