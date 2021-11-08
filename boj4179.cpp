#include <iostream>
#include <string>
#include <stack>
#include <queue>
#define X first
#define Y second 
using namespace std;
int r, c;
string board[1001];
int dist1[1001][1001]; //���� ���� �ð�
int dist2[1001][1001]; //J�� �̵��ð�
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

int main(void) {
	//���� ���� �������� bfs ���ϰ� J�� ���� �� bfs�� ���Ѵ�.
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> r >> c;
	for (int i = 0; i < r; i++) { //��� dist -1�� �ʱ�ȭ
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
			if (board[i][j] == 'F') { //���� ���� Ǫ��
				q1.push({ i, j });
				dist1[i][j] = 0;
			}
			if (board[i][j] == 'J') { //���� ���� Ǫ��
				q2.push({ i, j });
				dist2[i][j] = 0;
			}
		}
	}
		//�ҿ� ���� bfs
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
	
		//J�� ���� bfs
		while (!q2.empty()) {
			pair <int, int> cur = q2.front();
			q2.pop();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.X + dx[dir];
				int ny = cur.Y + dy[dir];
				if (nx < 0 || nx >= r || ny <0 || ny >= c) { // ������ �ѱ�ٴ� ���� �� Ż�⿡ �����ߴٴ� �ǹ�. ť�� �ݵ�� �Ÿ������� ���Ƿ� ���ʿ� Ż���� �ð��� �Է��ϸ� ��. 
					cout << dist2[cur.X][cur.Y] + 1;
					return 0;
				}
				if (dist2[nx][ny] >= 0 || board[nx][ny] == '#') continue;
				if (dist1[nx][ny] != -1 && dist1[nx][ny] <= dist2[cur.X][cur.Y] + 1) continue;
				//�ش� ĭ�� J�� ���� �ּ� �ð��� �ұ��� ��� �ð����� ���ų� ũ�� �ش� ĭ�� �� �� ����. ���� ���� �ð��� ���ǿ� �߰�.
				dist2[nx][ny] = dist2[cur.X][cur.Y] + 1;
				q2.push({ nx, ny });
			}
		}
		cout << "IMPOSSIBLE"; // ���⿡ �����ߴٴ� ���� Ż�⿡ ���и� �ǹ�. 
	
}