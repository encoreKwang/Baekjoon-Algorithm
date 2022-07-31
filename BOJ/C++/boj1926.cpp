#include <iostream>
#include <string>
#include <stack>
#include <queue>

using namespace std;
#define X first
#define Y second

int board[502][502];
bool vis[502][502];
int n, m;
int dx[4] = { 0, 1, -1, 0 };
int dy[4] = { 1, 0, 0, -1 };

int cnt = 0, mxSize=0 , curSize = 0; // �׸� ��, ���� ū �׸� ����

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
		}
	}
	 
	for (int i = 0; i < n; i++) {//������ִ� �ٸ� �׸����� ù������ �����ϱ� ���ؼ� ���� for���� �ʿ�.
		for (int j = 0; j < m; j++) {
			//if (board[i][j] == 1 && !vis[i][j]) {
			if (board[i][j] == 0 || vis[i][j]) continue;
			cnt++;
			queue <pair<int, int>> q;
			vis[i][j] = 1;
			q.push({i,j}); //�� �׸��� ù ������ ã���Ŵ�
			curSize = 0;
			while(!q.empty()) {
				curSize++;
				pair<int, int> cur = q.front();
				q.pop();
				for (int dir = 0; dir < 4; dir++) { //�����¿� ĭ ���캸��
					int nx = cur.X + dx[dir];
					int ny = cur.Y + dy[dir];
					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue; //���� ������ ��� ���� �����°ͺ��� ������ ���� �����ߵȴ�.
					if (vis[nx][ny] || board[nx][ny] != 1)
						continue;
					vis[nx][ny] = 1;
					q.push({ nx, ny });
				}
			}
			mxSize = max(mxSize, curSize);
		}
	}
	cout << cnt <<'\n' << mxSize;
}