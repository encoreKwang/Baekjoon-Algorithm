
/**
 * 숨바꼭질
 * 최단거리가 보장되야하므로 bfs
 * 이미 방문한 곳은 또 다시 방문하면 안되므로 체크한다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_이광용 {
	static Queue<Integer> q;
	private static int N;
	private static int K, ans;
	static int[] dir = { 1, -1 };
	static int dis[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dis = new int[100001]; // 방문 체크와 거리 체크(해당 위치까지 가는데 걸리는 시간)를 동시에 할 수 있는 배열
		Arrays.fill(dis, -1); // -1이라면 방문을 하지 않은것을 의미.
		dis[N] = 0;//시작점 방문처리
		BFS();
		System.out.println(dis[K]);

	}

	public static void BFS() {
		q = new LinkedList<>();
		q.add(N);
		while (!q.isEmpty()) {
			int pos = q.poll();
			for (int j = 0; j < 3; j++) {
				int nx = 0;
				if (j == 2)
					nx = pos * 2;
				else
					nx = pos + dir[j];
				if (nx < 0 || nx > 100000) //범위 체크
					continue;
				if(dis[nx] != -1) continue; //이미 방문했다면
				dis[nx] = dis[pos] + 1; //현재 거리(시간값)에서 +1 그리고 동시에 방문했다는 의미도 포함
				q.offer(nx);

				if (nx == K) {
					return;
				}
			}

		}

	}
}