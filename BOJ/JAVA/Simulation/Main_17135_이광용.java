/**
 * 궁수의 사정거리를 BFS로 삼방탐색을 한다.
 * 그 사정거리에 있는 적들의 (x, y, 거리)를 저장한다.
 * 좌 상 우 순서로 탐색 -> 같은 거리 일 때, 자동적으로 왼쪽부터 뽑힌다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17135_이광용{

	private static int N;
	private static int M;
	private static int D, maxKill;
	private static int[] combiArr;
	private static int enemyCnt;
	private static int[][] map;
	private static int[] dx = {0, -1, 0}; //좌 상 우
	private static int[] dy = {-1, 0, 1};

	public static class DisPoint{
		int x, y, dis;
		public DisPoint(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 행
		M = Integer.parseInt(st.nextToken());// 열
		D = Integer.parseInt(st.nextToken());// 공격 거리 제한
		maxKill = 0;
		enemyCnt = 0;
		map = new int[N][M]; 
		
		combiArr = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == 1) enemyCnt++;
			}
		}//입력 완료
		combi(0, 0);
		System.out.println(maxKill);
	}
	private static void combi(int cnt, int sIdx) {
		if (cnt == 3) { // 기저조건, 궁수 3명의 자리 뽑음(열 위치를 정함)
			startGame(combiArr);
			return;
		}
		//조합 경우의 수 만들기
		else {
			for (int i = sIdx; i < M; i++) {
				combiArr[cnt] = i;
				combi(cnt + 1, i + 1);
				if(maxKill == enemyCnt) break;
			}
		}
	}

	private static void startGame(int[] combiArr) {
		int[][] combiMap = new int[N][M]; //궁수 배치 후 사용할 map deep copy
		for(int i = 0 ;i < N; i++) {
			for (int j = 0; j < M; j++) {
				combiMap[i][j] = map[i][j];
			}
		}
		int killCnt =0; //화살로 죽은 적의 수
		for(int shootArrowTimes = 0; shootArrowTimes < N; shootArrowTimes++) {
			HashSet<List<Integer>> arrowedHashSet = shootArrow(combiArr, combiMap);//화살 한번 쏴서 맞은 적 받아옴
			
			for(List<Integer> tmpArr : arrowedHashSet) {
				combiMap[tmpArr.get(0)][tmpArr.get(1)] = 0; //적 죽이기
				killCnt++;
			}
			
			//적 한 칸 아래로 이동
			boolean flag = moveEnemys(combiMap);
			if(!flag) break; //남아있는 적이 없을 때
		}
		maxKill = Math.max(maxKill, killCnt);
	}
	private static HashSet<List<Integer>> shootArrow(int[] combiArr, int[][] combiMap) {
		HashSet<List<Integer>> arrowedHashSet = new HashSet<>(); //화살 맞은 적을 저장할 set
		
		for(int col : combiArr) {
			boolean[][] isVisited = new boolean[N][M]; //방문 체크 배열
			if(combiMap[N-1][col] == 1) { //탐색 시작 위치에 적이 있는 경우
				List<Integer> tmpArr = Arrays.asList(N-1, col);
				arrowedHashSet.add(tmpArr);
				continue;
			}
			isVisited[N-1][col] = true;
			Queue<DisPoint> queue = new LinkedList<>();
			queue.offer(new DisPoint(N-1, col, 1));
			boolean flag = false;
			while(!queue.isEmpty()) {
				DisPoint tmpDisPoint = queue.poll();
				if(tmpDisPoint.dis >= D) break;  //공격 제한 거리를 넘는 경우
				for(int dir = 0; dir < 3; dir++) { //3방 탐색 좌 상 우
					int nx = tmpDisPoint.x + dx[dir];
					int ny = tmpDisPoint.y + dy[dir];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || isVisited[nx][ny] == true)
						continue;
					isVisited[nx][ny] = true;
					queue.offer(new DisPoint(nx, ny, tmpDisPoint.dis + 1));
					if(combiMap[nx][ny] == 1) { //가장 처음 만난 적을 담고 break
						List<Integer> tmpArr = Arrays.asList(nx, ny);
						arrowedHashSet.add(tmpArr);
						flag = true;
						break;
					}
				}//end of 3방 탐색
				if(flag) break;
			}//end of while(queue)
		}//end of 궁수 3명
		return arrowedHashSet;
	}
	
	private static boolean moveEnemys(int[][] combiMap) {
		boolean flag = false;
		
		for(int i = N-1; i >= 0; i-- ) {
			for(int j = 0; j < M ; j++) {
				if(i == N-1) combiMap[i][j] = 0; 
				else {
					if(combiMap[i][j] == 1) {
						flag = true;
						combiMap[i+1][j] = 1;
						combiMap[i][j] = 0;
					}
				} 
			}
		}
		return flag;
	}
}
