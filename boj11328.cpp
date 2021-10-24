#include <iostream>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n; 
	cin >> n;
	while (n--) {
		// �迭 �ε����� �� ���ĺ��� �ǹ��ϰԲ��ϴ� �迭
		int arr[26] = {};
		string s1, s2; 
		cin >> s1 >> s2;
		for (auto alph : s1) {
			arr[alph - 'a']++;
		}
		for (auto alph : s2) {
			arr[alph - 'a']--;
		}

		//0�� �ƴ� �迭�� ��Ұ� ���� ���, �ٸ� ���ڰ� �����ϹǷ� false
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
