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
		q.pop(); // 1을 버림
		q.push(q.front()); // 2를 맨뒤로 옮김.
		q.pop(); //원래 2는 삭제 
	}
	cout << q.back();
}