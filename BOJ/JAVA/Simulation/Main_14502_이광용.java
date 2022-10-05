import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * ������
 * 
 */
public class Main_14502_�̱��� {
	static class Point{
		int r , c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	private static ArrayList<Point>  emptyPosList;
	private static int[][] map, newMap;
	private static int N, M, maxArea;
	private static Point[] combiArr;
	private static int[] dr = {0, 1, 0, -1};
	private static int[] dc = {1, 0, -1, 0};
	private static Queue<Point>  q, newQ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //���� ũ��
		M = Integer.parseInt(st.nextToken()); //���� ũ��
		maxArea = Integer.MIN_VALUE;
		map = new int[N][M];
		newMap = new int[N][M];
		emptyPosList = new ArrayList<>();
		combiArr = new Point[3]; 
		q = new LinkedList<>();
		newQ = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M ; j ++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				if(map[i][j]==0) emptyPosList.add(new Point(i, j));
				else if(map[i][j]==2) q.add(new Point(i,j));
			}
		}//end of �Է�
		
		//���� Ž��, ���� 0 ��ĭ�� ���� ������ ��, bfs�� ��������.
		//0 ��ĭ�� �߿� 3���� �̴� ����� �� == ����
		//0�� Point���� ��� �ִ� �迭�� ���� ���� ����� ��
		combi(0, 0);
		System.out.println(maxArea);
	}
	private static void combi(int cnt, int idx) {
		if(cnt == 3) { //�� 3���� ��ġ�� ������ ��������
			copy();//map, queue ����
			bfs();
			int safeCnt = countSafeArea();//maxarea ī��Ʈ
			if(maxArea < safeCnt) maxArea = safeCnt;
			return;
		}
		for(int i = idx; i < emptyPosList.size(); i++) {
			combiArr[cnt] = emptyPosList.get(i);
			combi(cnt+1, i+1);
		}
	}
	private static int countSafeArea() {
		int count= 0;
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M ; j ++) {
				if(newMap[i][j] == 0) count++;
			}
		}
		return count;
	}
	private static void copy() {
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M ; j ++) {
				newMap[i][j] = map[i][j];
			}
		}
		for(Point p : q) {
			newQ.add(p);
		}
		//�� �� ��ġ ����
		for(Point p : combiArr) {
			newMap[p.r][p.c] = 1;
		}
	}
	private static void bfs() {
		while(!newQ.isEmpty()) {
			Point p = newQ.poll();
			for(int i = 0 ; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(isIn(nr,nc) && newMap[nr][nc] == 0) {
					newMap[nr][nc] = 2;
					newQ.add(new Point(nr,nc));
				}
			}
		}//end of while
	}
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

}
