//'N���� �ڿ����� ��� �޶�ߵȴ�' ������ ����.
// '���� ���� ���� �� ��� �ȴ�' ���� -> �󵵼��� ��� ����? 
// ���� N���� �ڿ��� �߿��� ���� ������ �ϳ��� ����
// nPm ������ �ǰڳ�? 
// �ߺ� �Է°��� map���� ���
// �̴� ������ ���� �� �ڸ����� ��� key���� �ѹ��� ���� �ȴ�. 
#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;
int n, m, input;
vector <int > res;
map <int, int> seq;

void solve(int cur) {
	if (cur == m) {
		for (int i = 0; i < res.size(); i++) {
			cout << res[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (auto i = seq.begin(); i != seq.end(); i++) {
		//�󵵼� ������� �� �������� �ֳĸ� ���� ���� ������ ��� �Ǵϱ�
			res.push_back(i->first);
			solve(cur + 1);
			res.pop_back();
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> input; 
		if (seq.find(input) == seq.end()) {
			seq.insert({ input, 1 });
		}
		else
			seq[input]++;
	}
	solve(0);
}