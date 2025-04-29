import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming {
    public int climbStairs(int n){
        List<Integer> list = new ArrayList<>();

        list.add(0);
        list.add(1);
        for (int i = 2; i <= n+1; i++) {
            list.add(list.get(i-1) + list.get(i-2));
        }

        return list.get(n+1);
        
    }
    public int minCostClimbingStairs(int[] cost){
        int top = cost.length;
        int[] dp = new int[top+1];
        dp[top] = 0;
        int i = top-1;
        while(i >= 0){
            if(i+2 >= top) dp[i] = cost[i];
            else dp[i] = cost[i] + Math.min(dp[i+1], dp[i+2]);
            i--;
        }
        return Math.min(dp[0], dp[1]);
    }
    private int minCostClimbingStairsRec(int[] cost, int index, int sumSoFar){
        if(index + 1 >= cost.length || index + 2 >= cost.length) return sumSoFar;
        return Math.min(minCostClimbingStairsRec(cost, index + 1, sumSoFar+ cost[index+1]),
                        minCostClimbingStairsRec(cost, index+2, sumSoFar + cost[index+2]));
    }
    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(dp.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
        
    }
}
