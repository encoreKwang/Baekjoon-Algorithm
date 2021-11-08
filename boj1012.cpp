#include <iostream>
#include <string>
#include <stack>
#include <queue>
using namespace std;
#define X first
#define Y second
int n, m, k; // 베추가 심어져있는 위치의 개수
int board[51][51];
bool vis[51][51];
int dx[4] = { 0, 1, 0 ,-1 };
int dy[4] = { 1, 0, -1 , 0 };
int testCase;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//시작점이 떨어져있는 경우.. 이중 for문.. 
	//board 필요, vis 필요 .. , dist는 불필요.. 상하좌우 필요.. 
	cin >> testCase; 
	while (testCase--) {
		cin >> m >> n >> k;
		for (int i = 0; i < n; i++) { // board, vis 초기화
			for (int j = 0; j < m; j++) {
				board[i][j] = 0;
				vis[i][j] = 0;
			}
		}
		while (k--) { // board 채우기
			int x, y;
			cin >> y >> x; //열에 해당 하는 값을 먼저 입력해준다.
			board[x][y] = 1; 
		}

		int cnt = 0; // cnt는 필요한 벌레의 수.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) { //이중for문으로 떨어져있는 시작점을 찾고 찾으면 bfs탐색
				if (vis[i][j] || board[i][j] == 0) continue;
				cnt++; //
				vis[i][j] = true;
				queue <pair <int, int >> q;
				q.push({ i,j });
				while (!q.empty()) {
					pair <int , int> cur = q.front();
					q.pop();
					for (int i = 0; i < 4; i++) {
						int nx = cur.X + dx[i];
						int ny = cur.Y + dy[i];
						if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
						if (vis[nx][ny] || board[nx][ny] == 0) continue; //이미 방문했거나배추가 없다면 넘긴다. 
						vis[nx][ny] = true;
						q.push({ nx, ny });
					}
				}
			}
		}
		cout << cnt << "\n";
	}
}