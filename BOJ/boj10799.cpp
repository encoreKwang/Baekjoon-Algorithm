#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//쌍이 나오면 무조건 레이저
	//레이저를 쐈을때 몇개의 쇠막대기가 나오는지는 스택의 갯수로 알 수 있다. -> 스택size=여는 괄호 있는 갯수니까 3개 추가, 두번째 레이저도 스택에 3개 있으니까 3개 추가, 그담에 닫는 괄호 나왔으니 스택에서 pop하고, 여는 괄호 나왔으니 push, 세번째 레이저 나왔으니 스택의 길이(3) 추가, 닫는 괄호 나왔으니 pop, 네번째 레이저 나왔으니 스택size추가(2) , 닫는 괄호 2번 연속 나왔으니 pop 2번 하고 empty 됐으니 마지막 레이저일때 추가 한만큼 마지막 추가. 
	string s;
	int cnt=0, num;
	bool isRaser = true; // true일때 닫는 괄호가 나오면 레이저임을 나타내는 flag변수. 
	cin >> s; 
	stack<char> st;
	for (char c : s) {
		if (c == '(') {
			st.push(c);
			isRaser = true; // 여느괄호가 들어오면바로 다음 마주하는 닫는 괄호일땐 무조건레이저다. 
		}
		if (c == ')' && isRaser) { // 닫는 괄호가 나왔을때 레이저일때
			st.pop();
			cnt += st.size();//스택에 쌓인 여는괄호만큼 쇠막대기 추가
			isRaser = false;
		}
		else if (c == ')' && !isRaser) { //닫는 괄호가 레이저가 아닌 쇠막대기 오른쪽 끝을 의미할때
			st.pop();
			cnt++;//닫는괄호가 나올때 하나 추가한다.
		}
	}
	cout << cnt;
}