
/**
 * 프로세서 문제
 * 먼저 부분집합(2^n)으로 선택 or not선택 고르고
 * 선택시 중복순열(4방향^n개)으로 탐색한다.
 * 중복순열 호출 하기 전에 선택된 코어의 개수가 현재 max코어개수보다 작다면 호출하지 않고 리턴 (가지치기!)
 * 그리고 한 코어에서 한 방향 선택할 때마다 다른 전선 혹은 코어와 교차되는지 확인하고 겹친다면 그 방향은 건너뛴다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1767_이광용 {

	private static int N;
	private static boolean[][] map;

	private static ArrayList<Point> coreArr;
	private static boolean[] vis;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };

	private static int MaxCoreCnt;
	private static int ans;

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new boolean[N][N];

			coreArr = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						map[i][j] = true; 
						//가장 자리 코어는 체크는 하지만 coreArr에 넣어서 경우의 수에 포함하지 않는다.
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1)
							continue;
						coreArr.add(new Point(i, j));
					}
				}
			} // 입력완료

			vis = new boolean[coreArr.size()]; // subset 배열, 각 코어의 인데스마다 결정했는지 체크
			// 코어의 수 최대값 비교 변수
			MaxCoreCnt = Integer.MIN_VALUE;
			// 전선의 길이의 최소값 비교 변수
			ans = Integer.MAX_VALUE;//
			subRecur(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void subRecur(int cnt, int selectCnt) { // 부분집합
		if (cnt == coreArr.size()) { // 모든 코어에 대하여 O X 결정 완료

			// 뽑힌 코어들을 가지고 "중복 순열" 돌리기
			// 뽑은 코어들이 개수가 현재 MaxCoreCnt보다 작다면 중복순열 돌릴 필요도 없음
			if (selectCnt < MaxCoreCnt)
				return;

			//중복 순열 안에서 다 탐색하고나서 재귀리턴하면서 원래대로 원상복귀를 시키기 때문에 
			//초기 map으로 될 것임
			//따라서 부분집합 구하는 이 함수에서는 map복귀 로직은 필요 없어보임

			ArrayList<Point> tmpCoreList = new ArrayList<>();
			for (int i = 0; i < coreArr.size(); i++) {
				if (vis[i] == true) {
					tmpCoreList.add(coreArr.get(i));
				}
			}

			// 중복 순열 호출
			duplicatePerm(tmpCoreList, selectCnt, 0, 0);

			return;
		}
		vis[cnt] = true; // 선택O
		subRecur(cnt + 1, selectCnt + 1);
		vis[cnt] = false;// 선택X
		subRecur(cnt + 1, selectCnt);

	}

	private static void duplicatePerm(ArrayList<Point> tmpCoreList, int selectCnt, int idx, int LengthSum) {
		if (selectCnt == idx) {// 선택된 모든 코어들에 대해서 방향을 정해줬다면
			//어느 한 방향이라도 방향이 정해지지 않은 코어가 있다면 기저조건에 도달 못한다.
			if(MaxCoreCnt < selectCnt) {
				MaxCoreCnt = selectCnt;
				ans = LengthSum;
			}else if(MaxCoreCnt == selectCnt) {
				ans = Math.min(ans, LengthSum);
			}
			return;
		}

		// 해당 코어들에 대해서 4방 중복 순열을 돌림
		// boolean[][] dfsVisMap = new boolean[N][N];
		Point selectCore = tmpCoreList.get(idx);

		for (int d = 0; d < 4; d++) {
			// 그쪽 방향을 쭉 갔을 때, 갈 수 있는지 체크하고 true리턴하면
			// map에 표시
			if (checkDir(selectCore, d)) {
				//지금의 map 복사해둔다.
				boolean[][] copyMap = new boolean[N][N];
				for (int i = 0; i < N; i++) {
					copyMap[i] = Arrays.copyOf(map[i], N);
				}
				int nx = selectCore.x+ dx[d];
				int ny = selectCore.y + dy[d];
				//해당 코어의 해당 방향에서 선택된 전선의 길이를 카운트함
				int tmpLengthCnt = 0;
				
				while(inRange(nx, ny)) {
					map[nx][ny] = true;
					tmpLengthCnt++;
					nx += dx[d];
					ny += dy[d];
				}
				//재귀 호출
				duplicatePerm(tmpCoreList, selectCnt, idx + 1, LengthSum+tmpLengthCnt);
				//map 다시 원상복구
				for (int i = 0; i < N; i++) {
					map[i] = Arrays.copyOf(copyMap[i], N);
				}
			}//end of 해당 방향
		}//end of 4방 탐색
	}//end of duplicatePerm()

	private static boolean checkDir(Point selectCore, int d) {
		int nx = selectCore.x+ dx[d];
		int ny = selectCore.y + dy[d];
		while(inRange(nx, ny)) {
			if(map[nx][ny] == true) return false; 
			nx += dx[d];
			ny += dy[d];
		}
		return true;
	}

	private static boolean inRange(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}
}
