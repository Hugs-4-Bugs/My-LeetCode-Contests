import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] invocation : invocations) {
            int a = invocation[0];
            int b = invocation[1];
            graph.get(a).add(b);
        }

        // Step 2: Find all suspicious methods using DFS
        Set<Integer> suspicious = new HashSet<>();
        dfs(graph, k, suspicious);

        // Step 3: Identify any non-suspicious methods that invoke suspicious methods
        Set<Integer> nonSuspiciousInvokers = new HashSet<>();
        for (int[] invocation : invocations) {
            if (!suspicious.contains(invocation[0]) && suspicious.contains(invocation[1])) {
                nonSuspiciousInvokers.add(invocation[0]);
            }
        }

        // Step 4: If there are non-suspicious invokers, return all methods
        if (!nonSuspiciousInvokers.isEmpty()) {
            List<Integer> allMethods = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                allMethods.add(i);
            }
            return allMethods;
        }

        // Step 5: Collect all remaining methods (non-suspicious)
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious.contains(i)) {
                remaining.add(i);
            }
        }

        return remaining;
    }

    private void dfs(List<List<Integer>> graph, int method, Set<Integer> suspicious) {
        suspicious.add(method);
        for (int neighbor : graph.get(method)) {
            if (!suspicious.contains(neighbor)) {
                dfs(graph, neighbor, suspicious);
            }
        }
    }
}
