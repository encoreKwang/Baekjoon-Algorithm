#include <iostream>
using namespace std;

int n;
const int mx = 10005;
int dat[mx * 2 + 1];
int head = mx, tail = mx;

int front() {
	return dat[head];
}
void pop_front() {
	head++;
}
int back() {
	return dat[tail - 1];
}
void pop_back() {
	tail--;
}


int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n;


	while (n--) {
		string s;
		cin >> s;
		if (s == "push_back") {
			int val;
			cin >> val;
			dat[tail++] = val;
		}
		else if (s == "push_front") {
			int val;
			cin >> val;
			dat[--head] = val;
		}
		else if (s == "front") {
			if (head == tail) {
				cout << -1 << '\n';
			}
			else cout << dat[head] << '\n';
		}
		else if (s == "back") {
			if (head == tail) {
				cout << -1 << '\n';
			}
			else cout << dat[tail - 1] << '\n';
		}
		else if (s == "size")  cout << tail - head << '\n';
		else if (s == "empty")  cout << (tail == head) << '\n';
		else if (s == "pop_front") {
			if (tail == head) cout << -1 << '\n';
			else {
				cout << front() << '\n';
				pop_front();
			}
		}
		else if (s == "pop_back") {
			if (tail == head) cout << -1 << '\n';
			else {
				cout << back() << '\n';
				pop_back();
			}
		}
	}
}