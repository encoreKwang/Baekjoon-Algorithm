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
			//������ ��� �ְų� top�� �ٸ��� Ǫ��
			//ABABA(B B)ABABA
			if (st.empty() || c != st.top()) {
				st.push(c);
				continue;
			}
			//top�� ���� ��� pop
			else if (c == st.top()) {
				st.pop();
			}
		}
		//stack�� ���Ұ� �����ִٸ� wrong!
		if (st.empty()) cnt++;
	}
	cout << cnt;
}