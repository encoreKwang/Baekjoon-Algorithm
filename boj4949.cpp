#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	string s;
	getline(cin, s); // 첫문장 받기
	while (s != ".") {	
		stack<char> st = {}; //스택 초기화
		bool isWrong = false;//flag변수 최기화
		for (auto c : s) {
			if (c == '(' || c =='[') {
				st.push(c);
			}
			if (c == ')') {
				if (st.empty() || st.top() == '['){ //empty인데 top()을 호출하면 런타임 에러 발생 -> 조건문에서 top보다 empty조건을 먼저 작성하므로써 empty인 경우 뒤에 top조건을 확인하지않고 if문 안의 본문을 실행해서 스택이 비어있는데 top을 호출하는 일이 발생하지않는다. 이를 short-circuit evaluation이라고 한다. 
					isWrong = true;
					break;
				}
				if (st.top()=='(') {
					st.pop();
				}
			}
			else if (c == ']') {
				if (st.empty() || st.top() == '('){
					isWrong = true;
					break;
				}
				if (st.top() == '[') {
					st.pop();
				}
			}
		}
		if (!st.empty() || isWrong == true) {//스택에 괄호가 남아있다면 올바르지 않은 쌍.
			cout << "no\n";
		}
		else cout << "yes\n";
		getline(cin, s); //다음 문장 받기.
	}
}