class Solution {
    public int maximumLengthSubstring(String s) {
        int[] charFrequency = new int[26];
        int maxLength = 0;
      
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int currentCharIndex = s.charAt(right) - 'a';
          
            charFrequency[currentCharIndex]++;
                      while (charFrequency[currentCharIndex] > 2) {
                int leftCharIndex = s.charAt(left) - 'a';
                charFrequency[leftCharIndex]--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
      
        return maxLength;
    }
}
