#include <iostream>
#include <string>
#include <stack>
#include <queue>
#define X first
#define Y second 
using namespace std;
int r, c;
string board[1001];
int dist1[1001][1001]; //불의 전파 시간
int dist2[1001][1001]; //J의 이동시간
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

int main(void) {
	//먼저 불이 지나가는 bfs 구하고 J가 가는 길 bfs를 구한다.
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> r >> c;
	for (int i = 0; i < r; i++) { //모든 dist -1로 초기화
		fill(dist1[i], dist1[i] + c, -1);
		fill(dist2[i], dist2[i] + c, -1);
	}
	for (int i = 0; i < r; i++) {
		cin >> board[i];
	}

	queue <pair<int, int>> q1;
	queue <pair<int, int>> q2;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if (board[i][j] == 'F') { //불을 먼저 푸시
				q1.push({ i, j });
				dist1[i][j] = 0;
			}
			if (board[i][j] == 'J') { //불을 먼저 푸시
				q2.push({ i, j });
				dist2[i][j] = 0;
			}
		}
	}
		//불에 대한 bfs
		while (!q1.empty()) {
			pair <int, int > cur = q1.front();
			q1.pop();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.X + dx[dir];
				int ny = cur.Y + dy[dir];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
				if (dist1[nx][ny] >= 0 || board[nx][ny] == '#') continue;
				dist1[nx][ny] = dist1[cur.X][cur.Y] + 1;
				q1.push({ nx, ny });
			}
		}
	
		//J에 대한 bfs
		while (!q2.empty()) {
			pair <int, int> cur = q2.front();
			q2.pop();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.X + dx[dir];
				int ny = cur.Y + dy[dir];
				if (nx < 0 || nx >= r || ny <0 || ny >= c) { // 범위를 넘긴다는 것은 곧 탈출에 성공했다는 의미. 큐에 반드시 거리순으로 들어가므로 최초에 탈출한 시간을 입력하면 됨. 
					cout << dist2[cur.X][cur.Y] + 1;
					return 0;
				}
				if (dist2[nx][ny] >= 0 || board[nx][ny] == '#') continue;
				if (dist1[nx][ny] != -1 && dist1[nx][ny] <= dist2[cur.X][cur.Y] + 1) continue;
				//해당 칸에 J가 가는 최소 시간이 불길이 닿는 시간보다 같거나 크면 해당 칸에 갈 수 없다. 불의 전파 시간을 조건에 추가.
				dist2[nx][ny] = dist2[cur.X][cur.Y] + 1;
				q2.push({ nx, ny });
			}
		}
		cout << "IMPOSSIBLE"; // 여기에 도달했다는 것은 탈출에 실패를 의미. 
	
}