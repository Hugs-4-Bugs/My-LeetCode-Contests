class Solution {
    private static final int MOD = 1000000007;
    private int[] f = new int[100005], g = new int[100005], h = new int[100005], a = new int[100005];
    private int n;

    private int add(int x, int y) {
        return (x + y >= MOD) ? (x + y - MOD) : (x + y);
    }

    private int mul(int x, int y) {
        return (int) ((1L * x * y) % MOD);
    }

    public int sumOfGoodSubsequences(int[] nums) {
        n = nums.length;
        System.arraycopy(nums, 0, a, 1, n);
        
        // Initialize the array f with zeros
        f = new int[100005];
        
        for (int i = 1; i <= n; i++) {
            g[i] = add(1, f[a[i] + 1]);
            if (a[i] >= 1) {
                g[i] = add(g[i], f[a[i] - 1]);
            }
            f[a[i]] = add(f[a[i]], g[i]);
        }
        
        // Re-initialize the array f with zeros
        f = new int[100005];
        
        for (int i = n; i >= 1; i--) {
            h[i] = add(1, f[a[i] + 1]);
            if (a[i] >= 1) {
                h[i] = add(h[i], f[a[i] - 1]);
            }
            f[a[i]] = add(f[a[i]], h[i]);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = add(ans, mul(a[i], mul(g[i], h[i])));
        }
        return ans;
    }
}













// class Solution {
//     public static int sumOfGoodSubsequences(int[] nums) {
//         int MOD = 1000000007;
//         long totalSum = 0;
//         int n = nums.length;

//         // Calculate the contribution of each element
//         for (int i = 0; i < n; i++) {
//             // Each element appears in 2^(n-1) subsequences
//             long contribution = (long) nums[i] * (1L << (n - 1)) % MOD; // 2^(n-1)
//             totalSum = (totalSum + contribution) % MOD;
//         }

//         return (int) totalSum;
//     }
// }











//  class Solution {
//     public int sumOfGoodSubsequences(int[] nums) {
//         long totalSum = 0;
//         int n = nums.length;

//         // Calculate the sum of the array elements
//         for (int num : nums) {
//             totalSum += num;
//         }
//         // Each element contributes to 2^(n-1) subsequences
//         long subsequenceCount = 1L << (n - 1); // This is 2^(n-1)

//         // Total sum of all subsequences
//         return (int)(totalSum * subsequenceCount);
//     }
// }





// class Solution {
//     private static final int MOD = 1000000007;

//     public int sumOfGoodSubsequences(int[] nums) {
//         int sum = 0;
//         for (int num : nums) {
//             sum = (sum + num) % MOD;
//         }

//         return (sum * power(2, nums.length - 1)) % MOD;
//     }

//     private int power(int base, int exp) {
//         int result = 1;
//         while (exp > 0) {
//             if (exp % 2 == 1) {
//                 result = (result * base) % MOD;
//             }
//             base = (base * base) % MOD;
//             exp /= 2;
//         }
//         return result;
//     }

//     public static void main(String[] args) {
//         Solution solution = new Solution();
//         int[] nums = {1, 2, 1};
//         System.out.print(solution.sumOfGoodSubsequences(nums));
//     }
// }
