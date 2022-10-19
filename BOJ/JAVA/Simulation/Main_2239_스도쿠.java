import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 스도쿠
 */
public class Main_2239_스도쿠 {
	static int n = 9, cnt;
	private static int[][] a;
	private static boolean[][] c;
	private static boolean[][] c2;
	private static boolean[][] c3;
	private static boolean flag = false;
	
	static int square(int x, int y) {
		return (x/3)*3 + (y / 3);
	}
	static boolean go(int z) {
		if(z == 81) {
			if(flag == false) {
				for (int i = 0; i < n; i++) {
					for(int j = 0; j < n; j ++) {
						System.out.print(a[i][j]);
					}
					System.out.println();
				}
				flag = true;
			}
			return true;
		}
		int x = z/n;
		int y = z%n;
		if(a[x][y] != 0) {
			return go(z+1);
		}else {
			for(int i =1; i <= 9; i++) {
				if (c[x][i] == false && c2[y][i] == false && c3[square(x, y)][i] == false) {
					c[x][i] = c2[y][i] = c3[square(x, y)][i] = true;
					a[x][y] = i;
					if(go(z+1)) {
						return true;
					}
					a[x][y] = 0;
					c[x][i] = c2[y][i] = c3[square(x, y)][i] = false;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a = new int[10][10];
		c = new boolean[10][10];
		c2 = new boolean[10][10];
		c3 = new boolean[10][10];
		cnt = 0;
		
	    for (int i=0; i<n; i++) {
	    	char[] cArr = br.readLine().toCharArray();
	        for (int j=0; j<n; j++) {
	        	a[i][j] = cArr[j] - '0';
	        	if(a[i][j] != 0) {
	        		c[i][a[i][j]] = true;
	        		c2[j][a[i][j]] = true;
	        		c3[square(i, j)][a[i][j]] = true;
	        	}
	        }
	    }
	    go(0);
	}
}
