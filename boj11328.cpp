#include <iostream>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n; 
	cin >> n;
	while (n--) {
		// 배열 인덱스가 각 알파벳을 의미하게끔하는 배열
		int arr[26] = {};
		string s1, s2; 
		cin >> s1 >> s2;
		for (auto alph : s1) {
			arr[alph - 'a']++;
		}
		for (auto alph : s2) {
			arr[alph - 'a']--;
		}

		//0이 아닌 배열의 요소가 있을 경우, 다른 문자가 존재하므로 false
		bool isPossible = true;
		for (int i : arr) {
			if (i != 0) isPossible = false;
		}

		if (isPossible)
			cout << "Posible\n";
		else
			cout << "Imposible\n";

	}
}
