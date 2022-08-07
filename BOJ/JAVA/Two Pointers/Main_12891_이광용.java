import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * dna 비밀번호
 * 슬라이딩 윈도우
 * 단, 부분문자열이 등장하는 위치가 다르다면 부분문자열이 같다고 하더라도 다른 문자열로  취급
 * map에 뽑은 문자열의 char에 맞는 개수를 입력해두고 완성시 조건과 비교한다.
 * @author multicampus
 *
 */
public class Main_12891_이광용 {
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); //문자열의 길이
		int p = Integer.parseInt(st.nextToken()); //부분문자열의 길이
		
		Map<Character , Integer > map = new HashMap<>(); //부분집합을 기록할 map
		
		char[] arr = br.readLine().toCharArray(); //문자열 받아오기
		
		
		st = new StringTokenizer(br.readLine());//조건 받아오기
		int[] condition = new int[4]; //조건을 받을 배열 : A C G T 순으로 주어짐 
		for(int i = 0; i < 4; i++) {
			condition[i] = Integer.parseInt(st.nextToken());
		}
		
		
		//처음에 문자열 0부터 p까지를 넣고 시작 ?!
		for(int i = 0; i < p; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1 );
		}
		checkCnt(condition, map);
		
		for(int i = p; i < s; i++) {
			//카운트 해야하는지 확인하자			
			map.put(arr[i-p], map.get(arr[i-p])-1); //만약 0이되면 ? 없애즐까? 안없앰
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1 );
			checkCnt(condition, map);
		}
		
		bw.write(cnt + "");
		bw.flush();
		bw.close();
		br.close();
	}
	public static void checkCnt(int[] condition, Map<Character,Integer> map) {
		for(int i = 0; i < 4; i++) {
			if(i == 0) {
				if(map.getOrDefault('A', 0) < condition[i]) {
					return;
				}
			}else if(i == 1) {
				if(map.getOrDefault('C', 0) < condition[i]) {
					return;
				}
			}else if(i== 2) {
				if(map.getOrDefault('G', 0) < condition[i]) {
					return;
				}
			}else if(i==3) {
				if(map.getOrDefault('T', 0) < condition[i]) {
					return;
				}
			}
		}
		cnt++;
	}

}
