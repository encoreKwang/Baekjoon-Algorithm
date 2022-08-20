
/**
 * 궁수의 위치에 대한 경우의 수를 따져야함
 * 포인트는 같은 적이 여러 궁수에게 공격 당할 수 있고
 * 가장 가까운 적이 여럿인 경우엔 , 가장 왼쪽에 있는 적을 공격한다
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_17135_이광용 {

	private static int N;
	private static int M;
	private static int D, ans;
//	private static int[][] map;
	private static ArrayList<Point> enemys;
	private static int[] combiArr;
	private static HashMap<Point, Integer> hmap;


	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 행
		M = Integer.parseInt(st.nextToken());// 열
		D = Integer.parseInt(st.nextToken());// 공격 거리 제한
		ans = 0;
		//map = new int[N + 1][M]; // 궁수는 N+1행에 있음
		enemys = new ArrayList<>();
		combiArr = new int[3]; // 3명의 궁수의 열 위치를 정한다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(st.nextToken())== 1)
					enemys.add(new Point(i, j)); // 적들의 위치를 리스트에 넣는다.
			}
		}

		// hmap: key값에 해당 턴에 화살을 맞은 적의 POINT를 주고 중복되지 않도록 관리하는 hashmap
		hmap = new HashMap<>();
		// 궁수는 n+1 행 이상 부터 있을 수 있다.그런데 궁수는 3명이고 M은 3이상이므로 n+1 행에 모두 위치한다.
		// 중복x, 조합 -> mC3 m개의 column중에 3개뽑는 조합

		combi(0, 0);
		System.out.println(ans);
	}

	private static void combi(int cnt, int sIdx) {
		if (cnt == 3) { // 기저조건, 궁수 3명의 자리 뽑음(열 위치를 정함)
			ArrayList<Point> tmpEnemys = new ArrayList<>();// 하드 카피
			for(Point x : enemys) {
				tmpEnemys.add(x);
			}
			// 이 조합 경우의 수에서 화살을 맞출 수 있는 적의 수 
			int tmpNum = 0;
			while (!tmpEnemys.isEmpty()) { //이 조합에서 적이 모두 사라질 때까지 반복
				//while문의 반복할 때마다 활을 한번 쏜다.
				// 화살은 3명의 궁수가 동시에 쏜다
				hmap = new HashMap<>(); // 화살을 한번 쏠때마다 hmap 초기화, 이번 화살턴에서 
				//맞출 적들의 point를 중복이 안되도록 저장
				for (int i = 0; i < cnt; i++) {
					int col = combiArr[i];
					// 한 궁수의 위치는 map[N][col]
					// 궁수 위치와 적들 사이의 거리차가 가장 작은 적을 선택한다. -> 오름차순
					// 거리로 정렬하는데 거리가 같다면 제일 왼쪽에 있는 적
					// 즉, x는 커야되고 y는 작아야함.
					// 그런데 궁수 위치에서부터 각각 잰 두 적의 거리가 같다는 것은 곧 하나는 X거리차가 크고
					// 다른 하나는 Y거리차가 크다는 것이므로 가장 왼쪽에 있는 적을 고르려면
					// Y의 위치만 고려하여 가장 작은 것을 고르면 된다. -> 오름차순
					Collections.sort(tmpEnemys, new Comparator<Point>() {
						@Override
						public int compare(Point o1, Point o2) {
							// 거리가 같을때
							if ((Math.abs(o1.x - N) + Math.abs(o1.y - col)) == (Math.abs(o2.x - N)
									+ Math.abs(o2.y - col))) {
								// 가장 왼쪽에 있는값 -> 거리는 같을 때, 열 기준 오름차순
								return o1.y - o2.y;
							}
							return (Math.abs(o1.x - N) + Math.abs(o1.y - col))
									- (Math.abs(o2.x - N) + Math.abs(o2.y - col)); // 오름차순
						}
					});
					Point p = tmpEnemys.get(0);//가장 가까운 적
					if( (Math.abs(p.x - N) + Math.abs(p.y - col)) <= D) {
						if (!hmap.containsKey(p)) { //그 적이 처음 화살을 맞았다면 
							hmap.put(p, 1);
							tmpNum++;
						}		
					}
				}//end of for(화살쏘기)

				for (Point arrowedEnemy : hmap.keySet()) { //화살을 맞은 적의 포인트에 해당하는 
					//tmpEnemys의 배열 값을 삭제한다
					for(int i = tmpEnemys.size()-1; i >= 0; i++) {
						if(arrowedEnemy.equals(tmpEnemys.get(i))) {
							tmpEnemys.remove(i);		
						}
					}
				}
				//남아있는 적은 한칸 앞으로 이동
				for(int i = tmpEnemys.size()-1; i >= 0 ; i--) {
					Point p = tmpEnemys.get(i);
					//성이 있는 칸으로 들어오면 삭제하고 아니면 이동
					if(p.x + 1 < N) {
						tmpEnemys.add(new Point(p.x + 1, p.y));
					}
					tmpEnemys.remove(i);
				}
			}//end of while
			ans = Math.max(ans, tmpNum);
			return;
		}
		//조합 경우의 수 만들기
		else {
			for (int i = sIdx; i < M; i++) {
				combiArr[cnt] = i;
				combi(cnt + 1, i + 1); //재귀 호출
			}
		}
	}
}
