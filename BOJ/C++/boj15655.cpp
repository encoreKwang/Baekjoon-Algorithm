//��Ʈ��ŷ.. �� ������ ���������̸� ���� nCr, arr�迭 isused �迭 �ʿ�. 
//�����̸� ��� �ݺ����� ������(st)�� �����ؾ��Ѵ�.
// N���� �ڿ����� �Է� ���� num�迭 �ʿ�.
// isused�� �ε����� num�� ���Ҹ� �ǹ��� �� �����Ƿ� 
//isused[i]�� num[i]�� ��뿩�θ� ��Ÿ���Բ� ����. 
// st�� �����Ҷ� arr�� ���� ���� �ε���(st)�� ����� �� ���� ������ 
// ���ʿ� arr�� num���Ҹ� �����ϴ°� �ƴ϶� num[]�� �����ϴ� �ε����� ����صΰ� 
// ���߿� ����� �� �ε����� �����Ǵ� num���Ҹ� ����Ѵ�. 

#include <iostream>
#include <algorithm>
using namespace std;
int n, m;
int arr[10];
int num[10];
bool isused[10];

void solve(int cur) { //���� cur������ ���� ������.
	if (cur == m) { // m���� ��� �������� ����ϰ� ����
		for (int i = 0; i < m; i++) {
			cout << num[arr[i]] << ' '; //arr�� ����ص� �ε����� �����Ǵ� ���� ���.
		}
		cout << '\n';
		return;
	}
	 // 'cur==0�϶�' 'int st = 1;' ������ �������� ���ҵ鿡�� arr�� i�� ����
	 // 1���� ���ߵǱ� ������ ����ѰŴ�. 
	//�� ������ �ε����� 0�϶��� isused[0], num[0]�� ���� ������ ó���� int st=0;. 
	int st = 0; // cur==0�϶� 
	if (cur != 0){ 
		st = arr[cur - 1] + 1;// ���� ����Լ� ȣ�� ������ arr�� ���� ��(�ε���)���� +1�ؼ�
		//�ݺ��� �����Ѵ�. 
	}
	for (int i = st; i < n; i++) {
		if (isused[i] == false) {
			arr[cur] = i; //arr�� num���Ҹ� �ִ°� �ƴ϶� �ش� �ε����� �ִ´� 
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
	sort(num, num + n); //����
	solve(0);
}