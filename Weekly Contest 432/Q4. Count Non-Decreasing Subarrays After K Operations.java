// THIS CODE IS GIVING TIME LIMIT EXCEEDED ERROR SO OPTIMIZE BEFORE SUBMITTING 



import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long result = 0;

        for (int start = 0; start < n; ++start) {
            int operationsLeft = k;
            int maxElement = nums[start];

            for (int end = start; end < n; ++end) {
                if (nums[end] >= maxElement) {
                    maxElement = nums[end];
                } else {
                    int requiredOps = maxElement - nums[end];
                    if (requiredOps > operationsLeft) {
                        break;
                    }
                    operationsLeft -= requiredOps;
                }

                result++;
            }
        }

        return result;
    }

}
