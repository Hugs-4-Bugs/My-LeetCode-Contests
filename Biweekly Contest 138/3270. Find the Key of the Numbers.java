class Solution {
    public int generateKey(int num1, int num2, int num3) {
        // Convert numbers to strings and pad with leading zeros if necessary
        String str1 = String.format("%04d", num1);
        String str2 = String.format("%04d", num2);
        String str3 = String.format("%04d", num3);

        // Initialize result string
        StringBuilder result = new StringBuilder();

        // Iterate over characters of strings from left to right
        for (int i = 0; i < 4; i++) {
            // Find smallest digit among the three numbers at current position
            char minDigit = (char) Math.min(Math.min(str1.charAt(i), str2.charAt(i)), str3.charAt(i));

            // Append smallest digit to result string
            result.append(minDigit);
        }

        // Convert result string back to integer and return
        return Integer.parseInt(result.toString());
    }
}
