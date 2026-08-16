class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] countByRemainder = new int[3];
        for (int stone : stones) {
            countByRemainder[stone % 3]++;
        }
        int[] scenario1 = {countByRemainder[0], countByRemainder[1], countByRemainder[2]};
        int[] scenario2 = {countByRemainder[0], countByRemainder[2], countByRemainder[1]};

        return check(scenario1) || check(scenario2);
    }

    private boolean check(int[] remainderCounts) {
        remainderCounts[1]--;
        if (remainderCounts[1] < 0) {
            return false;
        }
        int totalTurns = 1 + Math.min(remainderCounts[1], remainderCounts[2]) * 2 + remainderCounts[0];
        if (remainderCounts[1] > remainderCounts[2]) {
            remainderCounts[1]--;
            totalTurns++;
        }
        return totalTurns % 2 == 1 && remainderCounts[1] != remainderCounts[2];
    }
}
