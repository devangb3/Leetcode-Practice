import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class DailyLeetcode {
    public String triangleType(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        if(set.size() == 1) return "equilateral";
        else if(set.size() == 2) return "isosceles";
        else{
            if(nums[0] + nums[1] > nums[2] && nums[0] + nums[2] > nums[1] && nums[1] + nums[2] > nums[0]) return "scalene";
            else return "none";
        }
    }
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int[] prefixSum = new int[nums.length+1];
        prefixSum[0] = nums[0];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = nums[i-1] + prefixSum[i-1];
        }
        return true;
    }   
    public long sumOfLargestPrimes(String s){
        
        HashSet<Integer> primes = new HashSet<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> b-a);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String temp = s.substring(i, j);
                int num = Integer.parseInt(temp);
                if(isPrime(num)){
                    if(!primes.contains(num)){
                        primes.add(num);
                        queue.add(num);
                    }
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < 3; i++) {
            if(queue.isEmpty()) break;
            sum += queue.poll();
        }
        return sum;
    }
    private boolean isPrime(Integer num){
        if(num < 2) return false;
        for (int i = 2; i <= num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    public int maxDifference(String s){ //O(N)
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int minEven = Integer.MAX_VALUE;
        int maxOdd = 0;

        for(Character c : map.keySet()){
            int val = map.get(c);
            if(val % 2 == 0){
                minEven = val > minEven ? minEven : val;
            }
            else{
                maxOdd = val > maxOdd ? val : maxOdd;
            }
        }
        
        return maxOdd-minEven;
    }
    public int maxDifferenceHard(String s, int k){
        List<String> store = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {//O(N^3)
            for (int j = i+k; j <= s.length(); j++) { 
                store.add(s.substring(i, j));
            }
        }
        int res = Integer.MIN_VALUE;
        for (String sub : store) { //O(N^2)
            res = Math.max(res, maxDifference(sub));
        }
        return res;
    }
    public int countHillValley(int[] nums){
        int hillsValleysCount = 0;
        int fromLeft = nums[0];
        int fromRight = nums[nums.length-1];
        int i = 1;
        while(i < nums.length-1) {
            int j = i+1;
            int curr = nums[i];
            while(j < nums.length && nums[j] == curr) j++;
            if(j != nums.length){
                fromRight = nums[j];
                if(curr > fromLeft && curr >fromRight) hillsValleysCount++;
                else if(curr < fromLeft && curr < fromRight) hillsValleysCount++;
                fromLeft = curr;
                i = j;
            }
            else break;
        }
        return hillsValleysCount;
    }
    public String vennString(String a, String b){
        String ans = "";
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        String[] wordsA = a.trim().split(" ");
        for(String word : wordsA){
            if(!word.isEmpty()){
                map1.put(word, map1.getOrDefault(word, 0)+1);
            }
        }

        String[] wordsB = b.trim().split(" ");
        for(String word : wordsB){
            if(!word.isEmpty()){
                map2.put(word, map2.getOrDefault(word, 0)+1);
            }
        }
        
        PriorityQueue<String> queue = new PriorityQueue<>();
        for(String s : map1.keySet()){
            if(map2.containsKey(s)){
                int commonCount = Math.min(map1.get(s), map2.get(s));
                for (int i = 0; i < commonCount; i++) {
                    queue.add(s);
                }
            }
        }
        while(!queue.isEmpty()){
            ans += queue.poll();
            if(!queue.isEmpty()) ans += " ";
        }
        return ans;
    }
    public List<List<Integer>> generatePascalTriangle(int numRows){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        ans.add(list1);
        if(numRows == 1) return ans;
        ans.add(Arrays.asList(1,1));
        if(numRows == 2) return ans;
        for (int i = 3; i <= numRows; i++) {
            List<Integer> prevList = ans.get(ans.size()-1);
            List<Integer> newList = new ArrayList<>();
            for (int j = 1; j < i-1; j++) {
                newList.add(prevList.get(j-1) + prevList.get(j));
            }
            newList.addFirst(1);
            newList.addLast(1);
            ans.add(newList);
        }
        return ans;
    }
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(map.containsKey(i+j)){
                    List<Integer> temp = map.get(i+j);
                    temp.add(mat[i][j]);
                    map.put(i+j, temp);
                }
                else map.put(i+j, new ArrayList<>(Arrays.asList(mat[i][j])));
            }
        }
        int[] keyList = map.keySet().stream().mapToInt(x->x).toArray();
        Arrays.sort(keyList);
        for(Integer key : map.keySet()){
            if(key % 2 == 0){
                res.addAll(map.get(key).reversed());
            }
            else{
                res.addAll(map.get(key));
            }
        }
        return res.stream().mapToInt(x->x).toArray();
    }
    public long maxSum(List<Integer> nums, int m, int k){
        long sum = 0;
        int left = 0, right = left+k-1;

        long tempSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = left; i <= right; i++) {
                tempSum += nums.get(i);
                map.put(nums.get(i), map.getOrDefault(nums.get(i), 0)+1);
            }
        while(right< nums.size()){
            if(map.size() >= m){
                sum = Math.max(sum, tempSum);
            }
            tempSum -= nums.get(left);
            int leftCount = map.get(nums.get(left))-1;
            if(leftCount == 0) map.remove(nums.get(left));
            else map.put(nums.get(left), leftCount);
            left++;
            right++;
            if(right>= nums.size()) break;
            tempSum += nums.get(right);
            map.put(nums.get(right), map.getOrDefault(nums.get(right), 0)+1);
        }
        return sum;
    }
    public int areaOfMaxdiagonal(int[][] dimensions){
        int maxArea = 0;
        double maxDiagonal = 0;
        for(int[] dimension : dimensions){
            double diagonal = Math.sqrt(dimension[0] * dimension[0] + dimension[1]*dimension[1]);
            int area = dimension[0]*dimension[1];
            if(diagonal == maxDiagonal) maxArea = Math.max(area, maxArea);
            else if(diagonal > maxDiagonal){
                maxArea = area;
                maxDiagonal = diagonal;
            }
        }
        return maxArea;
    }
    public long maximumProfit(int[] prices, int k){
        long[][][] dp = new long[prices.length][k+1][3];
        for(long[][] record : dp){
            for(long[] row : record) Arrays.fill(row, -1);;
        }
        long profit = 0;
        profit = maximumProfitRec(dp, prices, k, 0, 0);
        return Math.max(0, profit);
    }
    private long maximumProfitRec(long[][][] dp, int[] prices, int remainingTransactions, int currentState, int i){
        if(i == prices.length) return (currentState == 0) ? 0 : Integer.MIN_VALUE;
        if(dp[i][remainingTransactions][currentState] != -1) return dp[i][remainingTransactions][currentState];
        long profit = maximumProfitRec(dp, prices, remainingTransactions, currentState, i+1); //do nothing
        if(currentState == 0){
            if(remainingTransactions > 0){
                profit = Math.max(profit, -prices[i] + maximumProfitRec(dp, prices, remainingTransactions, 1, i+1)); //buy
                profit = Math.max(profit, prices[i] + maximumProfitRec(dp, prices, remainingTransactions, 2, i+1)); //sell
            }
        }
        else if(currentState == 1){
            profit = Math.max(profit, prices[i]+maximumProfitRec(dp, prices, remainingTransactions-1, 0, i+1));
        }
        else{
            profit = Math.max(profit, -prices[i]+maximumProfitRec(dp, prices, remainingTransactions-1, 0, i+1));
        }
        dp[i][remainingTransactions][currentState] = profit;
        return profit;
    }
    public int[][] sortMatrix(int[][] grid){
        int n = grid.length;
        
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i-j;
                if(!map.containsKey(key)){
                    PriorityQueue<Integer> queue = key>=0 ? new PriorityQueue<>((a,b) -> b-a) : new PriorityQueue<>();
                    queue.add(grid[i][j]);
                    map.put(key, queue);
                }
                else{
                    PriorityQueue<Integer> temp = map.get(key);
                    temp.add(grid[i][j]);
                    map.put(key, temp);
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int val = map.get(i-j).poll();
                grid[i][j] = val;
            }
        }
        return grid;
    }
    public int[] sumZero(int n){
        int[] arr = new int[n];
        int number = 1000;
        if(n%2 == 0){
            for (int i = 0; i < arr.length-1; i++) {
                arr[i] = number;
                arr[i+1] = -1 * number;
                i++;
                number--;
            }
        }
        else{
            for (int i = 0; i < arr.length-1; i++) {
                arr[i] = number;
                arr[i+1] = -1 * number;
                i++;
                number--;
            }
            arr[n-1] = 0;
        }
        return arr;
    }
    public int[] getNoZeroIntegers(int n) {
        int[] ans = new int[2];
        for (int i = 1; i < n; i++) {
            if(!containsZero(n-i) && !containsZero(i)) return new int[]{i, n-i};
        }
        return ans;
    }
    private boolean containsZero(int num){
        while(num > 0){
            if(num % 10 == 0) return true;
            num /= 10;
        }
        return false;
    }
    public int minCostToMoveChips(int[] position) {
        int countOdd = 0, countEven = 0;
        for (int i = 0; i < position.length; i++) {
            if(position[i] % 2 == 0) countEven++;
            else countOdd++;
        }
        return Math.min(countOdd, countEven);
    }
    public int repeatedNTimes(int[] nums){
       HashSet<Integer> set = new HashSet<>();
       for(int num : nums){
            if(set.contains(num)) return num;
            set.add(num);
       }
        return 0;
    }
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shoppingOffersRec(new HashMap(), price, special, needs);
    }
    private int shoppingOffersRec(HashMap<List<Integer>, Integer> map, List<Integer> price, List<List<Integer>> special, List<Integer> currentNeed){
        boolean allZeroes = true;
        for(int need : currentNeed){
            if(need != 0){
                allZeroes = false;
                break;
            } 
        }
        if(allZeroes) return 0;
        if(map.containsKey(currentNeed)) return map.get(currentNeed);
        int minCost = 0;
        for (int i = 0; i < currentNeed.size(); i++) {
            minCost += currentNeed.get(i) * price.get(i);
        }
        for(List<Integer> offer : special){
            List<Integer> newNeeds = new ArrayList();
            boolean skipOffer = false;
            for (int i = 0; i < currentNeed.size(); i++) {
                int val = currentNeed.get(i) - offer.get(i);
                if(val < 0) skipOffer = true;
                else newNeeds.add(val);
            }
            if(!skipOffer){
                int potentialMinCost = offer.get(offer.size()-1) + shoppingOffersRec(map, price, special, newNeeds);
                minCost = Math.min(minCost, potentialMinCost);
            }
        }
        map.put(currentNeed, minCost);
        return minCost;
    }
    private static final int MOD = 1_000_000_007;  // 10^9 + 7
    public int peopleAwareOfSecret(int n, int delay, int forget) { 
        HashMap<Integer, Long> knowsButDelayed = new HashMap<>();
        HashMap<Integer, Long> spreading = new HashMap<>();
        knowsButDelayed.put(1,1L);
        for (int i = 1+delay; i <= n; i++) {
            HashMap<Integer, Long> copy = new HashMap<>(knowsButDelayed);
            for(int key : copy.keySet()){
                if(key <= i-delay){
                    spreading.put(key, knowsButDelayed.get(key));
                    knowsButDelayed.remove(key);
                }
            }
            copy = new HashMap<>(spreading);
            for(int key : copy.keySet()){
                if(key + forget == i) {
                    spreading.remove(key);
                }
                else{
                    long newCount = (knowsButDelayed.getOrDefault(i,0L) + spreading.get(key)) % MOD;
                    knowsButDelayed.put(i, newCount);
                }
            }
        }
        long count = 0;
        for(int key : spreading.keySet()) count = (count + spreading.get(key)) % MOD;
        for(int key : knowsButDelayed.keySet()) count = (count + knowsButDelayed.get(key)) % MOD;
        return (int) count;
    }
    public int maxSubarrayLength(int[] nums, int k) {
        int maxLength = 0;
        if(nums.length == 1) return 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        while(right < nums.length){
            int val = map.getOrDefault(nums[right], 0)+1;
            map.put(nums[right], val);
            if(val > k){
                while(map.get(nums[right]) != k){
                    int temp = map.get(nums[left]) - 1;
                    if(temp == 0) map.remove(nums[left]);
                    else map.put(nums[left], temp);
                    left++;
                }
            }
            right++;
            maxLength = Math.max(maxLength, right-left);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        DailyLeetcode dc = new DailyLeetcode();
         System.out.println(dc.maxSubarrayLength(new int[]{1,2,3,1,2,3,1,2}, 2));
        //System.out.println(dc.maxSubarrayLength(new int[]{1}, 1));
        //System.out.println(dc.maxSubarrayLength(new int[]{1,4,4,3}, 1)); 
        //System.out.println(dc.maxSubarrayLength(new int[]{1,1000000000}, 2));
    }
}
