
/**
 * 적록색약
 * 이차원 배열에서 해당 원소값의 범위를 점차 퍼져나가면서 방문을 해야한다 -> BFS
 * BFS가 호출될 때마다 카운트를 하고 방문 배열을 만들어서 체크한다. 
 * @author dnflr
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10026_이광용 {
	private static int N, ans1, ans2;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };
	private static char[][] map;
	private static boolean[][] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		vis = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		} // 입력완료
			// 적록색약이 아닌 사람이 봤을 때
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!vis[i][j]) {
					vis[i][j] = true;
					ans1++;
					BFS(i, j, map[i][j]);
				}
			}
		}
		// 적록색약 : 빨강 = 초록
		vis = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!vis[i][j]) {
					vis[i][j] = true;
					ans2++;
					BFS2(i, j, map[i][j]);
				}
			}
		}
		System.out.println(ans1 + " " + ans2);
	}

	private static void BFS2(int x, int y, char c) {
		if (c == 'B') {
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] { x, y });
			while (!q.isEmpty()) {
				int[] tmp = q.poll();
				int tmpX = tmp[0];
				int tmpY = tmp[1];
				for (int i = 0; i < 4; i++) {
					int nx = tmpX + dx[i];
					int ny = tmpY + dy[i];
					if (inRange(nx, ny) && !vis[nx][ny] && map[nx][ny] == c) {
						vis[nx][ny] = true;
						q.offer(new int[] { nx, ny });
					}
				}
			}
		}
		else {
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] { x, y });
			while (!q.isEmpty()) {
				int[] tmp = q.poll();
				int tmpX = tmp[0];
				int tmpY = tmp[1];
				for (int i = 0; i < 4; i++) {
					int nx = tmpX + dx[i];
					int ny = tmpY + dy[i];
					if (inRange(nx, ny) && !vis[nx][ny] && 
							(map[nx][ny] == 'R' || map[nx][ny] == 'G')) {
						vis[nx][ny] = true;
						q.offer(new int[] { nx, ny });
					}
				}
			}
		}
	}

	private static void BFS(int x, int y, char c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int tmpX = tmp[0];
			int tmpY = tmp[1];
			for (int i = 0; i < 4; i++) {
				int nx = tmpX + dx[i];
				int ny = tmpY + dy[i];
				if (inRange(nx, ny) && !vis[nx][ny] && map[nx][ny] == c) {
					vis[nx][ny] = true;
					q.offer(new int[] { nx, ny });
				}
			}
		}

	}

	private static boolean inRange(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			return false;
		else
			return true;

	}

}
