class Solution {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        long totalCost = 0;

        for (int i = 0; i < s.length(); i++) {
            char source = s.charAt(i);
            char target = t.charAt(i);

            if (source == target) {
                continue;
            }

            int start = source - 'a';
            int end = target - 'a';

            // Calculate forward and backward costs
            int forwardShifts = (end - start + 26) % 26;
            int backwardShifts = (start - end + 26) % 26;

            long forwardCost = 0;
            long backwardCost = 0;

            // Compute forward cost
            for (int j = 0; j < forwardShifts; j++) {
                forwardCost += nextCost[(start + j) % 26];
            }

            // Compute backward cost
            for (int j = 0; j < backwardShifts; j++) {
                backwardCost += previousCost[(start - j + 26) % 26];
            }

            // Add the minimum of forward or backward costs
            totalCost += Math.min(forwardCost, backwardCost);
        }

        return totalCost;
    }
}
