import java.util.HashMap;

class Solution {
    public int getLargestOutlier(int[] nums) {
        // Step 1: Compute the total sum of the array
        int sumNums = 0;
        for (int num : nums) {
            sumNums += num;
        }

        // Step 2: Create a frequency map of the numbers
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 3: Iterate through the numbers and check each as the outlier
        int largestOutlier = Integer.MIN_VALUE;
        for (int num : nums) {
            int remainingSum = sumNums - num;
            if (remainingSum % 2 == 0) {
                int candidateSum = remainingSum / 2;
                
                // If candidate sum exists and is not the same number as the current number
                if (freqMap.containsKey(candidateSum) && (candidateSum != num || freqMap.get(candidateSum) > 1)) {
                    // Update the largest outlier
                    largestOutlier = Math.max(largestOutlier, num);
                }
            }
        }

        return largestOutlier; // Return the largest outlier
    }
}
