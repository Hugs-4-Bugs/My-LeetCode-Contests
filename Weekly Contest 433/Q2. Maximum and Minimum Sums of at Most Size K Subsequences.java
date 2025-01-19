import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int minMaxSums(int[] nums, int k) {
        Arrays.sort(nums);  // Sort the array
        
        int n = nums.length;
        long totalSum = 0;
        
        // Precompute factorials and inverse factorials for combination calculation
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        fact[0] = invFact[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        
        invFact[n] = modInverse(fact[n], MOD);
        for (int i = n - 1; i > 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
        
        // Iterate over all possible subsequence lengths from 1 to k
        for (int length = 1; length <= k; length++) {
            for (int i = 0; i < n; i++) {
                // Calculate combinations for choosing length-1 elements from the left and right of nums[i]
                long leftSubsequences = binomial(i, length - 1, fact, invFact);
                long rightSubsequences = binomial(n - i - 1, length - 1, fact, invFact);
                
                // Add the contributions of current element as min and max
                totalSum = (totalSum + nums[i] * leftSubsequences) % MOD;
                totalSum = (totalSum + nums[i] * rightSubsequences) % MOD;
            }
        }
        
        return (int) totalSum;
    }

    // Function to compute nCr % MOD
    private long binomial(int n, int r, long[] fact, long[] invFact) {
        if (r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    // Function to compute modular inverse using Fermat's Little Theorem
    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    // Modular exponentiation
    private long power(long base, int exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exp /= 2;
        }
        return result;
    }
}
©leetcode
