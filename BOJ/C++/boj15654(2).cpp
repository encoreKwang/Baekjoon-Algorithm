//next_permutation을 이용한 순열 풀이. 중복 없음. 
//vector v에 입력 받는다. 
// m개를 뽑아야하므로 nPm = nCr * r! 
// 0이 m개인 벡터 k를 생성해서 m개를 뽑은 다음에 (nCr) 
//그 뽑은 순열의 가능한 모든 순열을 result에 넣는다. (r!) 
// result를 정렬해서 출력.
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
	sort(v.begin(), v.end()); //정렬
	fill(t.begin() + m, t.end(), 1); // 0 0 0 1로 나와야함.
	
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