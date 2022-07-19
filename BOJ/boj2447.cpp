#include <iostream>
#include <vector>
using namespace std;

char board[2187][2187];

void solve(int n, int x, int y) {
	if (n == 1) return;
	n = n / 3;
	for (int i = x+n; i < x+n + n; i++) {
		for (int j = y+n; j < y+n + n; j++) {
			board[i][j] = ' ';
		}
	}
	for (int i = 0; i < 3; i++) { // 9µîºÐ
		for (int j = 0; j < 3; j++) {
			if (board[x + i * n][y + j * n] == ' ')
				continue;
			solve(n, x + i * n, y + j * n);
		}
	}
	
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			board[i][j] = '*';
	solve(n, 0, 0);
	for (int i = 0; i < n; i++){
		for (int j = 0; j < n; j++) {
			cout << board[i][j];
		}
		cout << '\n';
	}
}