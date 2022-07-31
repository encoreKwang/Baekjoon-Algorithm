#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	string s;
	getline(cin, s); // ù���� �ޱ�
	while (s != ".") {	
		stack<char> st = {}; //���� �ʱ�ȭ
		bool isWrong = false;//flag���� �ֱ�ȭ
		for (auto c : s) {
			if (c == '(' || c =='[') {
				st.push(c);
			}
			if (c == ')') {
				if (st.empty() || st.top() == '['){ //empty�ε� top()�� ȣ���ϸ� ��Ÿ�� ���� �߻� -> ���ǹ����� top���� empty������ ���� �ۼ��ϹǷν� empty�� ��� �ڿ� top������ Ȯ�������ʰ� if�� ���� ������ �����ؼ� ������ ����ִµ� top�� ȣ���ϴ� ���� �߻������ʴ´�. �̸� short-circuit evaluation�̶�� �Ѵ�. 
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
		if (!st.empty() || isWrong == true) {//���ÿ� ��ȣ�� �����ִٸ� �ùٸ��� ���� ��.
			cout << "no\n";
		}
		else cout << "yes\n";
		getline(cin, s); //���� ���� �ޱ�.
	}
}