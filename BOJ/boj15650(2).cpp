#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;
int n, m;
int arr[10];
bool isused[10];

void solve(int cur) { //cur�� ���� arr�� ���� ����
	//base condition �� �־ cur�� m�� ������ ����ϰ� ����
	if (cur == m) {
		for (int i = 0; i < m; i++) {
			cout << arr[i] << ' ';
		}
		cout << '\n';
		return;
	}  

	int st = 1; //��������, k = 0�� ������ st = 1
	if (cur != 0)
		st = arr[cur - 1] + 1; // k != 0�ϋ��� st = arr[cur-1]+1 -> ��� ���� �ٷ� ���� ���� ���� + 1
	// cur == 1 , arr[1-1]
	for (int i = st; i <= n; i++) {
		//������ true , ������ false 
		if (isused[i] == false) {
			arr[cur] = i; //���� ���� ������ cur�� �ε����� ����ϰ� i�� ����
			isused[i] = true;
			solve(cur + 1);
			isused[i] = false;
		}
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	//��Ʈ��ŷ���� combination ����..array�� boolŸ�� �迭�� �ʿ�.
	cin >> n >> m; 
	solve(0);

}