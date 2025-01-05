import java.util.*;

class Solution {
    public int maxGoodNumber(int[] nums) {
        // List to store all permutations of nums
        List<List<Integer>> permutations = new ArrayList<>();
        // Generate all permutations of nums
        permute(nums, 0, permutations);
        
        int maxNumber = 0; // Store the maximum number
        
        // Iterate over all permutations
        for (List<Integer> perm : permutations) {
            StringBuilder binaryConcat = new StringBuilder();
            
            // Convert each number in the permutation to binary and concatenate
            for (int num : perm) {
                binaryConcat.append(Integer.toBinaryString(num));
            }
            
            // Convert the concatenated binary string to a decimal number
            int decimalValue = Integer.parseInt(binaryConcat.toString(), 2);
            
            // Update maxNumber if we found a larger value
            maxNumber = Math.max(maxNumber, decimalValue);
        }
        
        return maxNumber;
    }
    
    // Helper method to generate all permutations of nums
    private void permute(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                permute(nums, start + 1, result);
                swap(nums, start, i); // Backtrack
            }
        }
    }
    
    // Helper method to swap elements in an array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
