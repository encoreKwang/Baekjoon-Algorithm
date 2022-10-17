import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_15961_이광용 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 접시의 수
		int d = Integer.parseInt(st.nextToken());// 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken());// 연속 해서 먹는 접시의 수 
		int c = Integer.parseInt(st.nextToken());// 쿠폰 번호 c
		
		int[] arr = new int[N];
		int[] sel = new int[d+1];
		int ans = 0;
		int cnt = 0;
//		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(i < k) {
				if(sel[arr[i]] == 0) {
					cnt++;
				}
				sel[arr[i]]++;
			}
//			set.add(arr[i]);
		}
		ans = cnt;
		int s = 1;
		for(; s < N; s++) {
			int e = (s + k - 1) % N;
			sel[arr[s-1]]--;
			if(sel[arr[s-1]] == 0) cnt--;
			if(sel[arr[e]]==0) {
				cnt++;
			}
			sel[arr[e]]++;
			if(cnt >= ans) {
				ans = cnt;
				if(sel[c] == 0) ans++;
			}
		}
		System.out.println(ans);
		
	}
}
