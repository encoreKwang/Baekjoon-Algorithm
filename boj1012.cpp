#include <iostream>
#include <string>
#include <stack>
#include <queue>
using namespace std;
#define X first
#define Y second
int n, m, k; // ���߰� �ɾ����ִ� ��ġ�� ����
int board[51][51];
bool vis[51][51];
int dx[4] = { 0, 1, 0 ,-1 };
int dy[4] = { 1, 0, -1 , 0 };
int testCase;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//�������� �������ִ� ���.. ���� for��.. 
	//board �ʿ�, vis �ʿ� .. , dist�� ���ʿ�.. �����¿� �ʿ�.. 
	cin >> testCase; 
	while (testCase--) {
		cin >> m >> n >> k;
		for (int i = 0; i < n; i++) { // board, vis �ʱ�ȭ
			for (int j = 0; j < m; j++) {
				board[i][j] = 0;
				vis[i][j] = 0;
			}
		}
		while (k--) { // board ä���
			int x, y;
			cin >> y >> x; //���� �ش� �ϴ� ���� ���� �Է����ش�.
			board[x][y] = 1; 
		}

		int cnt = 0; // cnt�� �ʿ��� ������ ��.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) { //����for������ �������ִ� �������� ã�� ã���� bfsŽ��
				if (vis[i][j] || board[i][j] == 0) continue;
				cnt++; //
				vis[i][j] = true;
				queue <pair <int, int >> q;
				q.push({ i,j });
				while (!q.empty()) {
					pair <int , int> cur = q.front();
					q.pop();
					for (int i = 0; i < 4; i++) {
						int nx = cur.X + dx[i];
						int ny = cur.Y + dy[i];
						if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
						if (vis[nx][ny] || board[nx][ny] == 0) continue; //�̹� �湮�߰ų����߰� ���ٸ� �ѱ��. 
						vis[nx][ny] = true;
						q.push({ nx, ny });
					}
				}
			}
		}
		cout << cnt << "\n";
	}
}