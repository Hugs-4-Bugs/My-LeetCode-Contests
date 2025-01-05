class Solution {
    public int minimumArea(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        int minRow = Integer.MAX_VALUE, maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE, maxCol = Integer.MIN_VALUE;
        
        // Find the bounding box of all 1s
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    minRow = Math.min(minRow, r);
                    maxRow = Math.max(maxRow, r);
                    minCol = Math.min(minCol, c);
                    maxCol = Math.max(maxCol, c);
                }
            }
        }
        
        // Calculate the dimensions and area
        int height = maxRow - minRow + 1;
        int width = maxCol - minCol + 1;
        int area = height * width;
        
        return area;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid1 = {{0, 1, 0}, {1, 0, 1}};
        System.out.println(solution.minimumArea(grid1)); // Output: 6

        int[][] grid2 = {{1, 0}, {0, 0}};
        System.out.println(solution.minimumArea(grid2)); // Output: 1
    }
}
