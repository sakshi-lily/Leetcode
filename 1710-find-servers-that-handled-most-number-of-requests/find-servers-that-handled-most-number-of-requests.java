class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCount = new int[k];
      
        PriorityQueue<int[]> busyServers = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0])
        );
      
        TreeSet<Integer> availableServers = new TreeSet<>();
      
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
      
        for (int i = 0; i < arrival.length; i++) {
            int requestArrivalTime = arrival[i];
            int requestEndTime = requestArrivalTime + load[i];
          
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= requestArrivalTime) {
                int[] completedServer = busyServers.poll();
                availableServers.add(completedServer[1]);
            }
          
            if (availableServers.isEmpty()) {
                continue;
            }
          
            Integer targetServer = availableServers.ceiling(i % k);
          
            if (targetServer == null) {
                targetServer = availableServers.first();
            }
          
            requestCount[targetServer]++;
            busyServers.offer(new int[] {requestEndTime, targetServer});
            availableServers.remove(targetServer);
        }
      
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
      
        List<Integer> busiestServersList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                busiestServersList.add(i);
            }
        }
      
        return busiestServersList;
    }
}
