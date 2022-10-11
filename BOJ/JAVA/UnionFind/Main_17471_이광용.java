import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 게리맨더링
 */
public class Main_17471_이광용{

	private static int[] population;
	private static int N;
	private static boolean[] check;
	private static int[] root_true;
	private static int[] root_false;
	private static int ANS;
	private static ArrayList<ArrayList<Integer>>  adjList;

	static int[] init(int[] root, int n) {
		root = new int[n+1];
		for (int i = 1; i <= n; i++) {
			root[i] = i;
		}
		return root;
	}
	static int find(int x, int[] root) {
		//루트 노드는 부모 노드 번호로 자기 자신을 가진다.
		if(root[x] == x) {
			return x;
		}else {
			//각 노드의 부모노드를 찾아 올라간다.
			return root[x] = find(root[x], root);
		}
	}
	static int[] union(int x, int y, int[] root) {
		//각 원소가 속한 트리의 루트노드를 찾는다. 
		x = find(x, root);
		y = find(y, root);
		
		root[y] = x;
		return root;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		adjList = new ArrayList<>();
		for(int i = 0; i <= N; i++) adjList.add(new ArrayList<Integer>());
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int adjCnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < adjCnt; j++) {
//				map[i][] = true;
//				union(i, Integer.parseInt(st.nextToken()));
				adjList.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}//end of 입력
		check = new boolean[N+1];
		ANS =Integer.MAX_VALUE;
		subset(1, 0);
		System.out.println((ANS == Integer.MAX_VALUE) ? -1 : ANS );
	}
	private static void subset(int cnt, int checkCnt) {
		if(cnt == N+1) {
			if(checkCnt == 0 || checkCnt == N) return;
			//bfs
			ArrayList<Integer> arr_true = new ArrayList<>();
			ArrayList<Integer> arr_false = new ArrayList<>();
			Queue<Integer> qTrue= new LinkedList<>();
			Queue<Integer> qFalse = new LinkedList<>();
			boolean first_true = true;
			boolean first_false = true;
			
			for(int i = 1; i <= N; i++) {
				if(check[i] == true) {
					if(first_true) {
						qTrue.add(i);
						first_true=false;
					}
					arr_true.add(i);
				}else {
					if(first_false) {
						qFalse.add(i);
						first_false=false;
					}
					arr_false.add(i);
				}
			}
			root_true = new int[N+1];
			root_false = new int[N+1];
			root_true = init(root_true, N);
			root_false = init(root_false, N);
			
			//bfs를 각 선거구마다 두번 돌려야겟다
			root_true= bfs(arr_true, qTrue, root_true);
			root_false= bfs(arr_false, qFalse, root_false);
			
			boolean flag = true;
			first_true = true;
			first_false = true;
			int rootNode_true = 0;
			int rootNode_false = 0;
			int popSum_true = 0;
			int popSum_false = 0;
			for(int i = 1; i <= N; i++) {
				if(check[i] == true) {// 1 3 4
					//모두 같은 루트노드를 가진다면 선거구로써 가능
					if(first_true) {
						rootNode_true=find(i, root_true);
						first_true=false;
						popSum_true += population[i];
					}else {
						if (find(i,root_true) != rootNode_true){
							flag = false;
							break;
						}
						else {
							popSum_true += population[i];
						}
					}
				}else { //false인 경우 (다른 선거구)
					//모두 같은 루트노드를 가진다면 선거구로써 가능
					if(first_false) {
						rootNode_false=find(i, root_false);
						first_false=false;
						popSum_false += population[i];
					}else {
						if (find(i, root_false) != rootNode_false){
							flag = false;
							break;
						}else {
							popSum_false += population[i];
						}
					}
				}
			}
			if(flag){
				//ANS갱신
				int diff = Math.abs(popSum_true - popSum_false);
				ANS = Math.min(ANS, diff);
				return;
			}else {
				return;
			}
		}
		check[cnt] = true;
		subset(cnt+1, checkCnt+1);
		check[cnt] = false;
		subset(cnt+1, checkCnt);
	}
	private static int[] bfs(ArrayList<Integer> arr, Queue<Integer> q, int[] roots) {
		boolean[] vis = new boolean[N+1]; 
		while(!q.isEmpty()) {
			int sNode = q.poll();
			for(int adjNode : adjList.get(sNode)) {
				if(arr.contains(adjNode) && !vis[adjNode]) {
					vis[adjNode] = true;
					roots = union(sNode, adjNode, roots);
					q.offer(adjNode);
				}
			}
		}
		return roots;
	}
}
