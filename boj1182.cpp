#include <iostream>
using namespace std;
int n, s;
int arr[30];
int cnt = 0;

void func(int cur, int tot) { //tot�� ��������� ��.
	if (cur == n) { //��� ���ҿ� ���� ������ ������ ������ �� ������ ����� ���� ���� �� �翬�� curȽ���� ������ ���� ��������.
		if (tot == s) cnt++; 
		return; 
	}
	func(cur + 1, tot); //���� ���Ҹ� ������ �ʴ� ���
	func(cur + 1, tot + arr[cur]); //���� ���Ҹ� ���ϴ� ���
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> s;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	func(0, 0);
	if (s == 0) cnt--; // s�� 0�϶� �����յ� ī��Ʈ �ǹǷ� �ϳ� ���ߵȴ�.
	cout << cnt;
}