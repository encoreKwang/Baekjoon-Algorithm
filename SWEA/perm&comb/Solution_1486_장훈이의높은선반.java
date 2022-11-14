package SWEA_AD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분집합
// 각 점원이 탑을 이룬다고 할 때, 구성되는 점원을 부분집합의 경우의 수로 생각
public class Solution_1486_장훈이의높은선반 {

static int T, N, B, min;
// 부분집합은 배열 + select
static int[] staff;
static boolean[] select;

public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    
    for (int t = 1; t <= T; t++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        staff = new int[N]; // 직원의 키
        select = new boolean[N]; // 각 직원의 선택 여부 
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            staff[i] = Integer.parseInt(st.nextToken());
        }
        
        // 풀이
        min = Integer.MAX_VALUE;
        subset(0);
        
        System.out.println("#" + t + " " + min);
    }

}

static void subset(int srcIdx) {
    // 재귀호출 기저조건
    // 끝까지 다 왔을 때
    if( srcIdx == N ) {
        // 부분집합의 경우의 수가 완성된 상태
        // complete code
        
        // 현재 선택된 직원의 키의 합을 구해서 B 크거나 같은지 확인 => min 갱신
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if( select[i] ) sum += staff[i];
        }
        
        if( sum >= B ) min = Math.min(min, sum - B); // 차이가 최소인 값으로 선택
        
        return;
        
    }
    
    // 부분집합의 경우의 수를 완성하기 위한 재귀호출 계속
    // 현재 srcIdx 선택하거나, 비선택하는 2가지 경우를 계속 만들어 간다.
    select[srcIdx] = true;
    subset( srcIdx + 1 ); // 현재 idx 를 선택하고 다음으로 이동
    select[srcIdx] = false;
    subset( srcIdx + 1 ); // 현재 idx 를 선택하지 않고 다음으로 이동
}
}