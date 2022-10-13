/**
 * 자신의 키가 몇번째인지 알 수 있을려면 자신보다 키가 크거나 작은 학생의 수가 N-1명이어야한다.
 * 인접행렬로 입력 받아서 DFS탐색을 한다. => 두가지 버전의 인접행렬
 * 자신보다 키가 큰 경우를 탐색하면서 카운트 + 자신보다 키가 작은 경우를 탐색하면서 카운트 = N - 1 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5643_키순서 {
	private static int N, ANS, CNT;
	private static int M, compare[][],reverseCompare[][] ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());//학생 수 
			M = Integer.parseInt(br.readLine());// 비교 횟수
			ANS=0;
			compare= new int[N+1][N+1];
			reverseCompare= new int[N+1][N+1];
			for(int i = 0 ;i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				compare[a][b] = 1; //행보다 열이 더 크다.
				reverseCompare[b][a] = 1; //행보다 열이 더 작다
			}
			for(int i = 1; i <= N; i++) {
				CNT = 0;
				//시작점, 인접행렬, 방문체크배열
				dfs(i ,compare, new boolean[N+1]); //시작점보다 더 큰 학생을 카운트 
				dfs(i ,reverseCompare, new boolean[N+1]); //시작점보다 더 작은 학생을 카운트
				if(CNT == N-1) ANS++;
			}
			System.out.println("#" + tc +" " + ANS);
		}
	}

	private static void dfs(int s, int[][] adjMatrix, boolean[] vis) {
		vis[s] = true;
		for(int i = 1; i <=N; i++) {
			if(adjMatrix[s][i] == 1 && !vis[i]) { //s정점과 i정점이 관계가 있다면
				CNT++;
				dfs(i, adjMatrix, vis);
			}
		}
	}
}
