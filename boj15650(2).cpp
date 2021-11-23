#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;
int n, m;
int arr[10];
bool isused[10];

void solve(int cur) { //cur은 현재 arr에 넣은 갯수
	//base condition 다 넣어서 cur이 m이 됐을때 출력하고 리턴
	if (cur == m) {
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}  

	int st = 1; //시작지점, k = 0일 때에는 st = 1
	if (cur != 0)
		st = arr[cur - 1] + 1; // k != 0일떄는 st = arr[cur-1]+1 -> 재귀 들어가기 바로 전에 넣은 원소 + 1
	// cur == 1 , arr[1-1]
	for (int i = st; i <= n; i++) {
		//넣을때 true , 끝나고 false 
		if (isused[i] == false) {
			arr[cur] = i; //현재 넣은 갯수인 cur을 인덱스로 사용하고 i를 삽입
			isused[i] = true;
			solve(cur + 1);
			isused[i] = false;
		}
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//백트래킹으로 combination 구현..array랑 bool타입 배열이 필요.
	cin >> n >> m; 
	solve(0);

}