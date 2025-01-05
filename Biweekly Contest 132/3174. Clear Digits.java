class Solution {
    public String clearDigits(String s) {
        // Convert the string to a StringBuilder for easier manipulation
        StringBuilder sb = new StringBuilder(s);

        // Iterate through the string until there are no more digits
        while (true) {
            int digitIndex = findFirstDigit(sb);
            if (digitIndex == -1) break; // No more digits found

            // Find the closest non-digit character to the left
            int closestNonDigitIndex = findClosestNonDigitToLeft(sb, digitIndex);
            if (closestNonDigitIndex != -1) {
                // Remove the closest non-digit character
                sb.deleteCharAt(closestNonDigitIndex);
                // Adjust the digitIndex since we've modified the string
                digitIndex--;
            }

            // Remove the digit
            sb.deleteCharAt(digitIndex);
        }

        return sb.toString();
    }

    // Helper method to find the first digit in the StringBuilder
    private int findFirstDigit(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            if (Character.isDigit(sb.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    // Helper method to find the closest non-digit character to the left of a given index
    private int findClosestNonDigitToLeft(StringBuilder sb, int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (!Character.isDigit(sb.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.clearDigits("abc")); // Output: "abc"
        System.out.println(solution.clearDigits("cb34")); // Output: ""
        System.out.println(solution.clearDigits("a1b2c3")); // Output: ""
        System.out.println(solution.clearDigits("ab12c34d")); // Output: "d"
        System.out.println(solution.clearDigits("1a2b3c")); // Output: ""
    }
}
