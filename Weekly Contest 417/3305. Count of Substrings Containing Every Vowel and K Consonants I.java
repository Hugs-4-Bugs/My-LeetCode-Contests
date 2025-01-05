
class Solution {
    public int countOfSubstrings(String word, int k) {
        int n = word.length();
        int count = 0;

        for (int start = 0; start < n; start++) {
            int[] vowelCount = new int[5]; // a, e, i, o, u
            int consonantCount = 0;

            for (int end = start; end < n; end++) {
                char c = word.charAt(end);
                if (isVowel(c)) {
                    vowelCount[vowelIndex(c)]++;
                } else {
                    consonantCount++;
                }

                if (hasAllVowels(vowelCount) && consonantCount == k) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private int vowelIndex(char c) {
        switch (c) {
            case 'a': return 0;
            case 'e': return 1;
            case 'i': return 2;
            case 'o': return 3;
            case 'u': return 4;
            default: return -1;
        }
    }

    private boolean hasAllVowels(int[] vowelCount) {
        for (int count : vowelCount) {
            if (count == 0) return false;
        }
        return true;
    }
}
