#include <iostream>
using namespace std;
int n, m;
int arr[10]; 

void solve(int cur) {
	if (cur == m) {// cur과 m이 같아지면 출력 후 리턴 
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (int i = 1; i <= n; i++) {
			arr[cur] = i;
			solve(cur + 1);
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m; 
	// 중복 순열.. isused 배열 필요 없음
	solve(0);

}