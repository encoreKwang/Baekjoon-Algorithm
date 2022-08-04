
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_이광용 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] sigma = new int[N + 1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) { // 2차원 누적 시그마 배열 만들기
				//해당 위치의 원소까지의 누적합 = 왼쪽원소의 누적합 + 위쪽 원소의 누적합 - 대각선 누적합 (두 번 중복됐으니까 뻼) + 현재 위치 원소값
				sigma[i][j] = sigma[i-1][j]+sigma[i][j-1]-sigma[i-1][j-1]+Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb= new StringBuilder();
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum=0;
			sum = sigma[x2][y2]-sigma[x1-1][y2]-sigma[x2][y1-1]+sigma[x1-1][y1-1];
			//마지막 더하기는 추가되는 겹쳐서 두 번 빠지기 떄문에 한번 더해준다. 
//			System.out.println(sum); // M의 범위가 10만이라 출력이 너무 많아서 시간터짐.
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	}
}