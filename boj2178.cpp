#include <iostream>
#include <string>
#include <stack>
#include <queue>

#define X first
#define Y second
using namespace std;
int dist[102][102]; 
string board[102]; //�� ��Ʈ������ ���������� ���߹迭ó�� ���±���
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int n, m;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) 
		cin >> board[i];
	for (int i = 0; i < n; i++) { //dist �迭�� -1�� �ʱ�ȭ�س����� ���� vis�迭 ���� �ش� ������ ����� �� �ִ�. 
		fill(dist[i], dist[i] + m, -1);
	}
	queue <pair<int, int>> q;
	q.push({ 0, 0 });
	dist[0][0] = 1; // ���� ��ġ�� ���ԵǼ� �Ÿ� 1�� �Է�
	while (!q.empty()) {
		pair <int,int> cur = q.front();
		q.pop();
		for (int dir= 0; dir < 4; dir++) {
			int nx = cur.X + dx[dir];
			int ny = cur.Y + dy[dir];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (dist[nx][ny] >= 0 || board[nx][ny] != '1') continue; //board���ڿ� �迭�� char �����̹Ƿ� ���� ����ǥ ����ߵȴ�.
			dist[nx][ny] = dist[cur.X][cur.Y] + 1;//������ ���� �Ÿ� 1��ŭ �þ��.
			q.push({ nx, ny });
		}
	}
	cout << dist[n-1][m-1];
}