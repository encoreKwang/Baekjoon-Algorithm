#include <iostream>
using namespace std;
bool isused1[40]; //해당 인덱스 열에 대해 사용 여부를 표시. 인덱스 [y]
bool isused2[40]; //좌측 하단과 우측 상단을 잇는 대각선에 대해 사용 표시 -> 인덱스[x + y]
bool isused3[40]; //좌측 상단과 우측 하단을 잇는 대각선에 대해 사용 표시 -> 인덱스[x-y+n-1]
int cnt, n;

void func(int cur) {
	if (cur == n) {
		cnt++;
		return;
	}
	for (int y = 0; y < n; y++) { // curd이 x축을 의미하는구나 
		if (!isused1[y] && !isused2[cur+y] && !isused3[cur-y+n-1]) {
			isused1[y] = true; 
			isused2[cur + y] = true;
			isused3[cur - y + n - 1] = true;
			func(cur + 1);
			isused1[y] = false;
			isused2[cur + y] = false;
			isused3[cur - y + n - 1] = false;
		}
		
	}

}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n; 
	func(0);
	cout << cnt; 
}