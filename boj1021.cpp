#include <iostream>
#include <deque>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, m, left=0 , right=0 ,cnt = 0; 
	deque <int> dq;
	cin >> n >> m;
	for(int i = 1; i <= n; i ++) {
		dq.push_back(i);
	}
	while (m--) {
		int a; 
		cin >> a;
		//왼쪽or오른쪽 돌릴 방향을 정하기 위한 변수 left, right
		for (int i = 0; i < dq.size(); i++) {
			if (dq[i] == a) { //deque는 인덱스접근이 가능하다 -> 원소들이 제거, 삽입되도 다시 인덱스로 정렬된다. 
				left = i;
				right = dq.size() - i;
				break;
			}
		}
		//왼쪽이 더 작은 경우 왼쪽으로 돌린다. 
		if (left <= right) {
			while (1) {
				if (dq.front() == a)
					break;
				dq.push_back(dq.front());
				dq.pop_front(); //맨앞 원소 맨 뒤로 보내기
				cnt++;
			}
			dq.pop_front(); //a 원소 제거
		}
		
		else { //오른쪽으로 돌리는게 더 적은 경우
			while (1) {
				if (dq.front() == a)
					break;
				dq.push_front(dq.back());
				dq.pop_back();//맨뒤 원소 맨앞으로 보내기
				cnt++;
			}
			dq.pop_front();//a 원소 제거
		}
	}
	cout << cnt;
}