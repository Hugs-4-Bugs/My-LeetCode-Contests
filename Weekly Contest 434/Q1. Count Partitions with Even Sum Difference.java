class Solution {
    public int countPartitions(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0, count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            int rightSum = totalSum - leftSum;
            if ((leftSum - rightSum) % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
