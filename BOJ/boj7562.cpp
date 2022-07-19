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
	//�����¿� Ž���� �ƴ϶� ����Ʈ�� Ž���̴�. �� ��������
	//�׸��� ��ü�� Ž���ϰų� ���������� Ž���ϴ°� �ƴ϶� Ư��ĭ�� Ž���ϴ� �ּڰ��ε�
	//bfs ������ ���� ��÷�� Ž���� �Ÿ��� �ּ� �Ÿ���. ť�� �Ÿ������� ���� �����̴�.
	cin >> tc;
	while (tc--) {
		queue <pair <int, int>> q;
		//dist �ʱ�ȭ
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