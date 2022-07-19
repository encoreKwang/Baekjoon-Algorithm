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
		//����or������ ���� ������ ���ϱ� ���� ���� left, right
		for (int i = 0; i < dq.size(); i++) {
			if (dq[i] == a) { //deque�� �ε��������� �����ϴ� -> ���ҵ��� ����, ���Եǵ� �ٽ� �ε����� ���ĵȴ�. 
				left = i;
				right = dq.size() - i;
				break;
			}
		}
		//������ �� ���� ��� �������� ������. 
		if (left <= right) {
			while (1) {
				if (dq.front() == a)
					break;
				dq.push_back(dq.front());
				dq.pop_front(); //�Ǿ� ���� �� �ڷ� ������
				cnt++;
			}
			dq.pop_front(); //a ���� ����
		}
		
		else { //���������� �����°� �� ���� ���
			while (1) {
				if (dq.front() == a)
					break;
				dq.push_front(dq.back());
				dq.pop_back();//�ǵ� ���� �Ǿ����� ������
				cnt++;
			}
			dq.pop_front();//a ���� ����
		}
	}
	cout << cnt;
}