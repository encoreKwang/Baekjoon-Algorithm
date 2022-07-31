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
	//1�� ������ ������� ���ÿ� �������ٵ�.. 
	//���� �丶�䰡 ���ÿ� ... 
	//���� �丶�並 ���ʿ� ��� ť�� Ǫ���ϰ� bfs�� ������ �ڿ������� ���� ���ư��鼭 bfs ����!
	queue <pair <int, int>> q;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
			if (board[i][j] == 1)
				q.push({i,j}); 
			if (board[i][j] == 0)
				dist[i][j] = -1; //������ �丶��� -1, ���� �丶��� �丶�䰡 ���� ĭ�� dist�� 0�̴�. 
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
			dist[nx][ny] = dist[cur.X][cur.Y] + 1; //���������� 1�� �߰� 
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
