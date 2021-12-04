#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
using namespace std;

#define MAX 8
typedef map<int, int>::iterator it;

map<int, int> seq;
int n, m, input;
vector<int> res;

void backtracking(int cnt, it idx) {
	if (cnt == m) {
		for (int i = 0; i < res.size(); i++) {
			cout << res[i] << " ";
		}
		cout << '\n';
		return;
	}
	for (it i = idx; i != seq.end(); i++) {
		if (i->second > 0) {
			i->second--;
			res.push_back(i->first);
			backtracking(cnt + 1, i);
			res.pop_back();
			i->second++;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> input;
		if (seq.find(input) == seq.end()) seq.insert({ input,1 });
		else seq[input]++;
	}
	backtracking(0, seq.begin());
	return 0;
}