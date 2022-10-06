import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 낚시왕
 */
public class Main_17143_이광용{
	private static int[] dr = {0, -1, 1, 0, 0}; //상 하 우 좌
	private static int[] dc = {0, 0, 0, 1, -1};

	static class Shark implements Comparable<Shark>{
		int r, c, s, d, z;
		//depth도 있어야될것같아 : 위치가 겹친 상어가 이번 턴에서 아직 움직이지 않은건지, 움직여서 합쳐져야하는 상어인지 판별
		//근데 내림차순으로 꺼내면 그 자리에 크기가 큰 값이 있다면 이번 턴에서 움직인 상어를 뜻하므로  depth가 필요 x 
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;//속력
			this.d = d;//방향
			this.z = z;//크기
		}
		@Override
		public int compareTo(Shark o) {
			return o.z - this.z; //내림차순
		}
	}
	static private Shark[][] map;
	static private int R;
	static private int C;
	static private int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //상어의 수
		int ans = 0;
		map = new Shark[R+1][C+1];
		//PriorityQueue<Shark> pQ = new PriorityQueue<>();
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int r =Integer.parseInt(st.nextToken());
			int c =Integer.parseInt(st.nextToken());
			int s =Integer.parseInt(st.nextToken());
			int d =Integer.parseInt(st.nextToken());
			int z =Integer.parseInt(st.nextToken());
			//pQ.add(new Shark(r, c, s, d, z));
			map[r][c] = new Shark(r, c, s, d, z);
		}//end of 입력
		
		for(int fisher = 1; fisher <= C; fisher++) {
			for(int x = 1; x <= R; x++) {
				if(map[x][fisher] != null) {
					ans += map[x][fisher].z;
					map[x][fisher] = null;
					break;
				}
			}//한 턴에서의 낚시
			move();//상어 이동
		}
		System.out.println(ans);
	}
	static private void move() {
		PriorityQueue<Shark> pQ = new PriorityQueue<>();
		for(int i = 1; i <= R; i++) {//map에 있는 상어 큐에 넣기
			for(int j = 1; j <= C; j++) {
				if(map[i][j] != null) {
					pQ.add(map[i][j]);
					map[i][j] =null;//map에 상어 모두 제거
				}
			}
		}
		while(!pQ.isEmpty()) {
			Shark shark = pQ.poll();
			int speed = shark.s;
			int nr = shark.r;
			int nc = shark.c;
			while(speed-- > 0) {
				nr += dr[shark.d];
				nc += dc[shark.d];
				if(nr >= 1 && nr <= R && nc >= 1 && nc <= C) continue;
				else {//범위를 벗어났으면
					nr -= dr[shark.d];
					nc -= dc[shark.d]; //다시 이전으로 복귀
					switch (shark.d) {
					case 1:
						shark.d = 2;
						break;
					case 2:
						shark.d = 1;
						break;
					case 3:
						shark.d = 4;
						break;
					case 4:
						shark.d = 3;
						break;
					}
					//방향 바꿔주고 그 방향으로 진행
					nr += dr[shark.d];
					nc += dc[shark.d];
				}
			}
//			if(!(nr >= 1 && nr <= R && nc >= 1 && nc <= C)) System.out.println("nr : " + nr + " nc : " + nc);;
			//&& map[nr][nc].z > shark.z
			if(map[nr][nc] != null ) { //이번 턴에 옮긴 다른 상어가 이미 해당 위치에 있는 경우, 크기가 큰 상어만 남도록함.
				continue;
//			}else if(map[nr][nc] != null && map[nr][nc].z < shark.z){ //이번 턴이 아닌 저번 턴에 옮긴 상어가 있는 경우 -> 큐에 그 상어가 들어있기 때문에 map에 덮어써도 됨.
//				map[nr][nc] = shark;
			}else {
				map[nr][nc] = new Shark(nr, nc, shark.s, shark.d, shark.z);
			}
		}
	}
}
