//고른 수열이 오름차순 이므로 조합.
//중복이 없으므로 next_permutation 가능
//조합 -> N개의 원소를 입력받을 백터 v, 0과 1을 저장할 백터 t 필요
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
	fill(t.begin()+ m, t.end(), 1); // m개만큼 0 그리고 그 다음 인덱스부터 나머지 1.
	
	sort(v.begin(), v.end()); //정렬
	do {
		for (int i = 0; i < n; i++) {
			if (t[i] == 0) {
				cout << v[i] << ' ';
			}
		}
		cout << '\n';
	} while (next_permutation(t.begin(), t.end()));

}