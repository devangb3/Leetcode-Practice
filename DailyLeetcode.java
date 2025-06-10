import java.util.HashMap;
import java.util.HashSet;
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
    public int maxDifference(String s){
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
    public static void main(String[] args) {
        DailyLeetcode dc = new DailyLeetcode();
        System.out.println(dc.maxDifference("mmsmsym"));
    }
}
