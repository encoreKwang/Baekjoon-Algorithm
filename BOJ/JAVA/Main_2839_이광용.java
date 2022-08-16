import java.util.Arrays;
import java.util.Scanner;

/**
 * 설탕배달
 * 정확하게 n키로그램을 배달
 * 3weight, 5weight 
 * 최대한 적은 봉지로 배달
 * dp배열의 인덱스를 배달 가야할 n킬로그램으로 보고
 * 리소스는 무한이므로 앞에서부터 채운다.
 * dp[n] : 무게n을 옮겨야할 때, 필요한 최소 봉지 개수
 * @author dnflr
 *
 */

public class Main_2839_이광용 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		int[] weight = new int[2]; //3, 5weight
		
		weight[0] = 3;
		weight[1] = 5;
		//dp[k] = min(dp[k], dp[k-weight[i]]+1)
		Arrays.fill(dp, 5000);
		dp[0] = 0; //처음 시작을 0으로 초기화 해줘야 한다.
			
		for(int i = 0; i < weight.length; i++) {
			for(int j = weight[i]; j <= n; j ++) {
				dp[j] = Math.min( dp[j], dp[j-weight[i]] + 1 );
			}
		}
		if(dp[n] == 5000) System.out.println(-1);
		else System.out.println(dp[n]);

	}

}
