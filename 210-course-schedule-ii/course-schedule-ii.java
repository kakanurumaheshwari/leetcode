import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        // Step 1: build graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]); // b → a
            indegree[p[0]]++;
        }

        // Step 2: queue for 0 indegree
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;

        // Step 3: BFS
        while (!q.isEmpty()) {
            int curr = q.poll();
            result[index++] = curr;

            for (int nei : graph.get(curr)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        // Step 4: check cycle
        if (index == numCourses) {
            return result;
        }

        return new int[0]; // cycle exists
    }
}