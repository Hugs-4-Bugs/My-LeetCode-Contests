class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        // Step 1: Create a count array to keep track of number frequencies
        int n = nums.length - 2;  // Since the array has length n + 2
        int[] count = new int[n];
        
        // Step 2: Count the occurrences of each number
        for (int num : nums) {
            count[num]++;
        }
        
        // Step 3: Identify the two numbers that appear more than once
        int[] result = new int[2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > 1) {
                result[index++] = i;
            }
        }
        
        return result;
    }
}
