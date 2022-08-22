/**
 * 알파벳
 * dfs로 4방 탐색을 돌리면서 방문한 알파벳에 표시해두고 
 * 리턴 돌아올때 원상복귀해준다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987_이광용 {
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {1, 0, -1, 0};
	static boolean[] vis;
	static int[][] map;
	static int ans, tmpCnt;
	private static int R;
	private static int C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); //행
		C = Integer.parseInt(st.nextToken()); //열
		vis = new boolean[26]; //알파벳 방문 체크 26개 0~25
		map = new int[R][C];
		for(int i = 0; i < R ;i ++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - 'A';
			}
		}//입력 완료
		vis[map[0][0]] = true; //출발점 알파벳을 방문하고 카운트함.
		ans++;
		tmpCnt++; //ans와 최대값 비교할 카운트변수
		dfs(0, 0);
		System.out.println(ans);
	}
	public static void dfs(int x, int y) {
		ans = Math.max(ans, tmpCnt);
		for(int k = 0 ; k < 4 ; k ++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if(vis[map[nx][ny]]) continue; //이미 방문한 적있는 알파벳이라면 넘긴다.
			vis[map[nx][ny]] = true;
			tmpCnt++;
			dfs(nx, ny);
			//원상복귀
			tmpCnt--;
			vis[map[nx][ny]] = false;
		}
	}
}
