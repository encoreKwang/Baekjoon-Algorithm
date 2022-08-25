/**
 * ABCDE
 * A B C D E
 * 서로 다른 정점의 간선의 연속적인 깊이가 4인  연속된 간선들을  찾아라
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13023_이광용 {

	private static int M;
	private static int N;
	private static boolean[] vis;
	private static boolean flag;
	private static ArrayList<ArrayList<Integer>>  arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N =Integer.parseInt(st.nextToken()); //친구의 수
		M =Integer.parseInt(st.nextToken()); //관계의 수

		//정점 인접 행렬 -> 시간초과 -> 인접 리스트

		arr = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			arr.add(new ArrayList<>());
		}
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr.get(s).add(e);
			arr.get(e).add(s);
		}//입력완료
		//재귀 방문 배열: 각 인덱스를 숫자 번호로 하고 방문 여부를 체크한다.
		//방문한 곳으로는 깊이 탐색을 하지 않는다.
		//dfs 호출시 각 시작정점을 넘겨준다.
		flag = false;
		for(int i = 0; i < N; i++) {
			if(flag) break;
			vis = new boolean[N];
			vis[i] = true;
			dfs(i, 0);
		}
		if(flag) System.out.println(1);
		else System.out.println(0);
	}
	public static void dfs(int sNode, int cnt) {
		if(flag) return; 
		if(cnt == 4) { //깊이(cnt)가 4개가 되면 멈춘다.
			flag = true;
			return;
		}
		else {
			for(int i = 0; i < arr.get(sNode).size(); i++) {
//간선이 있다면 깊이를 +1시키고 그 정점 (행)으로 넘어간다.
				int nextNode = arr.get(sNode).get(i);
				if(vis[nextNode]) continue;
					vis[nextNode] = true;
					dfs(nextNode ,cnt+1);
					vis[nextNode] = false;
			}
		}
	}

}

