//같은 원소 중복 가능 
//고른 수열은 비내림차순 -> '조합' nCm
//따라서 st를 설정해야한다. -> st = arr[i-1] + 1;
//st는 직전에 arr에 넣은 원소의 인덱스보다 커야한다. 
//N개의 자연수 원소를 arr[]에 넣는게 아니라  N개 자연수의 인덱스를 넣는다. 

#include <iostream>
#include <vector>
#include <list>
#include <algorithm>
using namespace std;
int n, m; 
int arr[10];
int num[10];

void solve(int  cur) {
	if (cur == m) {
		for (int i = 0; i < m; i++) {
			cout << num[arr[i]] << ' ';
		}
		cout << '\n';
		return; 
	}
	int st = 0;
	if (cur != 0) {
		st = arr[cur-1];
	}
	for (int i = st; i < n; i++) {
			arr[cur] = i;
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
	sort(num, num + n);
	solve(0);
}