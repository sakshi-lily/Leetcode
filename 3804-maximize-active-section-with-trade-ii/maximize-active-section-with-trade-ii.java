import java.util.*;

class SegmentTree {
    int n;
    int[] st;

    public SegmentTree(int[] pairs) {
        this.n = pairs.length;
        if (n == 0) return;
        st = new int[4 * n];
        build(pairs, 1, 0, n - 1);
    }

    private int build(int[] pairs, int node, int l, int r) {
        if (l == r) {
            st[node] = pairs[l];
            return st[node];
        }

        int mid = l + (r - l) / 2;

        int left = build(pairs, node * 2, l, mid);
        int right = build(pairs, node * 2 + 1, mid + 1, r);

        st[node] = Math.max(left, right);
        return st[node];
    }

    public int query(int node, int l, int r, int ql, int qr) {

        if (n == 0 || ql > qr) return 0;

        if (l > qr || r < ql)
            return 0;

        if (ql <= l && r <= qr)
            return st[node];

        int mid = l + (r - l) / 2;

        int left = query(node * 2, l, mid, ql, qr);
        int right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return Math.max(left, right);
    }
}

class Solution {

    private int lowerBoundEnd(List<int[]> zeros, int target) {
        int l = 0, r = zeros.size();

        while (l < r) {
            int mid = (l + r) / 2;

            if (zeros.get(mid)[1] < target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }

    private int upperBoundStart(List<int[]> zeros, int target) {
        int l = 0, r = zeros.size();

        while (l < r) {
            int mid = (l + r) / 2;

            if (zeros.get(mid)[0] <= target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int n = s.length();

        int ones = 0;

        List<int[]> zeros = new ArrayList<>();

        int i = 0;

        while (i < n) {

            if (s.charAt(i) == '0') {

                int j = i;

                while (j < n && s.charAt(j) == '0')
                    j++;

                zeros.add(new int[]{i, j - 1});

                i = j;

            } else {

                ones++;

                i++;
            }
        }

        List<Integer> ans = new ArrayList<>();

        if (zeros.size() < 2) {

            for (int k = 0; k < queries.length; k++)
                ans.add(ones);

            return ans;
        }

        int[] pairs = new int[zeros.size() - 1];

        for (i = 1; i < zeros.size(); i++) {

            int[] a = zeros.get(i - 1);
            int[] b = zeros.get(i);

            pairs[i - 1] =
                    (a[1] - a[0] + 1)
                    +
                    (b[1] - b[0] + 1);
        }

        SegmentTree st = new SegmentTree(pairs);

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            int first = lowerBoundEnd(zeros, l);

            int last = upperBoundStart(zeros, r) - 1;

            if (first >= last) {

                ans.add(ones);

                continue;
            }

            int best = st.query(
                    1,
                    0,
                    st.n - 1,
                    first + 1,
                    last - 2
            );

            int prev =
                    Math.min(zeros.get(first)[1], r)
                            -
                            Math.max(zeros.get(first)[0], l)
                            + 1;

            int next =
                    Math.min(zeros.get(first + 1)[1], r)
                            -
                            Math.max(zeros.get(first + 1)[0], l)
                            + 1;

            best = Math.max(best, prev + next);

            prev =
                    Math.min(zeros.get(last - 1)[1], r)
                            -
                            Math.max(zeros.get(last - 1)[0], l)
                            + 1;

            next =
                    Math.min(zeros.get(last)[1], r)
                            -
                            Math.max(zeros.get(last)[0], l)
                            + 1;

            best = Math.max(best, prev + next);

            ans.add(ones + best);
        }

        return ans;
    }
}