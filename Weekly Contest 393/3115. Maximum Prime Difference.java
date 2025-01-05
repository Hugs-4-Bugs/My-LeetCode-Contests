import java.util.*;

class Solution {
    // Helper function to check if a number is prime
    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
    
    public int maximumPrimeDifference(int[] nums) {
        int minIndex = Integer.MAX_VALUE;
        int maxIndex = Integer.MIN_VALUE;
        int maxDifference = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i])) {
                minIndex = Math.min(minIndex, i);
                maxIndex = Math.max(maxIndex, i);
                maxDifference = Math.max(maxDifference, maxIndex - minIndex);
            }
        }
        
        return maxDifference;
    }
}
