//��Ʈ��ŷ.. 
//���������̹Ƿ� ���� combination -> �ݺ��� start���� ���� 
// �ߺ� �����̹Ƿ� isused �ʿ� ����.
#include <iostream>
using namespace std;
int n, m;
int arr[10];

void solve(int cur) {
	if (cur == m) {//cur==m�̸� ��� �� ����
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}
	int st = 1; // cur�� 0�϶� 
	if (cur != 0) st = arr[cur - 1]; //��� ���������� arr�� ���� ���� ���۰�. �ߺ��̹Ƿ� +1�� ���Ѵ�.
	for (int i = st; i <= n; i++) {
		arr[cur] = i; 
		solve(cur + 1);
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	solve(0);
}