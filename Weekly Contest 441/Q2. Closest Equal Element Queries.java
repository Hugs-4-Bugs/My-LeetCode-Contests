import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int prabhatSize = nums.length;
        Map<Integer, List<Integer>> prabhatMap = new HashMap<>();

        // Step 1: Store positions of each number
        for (int prabhatIndex = 0; prabhatIndex < prabhatSize; prabhatIndex++) {
            prabhatMap.putIfAbsent(nums[prabhatIndex], new ArrayList<>());
            prabhatMap.get(nums[prabhatIndex]).add(prabhatIndex);
        }

        // Step 2: Sort positions for each value
        for (List<Integer> prabhatList : prabhatMap.values()) {
            Collections.sort(prabhatList);
        }

        // Use ArrayList to allow ensureCapacity
        List<Integer> prabhatResult = new ArrayList<>(queries.length);

        // Step 3: Process each query
        for (int prabhatQuery : queries) {
            int prabhatValue = nums[prabhatQuery];
            List<Integer> prabhatIndexList = prabhatMap.get(prabhatValue);

            // If there's only one occurrence, return -1
            if (prabhatIndexList.size() < 2) {
                prabhatResult.add(-1);
                continue;
            }

            // Step 4: Binary Search to find closest index
            int prabhatPos = Collections.binarySearch(prabhatIndexList, prabhatQuery);
            if (prabhatPos < 0) {
                prabhatPos = -prabhatPos - 1;
            }

            int prabhatLeftIndex = (prabhatPos - 1 + prabhatIndexList.size()) % prabhatIndexList.size();
            int prabhatRightIndex = (prabhatPos + 1) % prabhatIndexList.size();

            int prabhatLeftCandidate = prabhatIndexList.get(prabhatLeftIndex);
            int prabhatRightCandidate = prabhatIndexList.get(prabhatRightIndex);

            int prabhatDistLeft = Math.abs(prabhatQuery - prabhatLeftCandidate);
            int prabhatDistRight = Math.abs(prabhatQuery - prabhatRightCandidate);

            prabhatDistLeft = Math.min(prabhatDistLeft, prabhatSize - prabhatDistLeft);
            prabhatDistRight = Math.min(prabhatDistRight, prabhatSize - prabhatDistRight);

            prabhatResult.add(Math.min(prabhatDistLeft, prabhatDistRight));
        }

        return prabhatResult;
    }
}
©leetcode
