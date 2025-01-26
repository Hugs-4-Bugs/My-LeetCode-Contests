import java.util.*;

class Solution {
    public int maxFrequency(int[] nums, int k) {
        int totalK = 0;
        
        // Count how many times k appears in the array
        for (int num : nums) {
            if (num == k) {
                totalK++;
            }
        }
        
        Set<Integer> xVals = new HashSet<>();
        
        // Create the set of possible values for x
        for (int num : nums) {
            xVals.add(k - num);
        }
        
        int maxCount = totalK; // At least the original count if we don't perform the operation
        
        for (int x : xVals) {
            int currentCandidate;
            
            if (x == 0) {
                currentCandidate = totalK;
            } else {
                int[] transformed = new int[nums.length];
                
                // Transform the array based on x and k
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == k - x) {
                        transformed[i] = 1;
                    } else if (nums[i] == k) {
                        transformed[i] = -1;
                    } else {
                        transformed[i] = 0;
                    }
                }
                
                // Find the maximum subarray sum using Kadane's algorithm
                int currentMax = kadane(transformed);
                currentCandidate = currentMax + totalK;
            }
            
            // Update maxCount with the maximum of currentCandidate and maxCount
            maxCount = Math.max(maxCount, currentCandidate);
        }
        
        return maxCount;
    }

    private int kadane(int[] arr) {
        if (arr.length == 0) return 0;
        
        int maxSoFar = arr[0];
        int currentMax = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        
        return maxSoFar;
    }
}
