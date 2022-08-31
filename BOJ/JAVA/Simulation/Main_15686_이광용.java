import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 치킨 배달
 * 전체 도시의 치킨 거리 최소값
 * m개를 선택할 거임
 * bfs
 * 전체 치킨집중 m개를 뽑아서 조합의 경우의 수 만들고
 * 각 경우의 수마다 도시최소거리를 구함.  
 * 
 * @author multicampus
 *
 */
class Point {
	int x;
	int y;
	Point(int x,int y){
		this.x = x;
		this.y = y;
	}
}
public class Main_15686_이광용 {
	public static int n , m , ans, ckCnt;
	public static int[] combiArr, minDistance;
	public static int[][] map;
	public static ArrayList<Point> chickens;//치킨집의 pos를 저장할 배열
	public static ArrayList<Point> homes;//집의 pos를 저장할 배열
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n+1][n+1]; //1,1부터 시작
		homes = new ArrayList<>();
		chickens = new ArrayList<>();

		for(int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) { //집
					homes.add(new Point(i,j));
				}
				if(map[i][j] == 2) { //치킨집
					chickens.add(new Point(i,j));
				}
			}
		}
		ans=Integer.MAX_VALUE;
		ckCnt = chickens.size();
		combiArr = new int[m];
		combi(0, 0);
		System.out.println(ans);
	}
	public static void combi(int cnt, int sIdx) {
		if(cnt == m) { 
			//뽑은 치킨집을 각 집과 비교해서 최소거리 도출
			minDistance = new int[homes.size()]; //각 집에서 갈 수 있는 최소치킨거리를 저장할 배열
			Arrays.fill(minDistance, Integer.MAX_VALUE);
			int tmpCityDisMin = 0; //뽑은 치킨집 경우의 수에서 가질 수 있는 총 도시치킨거리를 구한다.
			for(int i = 0; i < homes.size(); i++) {//i번째 집에서~
				int tmpDis = 0;
				for(int j = 0; j < cnt; j++) {//cnt개의 치킨집중 가장 가까운 거리
					int tmpX = Math.abs(homes.get(i).x - chickens.get(combiArr[j]).x);
					int tmpY = Math.abs(homes.get(i).y - chickens.get(combiArr[j]).y);
					tmpDis = tmpX + tmpY;
					minDistance[i] = Math.min(minDistance[i], tmpDis);
				}
				tmpCityDisMin += minDistance[i];  
			}
			ans = Math.min(tmpCityDisMin, ans);
		}
		else {
			for (int i = sIdx; i < ckCnt; i++) {
				combiArr[cnt] = i;
				cnt++;
				combi(cnt, i+1);
				cnt--;
			}
		}
	}

}
