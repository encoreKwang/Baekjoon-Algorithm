#include <iostream>

using namespace std;
int n, cnt[3];
int board[2187][2187];

void solve(int n , int row, int col ) {
	bool isFail = false;
	int success = -2; 
	for (int i = row; i < row + n; i++)
		for (int j = col; j < col + n; j++) {
			if (board[i][j] != board[row][col]) {
				isFail = true;
				break;
			}
			else
				success = board[0][0];
		}
	if (isFail == true) {
		int divThird = n / 3; //divThird가 인자로 들어가야겠다.
		for (int k = 0; k < 3; k++) { //총 9번만큼 불러야됨. 9등분
			for (int l = 0; l < 3; l++) {
				solve(divThird, row + divThird * k , col + divThird * l);
			}
		}
	}
	else {
		cnt[board[row][col] + 1] += 1;
		return;
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n;
	for (int i =0;i<n; i ++ )
		for (int j = 0; j < n; j++) {
			cin >> board[i][j];
		}
	solve(n, 0, 0);
	for (int i = 0; i < 3; i++)
		cout << cnt[i] << '\n';
}