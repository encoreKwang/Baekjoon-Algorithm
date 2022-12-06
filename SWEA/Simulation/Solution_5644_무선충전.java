import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * swea 무선충전
 * 그리디
 * 둘 중 선택해야하는 상황에서 나머지 반대편을 먼저 보고 선택해야함
 * 혹은 모든 경우의 수를 따져야함
 * 
 * @author multicampus
 *
 */

public class Solution_5644_이광용 {
	static int[] dx = {0, -1, 0, 1, 0}; //상 우 하 좌
	static int[] dy = {0, 0, 1, 0, -1};
	static int[][] map;
	static Point personA, personB;
	static ArrayList<BC> arrBc;
	static int ans;
	static int sumB;

	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class BC implements Comparable<BC>{
		int  c, p; // c충전범위 , p 처리량
		Point pos;
		public BC(int y, int x, int c, int p) {
			pos = new Point(x, y);
 
			this.c = c;
			this.p = p;
		}
		@Override
		public int compareTo(BC o) {
			// TODO Auto-generated method stub
			return o.p - this.p;
		}
		@Override
		public String toString() {
			return "BC [c=" + c + ", p=" + p + ", pos=" + pos.x + " pos.y" + "]";
		}
		
		

	}
	
//	public static void bfs(BC tmp, int c, Point p) {
//		Queue<Point> q = new LinkedList<>();
//		q.add(tmp.pos);
//		
//		while(!q.isEmpty()) {
//			Point tmpPoint = q.poll();
//
//			for(int i =1; i <= 4; i ++ ) {
//				int nx = tmpPoint.x + dx[i];
//				int ny = tmpPoint.y + dy[i];
//				if(nx < 1 || nx > 10 || ny < 1 || ny > 10) continue;
//				q.offer(new Point(nx, ny));
//				
//			}
//		}
//		
//	}
	
