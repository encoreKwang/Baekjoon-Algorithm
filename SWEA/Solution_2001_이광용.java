import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_2001_이광용 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t<= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n+1][n+1];
			int[][] sigma = new int[n + 1][n + 1];//누적합 이차원 배열
			
			for(int i = 1 ; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//해당 i j 위치의 누적합 = 누적합 배열의 왼쪽 칸 + 누적합 배열의 왼쪽 칸 - 누적합 배열의 대각선 칸(겹쳐서 두번 더해지므로 한번 빼준다.) + 해당 위치의 원소값
					sigma[i][j] = sigma[i][j-1] + sigma[i-1][j] - sigma[i-1][j-1] + map[i][j];
				}
			}
			
			//누적합 이차원 배열을 이용해서 MxM 범위에 해당하는 값들의 최대값을 구해서 갱신해나간다.
			//MxM의 범위의 누적합을 구하는 방법
			//누적합 배열을 이용한 i j 해당 위치에서 MxM 범위의 누적합
			// = 누적합 배열의 (i, j)의 값 - 왼쪽에 해당하는 (i, j-m)의 값 - 위쪽에 해당하는 (i - m, j)의 값  + 대각선에 해당하는 (i-m, j-m)의 값 (두 번 빼지기때문에 한번 더해줌)
			int max =Integer.MIN_VALUE;
			for(int i = m; i <= n; i++) {
				for(int j = m; j <= n; j++) { //m,m부터 시작
					max = Math.max(max,
							sigma[i][j] - sigma[i][j-m] - sigma[i-m][j] + sigma[i-m][j-m]);
				}
			}
			bw.write("#"  + t + " " + max);
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
		
		
	}
}
