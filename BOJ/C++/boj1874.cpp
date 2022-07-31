#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//1~n������ ��� ���� ���;��ϹǷ� ���ÿ� �� ���ÿ� �� ������ ���ڿ� �Է��� ���ڰ� ���ٸ� ������. �����ʴٸ� �Ұ��� !! 
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