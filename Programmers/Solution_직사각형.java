package programmers;

import java.util.HashMap;
import java.util.Map;

public class Solution_직사각형 {

	public static int[] solution(int[][] v) {
		int[] answer = new int[2];
		Map<Integer, Integer> rowM = new HashMap<>();
		Map<Integer, Integer> colM = new HashMap<>();
		for(int i =0; i < 3; i++) {
			rowM.put(v[i][0], rowM.getOrDefault(v[i][0], 0) + 1);
			colM.put(v[i][1], colM.getOrDefault(v[i][1], 0) + 1);
		}
		for(int x : rowM.keySet()) {
			System.out.print("x : " + x +" "  + "cnt : " + rowM.get(x));
			System.out.println();
		}
		System.out.println();
		for(int x : rowM.keySet()) {
			if(rowM.get(x) == 1) answer[0] = x;
		}
		for(int y : colM.keySet()) {
			if(colM.get(y) == 1) answer[1] = y;
		}
		System.out.println(answer[0] + "  " +  answer[1]) ;
		return answer;
	}
	public static void main(String[] args) {
		int[][] v = {{1,4},{3,4}, {3,10}};
		solution(v);
	}

}
