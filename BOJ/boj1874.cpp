#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//1~n까지의 모든 값이 나와야하므로 스택에 들어간 스택에 들어간 마지막 숫자와 입력한 숫자가 같다면 빼낸다. 같지않다면 불가능 !! 
	int n = 0 , num = 0; 
	cin >> n; 
	stack<int> s; 
	vector<char> ans;
	while (n--) {
		int a; 
		cin >> a; 
		while (a > num) {
			s.push(++num);
			ans.push_back('+');
		}
		if (s.top() == a) {
			s.pop();
			ans.push_back('-');
		}
		else {
			cout << "NO";
			return 0;
		}
	}
	for (auto a : ans) {
		cout << a << '\n';
	}

}