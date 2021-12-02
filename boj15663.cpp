//�ߺ��Ǵ� ������ ������ ����ϸ� �ȵȴ�. -> ���ø� ���ϱ� ���� ������ �ƴϴ�.
//�����ε� �ٸ� N���� �ڿ��� ���Ұ� �����Ѱ� ������ ������ ������ ������ �Ѵ�. 
//��, ���Ҹ� �ߺ��ص� �Ǵµ� ��, ������ �ߺ��� �Ǹ� �ȵȴ�. 
//���ʿ� �Է¹����� map���� �޴´�. ������ ������ ����. 
//map�� key�� �ߺ������ʴ´�. map�� key���� �������� �ڵ����� ���������Ѵ�.
//value���� �󵵼��� �ִ´�. �ݺ������� iter�� key ���� ��ŭ ����.
//���� �ߺ��� ���� ���������� key������ŭ�� ���� �ǰ� �󵵼��� m���� ���� ���� ���� �󸶵��� �ߺ��Ǽ� ���� ���� �ִ�. 
//�Ȱ��� ������ ������ �ʴ´�.
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int n, m, input;
vector <int>  res;
map <int, int >  seq; // n���� �ڿ����� ������ ��

void solve(int cur) {
	if (cur == m) {
		for (unsigned int i = 0; i < res.size(); i++) {
			cout << res[i] << ' ';
		}
		cout << '\n';
		return;
	}
	for (auto i = seq.begin(); i != seq.end(); i++) { //�󵵼��� ��� ���� key������ŭ ���� �ȴ�. 
		if (i->second > 0) { //�󵵰� �����ִٸ� �ߺ��Ǵ� ���� �������.
			i->second--; //�� �ϳ� ���̱�. 
			res.push_back(i->first); //key�� ����
			solve(cur + 1);
			res.pop_back();//res�� �־����� ����
			i->second++; //Ž���ϰ� ���°Ŵϱ� �ٽ� �� �÷��ֱ�
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> input;
		if (seq.find(input) == seq.end()) { //map�� find�ؼ� �ش� key���� ���ٸ� ������ iterator�� ������.
			seq.insert({ input, 1 });
		}
		else
			seq[input]++;//�̹� key���� �ִٸ� value ���� +1�Ѵ�.
	}
	//map�� �ڵ����� key���� ���������ǹǷ� ������ �ʿ� ����.
	solve(0);
}