import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        
        // Create graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]); // b → a
        }

        int[] state = new int[numCourses]; // 0,1,2

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (dfs(graph, state, i)) {
                    return false; // cycle found
                }
            }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> graph, int[] state, int node) {
        if (state[node] == 1) return true;  // cycle
        if (state[node] == 2) return false; // already safe

        state[node] = 1; // visiting

        for (int nei : graph.get(node)) {
            if (dfs(graph, state, nei)) {
                return true;
            }
        }

        state[node] = 2; // done
        return false;
    }
}