//next_permutation STL함수를 사용한 풀이
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
	//iota(v.begin(), v.end(), 1); //1 2 3... 차례로 벡터를 채운다.
	for (int i = 0; i < n; i++) {
		v[i] = i + 1;
	}
	fill(w.end() - m, w.end(), 1);// nCr을 위해 0과 1을 채울때 next_per을 쓸려면 
	// 가장 작은 값으로 만들어놔야한다. -> 1의 갯수는 m(=r)과 같아야하고 가장 작은 값 
	//-> 뒷부분을 1로 채운다.
	for (int i = 0; i < n; i++) {
		cout <<w[i] << ' ';
	}
	cout << '\n';

	vector<vector<int>> res;
	//1. n개의 수에서 "중복 없이" m개의 수를 선택하는 과정
	//2. m개의 수로 만들 수 있는 모든 순열을 나열하는 과정으로 쪼갠다. (nPr = nCr * r!) 
	//수열에 대해 next_permutation == nPn = n! 
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