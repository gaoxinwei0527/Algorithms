package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 711. Number of Distinct Islands II

 Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

 Example 1:
 11000
 10000
 00001
 00011
 Given the above grid map, return 1.

 Notice that:
 11
 1
 and
 1
 11
 are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
 Example 2:
 11100
 10001
 01001
 01110
 Given the above grid map, return 2.

 Here are the two distinct islands:
 111
 1
 and
 1
 1

 Notice that:
 111
 1
 and
 1
 111
 are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
 Note: The length of each dimension in the given grid does not exceed 50.
 */
public class S_Leetcode_711 {
    /*
    Intuition
    As in Approach #1 to the sister problem Number of Distinct Islands, we determine local coordinates for each island.
    Afterwards, we will rotate and reflect the coordinates about the origin and translate the shape so that the bottom-left-most coordinate is (0, 0). At the end, the smallest of these lists coordinates will be the canonical representation of the shape.

    Algorithm
    We feature two different implementations, but the core idea is the same. We start with the code from the previous problem, Number of Distinct Islands.
    For each of 8 possible rotations and reflections of the shape, we will perform the transformation and then translate the shape so that the bottom-left-most coordinate is (0, 0). Afterwards, we will consider the canonical hash of the shape to be the maximum of these 8 intermediate hashes.
    In Python, the motivation to use complex numbers is that rotation by 90 degrees is the same as multiplying by the imaginary unit, 1j. In Java, we manipulate the coordinates directly. The 8 rotations and reflections of each point are (x, y), (-x, y), (x, -y), (-x, -y), (y, x), (-y, x), (y, -x), (-y, -x).
    */
    int[][] grid;
    boolean[][] seen;
    ArrayList<Integer> shape;

    public void explore(int r, int c) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add(r * grid[0].length + c);
            explore(r+1, c);
            explore(r-1, c);
            explore(r, c+1);
            explore(r, c-1);
        }
    }

    public String canonical(ArrayList<Integer> shape) {
        String ans = "";
        int lift = grid.length + grid[0].length;
        int[] out = new int[shape.size()];
        int[] xs = new int[shape.size()];
        int[] ys = new int[shape.size()];

        for (int c = 0; c < 8; ++c) {
            int t = 0;
            for (int z: shape) {
                int x = z / grid[0].length;
                int y = z % grid[0].length;
                //x y, x -y, -x y, -x -y
                //y x, y -x, -y x, -y -x
                xs[t] = c<=1 ? x : c<=3 ? -x : c<=5 ? y : -y;
                ys[t++] = c<=3 ? (c%2==0 ? y : -y) : (c%2==0 ? x : -x);
            }

            int mx = xs[0], my = ys[0];
            for (int x: xs) mx = Math.min(mx, x);
            for (int y: ys) my = Math.min(my, y);

            for (int j = 0; j < shape.size(); ++j) {
                out[j] = (xs[j] - mx) * lift + (ys[j] - my);
            }
            Arrays.sort(out);
            String candidate = Arrays.toString(out);
            if (ans.compareTo(candidate) < 0) ans = candidate;
        }
        return ans;
    }

    public int numDistinctIslands2(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set<String> shapes = new HashSet<>();

        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                shape = new ArrayList<>();
                explore(r, c);
                if (!shape.isEmpty()) {
                    shapes.add(canonical(shape));
                }
            }
        }

        return shapes.size();
    }
}
