#include <iostream>
#include <queue>
using namespace std;

const int mx = 10005;
int q[mx] = {};
int head = 0, tail = 0;

void push(int a) {
	q[tail++] = a;
}

int front() {
	return q[head];
}

int back() {
	return q[tail-1];
}

int size() {
	return tail - head;
}

void pop() {
	head++;
}
int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n; 
	cin >> n; 
	while (n--) {
		string s;
		cin >> s;
		if (s == "push") {
			int a;
			cin >> a;
			push(a);
		}
		else if (s == "front") {
			if (head == tail)
				cout << -1 << '\n';
			else
				cout << front() << '\n';
		}
		else if (s == "back") {
			if (head == tail)
				cout << -1 << '\n';
			else
				cout << back() << '\n';
		}
		else if (s == "size") {
			cout << size() << '\n';
		}
		else if (s == "empty") {
			cout << (tail==head) << '\n';
		}
		else if (s == "pop") {
			if (tail == head) 
				cout << -1 << '\n';
			else {
				cout << front() << '\n';
				pop();
			}
		}
	}
}