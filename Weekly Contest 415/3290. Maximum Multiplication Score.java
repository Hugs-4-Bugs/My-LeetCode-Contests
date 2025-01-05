class Solution {
    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        // dp[k] will store the maximum score using the first k elements from array a
        long[][] dp = new long[5][n + 1];  // We use 5 because we are processing 4 elements from a

        // Initialize dp with Long.MIN_VALUE for all k except for dp[0][i] because for 0 elements, the score is 0
        for (int k = 1; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = Long.MIN_VALUE;
            }
        }

        // Build the dp table
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= 4; k++) {
                if (i >= k) {
                    // Update dp[k][i] using dp[k-1][i-1]
                    dp[k][i] = Math.max(dp[k][i - 1], dp[k - 1][i - 1] + (long) a[k - 1] * b[i - 1]);
                }
            }
        }

        // The maximum score for using all 4 elements from array a is dp[4][n]
        return dp[4][n];
    }
}
