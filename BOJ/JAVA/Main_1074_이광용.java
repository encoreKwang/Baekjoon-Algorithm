import java.util.Scanner;

/**
 * Z
 * 이분탐색
 * @author dnflr
 *
 */
public class Main_1074_이광용 {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		int x = 0, y = 0;
		int ans = 0; //사사분면을 행 과 열로 나눠서 각각 결정될때마다 
		
		int length = 1; //한 변의 길이
		for(int i = 1 ; i <= n; i ++) {
			length = length * 2;
		}
		//한 사사분면에 몇개? 
		
		
		for(int k = 0; k < n; k++) {
			length /= 2; //4 
			if(r >= x + length) { //행 범위 좁히기 -> 조건 만족하면 3 or 4분면
				x += length;
				//처음 1분면 첫 인덱스에서 최소한 3분면 첫 인덱스로 간다는 것을 의미함. (0,0) = 0 -> (4, 0) = 32
				ans += length * length * 2;
			}
			if(c >= y + length) { //열 범위 좁히기 -> 조건 만족하면 2 or 4분면
				y += length;
				//조건 만족하면 x의 위치와 상관없이 한 분면 옆으로 간다는 것을 의미
				ans += length * length;
			}
			if(x == r && y == c) break;
		}
		System.out.println(ans);
	}

}
