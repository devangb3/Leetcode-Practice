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
    public int findDistance(String word1, String word2){
        int rows = word1.length()+1;
        int cols = word2.length()+1;
        int[][] grid = new int[rows][cols];
        for(int i=0; i<cols; i++) grid[rows-1][i] = cols-i-1;
        for(int i=0; i<rows; i++) grid[i][cols-1] = rows-i-1;
        for (int i = rows-2; i >=0; i--) {
            for (int j = cols-2; j >= 0; j--) {
                if(word1.charAt(i) != word2.charAt(j))
                    grid[i][j] = Math.min(Math.min(grid[i+1][j], grid[i][j+1]), grid[i+1][j+1]) + 1;
                else grid[i][j] = grid[i+1][j+1];
            }
        }
        return grid[0][0];
    }
    private int minCostClimbingStairsRec(int[] cost, int index, int sumSoFar){
        if(index + 1 >= cost.length || index + 2 >= cost.length) return sumSoFar;
        return Math.min(minCostClimbingStairsRec(cost, index + 1, sumSoFar+ cost[index+1]),
                        minCostClimbingStairsRec(cost, index+2, sumSoFar + cost[index+2]));
    }
    public int longestCommonSubsequence(String text1, String text2){
        int rows = text1.length() + 1;
        int cols = text2.length() + 1;
        int[][] grid = new int[rows][cols];
        for(int i=0; i< rows; i++) grid[i][0] = 0;
        for(int i=0; i< cols; i++) grid[0][i] = 0;
        String ans = "";
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    ans += text1.charAt(i-1);
                    grid[i][j] = 1 + grid[i-1][j-1];
                }
                else{
                    grid[i][j] = Math.max(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        System.out.println(ans);
        return grid[rows-1][cols-1];
    }
    public int rob(int[] nums){
        int len = nums.length;
        if(len == 1) return nums[0];
        if(len == 2) return Math.max(nums[1],nums[0]);
        nums[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i-1], nums[i]+nums[i-2]);
        }
        return nums[len-1];
    }
    public int rob2(int[] nums){
        int n = nums.length;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(nums[0]);
        list1.add(Math.max(nums[0], nums[1]));

        list2.add(0);
        list2.add(nums[1]);
        list2.add(Math.max(nums[1],nums[2]));

        for(int i=2; i<n-1; i++) list1.add(Math.max(list1.get(i-1), list1.get(i-2) + nums[i]));
        for(int i=3; i<n; i++){
            int num = Math.max(list2.get(i-1), list2.get(i-2) + nums[i]);
            list2.add(num);
        } 
        
        return Math.max(list1.get(n-2), list2.get(n-1));
    }
    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(dp.rob2(new int[]{1,3,1,3,100}));
    }
}
