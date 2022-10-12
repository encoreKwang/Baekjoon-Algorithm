package SWEA_AD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간 {
	private static int N;
	private static int[][] map;
	private static ArrayList<Point> people;
	private static Point[] stairs;
	private static int peopleSize;
	private static boolean[] subsetArr;
	private static Queue<Point> bfsQ;
	private static int time;
	private static int ANS;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	private static Queue<Integer> downstairQ;
	private static Queue<Integer> waitingQ;
	private static Queue<Integer> waitingQ2;
	private static Queue<Integer> downstairQ2;
	private static Queue<Point> bfsQ2;

	static class Point {
		int x, y, downTime = Integer.MAX_VALUE, stairNum, personNum, dis;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			//			
			people = new ArrayList<>();
			stairs = new Point[2];
			int stairIdx = 0;
			int personIdx = 0;
			for(int i = 0; i < N ;i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						Point p = new Point(i, j);
						p.personNum = personIdx++;
						people.add(p);
						map[i][j] = 0;
					}else if(map[i][j] != 0) {
						stairs[stairIdx++] = new Point(i, j);
					}
				}
			}//end of 입력
			peopleSize = people.size();
			subsetArr = new boolean[peopleSize];
			time = 0;
			ANS = Integer.MAX_VALUE;
			subset(0);
			System.out.println("#" + tc + " " + ANS);
		}
	}

	private static void subset(int cnt) {
		if(cnt == peopleSize) {

			time = 0;
			bfsQ = new LinkedList<Point>();
			bfsQ2 = new LinkedList<Point>();

			for(int i = 0 ; i < peopleSize; i ++ ) {
			if(subsetArr[i]) { //1번 계단
				people.get(i).stairNum = 0;
				people.get(i).dis = distance(people.get(i));
				bfsQ.add(people.get(i));
			}else { //2번 계단
				people.get(i).stairNum = 1;
				people.get(i).dis = distance(people.get(i));
				bfsQ2.add(people.get(i));
				}
			}

			 waitingQ = new LinkedList<>();
			 waitingQ2 = new LinkedList<>();
			 downstairQ = new LinkedList<>();
			 downstairQ2 = new LinkedList<>();
			start(peopleSize);

			ANS = Math.min(ANS, time);
			return;
		}
		subsetArr[cnt] = true;
		subset(cnt+1);
		subsetArr[cnt] = false;
		subset(cnt+1);
	}

	private static void start(int peopleSize) {
		while(true) {
			if(peopleSize == 0) return;
			time++;
			//내려가는 사람들 먼저 진행
			int downsize = downstairQ.size();
			while(downsize-- > 0) {
				int rest = downstairQ.poll();
				rest--;
				if(rest != 0) downstairQ.add(rest);
				else peopleSize--;
			}
			int downsize2 = downstairQ2.size();
			while(downsize2-- > 0) {
				int rest = downstairQ2.poll();
				rest--;
				if(rest != 0) downstairQ2.add(rest);
				else peopleSize--;
			}
			// waiting에 있는지 확인
			int waitsize = waitingQ.size();
			while(waitsize-- > 0 && downstairQ.size() < 3) {
				int rest = waitingQ.poll();
				downstairQ.add(rest);
			}
			int waitsize2 = waitingQ2.size();
			while(waitsize2-- > 0 && downstairQ2.size() < 3) {
				int rest = waitingQ2.poll();
				downstairQ2.add(rest);
			}
			//계단 입구까지의 거리줄이기
			int qsize = bfsQ.size();
			while(qsize -- > 0) {
				Point p = bfsQ.poll();
				Point s = stairs[0];
				p.dis--;
				if(p.dis == 0) waitingQ.add(map[s.x][s.y]);
				else bfsQ.add(p);
			}
			int qsize2 = bfsQ2.size();
			while(qsize2 -- > 0) {
				Point p = bfsQ2.poll();
				Point s = stairs[1];
				p.dis--;
				if(p.dis == 0) waitingQ2.add(map[s.x][s.y]);
				else bfsQ2.add(p);
			}
			
		}
	}

	private static int distance(Point p) {
		return Math.abs(p.x - stairs[p.stairNum].x) + 
				Math.abs(p.y - stairs[p.stairNum].y);
	}
}
