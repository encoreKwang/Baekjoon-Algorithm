import java.util.Scanner;

//_1244_이광용
public class Main_1244_이광용 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();//스위치 개수
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int m = sc.nextInt(); //학생수
		for(int i = 0; i < m; i ++) {
			int gender = sc.nextInt();
			if(gender == 1) { //남자인 경우, 배수 스위치
				int num = sc.nextInt();
				for(int j = 1; num * j - 1< n; j++) {
					if(arr[num * j - 1] == 0) arr[num * j - 1] = 1;
					else arr[num * j - 1] = 0;
				}
			}
			else { //여자인 경우
				int num = sc.nextInt();
				int j = 1;
				if(arr[num-1] == 0) arr[num - 1] =1;
				else arr[num - 1] = 0;
				while (num -1 -j >= 0 && num -1 +j < n  && arr[num - 1 - j] == arr[num - 1 + j] ) {
					if(arr[num - 1 - j] == 0) {
						arr[num - 1 - j] =1;
						arr[num - 1 + j] =1;
					}
					else {
						arr[num - 1 - j] =0;
						arr[num - 1 + j] =0;
					}
					j++;
				}
			}
		}
		for(int i = 1; i<= n; i++) {
			System.out.print(arr[i - 1] + " ");
			if(i % 20 == 0) System.out.println();
		}
		
		
		
	}
}
