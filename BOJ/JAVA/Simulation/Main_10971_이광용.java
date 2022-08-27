/**
 * 외판원 순회
 * 순회이므로 시작 정점이 무슨 정점이든지 상관없이 최소 경로는 유일한 한 경로만 존재한다.
 * 모든 정점을 한번씩 뽑는데,순차적으로 그 전에 뽑은 열에 해당하는 행에서 뽑아야한다.
 * 그리고 한번 방문한 정점은 다시 방문하지 못하도록 막는데 순회의 마지막은 다시 시작정점으로 돌아와야하므로
 * 그 때는 방문여부와 상관없이 시작 정점으로 돌아오게 해야한다.
 * @author multicampus
 *
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_10971_이광용 {

	private static int N;
	private static int[][] map;
	private static ArrayList<Integer> vis1;
	
	private static int MinCost, tmpCost;
	private static boolean[] vis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		MinCost = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		vis = new boolean[N];
		dfs(0,0);
		System.out.println(MinCost);
	}

	private static void dfs(int sNode, int cnt) {
		if (cnt == N) {
			MinCost = Math.min(MinCost, tmpCost);
//			System.out.println("MINCOST : " + MinCost);
			return;
		} else {
			for (int j = 0; j < N; j++) {
				if ((vis[j] || map[sNode][j] == 0) && (cnt < N-1)) 
					//0인 경우를 따로 빼놓으면, 마지막 원소임에도 불구하고  continue가 된다
					continue;
				else if(cnt == N-1){
					//마지막 정점은 무조건 시작 정점인 0으로만 가게끔 해야한다
					//j의 시작은 0
					//원소가 0인 경우는 걸러야하므로 return
					if(map[sNode][j] == 0) return;
					vis[sNode] = true;
					tmpCost += (map[sNode][j]);
//					System.out.println(cnt + " , " + sNode + " : " + j + " : " + map[sNode][j]);
					dfs(j ,cnt + 1);
					tmpCost -= map[sNode][j];
					vis[sNode] = false;
//					System.out.println("[취소]" +cnt + " , " + sNode + " : " + j + " : " + map[sNode][j]);
					return;//마지막 노드는 시작정점(0)만 갈 수 있으므로 바로 리턴해줘야한다.
				}
				else {
					vis[sNode] = true;
					tmpCost += (map[sNode][j]);
//					System.out.println(cnt + " , " + sNode + " : " + j + " : " + map[sNode][j]);
					dfs(j ,cnt + 1);
					tmpCost -= (map[sNode][j]);
					vis[sNode] = false;
//					System.out.println("[취소]" +cnt + " , " + sNode + " : " + j + " : " + map[sNode][j]);
				}
			}
		}
	}
}
