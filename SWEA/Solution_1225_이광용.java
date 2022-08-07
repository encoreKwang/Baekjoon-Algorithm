import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 암호생성기
 * 숫자가 넣어진 순서대로 나와서 뒤로 다시 들어가니까 큐를 이용.
 * 
 * @author multicampus
 *
 */
public class Solution_1225_이광용 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int t = 1; t <= 10; t++) {
			String str = br.readLine();
			int tc = Integer.parseInt(str);
			Queue<Integer> q = new LinkedList<>();
			StringTokenizer st =new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			while(true) {
				int tmp = 1;
				for(int i = 1; i <= 5; i++) { //한 사이클
					tmp = q.poll() - i;
					//System.out.println(i + " "+tmp);
					if(tmp <= 0) {
						tmp = 0;
						q.offer(tmp);
						break;
					}
					q.offer(tmp);
				}
				if(tmp <= 0) break;
			}
			bw.write("#" + tc +" ");
			for(int i = 0 ; i < 8; i++) {
				bw.write(q.poll() +" ");
			}
			bw.flush();
			bw.newLine();
			
		}
		bw.close();
		br.close();
		
	}

}
