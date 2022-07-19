//백트래킹.. 
//오름차순이므로 조합 combination -> 반복문 start값을 변경 
// 중복 조합이므로 isused 필요 없음.
#include <iostream>
using namespace std;
int n, m;
int arr[10];

void solve(int cur) {
	if (cur == m) {//cur==m이면 출력 후 리턴
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}
	int st = 1; // cur이 0일때 
	if (cur != 0) st = arr[cur - 1]; //재귀 들어오기전에 arr에 넣은 값이 시작값. 중복이므로 +1은 안한다.
	for (int i = st; i <= n; i++) {
		arr[cur] = i; 
		solve(cur + 1);
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	solve(0);
}