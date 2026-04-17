import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) {
            redGraph[e[0]].add(e[1]);
        }

        for (int[] e : blueEdges) {
            blueGraph[e[0]].add(e[1]);
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        // visited[node][color]
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> q = new LinkedList<>();
        
        // {node, color, distance}
        // color: 0 = red, 1 = blue

        q.offer(new int[]{0, 0, 0});
        q.offer(new int[]{0, 1, 0});

        visited[0][0] = true;
        visited[0][1] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int color = curr[1];
            int d = curr[2];

            if (dist[node] == -1) {
                dist[node] = d;
            }

            // alternate color
            if (color == 0) { // last was red → go blue
                for (int nei : blueGraph[node]) {
                    if (!visited[nei][1]) {
                        visited[nei][1] = true;
                        q.offer(new int[]{nei, 1, d + 1});
                    }
                }
            } else { // last was blue → go red
                for (int nei : redGraph[node]) {
                    if (!visited[nei][0]) {
                        visited[nei][0] = true;
                        q.offer(new int[]{nei, 0, d + 1});
                    }
                }
            }
        }

        return dist;
    }
}