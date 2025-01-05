class Solution {
    public long validSubstringCount(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (m > n) return 0;

        HashMap<Character, Integer> count2 = new HashMap<>();
        HashMap<Character, Integer> count1 = new HashMap<>();

        for (char c : word2.toCharArray()) {
            count2.put(c, count2.getOrDefault(c, 0) + 1);
        }

        long result = 0;
        int required = count2.size();
        int formed = 0;
        int left = 0;

        for (int right = 0; right < n; ++right) {
            char c = word1.charAt(right);
            count1.put(c, count1.getOrDefault(c, 0) + 1);

            if (count2.containsKey(c) && count1.get(c).intValue() == count2.get(c).intValue()) {
                formed++;
            }

            while (formed == required) {
                result += (n - right);
                char leftChar = word1.charAt(left);
                count1.put(leftChar, count1.get(leftChar) - 1);
                
                if (count2.containsKey(leftChar) && count1.get(leftChar) < count2.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }

        return result;
    }
}
