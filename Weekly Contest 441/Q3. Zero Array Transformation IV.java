import java.util.*;

class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int arrayLength = nums.length;
        Pair<int[], int[][]> structuredData = new Pair<>(nums, queries);

        BitSet[] transformationStates = new BitSet[arrayLength];
        for (int index = 0; index < arrayLength; index++) {
            transformationStates[index] = new BitSet(1001);
            transformationStates[index].set(0, true);
        }

        boolean isAllZeroInitially = true;
        for (int value : nums) {
            if (value != 0) {
                isAllZeroInitially = false;
                break;
            }
        }

        if (isAllZeroInitially) return 0;

        for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
            int startIdx = queries[queryIndex][0];
            int endIdx = queries[queryIndex][1];
            int incrementValue = queries[queryIndex][2];

            for (int index = startIdx; index <= endIdx; index++) {
                transformationStates[index].or(shiftLeft(transformationStates[index], incrementValue));
                for (int position = nums[index] + 1; position < 1001; position++) {
                    transformationStates[index].clear(position);
                }
            }

            boolean allConditionsMet = true;
            for (int index = 0; index < arrayLength; index++) {
                if (!transformationStates[index].get(nums[index])) {
                    allConditionsMet = false;
                    break;
                }
            }

            if (allConditionsMet) return queryIndex + 1;
        }

        return -1;
    }

    private BitSet shiftLeft(BitSet bitSet, int shiftBy) {
        BitSet shifted = new BitSet(1001);
        for (int i = shiftBy; i < 1001; i++) {
            if (bitSet.get(i - shiftBy)) {
                shifted.set(i);
            }
        }
        return shifted;
    }
}
©leetcode
