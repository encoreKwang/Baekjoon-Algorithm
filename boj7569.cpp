#include <iostream>
#include <string>
#include<cstring>
#include <queue>
#include <vector>
#include <tuple>
#define MAX 103

using namespace std;

int n, m, h;
int board[MAX][MAX][MAX];
int dist[MAX][MAX][MAX];
int dx[6] = { 1, -1, 0, 0, 0, 0 };
int dy[6] = { 0, 0, 1, -1, 0, 0 };
int dz[6] = { 0, 0, 0, 0, 1, -1 };
queue <tuple <int, int, int>> q; //튜플을 원소로 받는 큐

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//3차원이므로 상하좌우 + 앞뒤 탐색을 해야한다. 
	//시작점이 여러개 동시에.. -> 모든 시작점을 큐에 넣고 bfs
	cin >> m >> n >> h; 
	for (int i = 0; i < h; i++) { //3차원 입력 받기
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				cin >> board[i][j][k];
				if (board[i][j][k] == 1)
					q.push({ i,j,k });
				else
					dist[i][j][k] = -1;
			}
		}
	}

	while (!q.empty()) { //bfs
		tuple<int,int,int> cur = q.front();
		q.pop();
		for (int dir = 0; dir < 6; dir++) {
			int nz = get<0>(cur) + dz[dir]; //3차원의 앞뒤가 튜플의 첫번째 원소다.
			int nx = get<1>(cur) + dx[dir];
			int ny = get<2>(cur) + dy[dir];
			if (nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= m || nz >= h)
				continue;
			if (board[nz][nx][ny] != 0 || dist[nz][nx][ny] >= 0)
				//익지 않은 토마토가 아니거나 이미 방문한 칸이라면 넘긴다. 
				continue;
			dist[nz][nx][ny] = dist[get<0>(cur)][get<1>(cur)][get<2>(cur)] +1;
			q.push({ nz, nx, ny });
		}
	}
	// 토마토가 모두 익지는 못하는지 확인
	int mx = 0; 
	for (int i = 0; i < h; i++){
		for (int j = 0; j < n; j++){
			for (int k = 0; k < m; k++) {
				if (board[i][j][k] == 0) {
					if (dist[i][j][k] == -1) {
						cout << -1;
						return 0;
					}
					mx = max(mx, dist[i][j][k]);
				}
			}
		}
	}
	cout << mx;
}