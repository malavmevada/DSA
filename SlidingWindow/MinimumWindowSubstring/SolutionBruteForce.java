class SolutionBruteForce {
    public String minWindow(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        String res = "";
        int min = Integer.MAX_VALUE;
        
        // Loop through all possible substrings of string s
        for (int i = 0; i < lenS; i++) {
            for (int j = i; j < lenS; j++) {
                String sub = s.substring(i, j+1);
                // Check if the substring contains all characters in string t
                if (containsAll(sub, t)) {
                    // Update the minimum substring if the current substring is smaller
                    if (sub.length() < min) {
                        res = sub;
                        min = sub.length();
                    }
                }
            }
        }
        
        return res;
    }
    
    // Helper function to check if a substring contains all characters in a string
    private boolean containsAll(String sub, String t) {
        // Initialize an array to keep track of the character count for each character in string t
        int[] count = new int[128];
        for (char c : t.toCharArray()) {
            count[c]++;
        }
        // Decrement the count for each character in the substring
        for (char c : sub.toCharArray()) {
            count[c]--;
        }
        // If any character count in the count array is positive, the substring does not contain all characters in string t
        for (int i = 0; i < 128; i++) {
            if (count[i] > 0) {
                return false;
            }
        }
        return true;
    }
}