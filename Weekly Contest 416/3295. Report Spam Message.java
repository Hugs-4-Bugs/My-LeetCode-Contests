class Solution {
    public boolean reportSpam(String[] message, String[] bannedWords) {
    HashSet<String> bannedSet = new HashSet<>();
        for (String word : bannedWords) {
            bannedSet.add(word);
        }
        
        int count = 0; // Counter to track matching banned words
        
        // Iterate through the message array
        for (String word : message) {
            // Check if the word exists in the bannedSet
            if (bannedSet.contains(word)) {
                count++;
            }
            // If two words are found in bannedWords, return true (spam detected)
            if (count >= 2) {
                return true;
            }
        }
        
        // If less than two banned words are found, return false (not spam)
        return false;
    }
}
