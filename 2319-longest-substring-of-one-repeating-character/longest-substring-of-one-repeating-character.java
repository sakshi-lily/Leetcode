class Solution {
    int[] pre, suf, best, tree;
    char[] a;

    public int[] longestRepeating(String s, String qc, int[] qi) {
        int n = s.length(), k = qi.length;
        a = s.toCharArray();
        pre = new int[4*n]; suf = new int[4*n];
        best = new int[4*n]; tree = new int[4*n];

        build(1, 0, n-1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            a[qi[i]] = qc.charAt(i);
            update(1, 0, n-1, qi[i]);
            ans[i] = tree[1];
        }
        return ans;
    }

    void build(int p, int l, int r) {
        if (l == r) {
            pre[p] = suf[p] = best[p] = tree[p] = 1;
            return;
        }
        int m = (l+r)/2;
        build(p*2,l,m);
        build(p*2+1,m+1,r);
        merge(p,l,r);
    }

    void update(int p, int l, int r, int idx) {
        if (l == r) return;
        int m = (l+r)/2;
        if (idx <= m) update(p*2,l,m,idx);
        else update(p*2+1,m+1,r,idx);
        merge(p,l,r);
    }

    void merge(int p, int l, int r) {
        int L=p*2, R=p*2+1, m=(l+r)/2;

        pre[p] = pre[L];
        suf[p] = suf[R];
        best[p] = Math.max(best[L], best[R]);

        if (a[m] == a[m+1]) {
            best[p] = Math.max(best[p], suf[L] + pre[R]);
            if (pre[L] == m-l+1) pre[p] += pre[R];
            if (suf[R] == r-m) suf[p] += suf[L];
        }
        tree[p] = best[p];
    }
}