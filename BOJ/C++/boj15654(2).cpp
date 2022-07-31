//next_permutation�� �̿��� ���� Ǯ��. �ߺ� ����. 
//vector v�� �Է� �޴´�. 
// m���� �̾ƾ��ϹǷ� nPm = nCr * r! 
// 0�� m���� ���� k�� �����ؼ� m���� ���� ������ (nCr) 
//�� ���� ������ ������ ��� ������ result�� �ִ´�. (r!) 
// result�� �����ؼ� ���.
#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, m;
	cin >> n >> m;
	vector <int> v(n), t(n); 
	for (int i = 0; i < n; i++)
		cin >> v[i];
	sort(v.begin(), v.end()); //����
	fill(t.begin() + m, t.end(), 1); // 0 0 0 1�� ���;���.
	
	vector <vector<int>> result; 
	do {
		vector <int> tmp;
		for (int i = 0; i < n; i++)
			if (t[i] == 0)
				tmp.push_back(v[i]);
		do {
			result.push_back(tmp);
		} while (next_permutation(tmp.begin(), tmp.end()));
	} while (next_permutation(t.begin(), t.end()));
	
	sort(result.begin(), result.end());
	for (auto i : result){
		for (auto j : i) {
			cout << j << ' ';
		}
		cout << '\n';
	}
}