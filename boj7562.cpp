#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <tuple>

using namespace std;
#define X first
#define Y second
int l, tc; 
int board[303][303];
int dist[303][303];
int dx[8] = {1, -1, 2, -2, 1, -1, 2, -2};
int dy[8] = {2, 2, 1, 1, -2, -2, -1, -1};

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//상하좌우 탐색이 아니라 나이트의 탐색이다. 총 여덟방향
	//그리고 전체를 탐색하거나 끝날때까지 탐색하는게 아니라 특정칸을 탐색하는 최솟값인데
	//bfs 성질상 가장 맨첨에 탐색한 거리가 최소 거리다. 큐에 거리순으로 담기기 때문이다.
	cin >> tc;
	while (tc--) {
		queue <pair <int, int>> q;
		//dist 초기화
		cin >> l;
		for (int i = 0; i < l; i++)
			fill(dist[i], dist[i] + l, -1);
		bool findAns = false;
		int x, y, targetX, targetY; 
		cin >> x >> y >> targetX >> targetY;
		dist[x][y] = 0; 
		q.push({ x, y });
		while (!q.empty()) { // bfs
			pair <int, int > cur = q.front();
			q.pop();
			if (cur.X == targetX && cur.Y == targetY) {
				cout << dist[cur.X][cur.Y] << '\n';
				continue;
			}
			for (int i = 0; i < 8; i++) {
				int nx = cur.X + dx[i];
				int ny = cur.Y + dy[i];
				if (nx < 0 || nx >= l || ny < 0 || ny >= l) continue; 
				if (dist[nx][ny] != -1) continue;
				if (nx == targetX && ny == targetY) {
					findAns = true;
					break;
				}
				dist[nx][ny] = dist[cur.X][cur.Y] + 1;
				q.push({ nx, ny });
			}
			if (findAns == true) {
				cout << dist[cur.X][cur.Y] + 1 << '\n';
				break;
			}

		}

	}

}