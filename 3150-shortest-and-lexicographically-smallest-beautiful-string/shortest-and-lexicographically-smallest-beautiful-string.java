class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int stringLength = s.length();
        String result = "";
      
        for (int startIndex = 0; startIndex < stringLength; ++startIndex) {
            for (int endIndex = startIndex + k; endIndex <= stringLength; ++endIndex) {
                String currentSubstring = s.substring(startIndex, endIndex);
              
                int onesCount = 0;
                for (char character : currentSubstring.toCharArray()) {
                    onesCount += character - '0'; 
                }
              
                
                if (onesCount == k && 
                    (result.isEmpty() ||                                  
                     endIndex - startIndex < result.length() ||           
                     (endIndex - startIndex == result.length() &&         
                      currentSubstring.compareTo(result) < 0))) {           
                    result = currentSubstring;
                }
            }
        }
      
        return result;
    }
}
