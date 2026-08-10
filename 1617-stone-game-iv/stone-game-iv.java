class Solution {
    private Boolean[] memo;

    /**
     * Determines if the current player can win the square game.
     * In this game, players take turns removing square number of stones.
     * The player who removes the last stone wins.
     * 
     * @param n The initial number of stones
     * @return true if the current player can guarantee a win, false otherwise
     */
    public boolean winnerSquareGame(int n) {
        memo = new Boolean[n + 1];
        return canWin(n);
    }

    /**
     * Recursively determines if the current player can win from state i.
     * Uses game theory: current player wins if there exists at least one move
     * that leaves the opponent in a losing position.
     * 
     * @param remainingStones The number of stones remaining in current state
     * @return true if current player can win from this state, false otherwise
     */
    private boolean canWin(int remainingStones) {
        if (remainingStones <= 0) {
            return false;
        }
        if (memo[remainingStones] != null) {
            return memo[remainingStones];
        }
        for (int j = 1; j * j <= remainingStones; j++) {
            if (!canWin(remainingStones - j * j)) {
                memo[remainingStones] = true;
                return true;
            }
        }
        memo[remainingStones] = false;
        return false;
    }
}