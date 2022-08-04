import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 카드2  
 * 큐를 이용해서 반복한다.
 *
 */

public class Main_2164_이광용  {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Queue<Integer> q = new LinkedList<>();
		int n = Integer.parseInt(br.readLine());
		for(int i = 1; i <= n; i++) {
			q.offer(i);
		}
		
		while(q.size() > 1) {
			q.poll();
			int tmp = q.poll();
			q.offer(tmp);
		}
		int ans = q.poll(); 
		bw.write(String.valueOf(ans));
		bw.newLine();
		
		bw.flush();
		br.close();
		bw.close();
	}
}
