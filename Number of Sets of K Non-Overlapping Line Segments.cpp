const int N=2001;
constexpr int MOD=1e9+7;
int F[N], invF[N];
using ll=long long;
// extended Euclidean algorithm to find the mod inverse
static ll inverse(long long a) {
    ll b=MOD; // MOD is a prime, so gcd(a, MOD)=1
    ll x0=1, x1=0; 
    while (b>0){
        auto [q, r]=div(a, b);// quoient & remainder
        x0=exchange(x1, x0-q*x1);// x0=x1, x1=x0-q*x1
        a=exchange(b, r);//(a, b)=(b, r)
    }
    return (x0<0)?x0+MOD:x0;// x0 is the inverse, but make x0>0
}
class Solution {
public:
    static void compute_F_invF(){
        if (F[0]==1) return; // compute only once
        ll prod=1, invP=1;
        F[0]=1;
        for(int i=1; i<N; i++){
            prod*=i;
            if (prod>MOD) prod%=MOD;
            F[i]=prod;
        }
        invF[N-1]=inverse(F[N-1]);
        
        for (int i=N-2; i>=0; i--) 
            invF[i]=1LL*invF[i+1]*(i+1)%MOD;
    }
    static inline ll nCk(int n, int k){
        if (n<k) return 0;
        return 1LL*F[n]*invF[n-k]%MOD*invF[k]%MOD;
    }
    static int numberOfSets(int n, int k) {
        compute_F_invF();
        return nCk(n+k-1, k*2);
    }
};
