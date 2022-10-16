package SWEA_AD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5650_핀볼게임 {
	static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0} }; // 상 좌 우 하
	static int[][] type = { //수평이나 수직면은 반대방향인데..경사면은 직각으로 진행방향이 꺾이게 된다.
			//3의 보수를 활용해서 들어오는 방향의 반대를 인덱스로 사용하면 type에는 그림 그대로 나아갈 방향으로 설정하면 된다.
			//예를들어 핀볼이 '하'방향으로 들어온 다는 것은 블록의 위로 들어온다는 것을 의미하므로 3-d로 바꿔서 
			//'상'에 해당하는 인덱스에 나아갈 방향을 설정한다.
			{},
			{2, 1, 0, 3}, //블록의 위로 핀볼이 들어올 때, 왼쪽에서, 오르쪽으로, 아래로  
			{0, 1, 3, 2},
			{0, 3, 2, 1},  
			{1, 0, 2, 3},
			{0, 1, 2, 3}
	};
	private static int[][] map;
	private static int ANS;
	private static int N;
	private static int score;
	static class Pinball{
		int sr, sc, r, c, dir, score = 0;
		public Pinball(int sr, int sc, int r, int c, int dir) {
			this.sr = sr;
			this.sc = sc;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static List<int[]>[] list;
	static int[][][] wormHole;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T =Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			N =Integer.parseInt(br.readLine().trim());
			//게임은 핀볼이 출발 위치로 돌아오거난 블랙홀에 빠질때 게임이 끝
			//핀볼의 출발 위치에서 4방으로 
			//핀볼은 처음 시작 위치를 기억해야함, 진행방향을 가지고 있어야함, 현재 위치, 점수를 가지고 있어야함
			//그리고 다음 스텝의 방향을 결정해야하는데 각 블록의 타입에 따른 방향을 이차원배열에 저장.
			//웜홀은 쌍으로 있으니 int[]배열을 원소로하는 List들의 배열을 만들고
			//바로 가고자하는 웜홀 위치를 알 수 있게끔 3차원 배열을 만들어서 삽입한다.
			map = new int[N][N];
			list = new ArrayList[11];
			for(int i = 6; i <= 10; i++) {
				list[i] = new ArrayList<>();
			}
			wormHole = new int[N][N][2];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 6) { //웜홀인 경우
						list[map[i][j]].add(new int[] {i,j});
					}
				}
			}// end of 입력
			for(int i = 6; i <=10 ; i++) {
				if(list[i].size() == 0) continue;
				int x1 = list[i].get(0)[0];
				int y1 = list[i].get(0)[1];
				int x2 = list[i].get(1)[0];
				int y2 = list[i].get(1)[1];
				
				wormHole[x1][y1][0] = x2;
				wormHole[x1][y1][1] = y2;
				wormHole[x2][y2][0] = x1;
				wormHole[x2][y2][1] = y1;
			}
			ANS = 0;
			//각 위치에 대해서 핀볼이 시작할 경우 -> 4가지 방향에 대해서 큐에 넣고 시작
			for(int i = 0; i < N; i++) {
				for(int j = 0; j <N ; j ++) {
					if(map[i][j] == 0) {
						for(int d = 0 ; d < 4 ; d ++) {
							score = 0;
							search(new Pinball(i, j, i, j, d));
							ANS = Math.max(ANS, score);
//							System.out.println(i + " " +j + " " + d + " ans : " + ANS);
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ANS);
		}// end of tc
	}
	private static void search(Pinball p) {
//		int cnt = 0;
		while(true) {
//			System.out.println("cnt : " + cnt++);
			int d = p.dir;
			int nr = p.r + dir[d][0];
			int nc = p.c + dir[d][1];
//			System.out.println("nr, nc: "+ nr + " " + nc);
			if(isIn(nr, nc)) {
				if(map[nr][nc] == 0) {
					if(nr == p.sr && nc == p.sc){ //시작 위치에 온다면 => 게임 끝
//						System.out.println("시작위치");
						return;
					}
					p.r = nr;
					p.c = nc;
				}else if(map[nr][nc] == -1) { //블랙홀 -> 게임 끝
//					System.out.println("블랙홀");
					return;
				}else if(map[nr][nc] >=1 && map[nr][nc] <= 5) { //블록인 경우
					if(map[nr][nc] == 5) {
						//5번에 부딪히면 지금까지의 경로를 다시 한번 반복하는 것
						score *= 2;
						score++;
						return;
					}
					score++;
					p.r = nr;
					p.c = nc;
					p.dir = type[map[nr][nc]][3-p.dir]; //블럭 만나서 각 타입에 해당하는 방향으로 바꾸기
//					System.out.println("p.dir : " + p.dir);
					//3의 보수를 활용
				}else { //웜홀인 경우
					p.r = wormHole[nr][nc][0];
					p.c = wormHole[nr][nc][1];
				}
			}else { //벽에 부딪힘
//				System.out.println("벽에 부딪힘");
				//벽이 부딪히면 지금까지의 경로를 다시 한번 반복하는 것
				score *= 2;
				score++;
				return;
//				p.r = nr;
//				p.c = nc;//범위를 벗어나지만 해당 위치로 바꿔놓고 
//				//다음 턴에서 반대방향을 돌려서 다시 지금 턴의 시작위치로 가게 한다. 
//				p.dir = 3 - p.dir; //반대방향으로 바꾸기
			}
		}//end of while
	}
	static boolean isIn(int r, int c) {
		return r >=0 && r < N && c>= 0 && c < N;
	}
}
