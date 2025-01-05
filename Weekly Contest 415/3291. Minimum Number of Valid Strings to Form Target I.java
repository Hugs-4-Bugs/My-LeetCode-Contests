// import java.util.HashSet;
// import java.util.Set;

// class Solution {
//     public int minValidStrings(String[] words, String target) {
//         TrieNode root = new TrieNode();
//         for (String word : words) {
//             for (int len = 1; len <= word.length(); len++) {
//                 insert(root, word.substring(0, len));
//             }
//         }


//         int n = target.length();
//         int[] dp = new int[n + 1];
//         for (int i = 1; i <= n; i++) {
//             dp[i] = Integer.MAX_VALUE;
//         }
//         dp[0] = 0; 

       
//         for (int i = 0; i < n; i++) {
//             if (dp[i] == Integer.MAX_VALUE) continue;

//             TrieNode node = root;
//             for (int j = i; j < n; j++) {
//                 char c = target.charAt(j);
//                 if (!node.children.containsKey(c)) break;
//                 node = node.children.get(c);
//                 if (node.isEndOfWord) {
//                     dp[j + 1] = Math.min(dp[j + 1], dp[i] + 1);
//                 }
//             }
//         }

//         return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
//     }

//     private void insert(TrieNode root, String word) {
//         TrieNode node = root;
//         for (char c : word.toCharArray()) {
//             if (!node.children.containsKey(c)) {
//                 node.children.put(c, new TrieNode());
//             }
//             node = node.children.get(c);
//         }
//         node.isEndOfWord = true;
//     }

//     private class TrieNode {
//         Map<Character, TrieNode> children = new HashMap<>();
//         boolean isEndOfWord = false;
//     }
// }















import java.util.*;

class Solution {
    public int minValidStrings(String[] words, String target) {
        TrieNode root = new TrieNode();
        
        // Build the Trie
        for (String word : words) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
                node.isEndOfWord = true;
            }
        }

        // BFS to find the minimum number of valid strings
        int n = target.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;

            TrieNode node = root;
            for (int j = i; j < n; j++) {
                char c = target.charAt(j);
                if (!node.children.containsKey(c)) break;
                node = node.children.get(c);
                if (node.isEndOfWord) {
                    dp[j + 1] = Math.min(dp[j + 1], dp[i] + 1);
                }
            }
        }

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }
}
