class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        long[][][] dp = new long[m][n][3];

        // Initialize first cell
        dp[0][0][0] = coins[0][0];
        dp[0][0][1] = coins[0][0] < 0 ? 0 : coins[0][0];
        dp[0][0][2] = coins[0][0] < 0 ? 0 : coins[0][0];

        // Initialize first row
        for (int j = 1; j < n; j++) {
            for (int k = 0; k < 3; k++) {
                dp[0][j][k] = dp[0][j-1][k] + coins[0][j];
                if (coins[0][j] < 0 && k > 0) {
                    dp[0][j][k] = Math.max(dp[0][j][k], dp[0][j-1][k-1]);
                }
            }
        }

        // Initialize first column
        for (int i = 1; i < m; i++) {
            for (int k = 0; k < 3; k++) {
                dp[i][0][k] = dp[i-1][0][k] + coins[i][0];
                if (coins[i][0] < 0 && k > 0) {
                    dp[i][0][k] = Math.max(dp[i][0][k], dp[i-1][0][k-1]);
                }
            }
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    // From top
                    long topVal = dp[i-1][j][k] + coins[i][j];
                    if (coins[i][j] < 0 && k > 0) {
                        topVal = Math.max(topVal, dp[i-1][j][k-1]);
                    }
                    
                    // From left
                    long leftVal = dp[i][j-1][k] + coins[i][j];
                    if (coins[i][j] < 0 && k > 0) {
                        leftVal = Math.max(leftVal, dp[i][j-1][k-1]);
                    }
                    
                    dp[i][j][k] = Math.max(topVal, leftVal);
                }
            }
        }

        // Return max value
        long maxCoins = Long.MIN_VALUE;
        for (int k = 0; k < 3; k++) {
            maxCoins = Math.max(maxCoins, dp[m-1][n-1][k]);
        }

        return (int)maxCoins;
    }
}©leetcode
