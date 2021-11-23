//��Ʈ��ŷ, �ߺ� ���� ���� -> arr, isused �迭 �ʿ�. 
//�־��� N���� �ڿ����� ���� �迭  num �ʿ�.
// �ٵ� boolŸ�� isused�� �ε����� N���� �ڿ����� ��Ÿ�� �� ����
// ���� isused[i]�� num[i]�� ��� ���¸� ��Ÿ���� ������ ����.
#include <iostream>
#include <algorithm>
using namespace std;
int n, m; 
int arr[10], isused[10];
int num[10];

void solve(int cur) {
	if (cur == m) {//cur == m �̸� ����ϰ� ����
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return; 
	}
	for (int i = 0; i < n; i++) {
		if (isused[i] == false) {
			arr[cur] = num[i]; //num�迭�� �ش� �ε����� �����Ѵ�. 
			isused[i] = true;
			solve(cur + 1);
			isused[i] = false;
		}
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		cin >> num[i];
	sort(num, num+n); //����
	solve(0);
}