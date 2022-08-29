/**
 * 토마토
 * bfs: 한 깊이에 해당하는 토마토들이 동시에 퍼져나감
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_이광용 {
	private static int M;
	private static int N;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {1, 0, -1, 0};
	private static int[][] map;
	private static Queue<int[]>  q;
	private static boolean[][] vis;
	private static int MinAns;
	private static int notTomatoCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vis = new boolean[N][M];
		q = new LinkedList<>();
		notTomatoCnt = 0; //익지 않은 토마토의 개수
		for(int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {//익은 토마토
					q.add(new int[] {i,j, 0});//배열의 마지막 인자는 distance를 의미함.
					vis[i][j]= true;
				}
				if(map[i][j] == 0) {//익지 않은 토마토
					notTomatoCnt++;
				}
			}
		}//입력 완료
		MinAns = 0;
		if(notTomatoCnt == 0) System.out.println(0); //모든 토마토가 익어있는 상태
		else {
			BFS();
			if(notTomatoCnt != 0) {
				//모두 익지는 못하는 상태
				System.out.println(-1);
			}
			else {
			 System.out.println(MinAns -1); //처음 시작점부터 qsize잴때 depth를 늘려주기 때문에 
			 //-1해줘야함.
			}
		}
	}

	private static void BFS() {
		while(!q.isEmpty()) {
			int tmpQsize = q.size();
			MinAns++;//while반복문이 실행될때마다 depth가 증가한다.
			for (int i = 0; i < tmpQsize; i++) {
				int[] tmp = q.poll();
				int tmpX = tmp[0];
				int tmpY = tmp[1];
				int tmpD = tmp[2];
				for (int j = 0; j < 4; j++) {
					int nx = tmpX + dx[j];
					int ny = tmpY + dy[j];
					if(isRange(nx,ny) && map[nx][ny] == 0 && !vis[nx][ny]) {
						notTomatoCnt--;
						vis[nx][ny] = true;
						q.offer(new int[] {nx,ny, tmpD+1});
					}
				}
			}

		}
		
	}

	private static boolean isRange(int nx, int ny) {
		if(nx < 0 || nx >= N || ny < 0 || ny >= M) return false;
		else return true;
	}
}