import java.util.ArrayList;
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
    public static void main(String[] args) {
        DailyLeetcode dc = new DailyLeetcode();
        String a = "1 2 7 5 2 5";
        String b = "ab a 3 2 7 1"; // ans is 1 2 7
        System.out.println(dc.vennString(a, b));
    }
}
