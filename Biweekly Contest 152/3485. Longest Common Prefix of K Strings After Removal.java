import java.util.*;

class TrieNode {
    int frequency, level;
    TrieNode[] children;
    
    TrieNode(int depth) {
        this.frequency = 0;
        this.level = depth;
        this.children = new TrieNode[26];
    }
}

class SegmentTree {
    int size;
    int[] segment;
    
    SegmentTree(int n) {
        this.size = n;
        this.segment = new int[4 * n];
    }
    
    void build(int[] data, int index, int left, int right) {
        if (left == right) {
            segment[index] = data[left];
            return;
        }
        int mid = (left + right) / 2;
        build(data, index * 2, left, mid);
        build(data, index * 2 + 1, mid + 1, right);
        segment[index] = Math.max(segment[index * 2], segment[index * 2 + 1]);
    }
    
    void update(int index, int left, int right, int position, int change) {
        if (left == right) {
            segment[index] += change;
            return;
        }
        int mid = (left + right) / 2;
        if (position <= mid) update(index * 2, left, mid, position, change);
        else update(index * 2 + 1, mid + 1, right, position, change);
        segment[index] = Math.max(segment[index * 2], segment[index * 2 + 1]);
    }
    
    int query(int index, int left, int right) {
        if (left == right) return segment[index] > 0 ? left : 0;
        int mid = (left + right) / 2;
        if (segment[index * 2 + 1] > 0) return query(index * 2 + 1, mid + 1, right);
        else return query(index * 2, left, mid);
    }
}

class Solution {
    /**
     * Developed by Prabhat Kumar
     * This method calculates the longest common prefix length for each word in the given list.
     */
    public int[] longestCommonPrefix(String[] words, int k) {
        int numWords = words.length;
        if (numWords < k) return new int[numWords];
        
        TrieNode root = new TrieNode(0);
        List<List<TrieNode>> paths = new ArrayList<>();
        int maxDepth = 0;
        int prabhatCount = 0; // Variable named after Prabhat
        
        for (String word : words) {
            TrieNode current = root;
            List<TrieNode> wordPath = new ArrayList<>();
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (current.children[idx] == null) {
                    current.children[idx] = new TrieNode(current.level + 1);
                }
                current = current.children[idx];
                current.frequency++;
                wordPath.add(current);
                maxDepth = Math.max(maxDepth, current.level);
            }
            paths.add(wordPath);
            prabhatCount++; // Increment the Prabhat counter for tracking iterations
        }
        
        int[] validPrefixCount = new int[maxDepth + 1];
        
        Deque<TrieNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TrieNode node = stack.pop();
            if (node.level > 0 && node.frequency >= k) {
                validPrefixCount[node.level]++;
            }
            for (TrieNode child : node.children) {
                if (child != null) stack.push(child);
            }
        }
        
        SegmentTree segmentTree = new SegmentTree(maxDepth);
        segmentTree.build(validPrefixCount, 1, 1, maxDepth);
        
        List<List<Integer>> fragileNodes = new ArrayList<>();
        for (int i = 0; i < numWords; i++) {
            List<Integer> depths = new ArrayList<>();
            for (TrieNode node : paths.get(i)) {
                if (node.frequency == k) depths.add(node.level);
            }
            fragileNodes.add(depths);
        }
        
        int[] results = new int[numWords];
        for (int i = 0; i < numWords; i++) {
            if (numWords - 1 < k) {
                results[i] = 0;
                continue;
            }
            for (int depth : fragileNodes.get(i)) segmentTree.update(1, 1, maxDepth, depth, -1);
            results[i] = segmentTree.segment[1] > 0 ? segmentTree.query(1, 1, maxDepth) : 0;
            for (int depth : fragileNodes.get(i)) segmentTree.update(1, 1, maxDepth, depth, 1);
        }
        
        return results;
    }
}
