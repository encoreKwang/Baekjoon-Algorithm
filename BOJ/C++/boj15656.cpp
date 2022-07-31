// ��Ʈ��ŷ.. nPr �ε� ���� ������ �ߺ��� �����ϴ�. -> next_permutation�� ��� �Ұ�.
// N���� �ڿ����� ���� num�迭�� �ʿ��ϰ�, arr�迭�� �ʿ��ϰ� 
// ���� ������ �ߺ� ->  ��� �湮�ؾ��ϹǷ� vis�迭�� �ʿ����.


#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
using namespace std;

int n, m;
int arr[10];
int num[10];

void solve(int cur) {
	if (cur == m) { //cur�� ���� arr�� ���� �� ���� 
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (int i = 0; i < n; i++) {
			arr[cur] = num[i]; //arr�迭�� cur�ε����� num���Ҹ� ���ʴ�� �ִ´�.
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
	sort(num, num + n); //����
	solve(0);
	
}