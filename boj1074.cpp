#include <iostream>

using namespace std;
int n, r, c;

int func(int n, int r, int c) {
	if (n == 0) return 0;//base condition
	int half = 1 << (n - 1); //비트연산자 2^(n-1) -> 한 변의 절반
	if (r < half && c < half) return func(n-1, r, c);//첫번째 칸일때
	if (r < half&& c >= half) return half*half + func(n - 1, r, c - half);//두번째 칸일때
	if (r >= half && c < half) return half*half*2 + func(n - 1, r-half, c); //세번째 칸일때
	return half * half * 3 + func(n - 1, r-half, c-half);//네번째 칸일때
	
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> r >> c;
	cout << func(n, r, c);
	
}