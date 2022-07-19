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
	// �� ������ ������ ����.. ���� ��ٿ��� ��, �������θ� ������ �ִ� ����..  �� �������� ���� bfs Ž�� �س��� �ٸ� �������� bfsŽ���Ѵ�. ->dist �迭�� 2�� �ʿ� -> ������ ����� Ż��!
	cin >> tc; 
	while (tc--) {
		//�ʱ�ȭ 
		for (int i = 0; i < 1003; i++) {
			board[i] = '\0'; //���ڿ��� ������ ������Ʈ('\0')�� �ϸ� ���۰� ������� �ȴ�.
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
		
		queue <pair <int, int > > q1; //�ҿ� ���� Queue
		for (int i = 0; i < n; i++) { // �ҿ� ���ؼ�... 
			for (int j = 0; j < m; j++) {
				if (board[i][j] == '*') {
					q1.push({ i, j }); //���� dist�� 0
				}
				else if (board[i][j] != '#') { // �ҵ� �ƴϰ� ���� �ƴϸ� �� �� �ִ� ��
					dist1[i][j] = -1;
				}
			}
		}
		
		while (!q1.empty()) { //�� bfs
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
		queue <pair <int, int > > q2;  //����̿� ���� Queue
		for (int i = 0; i < n; i++) { // ����̿� ���ؼ�...
			for (int j = 0; j < m; j++) {
				if (board[i][j] == '@') {
					q2.push({ i, j });
				}
				else if (board[i][j] != '#') { // ����̵� �ƴϰ� ���� �ƴϸ� �� �� �ִ� ��
					dist2[i][j] = -1;
				}
			}
		}
		bool escape = false;
		while (!q2.empty()) { //����� bfs
			auto cur = q2.front();
			q2.pop();
			for (int i = 0; i < 4; i++) {
				int nx = cur.X + dx[i];
				int ny = cur.Y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
					// ������ �Ѵ� �ٴ°��� Ż���� �ǹ�
					escape = true;
					break;
				}
				if (dist2[nx][ny] != -1 || (dist1[nx][ny]!=-1 &&dist1[nx][ny] <= dist2[cur.X][cur.Y] + 1)) continue; //���� dist1�� �� �۰ų� ������ ��, ���� �����ϸ� �� ĭ�� ������.dist1[nx][ny]!=-1 �� ������ ���� �������, ��� �� �� �ִ� ĭ�� -1�� �����ֱ� ������ �߰��ߴ�.
				dist2[nx][ny] = dist2[cur.X][cur.Y] + 1;
				q2.push({ nx, ny });
			}
			if (escape == true) {
				cout << dist2[cur.X][cur.Y] + 1 << '\n';
				break;
			}
		}
		//bfs Ž���� �� ���Ƶ� Ż���� ���Ѵٸ� �װ� Ż�� ���и� �ǹ� , escape�� ������ false����
		if(escape == false ) cout << "IMPOSSIBLE" << '\n';
	}
}