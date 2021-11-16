#include <iostream>

using namespace std;
int n;

void _bar(const char* str, int stk) {
	for (int j = 0; j < stk; ++j) cout << "____";
	cout << str;
}

void solve(int cnt) {
	_bar("\"����Լ��� ������?\"\n", cnt);
	if (cnt == n) _bar("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"\n", cnt);
	else {
		_bar("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.\n", cnt);
		_bar("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.\n", cnt);
		_bar("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"\n", cnt);
		solve(cnt + 1);
	}
	_bar("��� �亯�Ͽ���.\n", cnt);
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n;
	cout << "��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.\n";
	solve(0);
}