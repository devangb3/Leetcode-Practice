import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) grid[i][j] = grid[i-1][j-1] + 1;
                else grid[i][j] = Math.max(grid[i-1][j], grid[i][j-1]);
            }
        }
        StringBuilder ans = new StringBuilder();
        int i = rows-1, j = cols-1;
        while(i>0 && j>0){
            if(text1.charAt(i-1) == text2.charAt(j-1)){
                ans.append(text1.charAt(i-1));
                i--;
                j--;
            } 
            else if(grid[i-1][j] > grid[i][j-1]) i--;
            else j--;
        }
        System.out.println(ans.reverse());
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
    public String longestPalindrome(String s){
        String curr = "";
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i;
            while(left>= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                if(right-left + 1 > maxLength){
                    curr = s.substring(left, right+1);
                    maxLength = right-left+1;
                }
                left--;
                right++;
            }
            left = i;
            right = i+1;
            while(left>=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                if(right - left + 1 > maxLength){
                    curr = s.substring(left, right+1);
                    maxLength = right-left+1;
                }
                left --;
                right++;
            }
        }
        return curr;
    }
   
    private boolean isPalindrome(String check){
        int left = 0;
        int right = check.length()-1;
        while(right >= left){
            if(check.charAt(right) != check.charAt(left)) return false;
            left++;
            right--;
        }
        return true;
    }
    public boolean isInterleave(String s1, String s2, String s3){
        if(s1.length() + s2.length() != s3.length()) return false;
        int n = s1.length(), m = s2.length();
        boolean[][] dp = new boolean[n + 1][m+1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = (s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0]);
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = (s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1]);
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int k = i+j-1;
                if(s3.charAt(k) == s1.charAt(i-1) && dp[i-1][j] || s3.charAt(k) == s2.charAt(j-1) && dp[i][j-1]) 
                    dp[i][j] = true;
            }
        }
        return dp[n][m];
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 0; i<dp.length; i++){
            dp[n-i][m] = i;
        }
        for(int j=0; j<dp[0].length; j++){
            dp[n][m-j] = j;
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if(word1.charAt(i) == word2.charAt(j)) dp[i][j] = dp[i+1][j+1];
                else dp[i][j] = 1 + Math.min(dp[i+1][j+1], Math.min(dp[i][j+1], dp[i+1][j]));
            }
        }
        return dp[0][0];
    }
    public int countSubstrings(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i;
            while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                count++;
                left--;
                right++;
            }
            left = i;
            right = i+1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                count++;
                left--;
                right++;
            }
        }
        return count;
    }
    public int coinChange(int[] coins, int amount){
        int numCoins = 0;
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE-2;
        }
        for (int i = 1; i < dp.length; i++) {
            for(int coin : coins){
                if(i >= coin) {
                    if(dp[i] > 1 + dp[i-coin]){
                        dp[i] = 1 + dp[i-coin];
                    }
            }
        }
    }
        return dp[amount] == Integer.MAX_VALUE-2 ? -1 : dp[amount];
    }
    public int maxProduct(int[] nums){
        
        int overallMax = nums[0];

        int[] maxArray = new int[nums.length];
        int[] minArray = new int[nums.length];
        minArray[0] = nums[0];
        maxArray[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int currentNum = nums[i];

            int maxCandidate = currentNum * maxArray[i-1];
            int minCandidate = currentNum * minArray[i-1];

            maxArray[i] = Math.max(currentNum, Math.max(maxCandidate, minCandidate));
            minArray[i] = Math.min(currentNum, Math.min(maxCandidate, minCandidate));

            if(overallMax > maxArray[i]) overallMax = maxArray[i];
        }
        
        return overallMax;
    }
    public int uniquePaths(int m, int n){
        int[][] graph = new int[m][n];
        for (int i = 0; i < m; i++) {
            graph[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            graph[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                graph[i][j] = graph[i-1][j] + graph[i][j-1];
            }
        }
        return graph[m-1][n-1];
    }
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[n-1][j] = j >= coins[n-1] ? dp[n-1][j-coins[n-1]] : 0;
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = j - coins[i] >= 0? dp[i][j-coins[i]] + dp[i+1][j] : dp[i+1][j];
            }
        }
        return dp[0][amount];
    }
    public int findTargetSumWays(int[] nums, int target){
        return findTargetSumWaysRec(0, 0, target, nums);
    }
    private int findTargetSumWaysRec(int index, int currSum, int target, int[] nums){
        if(index == nums.length){
            return target == currSum ? 1 : 0;
        }
        return (
            findTargetSumWaysRec(index+1, currSum+nums[index], target, nums) + 
            findTargetSumWaysRec(index+1, currSum-nums[index], target, nums)
        );
    }
    public int longestIncreasingPath(int[][] matrix){
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, longestIncreasingPathDFS(matrix, dp, i, j, n, m, Integer.MIN_VALUE));
            }
        }
        return ans;
    }
    private int longestIncreasingPathDFS(int[][] matrix, int[][] dp, int i, int j, int rows, int cols, int prev){
        if(i < 0 || i >= rows || j < 0 || j >= cols || prev >= matrix[i][j]) return 0;
        if(dp[i][j] != 0) return dp[i][j];
        int res = 1;
        res = Math.max(res, 1 + longestIncreasingPathDFS(matrix, dp, i+1, j, rows, cols, matrix[i][j]));
        res = Math.max(res, 1 + longestIncreasingPathDFS(matrix, dp, i-1, j, rows, cols, matrix[i][j]));
        res = Math.max(res, 1 + longestIncreasingPathDFS(matrix, dp, i, j+1, rows, cols, matrix[i][j]));
        res = Math.max(res, 1 + longestIncreasingPathDFS(matrix, dp, i, j-1, rows, cols, matrix[i][j]));
        dp[i][j]= res;
        return res;
    }
    public int maxCoins(int[] nums){
        List<Integer> numsList = new ArrayList<>();
        numsList.add(1);
        for(int num : nums) numsList.add(num);
        numsList.add(1);

        int[][] dp = new int[numsList.size()][numsList.size()];
        for(int[] row : dp) Arrays.fill(row, -1);

        return maxCoinsRec(numsList, 1, numsList.size()-2, dp);
    }
    private int maxCoinsRec(List<Integer> numsList, int left, int right, int[][] dp){
        if(left > right) return 0;
        if(dp[left][right] != -1) return dp[left][right];
        
        for (int i = left; i <= right; i++) {
            int coins = numsList.get(left-1) * numsList.get(i) * numsList.get(right+1);
            coins += maxCoinsRec(numsList, left, i-1, dp) + maxCoinsRec(numsList,i+1, right, dp);
            dp[left][right] = Math.max(dp[left][right], coins);
        }

        return dp[left][right];
    }
    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(dp.maxCoins(new int[]{3,1,5,8}));
    }
}
