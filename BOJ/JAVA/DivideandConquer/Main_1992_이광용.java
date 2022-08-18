/**
 * 쿼드트리
 * 4등분해서 같은 원소로 채워져있으면 해당 숫자로 확정 -> 반복 
 * 한번 나눠질때마다 괄호를 연다.
 * 각 사사분면에 해당하는 값이 먼저 완전히 채워질 때까지 반복
 * 
 * 
 * 먼저 배열 해당하는 부분 시작점과 n값을 받으면  첫번째 원소 값을 변수에 넣고 걍 쭉 탐색함 
 * 진행중에 변수와 다른 값이 없다면 그대로 변수 출력하고 끝.
 * 만약에 진행중에 다른 변수가 있다면 괄호를 열고 4등분하고 
 * 그 시작점을 매개변수와 (현재 한 변의 길이 / 2) 넘겨주면서 해당 메소드를 재귀 호출 (4번반복)
 * 그 안에서 다시 또 일관되지 않은 변수가 나온다면 또 재귀를 호출한다.
 * 마지막에 리턴전에 괄호를 닫는다.
 * 
 * 
 * 
 */
import java.util.Scanner;

public class Main_1992_이광용 {
	static char[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		map = new char[n][n];
		for(int i =0; i < n ;i ++) {
			char[] cArr = sc.next().toCharArray();
			for(int j = 0; j < n ; j++) {
				map[i][j] = cArr[j];
			}
		}
		recur(0, 0, n);
	}
	public static void recur(int sX, int sY, int n) {
		char tmp = map[sX][sY]; //첫 원소를 기준 변수로 한다.
		//해당 범위를 돌면서 기준 변수와 일치하지 않는 변수가 있는지 확인한다.
		boolean flag = false;
		for(int i = 0; i < n; i++) {
			if(flag) break;
			for(int j = 0; j < n; j++) {
				if (tmp != map[sX+i][sY+j]) {
					//일치하지 않은 변수가 있다면 반복문을 벗어나고나서 재귀호출
					flag = true;
					break;
				}
			}
		}
		if(flag) { //괄호를 열고 4등분하고 각각 재귀호출한다. 되돌아오면 괄호를 닫는다.
			System.out.print("(");
			recur(sX, sY, n / 2);//1사분면
			recur(sX, sY+(n/2), n /2 ); //2사분면
			recur(sX+(n/2), sY, n /2 );//3사문면
			recur(sX+(n/2), sY+(n/2), n /2 ); //4사분면
			
			
			System.out.print(")");
			
		}
		else { //모두 일치한다는것이니까 출력하고 끝임
			System.out.print(tmp);
		}
	}

}
