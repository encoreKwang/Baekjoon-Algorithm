#include <iostream>
#include <deque>
#include <vector>
#include<algorithm>    //reverse

using namespace std;

void parse(string &tmp, deque<int> &dq) {
	int cur = 0; //십의 자리일 경우 때문에 필요.
	for (int i = 1; i+1 < tmp.size() ; i++) {
		if (tmp[i] == ',') {
			dq.push_back(cur);
			cur = 0;
		}
		else {
			cur = 10 * cur + (tmp[i]-'0');
			// 31 -> cur = 3 -> 10 * 3 + 1 = 31
		}
	}
	if (cur != 0) // 콤마 없는 마지막 원소인 경우 
		dq.push_back(cur);
}
void print_result(deque <int> &dq) {
	cout << '[';
	for (int i = 0; i< dq.size(); i++) {
		cout << dq[i];
		if(i+1 != dq.size()) //마지막 원소일땐 콤마 없어야함
			cout << ',';
	}
	cout << "]\n";
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n = 0; 
	cin >> n;
	while (n--) {
		deque <int> dq;
		string query, tmp; 
		int len;
		int rev = 0; // 뒤집을는 명령 카운트 변수
		bool isWrong = false; // 원소가 없을때 'D' 명령이 있을때
		cin >> query; // 수행할 함수 
		cin >> len; // 배열에 들어있는 수의 개수
		cin >> tmp; //배열입력 
		parse(tmp, dq);
		for (char q : query) {
			if (q == 'R') {
				rev = 1 - rev; // 뒤집기 2번은 그대로
			}
			else { // q == 'D'일때
				if (dq.empty()) { // 비어있으면 오류
					isWrong = true; // for문, while문을 벗어나야하기때문에 isWrong이라는 flag변수를 쓴거 같다
					break;
				}
				if (!rev) dq.pop_front(); // 뒤집지 않으면 맨앞 원소제거되겠지
				else dq.pop_back(); // 뒤집어야하니까 맨 뒤 원소가 제거되겠지
			}
		}
		if (isWrong) {
			cout << "error\n";
		}
		else {
			if (rev) 
				reverse(dq.begin(), dq.end());
			print_result(dq);

		}
	}
}