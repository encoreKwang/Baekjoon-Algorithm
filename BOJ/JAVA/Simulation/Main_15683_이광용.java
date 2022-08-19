import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 감시
 * 완전탐색 -> cctv 최대 8개 & 1번 카메라 4방향 가능 -> 4^8 = 6만개
 * 입력받을 때, 0을 카운트하고 cctv가 확보할 수 있는 0의 최대범위를 구해서 빼면 최소 사각지대가 나온다.  
 * @author dnflr
 *
 */
public class Main_15683_이광용 {
	static int n, m, tmpMaxCnt, maxCnt, zeroCnt, ans;
	static int[][] map;
	static ArrayList<Point> list; 
	static int dx[] = {0, -1, 0, 1}; //우 상 좌 하
	static int dy[] = {1, 0, -1, 0};
	static boolean[][] vis; 
	
	static class Point {
		int x,  y;
		
		Point(int x , int y){
			this.x = x;
			this.y = y;
		}
	}
	static void recur(int cctvIdx) {
		if(cctvIdx == list.size()) {
			//최대값 비교
			maxCnt = Math.max(maxCnt, tmpMaxCnt);
			return;
		}
		int tmpX = list.get(cctvIdx).x;
		int tmpY = list.get(cctvIdx).y;
		int x = map[tmpX][tmpY];

		switch (x) {
		case 1:
			for(int i = 0; i < 4; i++) { //4가지 방향
				int nx = tmpX+dx[i];
				int ny = tmpY+dy[i];
				//이번 방향에 대해서 방문한 점들을 모두 저장하고 나중에 재귀가 돌아왔을때, 다시 원복해준다.
				ArrayList<Point> tmpVisited = new ArrayList<>();

				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[i];
					ny += dy[i];
					//방문한자리 체크하고 재귀 부르고 
					//다시 왔을때... 방문 풀고 그럴려면 방문을 기억해둬야함... 
					//이번에 증가된 cnt도 함께 원복 해야하는데 그건 이번 방향에서 방문한 tmpVisited의 사이즈로 하면됨
				}
				recur(cctvIdx+1);
				//원상복귀
				tmpMaxCnt -= tmpVisited.size();
				for(Point p : tmpVisited) {
					vis[p.x][p.y]= false;
				}
			}
			break;
		case 2:
			for(int i = 0; i < 2; i++) { //2가지 방향-> 오른쪽과 왼쪽 / 위쪽과 아래쪽
				int nx = tmpX+dx[i];
				int ny = tmpY+dy[i];
				//이번 방향에 대해서 방문한 점들을 모두 저장하고 나중에 재귀가 돌아왔을때, 다시 원복해준다.
				ArrayList<Point> tmpVisited = new ArrayList<>();
				
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {//오른쪽 or 위쪽
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[i];
					ny += dy[i];
				}
				//반대쪽 확인 : 왼쪽 or 아래쪽
				nx = tmpX+dx[i+2]; //다시 방향 맞춰서 초기화
				ny = tmpY+dy[i+2];
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[i+2];
					ny += dy[i+2];
				}
				
				recur(cctvIdx+1);
				//원상복귀
				tmpMaxCnt -= tmpVisited.size();
				for(Point p : tmpVisited) {
					vis[p.x][p.y]= false;
				}
			}
			
