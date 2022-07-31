#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//�� ������ ���������̾�� �ϴϱ� �ᱹ�� combination�� ���ϴ°Ŵ�. 
	int n, m;
	cin >> n >> m;
	vector<int> v(n), k(n);
	iota(v.begin(), v.end(), 1);
	fill(k.begin() + m, k.end(), 1); // 0 0 0 1�� �Ǿ� next_permu�� ������������ ����Ѵ�.
	
	do {
		vector <int> tmp;
		for (int i = 0; i < n; i++)
			if (k[i] == 0)
				tmp.push_back(v[i]);
		for (auto i : tmp) cout << i << ' ';
		cout << '\n';
	} while (next_permutation(k.begin(), k.end()));
}