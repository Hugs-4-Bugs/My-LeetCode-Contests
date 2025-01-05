class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10; // Alice starts by removing 10 stones.
        boolean isAliceTurn = true;

        while (n > 0) {
            if (n < stonesToRemove) {
                // The current player cannot make a valid move and loses.
                return !isAliceTurn;
            }
            n -= stonesToRemove;
            stonesToRemove--; // Decrease the number of stones for the next move.
            isAliceTurn = !isAliceTurn; // Switch turns.
        }

        // If we exit the loop, it means the last move won the game.
        return !isAliceTurn; // Alice wins if the last turn was Bob's.
    }
}
