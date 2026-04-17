class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];

        // Important edge case
        if (original == color) return image;

        dfs(image, sr, sc, original, color);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int original, int color) {

        // Boundary + color check
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length 
            || image[i][j] != original) {
            return;
        }

        // Change color
        image[i][j] = color;

        // Explore all 4 directions
        dfs(image, i + 1, j, original, color);
        dfs(image, i - 1, j, original, color);
        dfs(image, i, j + 1, original, color);
        dfs(image, i, j - 1, original, color); // ✅ FIXED
    }
}