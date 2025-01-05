import java.util.List;

class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
         
        // Edge case: If there are fewer than 2 elements, no valid adjacent subarrays can exist
        if (n < 2) return 0;

        // Step 1: Precompute the length of the longest increasing subarray starting at each index
        int[] inc = new int[n];
         
        // Initialize the dp array
        inc[n - 1] = 1; // Last element is trivially an increasing subarray of length 1
         
        // Fill in the `inc` array
        for (int i = n - 2; i >= 0; i--) {
            if (nums.get(i) < nums.get(i + 1)) {
                inc[i] = inc[i + 1] + 1; // Extend the increasing subarray
            } else {
                inc[i] = 1; // Reset to 1 if the sequence breaks
            }
        }

        // Step 2: Use binary search to find the largest k such that there are two adjacent subarrays of length k
        
/* `left = 1` and `right = n / 2` define the search range for the maximum possible length `k` of adjacent increasing subarrays using binary search. `left = 1` is the minimum possible subarray length, while `right = n / 2` ensures two subarrays of length `k` can fit in the list.

OR

`left = 1` starts with the smallest possible subarray size, and `right = n / 2` limits the search to sizes that fit two adjacent subarrays. It helps find the largest size `k` of increasing subarrays that can fit next to each other.
    */
        
        int left = 1, right = n / 2;
        int maxK = 0;

       while(left<=right){
           int mid = left + (right - left) / 2;
           boolean found = false;

            for (int i = 0; i <= n - 2 * mid; i++) {
                if (inc[i] >= mid && inc[i + mid] >= mid) {
                    found = true;
                    break;
                }
            }

            if (found) {
                maxK = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return maxK;
    }
}
