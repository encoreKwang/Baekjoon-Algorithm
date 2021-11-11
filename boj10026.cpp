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

void BFS(int row , int col , char c) { //���ϻ����� �ƴ� ���. 
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

void BFS2(int row, int col, char c) { //���ϻ��� ���. 
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
			else //c�� R or G�� ��� 
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
	//���� for������ �湮�������� ĭ�� bfsŽ���Ѵ�. bfs�θ������� cnt +1
	//���ϻ����̶�� bfs�Ҷ� R, G�� ���� ĭ���� �ν�.
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> board[i];
	}
	int cnt = 0;
	for (int i = 0; i < n; i++) { //���ϻ����� �ƴ� ���
		for (int j = 0; j < n; j++) {
			if (vis[i][j] == false) {
				BFS(i, j, board[i][j]);
				cnt++;
			}
		}
	}
	cout << cnt << '\n';

	//vis �ʱ�ȭ
	for (int i = 0; i < n; i++) {
		fill(vis[i], vis[i] + n, 0);
	}

	cnt = 0;
	for (int i = 0; i < n; i++) { //���ϻ����� ���
		for (int j = 0; j < n; j++) {
			if (vis[i][j] == false) {
				BFS2(i, j, board[i][j]);
				cnt++;
			}
		}
	}
	cout << cnt << '\n';


}