import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 암호문1
 * 해당 인덱스에 입력값을 순서대로 넣어야함
 * 인덱스를 이용해서 삽입 수정할 수 있는 ArrayList를 사용  
 * @author dnflr
 *
 */
public class Solution_1228_이광용 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			ArrayList<Integer> arr = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			int m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			for(int i = 0; i < m; i++) {
				if(st.nextToken().equals("I")) {
					int pos = Integer.parseInt(st.nextToken());
					
					int num = Integer.parseInt(st.nextToken()); //몇개를 삽입할지
										
					while(num-- > 0) {
						int tmp = Integer.parseInt(st.nextToken());
						arr.add(pos++, tmp);
					}
				}
			}
			bw.write("#" + t);
			for(int i = 0; i < 10; i ++) {
				bw.write(" "+ arr.get(i));
			}
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
}
