#include <iostream>
#include <string>
#include<cstring>
#include <queue>
#include <vector>

#define X first
#define Y second
using namespace std;
int n;
string board[100];
bool vis[100][100];
int dx[4] = { 1, -1, 0, 0 };
int dy[4] = { 0, 0, 1, -1 };

void BFS(int row , int col , char c) { //적록색약이 아닌 사람. 
	vis[row][col] = true; 
	queue <pair<int, int >> q; 
	q.push({ row, col });
	while (!q.empty()) {
		pair <int, int> cur = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = cur.X+dx[i];
			int ny = cur.Y + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if (board[nx][ny] != c || vis[nx][ny] == true) continue;
			vis[nx][ny] = true;
			q.push({ nx, ny });
		}

	}
}

void BFS2(int row, int col, char c) { //적록색약 사람. 
	vis[row][col] = true;
	queue <pair<int, int >> q;
	q.push({ row, col });
	while (!q.empty()) {
		pair <int, int> cur = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = cur.X + dx[i];
			int ny = cur.Y + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if (vis[nx][ny] == true) continue;
			if (c == 'B') {
				if (board[nx][ny] != 'B')
					continue;
			}
			else //c가 R or G인 경우 
				if (board[nx][ny] == 'B')
					continue;
			vis[nx][ny] = true;
			q.push({ nx, ny });
		}

	}
}


int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//이중 for문으로 방문하지않은 칸을 bfs탐색한다. bfs부를때마다 cnt +1
	//적록색약이라면 bfs할때 R, G을 같은 칸으로 인식.
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> board[i];
	}
	int cnt = 0;
	for (int i = 0; i < n; i++) { //적록색약이 아닌 사람
		for (int j = 0; j < n; j++) {
			if (vis[i][j] == false) {
				BFS(i, j, board[i][j]);
				cnt++;
			}
		}
	}
	cout << cnt << '\n';

	//vis 초기화
	for (int i = 0; i < n; i++) {
		fill(vis[i], vis[i] + n, 0);
	}

	cnt = 0;
	for (int i = 0; i < n; i++) { //적록색약인 사람
		for (int j = 0; j < n; j++) {
			if (vis[i][j] == false) {
				BFS2(i, j, board[i][j]);
				cnt++;
			}
		}
	}
	cout << cnt << '\n';


}