	//해당 위치가 어떤 bc들에 영향을 받는지 리턴하는 메소드?
	public static ArrayList<Integer> getInfluBcArr(Point p) {
		//bcarr를 돌리면서 범위 안에 p가 포함되는지 확인
		ArrayList<Integer> arrBCinflu = new ArrayList<>();
		for(int k = 0; k < arrBc.size(); k++) {

			BC tmp = arrBc.get(k);
			if(Math.abs(p.x - tmp.pos.x) + Math.abs(p.y - tmp.pos.y) <= tmp.c) {
				arrBCinflu.add(k);
			}
			
			
			//위 삼각형
//			boolean flag = false;
//			for(int i = 0; i <= tmp.c; i++) { 
//				if(flag) break;
//				for(int j = tmp.pos.y-i; j <= tmp.pos.y + i; j++) {
//					int tmpX = tmp.pos.x - tmp.c + i;
//					int tmpY = j;
//					//범위 체크
//					if(tmpX < 1 || tmpX > 10 || tmpY < 1 || tmpY > 10) continue;
//					//매개변수로 받은 p의 위치에 해당하는지 체크
//					if(p.x == tmpX && p.y == tmpY) {
//						arrBCinflu.add(k);//그때의 arrbs의 인덱스를 넣는다
//						flag = true;
//						break;
//					}
//				}
//			}
//			//아래 삼각형
//			for(int i = tmp.c; i > 0; i--) {
//				if(flag) break;
//				for (int j = tmp.pos.y - (i-1); j < tmp.pos.y + (i-1); j -=(i-1)) {
//					int tmpX = (tmp.pos.x+1) - (i - tmp.c); //x  - 0 // x + 1 // 
//					int tmpY = j;
//					//범위체크
//					if(tmpX < 1 || tmpX > 10 || tmpY < 1 || tmpY > 10) continue;
//					//매개변수로 받은 p의 위치에 해당하는지 체크
//					if(p.x == tmpX && p.y == tmpY) {
//						arrBCinflu.add(k);//그때의 arrbs의 인덱스를 넣는다
//						flag = true;
//						break;
//					}
//				}
//			}
		}
		return arrBCinflu;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int test = 1; test <= tc; test++) {
			ans = 0;
			map = new int[11][11];

			personA = new Point(1,1);
			personB = new Point(10,10);

			int M = sc.nextInt(); //이동시간
			int A = sc.nextInt(); //BC 개수
			
			int[] arrA = new int[M];//사용자 A의 이동 정보 
			int[] arrB = new int[M];//사용자 B의 이동 정보

			for (int i = 0, k = 0; i < M; i++, k+=2) {
				arrA[i] = sc.nextInt();
			}
			for (int i = 0, k = 0; i < M; i++, k+=2) {
				arrB[i] = sc.nextInt();
			}
			
			arrBc = new ArrayList<>();
			for(int i = 0; i < A; i ++) {
				BC tmpBc= new BC(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
				arrBc.add(tmpBc);
			}
			
			for (int t = 0; t <= M; t++) {

				
				//사용자 위치에 영향을 주는 BC에 대한 인덱스 배열
				ArrayList<Integer> arrBCinfluA = getInfluBcArr(new Point(personA.x, personA.y));
				ArrayList<Integer> arrBCinfluB = getInfluBcArr(new Point(personB.x, personB.y));
								
				HashMap<BC, Integer> mapM = new HashMap<>();//키 값은 Bc의 인덱스 VALUE는 몇번 겹쳤는지 확인 가능 최대 2
				//각 배열을 p기준으로 정렬
				//우선 각 배열의 값을 인덱스로 한 새로운 bc배열이 필요..
				BC[] pickedBcArrA = new BC[arrBCinfluA.size()];
				BC[] pickedBcArrB = new BC[arrBCinfluB.size()];
				
				for(int k = 0; k < arrBCinfluA.size(); k++) {
					//mapM.put(arrBc.get(arrBCinfluA.get(k)), mapM.getOrDefault(arrBc.get(arrBCinfluA.get(k)), 0)+1 );
					pickedBcArrA[k] = arrBc.get(arrBCinfluA.get(k));
				}
				for(int k = 0; k < arrBCinfluB.size(); k++) {
//					mapM.put(arrBc.get(arrBCinfluB.get(k)), mapM.getOrDefault(arrBc.get(arrBCinfluB.get(k)), 0)+1 );
					pickedBcArrB[k] = arrBc.get(arrBCinfluB.get(k));
				}
				
//				Arrays.sort(pickedBcArrA);
//				Arrays.sort(pickedBcArrB);
				
				//정렬할 필요도 없네..
				//모든 경우의 수를 구할꺼야 다만 size가 1이상 일때만 
				//0일땐 따로 구해야함 
				//그리고 따로 sumA sumB 구하지 않을거고 
				//바로 ans에다가 합인 max를 더할거야
				
				
				int max = 0;
				//그 전에 size가 0일때 처리
				if(arrBCinfluA.size() == 0 || arrBCinfluB.size() == 0) {
					int tmpA =0;
					int tmpB =0;
					for(int k = 0; k < arrBCinfluA.size(); k++) { //충전원소가 있다면 그것의 최대값
						tmpA = Math.max(tmpA, pickedBcArrA[k].p);
					}
					for(int k = 0; k < arrBCinfluB.size(); k++) { //충전원소가 있다면 그것의 최대값
						tmpB = Math.max(tmpB, pickedBcArrB[k].p);
					}
					//두 변수중 하나는  0이겟지
					max = tmpA + tmpB;
				}
				
				else {
					for(int i = 0; i < pickedBcArrA.length; i++) {
						for(int j = 0; j <pickedBcArrB.length; j++ ) {
							int tmpsum = pickedBcArrA[i].p + pickedBcArrB[j].p;
							if(pickedBcArrA[i].equals(pickedBcArrB[j])) {
								tmpsum /= 2; //위에서 두번 더했으니까 서로 같은 경우 2로 나눠야함
							}
							max = Math.max(max, tmpsum);
						}
					}
				}
				
				ans += max;
				
//				System.out.println();
//				System.out.print("T = " + t + "\n");
//				System.out.print("A: ");
//				for(BC x: pickedBcArrA) {
//					System.out.print(x + " ");
//				}
//				System.out.println();
//				System.out.print("B: ");
//				for(BC x: pickedBcArrB) {
//					System.out.print(x + " ");
//				}
				
				
				if(t == M) break;
				
				int dirIdxA= arrA[t]; //사용자 A의 이동 방향
				int dirIdxB= arrB[t]; //사용자 B의 이동 방향
				personA.x += dx[dirIdxA];
				personA.y += dy[dirIdxA];
				
				personB.x += dx[dirIdxB];
				personB.y += dy[dirIdxB];
				
				
				
			}
			System.out.println("#" + test + " " + ans);
			
		}

	}

}
