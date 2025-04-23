package leetcodepractice.src.com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {

    public int knapsackBruteForce(int[] V, int[] W, int capacity){
        return knapsackBruteForceRec(V, W, capacity, 0, 0, 0);
    }
    private int knapsackBruteForceRec(int[] V, int[] W, int capacity, int index, int weightSoFar, int valueSoFar){
        if(weightSoFar > capacity){
            return 0;
        }
        else if(index == V.length){
            return valueSoFar;
        }
        int include = knapsackBruteForceRec(V, W, capacity,  index+1, weightSoFar + W[index], valueSoFar+V[index]);
        int exclude = knapsackBruteForceRec(V, W, capacity,  index+1, weightSoFar, valueSoFar);

        return Math.max(include, exclude);
    }
    public int knapSackDP(int[] V, int[] W, int capacity) {
        int n = V.length;
        int[][] dp = new int[n + 1][capacity + 1];
    
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (W[i - 1] <= j) {
                    int include = V[i - 1] + dp[i - 1][j - W[i - 1]];
                    int exclude = dp[i - 1][j]; 
                    dp[i][j] = Math.max(include, exclude);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    
        int ans = dp[n][capacity];
        List<Integer> path = new ArrayList<>();
        int i = n, j = capacity;
    
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                path.add(i - 1); 
                j -= W[i - 1];
            }
            i--;
        }
    
        System.out.println("Selected items: " + path);
        return ans;
    }
    
    public static void main(String[] args) {
        Knapsack k = new Knapsack();
        int[] V = {60, 100, 120};
        int[] W = {10, 20, 30};
        int capacity = 50;

        System.out.println("Max value: " + k.knapSackDP(V, W, capacity));
    }
}
