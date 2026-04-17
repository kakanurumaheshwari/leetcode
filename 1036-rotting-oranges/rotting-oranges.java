import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        int time = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // Step 1: count fresh and add rotten
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // Directions: up, down, left, right
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

        // Step 2: BFS
        while (!q.isEmpty() && fresh > 0) {
            int size = q.size();
            time++;

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();

                for (int[] d : dir) {
                    int r = curr[0] + d[0];
                    int c = curr[1] + d[1];

                    if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        q.offer(new int[]{r, c});
                        fresh--;
                    }
                }
            }
        }

        // Step 3: result
        return fresh == 0 ? time : -1;
    }
}