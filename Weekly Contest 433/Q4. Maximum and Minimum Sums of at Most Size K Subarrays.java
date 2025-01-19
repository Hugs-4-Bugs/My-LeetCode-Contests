// This code is giving TLE

class Solution {
    public long minMaxSubarraySum(int[] nums, int k) {
        long result = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int minElement = Integer.MAX_VALUE;
            int maxElement = Integer.MIN_VALUE;

            for (int j = i; j < Math.min(n, i + k); j++) {
                minElement = Math.min(minElement, nums[j]);
                maxElement = Math.max(maxElement, nums[j]);
                
                result += minElement + maxElement;
            }
        }
        
        return result;
    }
}
