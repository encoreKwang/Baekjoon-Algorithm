#include <iostream>
using namespace std;
int n, s;
int arr[30];
int cnt = 0;

void func(int cur, int tot) { //tot은 현재까지의 합.
	if (cur == n) { //모든 원소에 대해 합할지 안할지 선택한 한 가지의 경우의 수를 구할 때 당연히 cur횟수가 원소의 수와 같아진다.
		if (tot == s) cnt++; 
		return; 
	}
	func(cur + 1, tot); //현재 원소를 합하지 않는 경우
	func(cur + 1, tot + arr[cur]); //현재 원소를 합하는 경우
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> s;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	func(0, 0);
	if (s == 0) cnt--; // s가 0일때 공집합도 카운트 되므로 하나 빼야된다.
	cout << cnt;
}