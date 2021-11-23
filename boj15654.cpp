//백트래킹, 중복 없는 순열 -> arr, isused 배열 필요. 
//주어진 N개의 자연수를 받을 배열  num 필요.
// 근데 bool타입 isused의 인덱스로 N개의 자연수를 나타낼 수 없음
// 따라서 isused[i]는 num[i]의 사용 상태를 나타내는 것으로 설정.
#include <iostream>
#include <algorithm>
using namespace std;
int n, m; 
int arr[10], isused[10];
int num[10];

void solve(int cur) {
	if (cur == m) {//cur == m 이면 출력하고 리턴
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return; 
	}
	for (int i = 0; i < n; i++) {
		if (isused[i] == false) {
			arr[cur] = num[i]; //num배열의 해당 인덱스을 삽입한다. 
			isused[i] = true;
			solve(cur + 1);
			isused[i] = false;
		}
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		cin >> num[i];
	sort(num, num+n); //정렬
	solve(0);
}