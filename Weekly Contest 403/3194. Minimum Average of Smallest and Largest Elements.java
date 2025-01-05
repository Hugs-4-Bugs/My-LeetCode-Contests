import java.util.Arrays;

class Solution {
    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        double minAverage = Double.MAX_VALUE;
        
        while (left < right) {
            int minElement = nums[left];
            int maxElement = nums[right];
            double average = (minElement + maxElement) / 2.0;
            minAverage = Math.min(minAverage, average);
            left++;
            right--;
        }
        
        return minAverage;
    }
}
