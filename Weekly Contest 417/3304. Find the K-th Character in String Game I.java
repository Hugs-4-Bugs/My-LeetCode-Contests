

class Solution {
    public char kthCharacter(int k) {
        StringBuilder word = new StringBuilder("a");
        int length = 1;

        while (length < k) {
            StringBuilder next = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char nextChar = (char) ((word.charAt(i) - 'a' + 1) % 26 + 'a');
                next.append(nextChar);
            }
            word.append(next);
            length = word.length();
        }
        
        return word.charAt(k - 1);
    }
}
