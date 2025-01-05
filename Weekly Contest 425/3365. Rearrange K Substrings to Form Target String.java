import java.util.Arrays;

class Solution {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        int n = s.length();

        // If k == 1, the entire string s must match t exactly
        if (k == 1) {
            return s.equals(t);
        }

        // Check if k divides the length of the strings
        if (n % k != 0) {
            return false;
        }

        int segmentLength = n / k;

        // Split both strings into k segments
        String[] sSegments = new String[k];
        String[] tSegments = new String[k];

        for (int i = 0; i < k; i++) {
            sSegments[i] = s.substring(i * segmentLength, (i + 1) * segmentLength);
            tSegments[i] = t.substring(i * segmentLength, (i + 1) * segmentLength);
        }

        // Sort both arrays of segments
        Arrays.sort(sSegments);
        Arrays.sort(tSegments);

        // Now check if the sorted segments of s can match t
        for (int i = 0; i < k; i++) {
            if (!sSegments[i].equals(tSegments[i])) {
                return false;
            }
        }

        return true;
    }
}
