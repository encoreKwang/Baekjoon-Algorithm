//�� ������ �������� �̹Ƿ� ����.
//�ߺ��� �����Ƿ� next_permutation ����
//���� -> N���� ���Ҹ� �Է¹��� ���� v, 0�� 1�� ������ ���� t �ʿ�
#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, m;
	cin >> n >> m;
	vector<int> v(n), t(n);
	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}
	fill(t.begin()+ m, t.end(), 1); // m����ŭ 0 �׸��� �� ���� �ε������� ������ 1.
	
	sort(v.begin(), v.end()); //����
	do {
		for (int i = 0; i < n; i++) {
			if (t[i] == 0) {
				cout << v[i] << ' ';
			}
		}
		cout << '\n';
	} while (next_permutation(t.begin(), t.end()));

}