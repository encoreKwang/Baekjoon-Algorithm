import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 요세푸스
 * 원형 링크드 리스트  
 * @author dnflr
 * 
 */
public class Main_1158_이광용 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st  = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		
		LinkedList<Integer> li= new LinkedList<>();
		
		for(int i = 1; i <= n; i++) {
			li.add(i);
		}
		int pos = 0;//위치
		int num = 1;
		
		bw.write("<");
		while(li.size() > 1) {
			if(pos >= li.size()) { //마지막 인덱스+1이 되었다면 맨 첫 인덱스로 옮겨줌
				pos = 0;
			}
			if(num == k) {
				bw.write(li.get(pos) + ", ");
				li.remove(pos);
				num = 1;
				continue;
			}
			pos++;
			num++;
		}
		bw.write(li.get(0)+">");
		bw.flush();
		bw.close();
		br.close();

	}

}
