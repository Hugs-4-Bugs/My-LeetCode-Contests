// this code is passing only 456 / 677 testcases




import java.util.*;

class Solution {
    private Map<Integer, List<int[]>> graph;
    private int[] nums;
    private int maxLength;
    private int minNodes;
    
    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        int n = nums.length;
        this.nums = nums;
        this.graph = new HashMap<>();
        
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});
        }
        
        this.maxLength = 0;
        this.minNodes = Integer.MAX_VALUE;
        
        dfs(0, -1, new HashMap<>(), 0, 0);
        
        return new int[]{maxLength, minNodes};
    }
    
    private void dfs(int node, int parent, Map<Integer, Integer> countMap, int pathLength, int nodeCount) {
        countMap.put(nums[node], countMap.getOrDefault(nums[node], 0) + 1);
        nodeCount++;
        
        int duplicateCount = 0;
        for (int val : countMap.values()) {
            if (val > 1) duplicateCount++;
        }
        
        if (duplicateCount <= 1) {
            if (pathLength > maxLength) {
                maxLength = pathLength;
                minNodes = nodeCount;
            } else if (pathLength == maxLength) {
                minNodes = Math.min(minNodes, nodeCount);
            }
        }
        
        for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            int next = neighbor[0], weight = neighbor[1];
            if (next != parent) {
                dfs(next, node, new HashMap<>(countMap), pathLength + weight, nodeCount);
            }
        }
    }
}
