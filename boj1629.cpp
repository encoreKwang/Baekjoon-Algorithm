#include <iostream>

using namespace std;

using ll = long long; 
ll func(ll a, ll b, ll c) {
	ll ans; 
	if (b == 1) return a % c; 
	ans = func(a, b/2, c); //int�� 10�ڸ��� �Ѵ� ���� ǥ���� �� ����. ��� �ִ밪  2,147,483,647 
	ans = ans * ans % c; // long long�� 20�ڸ��� �Ѵ� ���� ǥ���� �� ����. -> ans * ans * a % c�� ���� �÷ο찡 �߻��Ѵ�.
	if (b%2 == 0) // Ȧ�����
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