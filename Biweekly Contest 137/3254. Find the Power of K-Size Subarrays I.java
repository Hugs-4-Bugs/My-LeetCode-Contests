// import java.util.Arrays;

public class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] results = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            if (isConsecutiveSorted(nums, i, i + k - 1)) {
                results[i] = nums[i + k - 1];
            } else {
                results[i] = -1;
            }
        }

        return results;
    }

    private boolean isConsecutiveSorted(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums[i] + 1 != nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[] nums1 = {1, 2, 3, 4, 3, 2, 5};
        int k1 = 3;
        System.out.println(Arrays.toString(solution.resultsArray(nums1, k1))); // Output: [3, 4, -1, -1, -1]

        // Test case 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int k2 = 4;
        System.out.println(Arrays.toString(solution.resultsArray(nums2, k2))); // Output: [-1, -1]

        // Test case 3
        int[] nums3 = {3, 2, 3, 2, 3, 2};
        int k3 = 2;
        System.out.println(Arrays.toString(solution.resultsArray(nums3, k3))); // Output: [-1, 3, -1, 3, -1]

        // Additional hidden test cases
        int[] nums4 = {5, 6, 7, 8, 9};
        int k4 = 5;
        System.out.println(Arrays.toString(solution.resultsArray(nums4, k4))); // Output: [9]

        int[] nums5 = {10, 12, 14, 16};
        int k5 = 2;
        System.out.println(Arrays.toString(solution.resultsArray(nums5, k5))); // Output: [-1, -1, -1]

        int[] nums6 = {1, 1, 2, 3, 4, 5, 6, 7};
        int k6 = 3;
        System.out.println(Arrays.toString(solution.resultsArray(nums6, k6))); // Output: [-1, -1, 4, 5, 6, 7]
    }
}
