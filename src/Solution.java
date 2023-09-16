import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    int[] row = {0, 0, -1, 1};
    int[] col = {-1, 1, 0, 0};

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] list = new int[n][m];

        for (int[] l : list) {
            Arrays.fill(l, Integer.MAX_VALUE);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.diff - b.diff);
        pq.add(new Pair(0, 0, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (p.r == n - 1 && p.c == m - 1) {
                return p.diff;
            }
            for (int i = 0; i < 4; i++) {
                int r = row[i] + p.r;
                int c = col[i] + p.c;
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    int curDist = Math.abs(heights[p.r][p.c] - heights[r][c]);
                    int maxDist = Math.max(p.diff, curDist);
                    if (maxDist < list[r][c]) {
                        list[r][c] = maxDist;
                        pq.add(new Pair(r, c, maxDist));
                    }
                }
            }
        }
        return -1;
    }
}