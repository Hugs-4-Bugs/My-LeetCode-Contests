import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE; // To track the minimum sum greater than 0
        boolean found = false;         // To check if any valid subarray is found

        // Iterate over possible lengths of the subarray
        for (int length = l; length <= r; length++) {
            int windowSum = 0;

            // Calculate the sum of the first subarray of this length
            for (int i = 0; i < length; i++) {
                windowSum += nums.get(i);
            }

            // Check if the initial window meets the condition
            if (windowSum > 0) {
                minSum = Math.min(minSum, windowSum);
                found = true;
            }

            // Slide the window over the array
            for (int i = length; i < n; i++) {
                windowSum += nums.get(i) - nums.get(i - length);
                if (windowSum > 0) {
                    minSum = Math.min(minSum, windowSum);
                    found = true;
                }
            }
        }

        // If no valid subarray is found, return -1
        return found ? minSum : -1;
    }
}
