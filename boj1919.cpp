#include <iostream>
using namespace std;
int arr[2][26], cnt; 
string s1, s2;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> s1 >> s2; 

	for (auto alph : s1) {
		arr[0][alph - 'a']++;
		
	}
	for (auto alph : s2) {
		arr[1][alph - 'a']++;
	}

	for (int i = 0 ; i<26; i++){
		cnt += abs(arr[0][i] - arr[1][i]);
	}
	cout << cnt;
}
