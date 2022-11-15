package SWEA_AD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dfs
//문제유형에 맞게 유연한 코드를 작성 할 수 있다.
//미리 정의된 자료구조보다 재귀호출의 파라미터를 이용해서 문제를 푼다.
//가지치기에 매우 유리 (재귀 호출 과정의 파라미터에 이미 유용한 데이터를
//포함하므로 중간에 갈 필요가 없을 경우에 대한 판단 유리
public class Solution_1486_장훈이의높은선반3 {

	static int T, N, B, min;
	static int[] staff; // 1, 2, 3,

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			staff = new int[N]; // 직원의 키

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				staff[i] = Integer.parseInt(st.nextToken());
			}

			// 풀이
			min = Integer.MAX_VALUE;
			dfs(0,0); //index, sum

			System.out.println("#" + t + " " + min);
		}

	}
	//가지치기
	private static void dfs(int idx, int sum) {
		//현재 호출된 idx에서 누적으로 전달되는 sum을 이용해서 조건에 맞으면 min 갱신
		//complete code check
		if(sum >= B) {
			min = Math.min(min, sum - B);
			return; //더 진행해서 값이 더 커졌봤자 최솟값 갱신에 해당하지 않으므로 
			//가지치기한다.
		}
		//기저조건
		if(idx == N) return;
		
		//이어지는 재귀호출 계속 진행
		//현재 idx의 직원의 키를 선택 or 비선택
		dfs(idx +1, sum+staff[idx]); //현재 idx 선택
		dfs(idx+ 1, sum);//현재 idx를 비선택
	}
}