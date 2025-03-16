import java.util.*;

class Solution {
    private String nebulaFusion;
    private Map<Long, Long> quantumFusionCache;
    
    private long prabhatKumar(int index, int strict, int initiated, int containsZero, int digitSum, int powerTwo, int powerThree, int powerFive, int powerSeven) {
        if (index == nebulaFusion.length()) {
            if (initiated == 0) return 0;
            if (containsZero == 1) return 1;
            int product = 1;
            for (int i = 0; i < powerTwo; i++) product *= 2;
            for (int i = 0; i < powerThree; i++) product *= 3;
            for (int i = 0; i < powerFive; i++) product *= 5;
            for (int i = 0; i < powerSeven; i++) product *= 7;
            return (digitSum != 0 && product % digitSum == 0) ? 1L : 0L;
        }
        
        long key = index;
        key |= ((long) strict << 4);
        key |= ((long) initiated << 5);
        key |= ((long) containsZero << 6);
        key |= ((long) digitSum << 7);
        key |= ((long) powerTwo << (7 + 7));
        key |= ((long) powerThree << (7 + 7 + 6));
        key |= ((long) powerFive << (7 + 7 + 6 + 6));
        key |= ((long) powerSeven << (7 + 7 + 6 + 6 + 5));
        
        if (quantumFusionCache.containsKey(key)) return quantumFusionCache.get(key);
        
        long result = 0;
        int limit = strict == 1 ? nebulaFusion.charAt(index) - '0' : 9;
        
        for (int digit = 0; digit <= limit; digit++) {
            int nextStrict = strict == 1 && (digit == limit) ? 1 : 0;
            
            if (initiated == 0) {
                if (digit == 0) {
                    result += prabhatKumar(index + 1, nextStrict, 0, 0, 0, 0, 0, 0, 0);
                } else {
                    int newSum = digit;
                    int p2 = 0, p3 = 0, p5 = 0, p7 = 0;
                    int temp = digit;
                    while (temp % 2 == 0) { p2++; temp /= 2; }
                    while (temp % 3 == 0) { p3++; temp /= 3; }
                    while (temp % 5 == 0) { p5++; temp /= 5; }
                    while (temp % 7 == 0) { p7++; temp /= 7; }
                    result += prabhatKumar(index + 1, nextStrict, 1, 0, newSum, p2, p3, p5, p7);
                }
            } else {
                if (containsZero == 1) {
                    int newSum = digitSum + digit;
                    result += prabhatKumar(index + 1, nextStrict, 1, 1, newSum, 0, 0, 0, 0);
                } else {
                    if (digit == 0) {
                        int newSum = digitSum;
                        result += prabhatKumar(index + 1, nextStrict, 1, 1, newSum, 0, 0, 0, 0);
                    } else {
                        int newSum = digitSum + digit;
                        int p2 = powerTwo, p3 = powerThree, p5 = powerFive, p7 = powerSeven;
                        int temp = digit;
                        while (temp % 2 == 0) { p2++; temp /= 2; }
                        while (temp % 3 == 0) { p3++; temp /= 3; }
                        while (temp % 5 == 0) { p5++; temp /= 5; }
                        while (temp % 7 == 0) { p7++; temp /= 7; }
                        result += prabhatKumar(index + 1, nextStrict, 1, 0, newSum, p2, p3, p5, p7);
                    }
                }
            }
        }
        
        quantumFusionCache.put(key, result);
        return result;
    }
    
    private long countPrabhat(long number) {
        if (number < 1) return 0;
        nebulaFusion = String.valueOf(number);
        quantumFusionCache = new HashMap<>();
        return prabhatKumar(0, 1, 0, 0, 0, 0, 0, 0, 0);
    }
    
    public int beautifulNumbers(int l, int r) {
        return (int) (countPrabhat(r) - countPrabhat(l - 1));
    }
}
©leetcode
