#include <iostream>
#include <list>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin >> n; 

	while (n--) {
		//s�� ���� �׽�Ʈ ���̽��� �ϳ��� �����鼭 ����Ʈ�� �����Ѵ�. 
		string s;
		cin >> s;
		list<char> L;
		list<char>::iterator cursor = L.begin();
		//auto cursor = L.begin()

		for (auto c : s) {
			if (c == '<') {
				if (cursor != L.begin())
					cursor--;
			}
			else if(c == '>') {
				if (cursor != L.end())
					cursor++;
			}
			else if (c == '-') {
				if (cursor != L.begin()) {
					//iterator�� ����Ű�� ���� ����°� �ƴ϶� �� �� ���ڸ� ����°Ŵϱ� cursor--�� ������Ѵ�.
					cursor--; 
					cursor = L.erase(cursor);
				}
				
			}
			else {
				//insert�� cursor���� �տ� �� ���Ҹ� �߰��ϰ� cursor�� �״�� �����ϱ��Ѵ�.  
				L.insert(cursor, c);
			}
		}
		for (auto p : L) {
			cout << p;
		}
		cout << '\n';
	}

}