#include <iostream>
#include <list>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin >> n; 

	while (n--) {
		//s에 넣은 테스트 케이스를 하나씩 꺼내면서 리스트에 삽입한다. 
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
					//iterator가 가리키는 값을 지우는게 아니라 그 앞 글자를 지우는거니까 cursor--를 해줘야한다.
					cursor--; 
					cursor = L.erase(cursor);
				}
				
			}
			else {
				//insert는 cursor원소 앞에 새 원소를 추가하고 cursor는 그대로 유지하기한다.  
				L.insert(cursor, c);
			}
		}
		for (auto p : L) {
			cout << p;
		}
		cout << '\n';
	}

}