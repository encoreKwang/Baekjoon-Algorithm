#include <iostream>

using namespace std;

using ll = long long; 
ll func(ll a, ll b, ll c) {
	ll ans; 
	if (b == 1) return a % c; 
	ans = func(a, b/2, c); //int는 10자리가 넘는 값을 표현할 수 없다. 양수 최대값  2,147,483,647 
	ans = ans * ans % c; // long long은 20자리가 넘는 값을 표현할 수 없다. -> ans * ans * a % c는 오버 플로우가 발생한다.
	if (b%2 == 0) // 홀수라면
		return ans;
	return ans * a % c;
}

int main() {
	ios::sync_with_stdio(0);  
	cin.tie(0);
	ll a, b, c; 
	cin >> a >> b >> c;
	cout << func(a, b, c);
}