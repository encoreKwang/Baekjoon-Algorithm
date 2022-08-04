import java.util.Scanner;

//
public class Solution_1873_이광용 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int t = 1; t <= tc; t++) {
			int h = sc.nextInt(); // 높이
			int w = sc.nextInt(); // 넓이
			int pX = -1, pY = -1; // 전차의 위치
			int dir = 0; // 전차의 방향을 나타냄 / 1-> 위 , 2-> 오른쪽, 3->아래쪽 , 4 -> 왼쪽
			int[] dx = { 0, -1, 0, 1, 0 };// 방향에 따른 포탄의 방향성
			int[] dy = { 0, 0, 1, 0, -1 };
			char[][] map = new char[h][w];
			for (int i = 0; i < h; i++) { // 입력 , 전차의 방향과 위치
				String s = sc.next();
				for (int j = 0; j < w; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '^') {
						dir = 1;
						pX = i;
						pY = j;
					} else if (map[i][j] == '>') {
						dir = 2;
						pX = i;
						pY = j;
					} else if (map[i][j] == 'v') {
						dir = 3;
						pX = i;
						pY = j;
					} else if (map[i][j] == '<') {
						dir = 4;
						pX = i;
						pY = j;
					}
				}
			}
			int n = sc.nextInt();

			String str = sc.next();
			for (int i = 0; i < n; i++) {
				char order = str.charAt(i);
				if (order == 'U') {
					// 일단 방향을 바꿈
					map[pX][pY] = '^';
					dir = 1;
					if (pX-1 >= 0 && pX-1 <h &&map[pX - 1][pY] == '.') { // 평지라면
						map[pX - 1][pY] = map[pX][pY];
						map[pX][pY] = '.'; // 기존의 장소는 평지로 바꿈
						pX = pX - 1;

					}
				} else if (order == 'R') {
					map[pX][pY] = '>';
					dir = 2;
					if (pY+1 >= 0 && pY+1 < w && map[pX][pY + 1] == '.') { // 평지라면
						map[pX][pY + 1] = map[pX][pY];
						map[pX][pY] = '.'; // 기존의 장소는 평지로 바꿈
						pY = pY + 1;
					}

				} else if (order == 'D') {
					map[pX][pY] = 'v';
					dir = 3;
					if (pX + 1 >= 0 && pX + 1 <h && map[pX + 1][pY] == '.') { // 평지라면
						map[pX + 1][pY] = map[pX][pY];
						map[pX][pY] = '.'; // 기존의 장소는 평지로 바꿈
						pX = pX + 1;
					}
				} else if (order == 'L') {
					map[pX][pY] = '<';
					dir = 4;
					if (pY - 1 >= 0 && pY - 1 < w && map[pX][pY - 1] == '.') { // 평지라면
						map[pX][pY - 1] = map[pX][pY];
						map[pX][pY] = '.'; // 기존의 장소는 평지로 바꿈
						pY = pY - 1;
					}
				} else if (order == 'S') { // 발포
					boolean shootingEnd = false;
					int shootX = pX + dx[dir];
					int shootY = pY + dy[dir];
					while (!shootingEnd) {
						if (shootX < 0 || shootX >= h || shootY < 0 || shootY >= w) {
							// 범위 확인
							break;
						}
						char tmp = map[shootX][shootY];
						if (tmp == '*') { // 벽돌벽
							shootingEnd = true;
							map[shootX][shootY] = '.';

						} else if (tmp == '#') { // 강철벽
							shootingEnd = true;
						}
						shootX += dx[dir];
						shootY += dy[dir];
					}
				}
			}
			System.out.print("#" + t + " ");
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}

		}

	}

}
