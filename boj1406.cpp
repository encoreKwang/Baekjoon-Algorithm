#include <iostream>
#include <list>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	string s; 
	list<char> L;
 	cin >> s; 
	for (auto c : s) L.push_back(c);
	list<char>:: iterator curser = L.end();
	int n; 
	cin >> n; 
	while (n--) {
		char op;
		cin >> op; 
		if (op == 'L') {
			if (curser != L.begin()) {
				curser--;
			}
		}
		else if (op == 'D') {
			if (curser != L.end()) {
				curser++;
			}
		}
		else if (op == 'B') {
			if (curser != L.begin()) {
				//list의 erase는 해당 위치의 값을 제거하는데 문제에서 커서 왼쪽에 있는 문자를 삭제하라고 했으니 curser--;를 해줘야한다.
				curser--;
				// erase는 해당 위치를 삭제 후 다음 원소의 위치를 반환하기때문에 그 반환을 받아줘야한다.
				curser = L.erase(curser);
			}
		}
		else if (op == 'P') {
			char addChar;
			cin >> addChar;
			L.insert(curser, addChar);
		} 

	}
	for (auto a : L)
		cout << a; 
	

}
