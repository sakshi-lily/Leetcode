class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        int[] best = new int[n + 1];
        Arrays.fill(best, INF);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int prefixSum = 0;
        int answer = INF;

        for (int i = 1; i <= n; i++) {

            prefixSum += arr[i - 1];

            best[i] = best[i - 1];

            int required = prefixSum - target;

            if (map.containsKey(required)) {
                int j = map.get(required);

                int currentLength = i - j;

                answer = Math.min(
                    answer,
                    best[j] + currentLength
                );

                best[i] = Math.min(
                    best[i],
                    currentLength
                );
            }

            map.put(prefixSum, i);
        }

        return answer == INF ? -1 : answer;
    }
}