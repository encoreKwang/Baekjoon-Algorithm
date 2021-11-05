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

int cnt = 0, mxSize=0 , curSize = 0; // 그림 수, 가장 큰 그림 넓이

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
		}
	}
	 
	for (int i = 0; i < n; i++) {//띄어져있는 다른 그림들의 첫공간에 접근하기 위해서 이중 for문이 필요.
		for (int j = 0; j < m; j++) {
			//if (board[i][j] == 1 && !vis[i][j]) {
			if (board[i][j] == 0 || vis[i][j]) continue;
			cnt++;
			queue <pair<int, int>> q;
			vis[i][j] = 1;
			q.push({i,j}); //한 그림의 첫 시작을 찾은거다
			curSize = 0;
			while(!q.empty()) {
				curSize++;
				pair<int, int> cur = q.front();
				q.pop();
				for (int dir = 0; dir < 4; dir++) { //상하좌우 칸 살펴보기
					int nx = cur.X + dx[dir];
					int ny = cur.Y + dy[dir];
					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue; //에러 때문에 행렬 조건 따지는것보다 범위를 먼저 따져야된다.
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