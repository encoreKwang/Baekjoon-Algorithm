#include <iostream>
#include <deque>
#include <vector>
#include<algorithm>    //reverse

using namespace std;

void parse(string &tmp, deque<int> &dq) {
	int cur = 0; //���� �ڸ��� ��� ������ �ʿ�.
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
	if (cur != 0) // �޸� ���� ������ ������ ��� 
		dq.push_back(cur);
}
void print_result(deque <int> &dq) {
	cout << '[';
	for (int i = 0; i< dq.size(); i++) {
		cout << dq[i];
		if(i+1 != dq.size()) //������ �����϶� �޸� �������
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
		int rev = 0; // �������� ��� ī��Ʈ ����
		bool isWrong = false; // ���Ұ� ������ 'D' ����� ������
		cin >> query; // ������ �Լ� 
		cin >> len; // �迭�� ����ִ� ���� ����
		cin >> tmp; //�迭�Է� 
		parse(tmp, dq);
		for (char q : query) {
			if (q == 'R') {
				rev = 1 - rev; // ������ 2���� �״��
			}
			else { // q == 'D'�϶�
				if (dq.empty()) { // ��������� ����
					isWrong = true; // for��, while���� ������ϱ⶧���� isWrong�̶�� flag������ ���� ����
					break;
				}
				if (!rev) dq.pop_front(); // ������ ������ �Ǿ� �������ŵǰ���
				else dq.pop_back(); // ��������ϴϱ� �� �� ���Ұ� ���ŵǰ���
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