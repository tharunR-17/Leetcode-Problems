class Solution {
    public static int GCD(int x, int y){
        final int bx=Integer.numberOfTrailingZeros(x);
        final int by=Integer.numberOfTrailingZeros(y);
        final int bb=(bx<by)?bx:by;
        x>>=bx; y>>=by;
        for(int r; y>0; y=Math.min(y, r)){
            r=x%y;
            x=y;
            y=r;
        }
        return x<<bb;
    }
    static public int countTriples(int n) {
        int cnt=0;
        int nsqrt=(int)Math.sqrt((double)n);
        for (int s=2; s<=nsqrt; s++) {
            for (int t=1+(s&1); t<s; t+=2) {
                if (GCD(s, t)!=1) continue;

                int c=s*s+t*t;

                if (c>n) break;

                // k multiples: ka, kb, kc
                int kmax=n/c;
                // count (a,b,c) and (b,a,c)
                cnt+=2*kmax;
            }
        }
        return cnt;
    }
}
