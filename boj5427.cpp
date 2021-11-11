#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <tuple>

#define X first
#define Y second
using namespace std;
int tc; 
string board[1003];
int dist1[1003][1003];
int dist2[1003][1003];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	// 두 종류의 시작점 유형.. 불이 상근에게 즉, 한쪽으로만 영향을 주는 유형..  한 시작점을 먼저 bfs 탐색 해놓고 다른 시작점을 bfs탐색한다. ->dist 배열이 2개 필요 -> 범위를 벗어나면 탈출!
	cin >> tc; 
	while (tc--) {
		//초기화 
		for (int i = 0; i < 1003; i++) {
			board[i] = '\0'; //문자열의 시작을 널포인트('\0')로 하면 버퍼가 비워지게 된다.
			for (int j = 0; j < 1003; j++) {
				dist1[i][j] = 0;
				dist2[i][j] = 0;
			}
		}

		int n, m;
		cin >> m >> n;
		for (int i = 0; i < n; i++) {
			cin >> board[i];
		}
		
		queue <pair <int, int > > q1; //불에 대한 Queue
		for (int i = 0; i < n; i++) { // 불에 대해서... 
			for (int j = 0; j < m; j++) {
				if (board[i][j] == '*') {
					q1.push({ i, j }); //불의 dist는 0
				}
				else if (board[i][j] != '#') { // 불도 아니고 벽도 아니면 갈 수 있는 길
					dist1[i][j] = -1;
				}
			}
		}
		
		while (!q1.empty()) { //불 bfs
			pair <int, int> cur = q1.front();
			q1.pop();
			for (int i = 0; i < 4; i++) {
				int nx = cur.X + dx[i];
				int ny = cur.Y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (dist1[nx][ny] >= 0) continue;
				dist1[nx][ny] = dist1[cur.X][cur.Y] + 1;
				q1.push({ nx, ny });
			}
		}
		queue <pair <int, int > > q2;  //상근이에 대한 Queue
		for (int i = 0; i < n; i++) { // 상근이에 대해서...
			for (int j = 0; j < m; j++) {
				if (board[i][j] == '@') {
					q2.push({ i, j });
				}
				else if (board[i][j] != '#') { // 상근이도 아니고 벽도 아니면 갈 수 있는 길
					dist2[i][j] = -1;
				}
			}
		}
		bool escape = false;
		while (!q2.empty()) { //상근이 bfs
			auto cur = q2.front();
			q2.pop();
			for (int i = 0; i < 4; i++) {
				int nx = cur.X + dx[i];
				int ny = cur.Y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
					// 범위를 넘는 다는것은 탈출을 의미
					escape = true;
					break;
				}
				if (dist2[nx][ny] != -1 || (dist1[nx][ny]!=-1 &&dist1[nx][ny] <= dist2[cur.X][cur.Y] + 1)) continue; //불의 dist1가 더 작거나 같은면 즉, 먼저 도착하면 그 칸은 못간다.dist1[nx][ny]!=-1 이 조건은 불이 없을경우, 모든 갈 수 있는 칸이 -1로 남아있기 때문에 추가했다.
				dist2[nx][ny] = dist2[cur.X][cur.Y] + 1;
				q2.push({ nx, ny });
			}
			if (escape == true) {
				cout << dist2[cur.X][cur.Y] + 1 << '\n';
				break;
			}
		}
		//bfs 탐색을 다 돌아도 탈출을 못한다면 그건 탈출 실패를 의미 , escape는 여전히 false겟지
		if(escape == false ) cout << "IMPOSSIBLE" << '\n';
	}
}