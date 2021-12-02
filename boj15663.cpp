//중복되는 수열을 여러번 출력하면 안된다. -> 예시를 보니까 같은 조합은 아니다.
//순열인데 다만 N개의 자연수 원소가 동일한게 있을때 동일한 순열이 없도록 한다. 
//즉, 원소를 중복해도 되는데 단, 순열이 중복이 되면 안된다. 
//애초에 입력받을때 map으로 받는다. 이유는 다음과 같다. 
//map의 key는 중복되지않는다. map은 key값은 기준으로 자동으로 오름차순한다.
//value값에 빈도수를 넣는다. 반복문에서 iter이 key 개수 만큼 돈다.
//따라서 중복된 값이 있을때에도 key개수만큼만 돌게 되고 빈도수와 m값에 따라 같은 값이 얼마든지 중복되서 나올 수도 있다. 
//똑같은 순열이 나오지 않는다.
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int n, m, input;
vector <int>  res;
map <int, int >  seq; // n개의 자연수를 저장할 맵

void solve(int cur) {
	if (cur == m) {
		for (unsigned int i = 0; i < res.size(); i++) {
			cout << res[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (auto i = seq.begin(); i != seq.end(); i++) { //빈도수와 상관 없이 key개수만큼 돌게 된다. 
		if (i->second > 0) { //빈도가 남아있다면 중복되는 수라도 상관없다.
			i->second--; //빈도 하나 줄이기. 
			res.push_back(i->first); //key값 삽입
			solve(cur + 1);
			res.pop_back();//res에 넣었던거 제거
			i->second++; //탐색하고 나온거니까 다시 빈도 올려주기
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> input;
		if (seq.find(input) == seq.end()) { //map은 find해서 해당 key값이 없다면 마지막 iterator을 리턴함.
			seq.insert({ input, 1 });
		}
		else
			seq[input]++;//이미 key값이 있다면 value 값은 +1한다.
	}
	//map은 자동으로 key기준 오름차순되므로 정렬할 필요 없다.
	solve(0);
}