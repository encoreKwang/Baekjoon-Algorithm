#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//고른 수열이 오름차순이어야 하니까 결국은 combination을 구하는거다. 
	int n, m;
	cin >> n >> m;
	vector<int> v(n), k(n);
	iota(v.begin(), v.end(), 1);
	fill(k.begin() + m, k.end(), 1); // 0 0 0 1이 되야 next_permu로 오름차순으로 출력한다.
	
	do {
		vector <int> tmp;
		for (int i = 0; i < n; i++)
			if (k[i] == 0)
				tmp.push_back(v[i]);
		for (auto i : tmp) cout << i << ' ';
		cout << '\n';
	} while (next_permutation(k.begin(), k.end()));
}