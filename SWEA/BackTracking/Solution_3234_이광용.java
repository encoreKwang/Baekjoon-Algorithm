import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.zip.InflaterInputStream;

/**
 * 순열 * 2가지 경우  중복순열 -> n! * 2^n
 * 추를 올리는 순간!!!에 왼쪽과 오른쪽을 비교해야함 ..
 * @author multicampus
 *
 */
public class Solution_3234_이광용 {
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t<= tc; t++) {
			ans = 0; //정답 변수
			int N = Integer.parseInt(br.readLine());//추의 개수
			int[] arr = new int[N];//추의 무게를 기록할 배열
			boolean[] vis = new boolean[N]; //순열을 구할 때, 방문여부를 기록할 배열 
			int[] permArr = new int[N]; //순열을 구할 때, 방문한 추 배열의 인덱스를 기록할 배열 
			int[] recurArr = new int[N]; //중복순열 구할 때, 인덱스는  해당 경우의 수의 배열(permArr)의 인덱스를 의미하고
			//값은 1은 왼쪽을 2는 오른쪽을 의미한다.
			int leftSum =0;
			int rightSum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N; i ++) {
				 arr[i] = Integer.parseInt(st.nextToken());
			}//입력 완료
			
			//추의 개수로 만들 수 있는 순열 경우의 수 구하기
			perm(0, N, vis, permArr, recurArr, arr, leftSum, rightSum);
			System.out.println("#" + t + " " + ans);
		}
		
	}

	private static void perm(int cnt, int N, boolean[] vis, int[] permArr, int[] recurArr, int[] arr, int leftSum, int rightSum) {
		if(leftSum < rightSum) return;
		if(cnt == N) { //n! = nPn
			//recur(0, N, recurArr, permArr, arr,leftSum , rightSum);
			ans++;
			return;
		}
		else {
			for (int i = 0; i < N; i++) {
				if(vis[i]==false) {
					vis[i]=true;
					//i가 왼쪽인 경우 left에 더하고 , 오른쪽인 경우 두가지
					
					permArr[cnt] = i;
					
					perm(cnt+1, N, vis , permArr, recurArr, arr, leftSum+arr[permArr[cnt]], rightSum);
					perm(cnt+1, N, vis , permArr, recurArr, arr, leftSum, rightSum+arr[permArr[cnt]]);
					vis[i]=false;//원상복귀
				}
			}
		}
	}

//	private static void recur(int cnt, int N, int[] recurArr, int[] permArr,int[] arr) {
//		if(cnt == N) { //모든 추에 대해서 왼쪽 오른쪽을 모두 정했을 때 stop
//			int tmpLeftSum =0;
//			int tmpRightSum =0;
//
//			for(int i = 0; i < N; i++) {
//				if(recurArr[i] == 1 ) { //왼쪽인 경우
//					tmpLeftSum += arr[permArr[i]]; 
//				}
//				if(recurArr[i] == 2 ) { //오른쪽인 경우
//					tmpRightSum += arr[permArr[i]]; 
//				}
//				//올리는 순간 검증해야됨
//				if(tmpLeftSum < tmpRightSum) return;
//				
//			}
//			if(tmpLeftSum >= tmpRightSum) {
//				ans++;
//			}
//			return;
//		}
//		for(int i = 0; i < 2; i ++) { //왼쪽(1) , 오른쪽(2) 두 가지 경우
//				recurArr[cnt] = i + 1;
//				recur(cnt+1, N, recurArr, permArr, arr);
//		}
//		
//	}

}

