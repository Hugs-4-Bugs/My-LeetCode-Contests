class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        int[] used = new int[201]; // Maps numbers from -100 to 100 using index shift
        int totalSum = 0;
        boolean hasPositive = false;
        int maxElement = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > maxElement) {
                maxElement = num;
            }
            // If the number is positive and hasn't been used yet, add to sum
            if (num > 0 && used[num + 100] == 0) {
                totalSum += num;
                used[num + 100] = 1;
                hasPositive = true;
            }
        }

        // If we found positive numbers, return their sum; otherwise, return the maximum element
        return hasPositive ? totalSum : maxElement;
    }
}
©leetcode
