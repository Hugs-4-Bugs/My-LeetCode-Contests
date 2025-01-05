class Solution {
    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length();
        int count = 0;
        
        // Iterate over all possible substrings
        for (int i = 0; i < n; i++) {
            int zeros = 0;
            int ones = 0;
            
            for (int j = i; j < n; j++) {
                // Count the number of 0s and 1s in the current substring
                if (s.charAt(j) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
                
                // Check the k-constraint
                if (zeros <= k || ones <= k) {
                    count++;
                } else {
                    // Break early if the current substring doesn't satisfy the k-constraint
                    break;
                }
            }
        }
        
        return count;
    }
}
