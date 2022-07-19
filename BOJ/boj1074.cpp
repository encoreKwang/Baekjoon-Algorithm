#include <iostream>

using namespace std;
int n, r, c;

int func(int n, int r, int c) {
	if (n == 0) return 0;//base condition
	int half = 1 << (n - 1); //��Ʈ������ 2^(n-1) -> �� ���� ����
	if (r < half && c < half) return func(n-1, r, c);//ù��° ĭ�϶�
	if (r < half&& c >= half) return half*half + func(n - 1, r, c - half);//�ι�° ĭ�϶�
	if (r >= half && c < half) return half*half*2 + func(n - 1, r-half, c); //����° ĭ�϶�
	return half * half * 3 + func(n - 1, r-half, c-half);//�׹�° ĭ�϶�
	
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> r >> c;
	cout << func(n, r, c);
	
}