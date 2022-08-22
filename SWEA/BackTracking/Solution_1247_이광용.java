import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 최적경로
 * 완전탐색
 * 고객의 집을 방문하는 모든 경우의 수, 순서에 따라 값이 달라짐 -> 순열 : nPn
 * 
 * @author multicampus
 *
 */

public class Solution_1247_이광용 {

	static boolean[] vis;
	static int[] permArr;
	static Point[] customers;
	private static Point company;
	private static Point home;
	private static int N, ans;
	
	public static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t=1; t <= tc; t++) {
			ans = Integer.MAX_VALUE;//최단 거리 변수 
			N = Integer.parseInt(br.readLine());//고객의 수
			permArr= new int[N];//순열의 경우의수를 저장할 배열, 고객들의 인덱스를 원소값으로 한다.
			vis = new boolean [N]; //방문 체크 배열: 고객의 인덱스를 인덱스로 한다. 
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			company = new Point(x, y);
			//map[x][y] = 1;// 회사 
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Point(x, y);
			//map[x][y] = 2;//집
			
			//N명의 고객 위치를 저장할 배열
			customers = new Point[N];
			
			for(int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customers[i] = new Point(x, y);
				//map[x][y] = 3;
			}
			//입력 완료
			
			//고객들로 만들 수 있는 순열에 따른 거리 계산
			perm(0);
			System.out.print("#" + t + " " + ans);
		}
		

	}
	public static void perm(int cnt) {
		if(cnt == N) {
			//이제 뽑아 놓은 경우의 수 순서로 거리를 구한다.
			int tmpDisSum = 0;
			//회사에서 첫 원소까지의 거리
			Point startP = company;
			Point endP = customers[permArr[0]];
			tmpDisSum += Math.abs(startP.x - endP.x) + Math.abs(startP.y - endP.y);
			
			for(int i = 0; i<cnt-1; i++) { //cnt-2
				startP = customers[permArr[i]];
				endP = customers[permArr[i+1]];//cnt-1
				tmpDisSum += Math.abs(startP.x - endP.x) + Math.abs(startP.y - endP.y);
			}
			//마지막 원소에서 집까지의 거리
			startP = customers[permArr[N-1]];
			endP = home;
			tmpDisSum += Math.abs(startP.x - endP.x) + Math.abs(startP.y - endP.y);
			
			ans = Math.min(ans, tmpDisSum);
			
		}
		else {
			for(int i = 0 ; i < N; i++) {
				if(vis[i] == false) {
					vis[i] = true;
					permArr[cnt] = i;
					perm(cnt+1);
					vis[i] = false;
				}
				
			}
		}
	}

}