			break;
		case 3:
			for(int i = 0; i < 4; i++) { //4가지 방향-> 오른쪽과 위쪽/ 위쪽과 왼쪽
				//왼쪽과 아래쪽 / 아래쪽과 오른쪽
				int nx = tmpX+dx[i];
				int ny = tmpY+dy[i];
				//이번 방향에 대해서 방문한 점들을 모두 저장하고 나중에 재귀가 돌아왔을때, 다시 원복해준다.
				ArrayList<Point> tmpVisited = new ArrayList<>();
				//총 두 방향 확인 : 첫번째 방향
				//오른쪽 / 위쪽 / 왼쪽 / 아래쪽
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[i];
					ny += dy[i];
				}
				//두번째 방향
				//위쪽 / 왼쪽 / 아래쪽 / 오른쪽
				nx = tmpX+dx[(i+1)%4]; //다시 방향 맞춰서 초기화
				ny = tmpY+dy[(i+1)%4];
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[(i+1)%4];
					ny += dy[(i+1)%4];
				}
				recur(cctvIdx+1);
				//원상복귀
				tmpMaxCnt -= tmpVisited.size();
				for(Point p : tmpVisited) {
					vis[p.x][p.y]= false;
				}
			}

			break;
		case 4:
			for(int i = 0; i < 4; i++) { //4가지 방향-> 오른쪽과 위쪽과 왼쪽 / 위쪽과 왼쪽과 아래쪽
				//왼쪽과 아래쪽과 오른쪽 / 아래쪽과 오른쪽과 위쪽
				int nx = tmpX+dx[i];
				int ny = tmpY+dy[i];
				//이번 방향에 대해서 방문한 점들을 모두 저장하고 나중에 재귀가 돌아왔을때, 다시 원복해준다.
				ArrayList<Point> tmpVisited = new ArrayList<>();
				//총 세방향 확인 : 첫번째 방향
				//오른쪽 / 위쪽 / 왼쪽 / 아래쪽
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[i];
					ny += dy[i];
				}
				//두번째 방향
				//위쪽 / 왼쪽 / 아래쪽 / 오른쪽
				nx = tmpX+dx[(i+1)%4]; //다시 방향 맞춰서 초기화
				ny = tmpY+dy[(i+1)%4];
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[(i+1)%4];
					ny += dy[(i+1)%4];
				}
				//세번째 방향
				//왼쪽 / 아래쪽 / 오른쪽 / 위쪽
				nx = tmpX+dx[(i+2)%4]; //다시 방향 맞춰서 초기화
				ny = tmpY+dy[(i+2)%4];
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[(i+2)%4];
					ny += dy[(i+2)%4];
				}
				recur(cctvIdx+1);
				//원상복귀
				tmpMaxCnt -= tmpVisited.size();
				for(Point p : tmpVisited) {
					vis[p.x][p.y]= false;
				}
			}

			break;
		case 5:
			//이번 방향에 대해서 방문한 점들을 모두 저장하고 나중에 재귀가 돌아왔을때, 다시 원복해준다.
			ArrayList<Point> tmpVisited = new ArrayList<>();
			for(int i = 0; i < 4; i++) { //4가지 방향
				int nx = tmpX+dx[i];
				int ny = tmpY+dy[i];
				while(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					//6인 경우, 거기까지 break하고 재귀로 다음 cctv를 탐색한다
					if( map[nx][ny] == 6) break;
					//혹시 CCTV 자리거나 이미 방문한 곳이라면 지나간다.
					if( map[nx][ny] == 0 && vis[nx][ny] == false) {
						tmpMaxCnt++;
						vis[nx][ny] = true;
						tmpVisited.add(new Point(nx, ny));
					}
					nx += dx[i];
					ny += dy[i];
				}
			}
			recur(cctvIdx+1);
			//원상복귀
			tmpMaxCnt -= tmpVisited.size();
			for(Point p : tmpVisited) {
				vis[p.x][p.y]= false;
			}

			break;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); //행
		m = sc.nextInt(); //열
		map = new int[n][m];
		vis = new boolean[n][m];
		
		maxCnt = 0; //cctv영역에 들어올 수 있는 0의 최대값
		tmpMaxCnt = 0; //최대값을 갱신할 임시변수
		zeroCnt = 0; //map의 총 0의 개수 저장
		ans = 0;//사각지대의 최소값
		
		list = new ArrayList<>();
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j < m; j ++) {
				vis[i][j] = false;
				map[i][j] = sc.nextInt();
				//0의 개수를 구하고 cctv영역에 들어온 0의 최대값(maxCnt)을 빼면 사각지대의 최소값이 나온다.
				if(map[i][j] == 0) zeroCnt++;
				//입력받으면서 cctv의 위치를 저장
				if(map[i][j] != 0 && map[i][j] != 6) { //cctv 위치 저장
					list.add(new Point(i, j));
				}
			}
		}

		recur(0);
		ans = zeroCnt - maxCnt;
		System.out.println(ans);
		
		
	}

}
