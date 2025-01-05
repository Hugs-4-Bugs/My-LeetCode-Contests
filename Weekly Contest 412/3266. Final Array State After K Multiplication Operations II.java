
import java.math.BigInteger;

class Solution {

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        TreeMap<Long, TreeSet<Integer>> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent((long) nums[i], t -> new TreeSet<>()).add(i);
        }
        for (; k > 0 && map.firstKey() * multiplier <= map.lastKey(); k--) {
            map.computeIfAbsent(map.firstKey() * multiplier, t -> new TreeSet<>()).add(map.firstEntry().getValue().pollFirst());
            if (map.firstEntry().getValue().isEmpty()) {
                map.remove(map.firstKey());
            }
        }
        int v = BigInteger.valueOf(multiplier).modPow(BigInteger.valueOf(k / nums.length), BigInteger.valueOf(1000000007)).intValue(), r = k % nums.length;
        for (Map.Entry<Long, TreeSet<Integer>> entry : map.entrySet()) {
            for (int i : entry.getValue()) {
                nums[i] = (int) (entry.getKey() % 1000000007 * v % 1000000007 * (r-- > 0 ? multiplier : 1) % 1000000007);
            }
        }
        return nums;
    }
}
