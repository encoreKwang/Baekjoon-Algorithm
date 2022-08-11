import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
	int x;
	int y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution_1227_이광용 {
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static Queue<Pair> q;
	static boolean vis[][];
	static int[][] map;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int t =1; t <= 10; t++) {
			sc.nextInt();
			map = new int[100][100];
			for (int i = 0; i < 100; i++) {
				char[] cArr = sc.next().toCharArray();
				for (int j = 0; j < 100; j++) {
					map[i][j] = cArr[j];
				}
			}//입력 완료
			q =new LinkedList<>();
			vis = new boolean[100][100];
			vis[1][1] = true; //시작점 방문
			q.offer(new Pair(1,1));//시작점 넣기
			ans = 0;
			BFS();
			System.out.println("#" + t + " "+ans);
			
		}
	}
	public static void BFS() {
		while(!q.isEmpty()) {
			if(ans == 1) break;
			Pair cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if(nx < 1 || nx >= 99 || ny < 1 || ny >= 99) continue;
				//다음 갈 곳이 3 도착점인경우
				if (map[nx][ny] == '3') {
					ans = 1;
					break;
				}
				if(vis[nx][ny] || map[nx][ny] == '1') continue;
				vis[nx][ny] = true;
				q.offer(new Pair(nx, ny));
			}
		}
	}

}
