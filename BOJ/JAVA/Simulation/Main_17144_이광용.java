/**
 * 미세먼지
 * 먼지가 퍼지면서 연쇄적인효과가 일어나면 안됨 -> 동시에 퍼짐을 가정하기 때문에 따로 기존의 값을 저장해둬야함
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17144_이광용 {
	private static int R;
	private static int C;
	private static int T;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {1, 0, -1, 0};
	private static int[][] map;
	private static int machine1;
	private static int machine2;
	private static ArrayList<int[]> arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		machine1 = 0;//공기청정기의 행을 저장
		machine2 = 0;
		for (int i = 0; i < R; i++) {
			 st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(machine1 == 0) {
						machine1 = i;
					}
					else {
						machine2=i;
					}
				}
			}
		}//입력완료
		
		//미세먼지 확산 -> 중복되서 처리되야하므로 방문 처리 하지 않는다.
		for(int i = 0; i < T; i++) {
			check();
			spread();
			circul();
		}
		
		check();
		int ans = 0;
		for(int i =0 ; i < arr.size(); i++) {
			ans += arr.get(i)[2];
		}
		System.out.println(ans);
	}


	private static void circul() {
		for(int i = machine1-1; i >= 0 ;i--) {
			map[i+1][0] = map[i][0];
		}
		for(int j = 1; j < C; j++) {
			map[0][j-1] = map[0][j];
		}
		for(int i = 1; i <= machine1 ;i++) {
			map[i-1][C-1] = map[i][C-1];
		}
		for(int j = C-2; j >= 0; j-- ) {
			map[machine1][j+1] = map[machine1][j];
		}
		map[machine1][0] = -1;
		map[machine1][1] = 0;
		
		for(int i = machine2 + 1; i < R; i++) {
			map[i-1][0] = map[i][0];
		}
		for(int j = 1; j < C; j++) {
			map[R-1][j-1] = map[R-1][j];
		}
		for(int i = R-2; i >= machine2; i--) {
			map[i+1][C-1] = map[i][C-1]; 
		}
		for(int j = C - 2; j >= 0; j--) {
			map[machine2][j+1] = map[machine2][j];
		}
		map[machine2][0] = -1;
		map[machine2][1] = 0;
	}


	private static void spread() {
		for(int i = 0; i < arr.size(); i++) {
			int tmpX = arr.get(i)[0];
			int tmpY = arr.get(i)[1];
			int tmpV = arr.get(i)[2];
			int cnt = 0;
			for(int k = 0; k < 4; k++) {
				int nx = tmpX + dx[k];
				int ny = tmpY + dy[k];
				if (inRange(nx, ny) && map[nx][ny] != -1) {
					cnt++;
					map[nx][ny] += tmpV / 5;
				}
			}
			map[tmpX][tmpY] -= (tmpV / 5) * cnt; 
		}
	}


	private static void check() {
		arr = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != -1 && map[i][j] != 0) {
					arr.add(new int[] {i,j,map[i][j]});
				}
			}
		}
	}


	private static boolean inRange(int nx, int ny) {
		if(nx < 0 || nx >= R || ny < 0 || ny >= C) return false;
		else return true;
	}
}