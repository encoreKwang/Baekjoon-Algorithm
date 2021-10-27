#include <iostream>
#include <list>
#include <vector>
using namespace std;
int n, k, len = 0;
//리스트에서 이전 노드/다음 노드를 가리키는 변수
int pre[5001];
int nxt[5001];
//요세푸스 순열을 저장하는 변수
vector <int> v;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n >> k;
	//n명 만큼 돌면서 pre와 nxt를 채우고 처음과 끝을 연결
	for (int i = 1; i <= n; i++) {
		pre[i] = (i == 1) ? n : i - 1;
		nxt[i] = (i == n) ? 1 : i + 1;
		len++;
	}

	//i가 1부터 k되는것을 반복.
	int i = 1;
	//연결 리스트를 순회하며 순열 생성
	for (int cur = 1; len != 0; cur = nxt[cur]) {
		//k번째일 때 제거
		if (i == k) {
			//cur값의 양쪽에 cur을 가리키는 원소들이 더이상 cur값을 가리키지 않도록 바꾼다. cur값이 없어지도록 바꾸는거다.
			pre[nxt[cur]] = pre[cur];
			nxt[pre[cur]] = nxt[cur];
			v.push_back(cur);
			i = 1;
			len--;
		}
		else i++;
	}
	//요세푸스 순열 출력
	cout << "<";
	for (size_t i = 0; i < v.size(); i++) {
		cout << v[i]; 
		if (i != v.size() - 1) cout << ", ";
	}
	cout << ">";
}