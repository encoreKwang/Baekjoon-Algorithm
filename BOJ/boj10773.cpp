#include <iostream>
#include <stack>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int k, sum =0 ;
	cin >> k;
	stack<int> s; 

	while (k--) {
		int a; 
		cin >> a;
		if (a == 0 && !s.empty())
			s.pop();
		else
			s.push(a);
	}
	while (s.size() != 0) {
		sum += s.top();
		s.pop();
	}
	cout << sum;
}