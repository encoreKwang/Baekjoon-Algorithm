//���� ���� �ߺ� ���� 
//�� ������ �񳻸����� -> '����' nCm
//���� st�� �����ؾ��Ѵ�. -> st = arr[i-1] + 1;
//st�� ������ arr�� ���� ������ �ε������� Ŀ���Ѵ�. 
//N���� �ڿ��� ���Ҹ� arr[]�� �ִ°� �ƴ϶�  N�� �ڿ����� �ε����� �ִ´�. 

#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
using namespace std;
int n, m; 
int arr[10];
int num[10];

void solve(int  cur) {
	if (cur == m) {
		for (int i = 0; i < m; i++) {
			cout << num[arr[i]] << ' ';
		}
		cout << '\n';
		return; 
	}
	int st = 0;
	if (cur != 0) {
		st = arr[cur-1];
	}
	for (int i = st; i < n; i++) {
			arr[cur] = i;
			solve(cur + 1);
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> num[i];
	}
	sort(num, num + n);
	solve(0);
}