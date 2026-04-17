import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        
        dfs(rooms, visited, 0);

        // Check if all rooms visited
        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    private void dfs(List<List<Integer>> rooms, boolean[] visited, int room) {
        if (visited[room]) return;

        visited[room] = true;

        for (int key : rooms.get(room)) {
            dfs(rooms, visited, key);
        }
    }
}