//next_permutation STL�Լ��� ����� Ǯ��
#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, m;
	cin >> n >> m;
	vector<int> v(n), w(n);
	//iota(v.begin(), v.end(), 1); //1 2 3... ���ʷ� ���͸� ä���.
	for (int i = 0; i < n; i++) {
		v[i] = i + 1;
	}
	fill(w.end() - m, w.end(), 1);// nCr�� ���� 0�� 1�� ä�ﶧ next_per�� ������ 
	// ���� ���� ������ ���������Ѵ�. -> 1�� ������ m(=r)�� ���ƾ��ϰ� ���� ���� �� 
	//-> �޺κ��� 1�� ä���.
	for (int i = 0; i < n; i++) {
		cout <<w[i] << ' ';
	}
	cout << '\n';

	vector<vector<int>> res;
	//1. n���� ������ "�ߺ� ����" m���� ���� �����ϴ� ����
	//2. m���� ���� ���� �� �ִ� ��� ������ �����ϴ� �������� �ɰ���. (nPr = nCr * r!) 
	//������ ���� next_permutation == nPn = n! 
	// nPr == n * (n-1) *(n-1)* ... *(n-r+1)
	do {
		vector<int> t;
		for (int i = 0; i < n; i++) if(w[i]) t.push_back(v[i]);
		do {
			res.push_back(t);
		} while (next_permutation(t.begin(), t.end()));
	} while (next_permutation(w.begin(), w.end()));

	sort(res.begin(), res.end());
	for (auto i : res) {
		for (auto j : i) {
			cout << j << ' ';
		}
		cout << '\n';
	}
}