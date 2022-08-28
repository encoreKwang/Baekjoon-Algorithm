import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 완탐 -> 제한시간 내에 풀수있느냐가 관건
 * 최대 400칸 * 400번의 bfs dfs = 16만번  -> 시간 안에 답 나올 수 있겠네
 * k
 * 시뮬레이션
 * @author dnflr
 *
 */
public class Solution_2117_홈방범서비스 {
	private static int[] cost = {1,1,5,13,25,41,61,85,113,145,181,221,265,313,365,421,481,545,613,685,761,841,925,1013,1105,1201,1301,1405,1513,1625,1741,1861,1985,2113,2245,2381,2521,2665,2813,2965,3121,};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= tc; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); //쪼개기
			int N = Integer.parseInt(st.nextToken()); //도시의 크기N, 5 <= n <= 20
			int M = Integer.parseInt(st.nextToken()); //집에서 지불할 비용 M, 1 <= M <= 10
			
			List<int[]> home = new ArrayList<int[]>(); //{행,열}
			for(int r = 0 ; r < N; r++ ) {
				String s = br.readLine();
				for(int c = 0, index = 0; c < N; c++, index += 2) {
					if(s.charAt(index) == '1') { //집이면 '1'
						home.add(new int[] {r, c});//{행, 열} 
						//익명 배열을 사용할 땐, new int[] 생략할 수 없음
					}
				}
			}
			int maxCntHome = 0;
			for(int r= 0 ; r < N; r ++){
				for(int c = 0; c < N; c++) {
					for(int k =1; k <= 40; k++) {
						int cntHome = 0; 
						for(int h = 0; h < home.size(); h++) {
							//r,c home.get(h) = {r, c}
							if(Math.abs(home.get(h)[0]-r) + Math.abs(home.get(h)[1]-r) < k ) {
								cntHome++;
							}
						}
						if(cntHome * M >= cost[k] && maxCntHome < cntHome) {
							maxCntHome = cntHome;
						}
					}
				}
			}

			sb.append("#").append(testcase).append(" ").append(maxCntHome).append("\n");
			System.out.println(sb.toString());
		}
		

	}

}
