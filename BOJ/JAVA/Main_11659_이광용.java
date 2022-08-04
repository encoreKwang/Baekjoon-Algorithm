import java.util.Scanner;
//
public class Main_11659_이광용 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//누적합
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] cumulatedSumArr = new int[n+1];
		for(int i = 1; i <= n; i++) { //input 받으면서 누적합 구하기
			int tmp = sc.nextInt();
			cumulatedSumArr[i] = cumulatedSumArr[i-1] + tmp;
		}
		for(int k = 0; k < m; k++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			System.out.println(cumulatedSumArr[j] - cumulatedSumArr[i-1]);
		}
		

	}

}
