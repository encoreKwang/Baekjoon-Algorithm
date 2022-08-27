/*
 * 7465 창용마을
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_7465_이광용 {
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			for(int i = 1; i <= N; i++) parents[i] = i;
			
			for(int i = 0; i < M; i++) {
				st =new StringTokenizer(br.readLine(), " ");
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= N; i++) set.add(find(i));
			 
			System.out.println("#" + tc + " " + set.size());
				
			
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; //이미 같은 집합으로 합치지 않음
		
		parents[bRoot] = aRoot;
		return true;
		
	}

	private static int find(int a) {
		if(a == parents[a]) return a; //자신이 대표자인 경우
		return parents[a] = find(parents[a]); //자신이 속한 집합의 대표자를 자신의 부모로 설정한다.
		//리턴값을 받아오면서 자신의 부모를 바꿔준다
	}
}
