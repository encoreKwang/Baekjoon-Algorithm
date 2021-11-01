#include <iostream>
#include <queue>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	queue <int> q;
	int n;
	cin >> n;
	for(int i = 1; i <= n; i++) {
		q.push(i);
	}
	while (q.size() != 1) {
		q.pop(); // 1�� ����
		q.push(q.front()); // 2�� �ǵڷ� �ű�.
		q.pop(); //���� 2�� ���� 
	}
	cout << q.back();
}