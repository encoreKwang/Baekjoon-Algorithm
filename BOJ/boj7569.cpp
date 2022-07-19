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
queue <tuple <int, int, int>> q; //Ʃ���� ���ҷ� �޴� ť

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//3�����̹Ƿ� �����¿� + �յ� Ž���� �ؾ��Ѵ�. 
	//�������� ������ ���ÿ�.. -> ��� �������� ť�� �ְ� bfs
	cin >> m >> n >> h; 
	for (int i = 0; i < h; i++) { //3���� �Է� �ޱ�
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
			int nz = get<0>(cur) + dz[dir]; //3������ �յڰ� Ʃ���� ù��° ���Ҵ�.
			int nx = get<1>(cur) + dx[dir];
			int ny = get<2>(cur) + dy[dir];
			if (nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= m || nz >= h)
				continue;
			if (board[nz][nx][ny] != 0 || dist[nz][nx][ny] >= 0)
				//���� ���� �丶�䰡 �ƴϰų� �̹� �湮�� ĭ�̶�� �ѱ��. 
				continue;
			dist[nz][nx][ny] = dist[get<0>(cur)][get<1>(cur)][get<2>(cur)] +1;
			q.push({ nz, nx, ny });
		}
	}
	// �丶�䰡 ��� ������ ���ϴ��� Ȯ��
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