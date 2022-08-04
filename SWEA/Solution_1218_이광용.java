
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.Stack;

public class Solution_1218_이광용 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//스택 자료구조를 이용해서 여는 괄호는 전부 넣고 닫는 괄호를 만나면 닫는 괄호는 넣지 않고 스택에서 최상단 원소를 꺼내는 pop을 통해 비교함. 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char open [] = {'(', '[', '{', '<'};
		char close [] = {')', ']', '}', '>'};
		
		for(int t = 1; t <= 10; t++) {
			Stack<Character> s = new Stack<>();
			int ans = 1;
			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			for(int i = 0; i < n; i++) {
				char c = str.charAt(i);
				boolean o = false;
				for(int k = 0; k < 4; k++) {
					if(c == open[k]) {
						s.add(c);
						o = true;
						break;
					}
				}
				if (o) continue;
				else { //닫는 괄호
					if(s.empty()) {
						ans = 0;
						break;
					}
					int openIdx = -1;
					char peekChar = s.peek();
					for(int k = 0; k < 4; k++) {
						if(peekChar == open[k]) {
							openIdx = k; //꺼낸 여는 괄호의 타입을 알아냄
							break;
						}
					}
					
					int closeIdx = -1;
					for(int k =0; k < 4; k++) {
						if (c == close[k]) {
							closeIdx = k; //이번에 들어온 닫는 괄호의 타입을 알아냄
							break;
						}
					}
					if(closeIdx != openIdx) {
						ans = 0;
						break;
					}else {
						s.pop();
					}
				}
			}
			// 마지막 스택에 여는 괄호 남아 있는 경우, 
			if(!s.empty()) {
				ans = 0;
			}
			System.out.println("#" + t + " " + ans);
		}
		
	}

}
