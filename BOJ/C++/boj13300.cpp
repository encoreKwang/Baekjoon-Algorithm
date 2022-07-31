#include <iostream>
using namespace std;

int n, k; 
int man[6] , woman[6];
int cnt; 

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> k; 

	for (int i = 0; i < n; i++) {
		int gender, grade;
		cin >> gender >> grade; 
		if (gender == 0) {
			woman[grade-1] ++; 
		}
		else
			man[grade-1] ++;
		if (woman[grade-1] == k) {
			cnt++;
			woman[grade-1] = 0;
		}
		if (man[grade-1] == k) {
			cnt++;
			man[grade-1] = 0;
		}
	}
	for (int i = 0; i < 6; i++) {
		if (woman[i] > 0)
			cnt++;
		if (man[i] > 0)
			cnt++;
	}
	cout << cnt;
}