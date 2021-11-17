#include <iostream>
#include <vector>
using namespace std;
string board[66];
string v;

bool check(int n , int x, int y) {
	for (int i = x; i < x+n; i ++)
		for (int j = y; j < y+n; j++) { // if 조건에 '<n'이 아니라 꼭 시작점 '<n+y'를 더해줘야한다!
			if (board[x][y] != board[i][j])
				return false;
		}
	return true;
}
void solve(int n, int x, int y ) {
	if (check(n, x, y)) {
		v += board[x][y];
		return;
	}
	else {
		v += '(';
		int divN = n / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0 ; j < 2; j ++ ){ // 4등분 + 왼쪽위->오른쪽위->왼쪽아래->오른쪽아래			
				solve(divN, x + i * divN, y + j * divN);
			}
		}
		v += ')';
	}

}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n; 
	cin >> n; 
	for (int i = 0; i < n; i++)
		cin >> board[i];
	solve(n, 0, 0);
	cout << v;
}