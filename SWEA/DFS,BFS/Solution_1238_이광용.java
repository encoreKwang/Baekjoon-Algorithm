
/**
 * contact
 * BFS
 * 인접리스트에 입력값 넣고
 * 시작 정점의 인접정점들을 전부 큐에 넣고 BFS탐색 시작
 * 큐의 사이즈만큼 큐에서 원소를 빼는데 그 때 그 거리에 해당하는 최대값을 구해서 갱신한다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238_이광용 {
	private static int length;
	private static int s, max;
	private static Queue<Integer> q;
	private static ArrayList<ArrayList<Integer>> arr;
	private static boolean[] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			s = 0;
			max = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			length = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			q = new LinkedList<>();
			vis = new boolean[101];
			arr = new ArrayList<>();
			for (int i = 0; i < 101; i++) {
				arr.add(new ArrayList<Integer>());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < length / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				arr.get(from).add(to);
			} // 입력완료

			BFS();
			System.out.println("#" + t + " " + max);
		}
	}

	private static void BFS() {
		vis[s] = true;
		max = s;
		q.offer(s);
		// 시작 정점만 큐에 넣고 시작
		while (!q.isEmpty()) {
			int tmpMax = 0;
			int tmpQsize = q.size();
			for (int i = 0; i < tmpQsize; i++) {
				int next = q.poll();
				tmpMax = Math.max(tmpMax, next);//현재 큐 사이즈에 있는 정점들은 전부 같은 거리이며 방문하지 않은 정점들이다.
				for (int j = 0; j < arr.get(next).size(); j++) {
					if(vis[arr.get(next).get(j)]) continue;
					vis[arr.get(next).get(j)] = true; //방문 체크는 방문 예정 정점을 큐에 넣을 때 한다.
					q.add(arr.get(next).get(j));
				}
				
			}
			if (tmpMax != 0) {
				max = tmpMax;
			}
		}
	}
}
