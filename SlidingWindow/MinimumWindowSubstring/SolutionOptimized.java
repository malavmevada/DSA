class Solution {
    public String SolutionOptimized(String s, String t) {
        // Create two HashMaps to store the character count for each string
        Map<Character,Integer> sMap = new HashMap<>();
        Map<Character,Integer> tMap = new HashMap<>();

        // Fill tMap with the character count for each character in string t
        for(char c: t.toCharArray()){
            tMap.put(c,tMap.getOrDefault(c,0) + 1);
        }

        // Initialize variables for the sliding window
        int tSize = tMap.size(),sSize = 0;
        int left = 0,minLeft = -1,minRight=-1,result = Integer.MAX_VALUE;

        // Loop through each character in string s
        for(int right=0;right<s.length();right++){
            char curr = s.charAt(right);

            // Update sMap with the character count for the current character
            sMap.put(curr,sMap.getOrDefault(curr,0) + 1);

            // Check if the current character is in tMap, and if its count matches in sMap
            if(tMap.containsKey(curr) && tMap.get(curr).intValue() == sMap.get(curr).intValue()){
                sSize++; // Increment the size of the window
            }

            // While the window contains all characters from tMap
            while(left <= right && sSize==tSize){
                // Update the minimum window if the current window is smaller
                if(right-left+1 < result){
                    minLeft = left;
                    minRight = right;
                    result = right-left+1;
                }

                // Remove the left-most character from sMap and move the left pointer to the right
                char leftCurr = s.charAt(left);
                sMap.put(leftCurr,sMap.get(leftCurr) - 1);
                left++;

                // Check if the removed character was in tMap, and if its count is less than in sMap
                if(tMap.containsKey(leftCurr) && sMap.get(leftCurr).intValue() < tMap.get(leftCurr).intValue()){
                    sSize--; // Decrement the size of the window
                }
            }
        }

        // Return the minimum window found, or an empty string if no window was found
        return result == Integer.MAX_VALUE ? "" : s.substring(minLeft,minRight+1);
    }
}