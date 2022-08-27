/**
 * 방향전환
 * 
 * @author dnflr
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_8382 {
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t<= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int dx = Math.abs(x1 - x2);
			int dy = Math.abs(y1 - y2);
			//대각선으로 먼저 이동하고 나머지를 이동하자 
			//dx dy 중에 작은 값을 구하고 그걸 기준으로 대각선 이동함
			int min = Math.min(dx, dy);
			int max = Math.max(dx, dy);
			//대각선으로 min 기준 한번 이동시 2번 이동함 + 남은 거리 * 2 - (남은 거리가 홀수인 경우) 1
			int oddorNot = (max-min) % 2==0? 0 : 1;
			int result = min * 2 + (max - min) * 2 -oddorNot; 
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}//end of for testcase
		System.out.println(sb.toString());
	}//end of main
}//end of class