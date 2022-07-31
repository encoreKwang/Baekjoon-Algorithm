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
				//list�� erase�� �ش� ��ġ�� ���� �����ϴµ� �������� Ŀ�� ���ʿ� �ִ� ���ڸ� �����϶�� ������ curser--;�� ������Ѵ�.
				curser--;
				// erase�� �ش� ��ġ�� ���� �� ���� ������ ��ġ�� ��ȯ�ϱ⶧���� �� ��ȯ�� �޾�����Ѵ�.
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
