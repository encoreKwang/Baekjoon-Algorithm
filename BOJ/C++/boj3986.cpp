#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, cnt = 0;
	cin >> n;
	while (n--) {
		string s;
		cin >> s;
		stack<char> st = {};
		//bool isOk = true;
		for (auto c : s) {
			//스택이 비어 있거나 top과 다르면 푸시
			//ABABA(B B)ABABA
			if (st.empty() || c != st.top()) {
				st.push(c);
				continue;
			}
			//top과 같은 경우 pop
			else if (c == st.top()) {
				st.pop();
			}
		}
		//stack에 원소가 남아있다면 wrong!
		if (st.empty()) cnt++;
	}
	cout << cnt;
}