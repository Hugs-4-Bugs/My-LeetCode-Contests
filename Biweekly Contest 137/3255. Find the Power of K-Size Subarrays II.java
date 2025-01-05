import java.util.*;

public class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] results = new int[n - k + 1];
        int[] consecutiveLength = new int[n];
        
        // Step 1: Precompute the length of consecutive sequences starting from each index
        consecutiveLength[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] + 1 == nums[i + 1]) {
                consecutiveLength[i] = consecutiveLength[i + 1] + 1;
            } else {
                consecutiveLength[i] = 1;
            }
        }

        // Step 2: Compute the power of each subarray
        for (int i = 0; i <= n - k; i++) {
            // Check if the current subarray of size k is consecutive and sorted
            if (consecutiveLength[i] >= k) {
                results[i] = nums[i + k - 1];  // The maximum element in the subarray
            } else {
                results[i] = -1;
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] nums1 = {1, 2, 3, 4, 3, 2, 5};
        int k1 = 3;
        System.out.println(Arrays.toString(solution.resultsArray(nums1, k1))); // Output: [3, 4, -1, -1, -1]

        // Example 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int k2 = 4;
        System.out.println(Arrays.toString(solution.resultsArray(nums2, k2))); // Output: [-1, -1]

        // Example 3
        int[] nums3 = {3, 2, 3, 2, 3, 2};
        int k3 = 2;
        System.out.println(Arrays.toString(solution.resultsArray(nums3, k3))); // Output: [-1, 3, -1, 3, -1]
    }
}
