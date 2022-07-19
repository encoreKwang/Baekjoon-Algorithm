//'N개의 자연수가 모두 달라야된다' 조건이 없다.
// '같은 수를 여러 번 골라도 된다' 조건 -> 빈도수가 상관 없다? 
// 따라서 N개의 자연수 중에서 같은 값들을 하나로 보고
// nPm 돌리면 되겠네? 
// 중복 입력값을 map으로 골라내
// 뽑는 순열의 각각 한 자리수에 모든 key값이 한번씩 들어가면 된다. 
#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;
int n, m, input;
vector <int > res;
map <int, int> seq;

void solve(int cur) {
	if (cur == m) {
		for (int i = 0; i < res.size(); i++) {
			cout << res[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (auto i = seq.begin(); i != seq.end(); i++) {
		//빈도수 상관없이 다 넣을꺼야 왜냐면 같은 수를 여러번 골라도 되니까
			res.push_back(i->first);
			solve(cur + 1);
			res.pop_back();
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> input; 
		if (seq.find(input) == seq.end()) {
			seq.insert({ input, 1 });
		}
		else
			seq[input]++;
	}
	solve(0);
}