import java.util.*;

class Solution {
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        // Build graphs for day 1 and day 2
        Map<String, List<Edge>> graph1 = buildGraph(pairs1, rates1);
        Map<String, List<Edge>> graph2 = buildGraph(pairs2, rates2);

        // Perform Bellman-Ford on Day 1 graph
        Map<String, Double> day1Amounts = bellmanFord(graph1, initialCurrency);

        // Prepare Day 2 starting amounts based on Day 1 results
        Map<String, Double> day2StartAmounts = new HashMap<>();
        for (String currency : day1Amounts.keySet()) {
            day2StartAmounts.put(currency, day1Amounts.get(currency));
        }

        // Perform Bellman-Ford on Day 2 graph
        Map<String, Double> day2Amounts = bellmanFord(graph2, day2StartAmounts);

        // Return the maximum amount of the initial currency
        return day2Amounts.getOrDefault(initialCurrency, 0.0);
    }

    private Map<String, List<Edge>> buildGraph(List<List<String>> pairs, double[] rates) {
        Map<String, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < pairs.size(); i++) {
            String start = pairs.get(i).get(0);
            String target = pairs.get(i).get(1);
            double rate = rates[i];

            graph.putIfAbsent(start, new ArrayList<>());
            graph.putIfAbsent(target, new ArrayList<>());

            graph.get(start).add(new Edge(target, rate));
            graph.get(target).add(new Edge(start, 1.0 / rate));
        }

        return graph;
    }

    private Map<String, Double> bellmanFord(Map<String, List<Edge>> graph, String startCurrency) {
        Map<String, Double> maxAmounts = new HashMap<>();
        maxAmounts.put(startCurrency, 1.0);

        boolean updated;
        for (int i = 0; i < graph.size(); i++) {
            updated = false;
            for (String currency : graph.keySet()) {
                if (!maxAmounts.containsKey(currency)) continue;

                double currentAmount = maxAmounts.get(currency);
                for (Edge edge : graph.get(currency)) {
                    double newAmount = currentAmount * edge.rate;
                    if (newAmount > maxAmounts.getOrDefault(edge.target, 0.0)) {
                        maxAmounts.put(edge.target, newAmount);
                        updated = true;
                    }
                }
            }
            if (!updated) break; // Early stopping if no changes
        }

        return maxAmounts;
    }

    private Map<String, Double> bellmanFord(Map<String, List<Edge>> graph, Map<String, Double> startAmounts) {
        Map<String, Double> maxAmounts = new HashMap<>(startAmounts);

        boolean updated;
        for (int i = 0; i < graph.size(); i++) {
            updated = false;
            for (String currency : graph.keySet()) {
                if (!maxAmounts.containsKey(currency)) continue;

                double currentAmount = maxAmounts.get(currency);
                for (Edge edge : graph.get(currency)) {
                    double newAmount = currentAmount * edge.rate;
                    if (newAmount > maxAmounts.getOrDefault(edge.target, 0.0)) {
                        maxAmounts.put(edge.target, newAmount);
                        updated = true;
                    }
                }
            }
            if (!updated) break; // Early stopping if no changes
        }

        return maxAmounts;
    }

    static class Edge {
        String target;
        double rate;

        Edge(String target, double rate) {
            this.target = target;
            this.rate = rate;
        }
    }
}
