class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;

        // Count characters in s.
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // A palindrome can have at most one character
        // with an odd frequency.
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) != 0) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Only half the characters are needed to construct
        // the palindrome.
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        char[] ans = new char[n];

        /*
         * Try to make the left half equal to target's
         * left half for as long as possible.
         */
        int pos = 0;

        while (pos < half) {
            int c = target.charAt(pos) - 'a';

            if (freq[c] == 0) {
                break;
            }

            ans[pos] = target.charAt(pos);
            freq[c]--;
            pos++;
        }

        /*
         * Case 1:
         * We managed to make the entire left half equal
         * to target's left half.
         *
         * The palindrome might still be greater because
         * of its middle/right half.
         */
        if (pos == half) {
            buildPalindrome(ans, half, middle);

            String candidate = new String(ans);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Case 2:
         * We need to make the palindrome greater.
         *
         * Backtrack to find the rightmost position where
         * we can put a character strictly greater than
         * target[pos].
         */
        while (true) {

            if (pos < half) {
                int minChar = target.charAt(pos) - 'a' + 1;

                // Find the smallest available character
                // greater than target[pos].
                for (int c = minChar; c < 26; c++) {

                    if (freq[c] == 0) {
                        continue;
                    }

                    // Choose this character.
                    ans[pos] = (char) ('a' + c);
                    freq[c]--;

                    // Fill everything after pos with the
                    // smallest possible characters.
                    int index = pos + 1;

                    for (int x = 0; x < 26; x++) {
                        for (int k = 0; k < freq[x]; k++) {
                            ans[index++] = (char) ('a' + x);
                        }
                    }

                    buildPalindrome(ans, half, middle);

                    return new String(ans);
                }
            }

            /*
             * No larger character works here.
             * Move one position to the left and restore
             * the character we had matched with target.
             */
            if (pos == 0) {
                return "";
            }

            pos--;

            int c = target.charAt(pos) - 'a';
            freq[c]++;
        }
    }

    private void buildPalindrome(char[] ans, int half, int middle) {

        // Put middle character in the center.
        if (middle != -1) {
            ans[half] = (char) ('a' + middle);
        }

        // Mirror the left half onto the right half.
        for (int i = 0; i < half; i++) {
            ans[ans.length - 1 - i] = ans[i];
        }
    }
}
