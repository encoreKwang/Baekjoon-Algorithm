//파이프의 유형에 따라 갈 수 있는 방향이 달라지고 next 위치가 그 "정반대" 방향을 보유한 파이프여야 next로 갈 수 있다.
//예를 들어, 현재 위치에서 "상"방향을 나아가고자 한다면 next위치에는 "하"방향을 가진 파이프여야 next로 진행이 가능하다.

//1. 탐색지점 -> 범위 내
//2. 미방문
//3. 연결
//파이프 정형화
//타입 별로 갈 수있는 방향

package SWEA_AD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거{

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int T;
	static int N,M,R,C,L;
	private static int[][] map;
	private static int A;
	private static int dir[][] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // 3의 보수를 사용하기 위해 -> 상 좌 우 하
	private static int type[][] = {
			{},
			{0, 1, 2, 3},
			{0, 3},
			{1, 2},
			{0, 2},
			{2, 3},
			{1, 3},
			{0, 1}
	};

	public static void main(String[] args) throws NumberFormatException, IOException {
		input = new BufferedReader(new StringReader(src));
		T = Integer.parseInt(input.readLine());
		for(int t=1;t<=T; t++) {
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken()); //탈출 소요된 시간

			map = new int[N][M];
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(input.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(tokens.nextToken());
				}
			} //end of 입력
			A = 0;
			bfs();
			output.append(String.format("#%d %d%n", t, A));
		}
		System.out.println(output);
	}

	static void bfs() {
		Queue<Pipe> q = new LinkedList<>();
		boolean[][] vis = new boolean[N][M];
		q.offer(new Pipe(R, C, map[R][C])); //멘홀 뚜껑 위치(시작위치)
		L--;//멘홀 뚜껑에 가기 위한 1시간
		A++;
		vis[R][C] = true;

		while(!q.isEmpty()) {
			if(L == 0) break;
			int size = q.size();
			while(size -- > 0) {
				Pipe p = q.poll();
				//파이프 유형에 따라서 갈 수 있는 방향이 달라진다.
				int[] typeDir = type[p.type];
				for(int d : typeDir) {
					int nr = p.r + dir[d][0];
					int nc = p.c + dir[d][1];
					//가고자하는 위치의 파이프가 현재 p의 반대 방향으로 받아줄 수 있어야함
					if(isIn(nr, nc) && !vis[nr][nc] &&possibleDir(nr, nc, 3-d)) {
						vis[nr][nc] =true;
						q.offer(new Pipe(nr, nc, map[nr][nc]));
						A++;
					}
				}
			}
			L--;
		}
	}

	private static boolean possibleDir(int nr, int nc, int d) {
		int[] tmpTypeDir = type[map[nr][nc]];
		for(int x : tmpTypeDir) {
			if(x == d) return true;
		}
		return false;
	}

	static class Pipe{
		int r,c,type;
		public Pipe(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
    static String src = "5\n"
            + "5 6 2 1 3\n"
            + "0 0 5 3 6 0\n"
            + "0 0 2 0 2 0\n"
            + "3 3 1 3 7 0\n"
            + "0 0 0 0 0 0\n"
            + "0 0 0 0 0 0\n"
            + "5 6 2 2 6\n"
            + "3 0 0 0 0 3\n"
            + "2 0 0 0 0 6\n"
            + "1 3 1 1 3 1\n"
            + "2 0 2 0 0 2\n"
            + "0 0 4 3 1 1\n"
            + "10 10 4 3 9\n"
            + "0 0 0 0 0 0 0 0 0 0\n"
            + "0 0 0 7 5 0 5 0 0 0\n"
            + "0 0 3 2 2 6 0 0 0 0\n"
            + "0 4 7 2 2 2 7 0 0 4\n"
            + "0 3 0 1 1 2 2 0 0 5\n"
            + "0 5 6 1 1 1 1 6 2 5\n"
            + "7 4 1 2 0 0 4 6 0 0\n"
            + "5 3 1 7 0 2 2 6 5 7\n"
            + "7 3 2 1 1 7 1 0 2 7\n"
            + "3 4 0 0 4 0 5 1 0 1\n"
            + "20 20 13 11 13\n"
            + "0 0 0 1 4 4 4 0 0 0 0 0 0 0 0 1 2 3 1 0\n"
            + "0 0 0 0 0 0 0 0 0 0 0 4 2 7 7 2 0 1 1 0\n"
            + "0 0 0 0 0 0 0 0 0 6 2 4 4 2 0 4 7 0 6 0\n"
            + "0 0 0 7 5 5 3 0 0 7 5 0 5 6 4 2 6 3 1 5\n"
            + "0 0 0 1 2 6 3 3 7 0 3 6 2 4 5 6 7 7 5 7\n"
            + "0 0 0 3 7 6 1 5 3 3 4 5 7 6 0 4 3 3 1 1\n"
            + "0 1 2 1 5 6 1 6 1 6 5 1 6 0 0 3 4 1 7 6\n"
            + "0 2 3 2 2 7 3 0 0 3 2 5 2 1 0 6 5 1 6 5\n"
            + "0 2 5 7 0 7 1 3 3 4 1 3 3 0 2 3 3 2 4 1\n"
            + "4 0 0 7 2 4 2 2 1 3 1 6 5 5 6 2 5 1 1 6\n"
            + "5 6 4 0 3 6 5 2 2 6 1 2 0 1 7 5 7 2 2 2\n"
            + "1 6 3 1 4 4 1 0 3 0 4 2 7 2 0 2 3 6 2 5\n"
            + "1 5 7 2 1 1 4 4 2 1 0 2 7 1 6 2 6 6 2 2\n"
            + "3 7 0 6 5 0 4 0 6 6 7 1 3 1 1 1 5 1 6 6\n"
            + "0 4 0 1 6 2 1 0 7 0 4 2 5 2 7 0 2 7 1 6\n"
            + "0 7 3 0 1 7 6 2 0 0 4 2 4 1 3 3 7 0 1 3\n"
            + "0 1 1 4 3 7 4 5 2 2 4 7 4 7 7 4 6 0 1 6\n"
            + "0 5 2 2 1 4 6 3 7 0 6 3 5 0 0 6 4 4 2 1\n"
            + "0 1 2 4 5 6 0 2 0 0 5 6 2 4 6 4 7 6 3 7\n"
            + "7 7 4 2 3 0 0 4 0 0 7 2 7 5 6 1 4 5 5 4\n"
            + "50 50 20 12 18\n"
            + "0 0 0 0 0 0 0 0 0 0 0 0 4 5 0 0 0 0 0 4 2 0 5 2 1 5 3 3 0 0 4 0 5 1 7 2 6 0 7 0 0 0 2 0 0 0 0 0 0 0\n"
            + "6 7 0 0 0 0 0 0 0 0 0 0 4 5 5 3 6 3 0 2 3 3 0 0 5 6 1 5 3 4 7 6 2 2 1 1 6 5 6 4 6 2 0 0 0 0 2 3 1 0\n"
            + "0 2 6 5 7 6 0 0 0 0 0 0 6 2 0 5 6 2 0 4 1 5 0 0 2 0 7 7 0 6 0 6 2 2 4 1 2 2 1 6 6 6 0 2 2 5 0 6 5 0\n"
            + "0 0 0 4 7 2 7 3 7 0 0 0 0 6 7 6 5 1 1 1 2 2 1 3 1 2 7 6 1 2 1 2 4 1 6 1 1 7 3 1 6 6 6 1 1 1 7 0 0 0\n"
            + "0 0 0 5 4 0 6 3 3 7 0 0 0 6 4 3 2 5 3 1 6 1 0 4 1 0 5 7 6 3 1 1 3 6 1 1 6 3 6 7 3 3 6 5 0 7 2 2 4 6\n"
            + "0 6 0 7 6 0 7 4 0 5 3 0 4 3 2 0 5 7 3 0 1 3 6 7 7 5 1 7 5 2 0 5 3 1 3 7 1 1 1 5 2 5 1 3 6 7 7 6 4 3\n"
            + "5 2 0 2 6 5 0 5 6 1 6 5 5 1 7 1 2 3 6 5 1 6 7 7 6 4 1 7 5 2 0 1 3 4 6 4 5 7 2 6 5 6 2 5 6 5 6 5 1 6\n"
            + "1 2 0 7 0 5 5 0 7 6 2 2 1 3 5 5 3 6 3 7 6 4 1 3 1 3 7 0 3 7 0 2 5 6 1 3 4 1 5 1 7 4 1 7 7 0 4 7 5 5\n"
            + "7 6 0 3 5 1 4 0 5 2 5 0 1 3 5 5 4 4 6 1 6 5 7 6 2 1 6 5 5 3 0 5 7 1 1 3 6 2 2 2 4 5 7 4 5 1 1 0 7 3\n"
            + "2 5 4 0 3 1 4 5 6 3 7 0 4 5 3 6 4 5 1 7 4 7 3 1 1 7 7 1 1 5 6 4 7 1 2 6 4 1 7 2 7 1 6 0 5 0 0 0 1 0\n"
            + "3 0 2 5 1 7 1 1 1 6 5 1 3 1 3 1 1 7 1 3 6 5 5 3 1 3 1 6 2 3 2 6 6 1 1 7 5 7 5 7 1 6 0 3 5 1 5 3 0 0\n"
            + "0 0 3 2 0 1 4 1 4 1 0 7 3 2 2 4 2 4 4 6 1 1 1 7 2 4 7 4 3 6 3 5 1 6 1 3 7 7 2 6 3 2 1 0 4 6 2 6 3 0\n"
            + "0 0 5 4 7 2 4 6 4 1 6 7 2 2 1 6 2 1 5 4 7 2 2 1 0 7 6 1 7 2 5 7 0 4 1 6 4 0 3 0 0 5 5 0 7 7 0 3 0 0\n"
            + "0 0 6 4 3 1 3 1 4 7 2 1 2 4 3 4 1 6 2 1 5 1 1 6 0 7 2 7 2 4 7 4 0 3 7 7 3 3 5 2 0 4 3 0 4 2 0 1 3 5\n"
            + "0 1 0 5 6 4 4 6 5 7 0 6 1 4 5 6 2 1 2 4 4 1 1 2 6 1 6 2 0 3 7 3 0 0 5 1 7 6 6 6 1 3 4 2 1 0 7 0 5 5\n"
            + "0 7 2 1 4 2 7 3 0 2 1 4 3 5 1 1 1 1 7 1 4 4 1 7 6 0 1 2 0 5 2 0 0 0 5 4 0 3 7 5 3 1 4 1 2 7 2 6 6 4\n"
            + "0 1 3 0 3 4 6 3 4 2 4 0 7 5 1 1 2 7 1 6 4 2 2 0 5 6 3 3 1 1 0 0 0 3 0 4 5 4 3 1 1 6 1 6 2 0 1 4 7 7\n"
            + "0 3 0 0 2 6 1 4 7 5 1 4 3 2 5 1 4 3 6 3 0 2 4 5 7 5 6 2 0 5 6 3 6 4 6 2 0 0 6 0 7 2 2 6 0 0 0 0 0 0\n"
            + "0 6 7 1 6 4 3 6 0 2 6 7 6 2 1 6 6 6 2 0 0 7 3 0 1 1 2 0 0 0 3 1 6 7 5 6 4 1 7 5 2 0 2 6 0 0 0 0 4 0\n"
            + "0 6 7 7 3 3 0 2 0 1 6 4 1 1 1 6 2 3 3 4 2 3 5 0 5 7 7 6 2 7 2 7 3 1 0 5 6 7 1 6 4 1 5 0 0 0 0 0 0 0\n"
            + "0 7 3 0 4 3 0 0 6 6 0 5 1 1 1 1 1 6 0 0 7 0 0 0 2 4 3 2 3 3 6 0 0 1 0 2 6 7 3 4 0 3 2 4 0 0 0 0 0 7\n"
            + "0 0 4 7 2 0 0 0 1 4 2 4 7 7 2 4 2 4 0 5 6 0 0 0 7 0 2 7 4 4 1 6 1 4 2 3 6 2 0 6 5 3 5 0 3 5 6 0 0 1\n"
            + "0 0 7 4 7 0 3 0 4 4 6 2 4 7 0 5 7 1 3 6 5 6 6 7 3 3 6 6 4 2 0 0 3 0 4 7 2 6 4 0 6 2 4 6 7 1 7 2 7 1\n"
            + "0 0 2 6 0 0 6 5 0 4 1 2 2 2 2 7 2 1 0 4 6 4 1 0 1 1 2 2 0 4 4 2 0 0 3 0 3 6 2 2 7 6 6 0 4 6 0 2 2 2\n"
            + "0 0 4 4 7 1 1 1 7 3 7 6 2 3 3 0 5 0 0 6 1 2 6 3 1 7 0 4 7 4 3 6 1 5 1 0 3 7 4 0 3 0 5 6 2 0 0 3 0 5\n"
            + "0 0 7 3 0 5 4 0 7 4 0 0 4 5 7 1 3 2 3 3 5 3 5 3 5 5 5 5 4 2 3 6 0 3 1 7 2 4 5 3 0 0 5 3 6 0 0 7 3 6\n"
            + "0 0 3 5 0 0 1 1 1 0 0 0 5 3 5 5 1 2 7 0 4 3 1 6 7 1 5 7 4 4 5 7 0 3 6 3 3 7 7 4 1 3 5 2 0 0 0 7 7 4\n"
            + "0 0 7 6 3 5 0 7 2 7 7 5 4 0 0 7 0 4 0 0 3 2 3 1 5 7 4 6 0 3 5 5 2 0 6 0 0 0 2 1 1 4 3 6 2 0 5 1 1 6\n"
            + "0 0 1 0 4 1 0 2 5 0 0 0 6 7 3 7 0 0 0 0 4 3 3 3 0 1 0 0 0 1 5 1 5 4 5 1 7 0 0 5 0 5 6 0 3 2 5 0 3 4\n"
            + "0 0 0 0 0 4 0 2 3 1 6 6 6 3 5 3 6 0 0 0 4 7 0 6 1 7 1 0 0 5 5 2 5 1 0 1 1 3 3 4 1 4 2 0 6 3 0 0 6 4\n"
            + "6 4 2 2 0 0 0 3 3 0 0 1 4 0 5 0 2 0 7 0 1 7 7 1 5 7 0 0 0 3 1 5 5 6 0 6 2 6 4 0 7 6 5 1 3 3 7 0 2 5\n"
            + "0 0 0 7 7 0 0 4 4 3 1 6 1 0 1 3 3 1 4 5 7 3 7 0 0 4 0 0 0 7 3 7 2 2 0 1 5 0 7 5 5 2 5 1 0 2 0 0 3 2\n"
            + "0 0 0 3 0 0 0 0 1 2 6 7 1 6 7 0 3 5 2 7 3 0 4 5 2 0 0 0 0 2 5 7 3 7 5 6 0 0 2 2 5 4 7 6 4 5 1 4 4 6\n"
            + "0 4 3 0 0 0 0 3 5 6 3 2 0 3 6 0 6 0 0 1 4 3 6 2 4 7 4 7 1 5 0 4 0 0 2 0 0 0 3 7 6 1 2 5 3 5 2 3 3 3\n"
            + "0 0 0 1 4 0 0 2 1 0 2 0 0 1 7 3 4 3 3 4 7 0 6 7 4 7 3 1 6 1 7 3 4 4 7 5 2 1 3 7 2 5 2 3 3 2 3 0 1 2\n"
            + "0 0 0 0 1 1 0 0 5 7 3 6 6 0 0 6 5 4 2 7 0 0 4 5 5 0 5 7 3 3 0 3 5 5 3 6 0 0 3 5 4 0 0 7 5 1 6 0 0 7\n"
            + "0 0 0 0 5 6 3 1 5 2 0 7 7 7 0 0 1 0 3 6 4 1 6 7 2 1 6 5 2 0 0 7 4 5 0 0 0 0 0 6 6 0 0 5 6 0 2 3 4 5\n"
            + "0 0 7 1 0 1 6 5 6 0 0 5 4 5 7 1 1 6 5 2 2 0 3 7 4 5 2 6 4 0 0 3 4 0 0 0 0 0 0 7 7 7 7 6 4 3 4 4 0 0\n"
            + "0 0 0 1 3 0 0 3 7 1 1 0 4 1 4 4 2 6 1 6 2 2 7 4 2 4 1 7 1 6 4 3 3 1 3 4 0 0 3 2 0 2 0 1 3 3 4 7 1 5\n"
            + "0 0 0 3 4 0 0 2 0 5 5 0 0 1 4 4 0 4 0 1 6 6 4 2 1 0 0 3 7 0 4 3 3 2 3 5 3 5 0 4 0 5 0 3 0 7 7 3 5 6\n"
            + "0 0 0 7 2 0 0 4 2 2 6 2 2 5 0 5 0 3 4 3 5 5 2 0 4 0 0 5 0 0 4 1 6 4 4 3 4 0 0 5 0 1 1 2 0 7 3 4 0 4\n"
            + "0 0 1 1 4 4 1 0 0 0 3 5 4 5 4 2 7 4 6 1 6 7 0 3 0 7 1 7 6 6 3 0 5 7 6 6 4 7 3 4 5 0 0 0 0 6 1 1 5 3\n"
            + "0 0 4 2 5 7 4 4 2 1 2 1 3 4 7 2 7 2 1 6 3 3 0 7 5 6 6 4 5 5 3 3 2 7 5 3 1 4 7 0 0 0 0 0 0 3 1 5 6 5\n"
            + "0 0 0 4 4 1 0 0 6 0 0 7 5 7 5 1 7 3 6 0 2 4 3 4 7 7 3 0 0 0 1 5 5 0 6 7 7 7 4 4 3 6 3 7 5 0 1 1 0 2\n"
            + "0 0 0 1 3 4 7 2 5 0 0 4 4 0 5 2 2 0 1 7 0 1 1 3 6 5 2 6 2 7 7 3 6 7 1 3 4 6 7 5 3 7 4 6 0 0 0 4 3 1\n"
            + "0 0 0 2 1 6 3 5 4 0 0 6 0 0 6 7 0 0 5 2 0 7 7 0 7 0 0 7 7 6 0 0 1 1 0 1 0 1 3 1 0 0 4 7 7 0 0 0 2 6\n"
            + "0 0 0 2 4 0 6 7 2 4 1 5 6 3 0 0 0 0 4 2 7 1 1 5 2 0 0 7 2 2 3 1 3 5 5 7 7 4 0 3 4 2 3 0 0 4 6 6 0 1\n"
            + "0 0 0 4 6 1 0 3 6 4 7 3 5 0 0 0 0 0 0 7 0 0 3 6 2 1 0 2 3 4 6 7 5 0 7 0 5 4 5 1 5 0 0 0 0 4 5 3 1 0\n"
            + "1 3 6 5 5 2 3 7 6 1 0 6 7 3 2 5 6 7 6 6 0 0 7 1 0 5 5 0 3 0 2 0 7 4 5 3 2 5 1 5 3 0 0 0 1 2 0 1 0 0\n"
            + "1 7 3 0 2 0 7 0 4 6 1 1 5 0 7 0 5 7 7 2 6 0 0 1 0 2 3 3 4 2 7 5 3 7 0 0 4 6 6 6 3 0 0 0 7 7 7 5 7 2\n"
            + "";
}
