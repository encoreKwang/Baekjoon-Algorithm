#include <iostream>
#include <queue>
using namespace std;
int n, k;
int dist[100001]; 
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//bfs할때 상하좌우로 탐색하는게 아니라 +1 -1 *2로 한다.
	cin >> n >> k; 
	fill(dist, dist + 100001, -1);
	dist[n] = 0;
	queue <int> q; 
	q.push(n);
	while (dist[k] == -1) {
		int cur = q.front();
		q.pop();
		for (int nxt : {cur-1, cur+1, 2*cur}){
			if (nxt < 0 || nxt > 100000) continue;
			if (dist[nxt] != -1) continue;
			dist[nxt] = dist[cur] + 1;
			q.push(nxt);
		}
	}
	cout << dist[k];
}