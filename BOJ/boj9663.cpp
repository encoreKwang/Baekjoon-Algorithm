#include <iostream>
using namespace std;
bool isused1[40]; //�ش� �ε��� ���� ���� ��� ���θ� ǥ��. �ε��� [y]
bool isused2[40]; //���� �ϴܰ� ���� ����� �մ� �밢���� ���� ��� ǥ�� -> �ε���[x + y]
bool isused3[40]; //���� ��ܰ� ���� �ϴ��� �մ� �밢���� ���� ��� ǥ�� -> �ε���[x-y+n-1]
int cnt, n;

void func(int cur) {
	if (cur == n) {
		cnt++;
		return;
	}
	for (int y = 0; y < n; y++) { // curd�� x���� �ǹ��ϴ±��� 
		if (!isused1[y] && !isused2[cur+y] && !isused3[cur-y+n-1]) {
			isused1[y] = true; 
			isused2[cur + y] = true;
			isused3[cur - y + n - 1] = true;
			func(cur + 1);
			isused1[y] = false;
			isused2[cur + y] = false;
			isused3[cur - y + n - 1] = false;
		}
		
	}

}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n; 
	func(0);
	cout << cnt; 
}