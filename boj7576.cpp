#include <iostream>
#include <string>
#include <stack>
#include <queue>
#define X first 
#define Y second
using namespace std;
int n, m;
int dist[1002][1002];
int board[1002][1002];
int dx[4] = {0, 1, -1, 0};
int dy[4] = {1, 0, 0 , -1};

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> m >> n;
	//1이 여러개 있을경우 동시에 시작할텐데.. 
	//익은 토마토가 동시에 ... 
	//익은 토마토를 애초에 모두 큐에 푸쉬하고 bfs에 돌리면 자연스럽게 순서 돌아가면서 bfs 동작!
	queue <pair <int, int>> q;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
			if (board[i][j] == 1)
				q.push({i,j}); 
			if (board[i][j] == 0)
				dist[i][j] = -1; //안익은 토마토는 -1, 익은 토마토와 토마토가 없는 칸은 dist가 0이다. 
		}	
	}
	while (!q.empty()) {
		pair<int,int> cur = q.front();
		q.pop();
		for (int dir = 0; dir < 4; dir++) {
			int nx = cur.X + dx[dir];
			int ny = cur.Y + dy[dir];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (dist[nx][ny] >= 0) continue;
			dist[nx][ny] = dist[cur.X][cur.Y] + 1; //기준점에서 1일 추가 
			q.push({nx, ny});
		}
	}
	int ans = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (dist[i][j] == -1) {
				cout << -1;
				return 0;
			}
			ans = max(ans, dist[i][j]);
		}
	}
	cout << ans;
}
