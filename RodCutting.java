package leetcodepractice.src.com.app;

import java.util.ArrayList;
import java.util.List;

public class RodCutting {
    public int rodCuttingBruteForce(int[] prices, int length){
        if(length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++) {
            int temp = prices[i-1] + rodCuttingBruteForce(prices, length-i);
            max = Math.max(temp, max);
        }

        return max;
    }
    public int rodCuttingDP(int[] prices, int length){
        int[] dp = new int[length+1];
        int[] cuts = new int[length+1];
        dp[0] = 0;
        for (int i = 1; i <= length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                if(dp[i-j] + prices[j-1] > max){
                    max = prices[j-1] + dp[i-j];
                    cuts[i] = j;
                }
            }
            dp[i] = max;
        }
        while(length > 0){
            System.out.println(cuts[length] + " ");
            length -= cuts[length];
        }
        return dp[dp.length -1];
    }
    public static void main(String[] args) {
        RodCutting rc = new RodCutting();
        
        int[] prices = {5,6,8,9,10};
        int rodLength = 5;

        int maxProfit = rc.rodCuttingDP(prices, rodLength);
        System.out.println("Maximum Obtainable Value: " + maxProfit);
    }
}
