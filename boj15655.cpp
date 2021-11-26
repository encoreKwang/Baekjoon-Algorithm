//백트래킹.. 고른 순열이 오름차순이면 조합 nCr, arr배열 isused 배열 필요. 
//조합이면 재귀 반복문의 시작점(st)을 설정해야한다.
// N개의 자연수를 입력 받을 num배열 필요.
// isused의 인덱스가 num의 원소를 의미할 수 없으므로 
//isused[i]가 num[i]의 사용여부를 나타내게끔 설정. 
// st를 설정할때 arr에 넣은 값을 인덱스(st)로 사용할 수 없기 때문에 
// 애초에 arr에 num원소를 삽입하는게 아니라 num[]에 대응하는 인덱스를 기록해두고 
// 나중에 출력할 때 인덱스에 대응되는 num원소를 출력한다. 

#include <iostream>
#include <algorithm>
using namespace std;
int n, m;
int arr[10];
int num[10];
bool isused[10];

void solve(int cur) { //현재 cur개까지 수를 택했음.
	if (cur == m) { // m개를 모두 택했으면 출력하고 리턴
		for (int i = 0; i < m; i++) {
			cout << num[arr[i]] << ' '; //arr에 기록해둔 인덱스에 대응되는 수를 출력.
		}
		cout << '\n';
		return;
	}
	 // 'cur==0일때' 'int st = 1;' 조건은 순차적인 원소들에서 arr에 i가 들어갈때
	 // 1부터 들어가야되기 때문에 사용한거다. 
	//이 문제는 인덱스가 0일때의 isused[0], num[0]을 쓰기 때문에 처음에 int st=0;. 
	int st = 0; // cur==0일때 
	if (cur != 0){ 
		st = arr[cur - 1] + 1;// 현재 재귀함수 호출 직전에 arr에 넣은 값(인덱스)보다 +1해서
		//반복문 시작한다. 
	}
	for (int i = st; i < n; i++) {
		if (isused[i] == false) {
			arr[cur] = i; //arr에 num원소를 넣는게 아니라 해당 인덱스를 넣는다 
			isused[i] = true;
			solve(cur + 1);
			isused[i] = false;
		}
	}

}
int main() {
	ios::sync_with_stdio(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> num[i];
	}
	sort(num, num + n); //정렬
	solve(0);
}