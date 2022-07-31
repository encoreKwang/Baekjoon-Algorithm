#include <iostream>
#include <list>
#include <vector>
using namespace std;
int n, k, len = 0;
//����Ʈ���� ���� ���/���� ��带 ����Ű�� ����
int pre[5001];
int nxt[5001];
//�似Ǫ�� ������ �����ϴ� ����
vector <int> v;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n >> k;
	//n�� ��ŭ ���鼭 pre�� nxt�� ä��� ó���� ���� ����
	for (int i = 1; i <= n; i++) {
		pre[i] = (i == 1) ? n : i - 1;
		nxt[i] = (i == n) ? 1 : i + 1;
		len++;
	}

	//i�� 1���� k�Ǵ°��� �ݺ�.
	int i = 1;
	//���� ����Ʈ�� ��ȸ�ϸ� ���� ����
	for (int cur = 1; len != 0; cur = nxt[cur]) {
		//k��°�� �� ����
		if (i == k) {
			//cur���� ���ʿ� cur�� ����Ű�� ���ҵ��� ���̻� cur���� ����Ű�� �ʵ��� �ٲ۴�. cur���� ���������� �ٲٴ°Ŵ�.
			pre[nxt[cur]] = pre[cur];
			nxt[pre[cur]] = nxt[cur];
			v.push_back(cur);
			i = 1;
			len--;
		}
		else i++;
	}
	//�似Ǫ�� ���� ���
	cout << "<";
	for (size_t i = 0; i < v.size(); i++) {
		cout << v[i]; 
		if (i != v.size() - 1) cout << ", ";
	}
	cout << ">";
}