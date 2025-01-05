class Solution {
    public int buttonWithLongestTime(int[][] events) {
        // Initialize variables
        int maxTime = events[0][1]; // The first button press time is its own timestamp
        int buttonIndex = events[0][0];
        
        for (int i = 1; i < events.length; i++) {
            int currentIndex = events[i][0];
            int currentTime = events[i][1];
            int timeTaken = currentTime - events[i - 1][1]; // Difference between consecutive timestamps
            
            // Update the button with the longest time if needed
            if (timeTaken > maxTime || (timeTaken == maxTime && currentIndex < buttonIndex)) {
                maxTime = timeTaken;
                buttonIndex = currentIndex;
            }
        }

        return buttonIndex;
    }
}
