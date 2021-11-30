// 백트래킹.. nPr 인데 같은 원소의 중복이 가능하다. -> next_permutation은 사용 불가.
// N개의 자연수를 넣을 num배열이 필요하고, arr배열이 필요하고 
// 같은 원소의 중복 ->  모두 방문해야하므로 vis배열이 필요없다.


#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
using namespace std;

int n, m;
int arr[10];
int num[10];

void solve(int cur) {
	if (cur == m) { //cur는 현재 arr에 원소 들어간 갯수 
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (int i = 0; i < n; i++) {
			arr[cur] = num[i]; //arr배열의 cur인덱스에 num원소를 차례대로 넣는다.
			solve(cur + 1);
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> num[i];
	}
	sort(num, num + n); //정렬
	solve(0);
	
}