
import java.util.ArrayList;
import java.util.List;

public class CoinChange {
    public int coinChange(int[] coins, int amount){
        int min = Integer.MAX_VALUE;
        /* for (int i = 0; i < coins.length; i++) {
            List<Integer> tempList = new ArrayList<>();
            for (int j = i; j < coins.length; j++) {
                tempList.add(j);
                if(sum(tempList) == amount) min = Math.min(tempList.size(),min);
            }
        } */
       coinChangeRec(amount, 0, min, new ArrayList<>(), coins, 0);
        if(min == Integer.MAX_VALUE) return -1;
        return min;
    }
    private int sum(List<Integer> temp){
        int ans = 0;
        for(int num : temp)ans += num;
        return ans;
    }
    private void coinChangeRec(int amount, int amountSoFar, int min, List<Integer> tempList, int[] arr, int index){
        if(amountSoFar > amount) return;
        if(amountSoFar == amount){
            min = Math.min(min, tempList.size());
            return;
        } 
        if(index == arr.length) return;

        tempList.add(arr[index]);
        amountSoFar += arr[index];
        coinChangeRec(amount, amountSoFar, min, tempList, arr, index+1);
        tempList.remove(tempList.size()-1);
        amountSoFar -= arr[index];
        coinChangeRec(amount, amountSoFar, min, tempList, arr, index+1);
    }
    public static void main(String[] args) {
        
    }
}
