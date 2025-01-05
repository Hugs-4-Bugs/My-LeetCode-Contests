class Solution {
    int[] dp = new int[1001];
    int solve(int[] prices, int i){
        if(i >= prices.length) return 0;
        if(dp[i] != 0) return dp[i];
        int res = Integer.MAX_VALUE;
        for(int j = i+1; j <= 2*i + 2; ++j) {
            res = Math.min(res, solve(prices, j));
        }
        return dp[i] = res + prices[i];
    }
    public int minimumCoins(int[] prices) {
        return solve(prices, 0);
    }
}
