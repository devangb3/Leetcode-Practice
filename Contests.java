import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Contests {
    public int minOperations(int[] nums) {
        int lastNum = nums[0];
        for(int num : nums){
            if(num != lastNum) return 1;
            else lastNum = num;
        }
        return 0;
    }
    public int minOperationsString(String s) {
        int count = 1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 122; i > 97; i--) {
            Character c = (char)i;
            map.put(c,count);
            count++;
        }
        map.put('a', 0);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = map.get(s.charAt(i));
            max = Math.max(max, val);
        }
        return max;
    }
    public long bowlSubarrays(int[] nums){
        long count = 0;
        int left = 0;
        while(left < nums.length-2){
            int max = Integer.MIN_VALUE;
            int right = left + 1;
            while(right < left+2){
                max = Math.max(max, nums[right]);
                right++;
            }
            
            while(right < nums.length){
                if(Math.min(nums[left], nums[right]) > max) count++;
                max = Math.max(max, nums[right]);
                right++;
            }
            left++;
        }
        return count;
    }
    public long bowlSubarrays2(int[] nums){
        int n = nums.length;
        long count = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n-1] = -1;
        int leftVal = nums[0];
        for (int i = 1; i < left.length; i++) {
            if(nums[i] < leftVal)   left[i] = leftVal;
            else left[i] = -1;
            leftVal = Math.max(leftVal, nums[i]);
        }
        int rightVal = nums[n-1];
        for (int i = right.length-2; i>= 0; i--) {
            if(nums[i] < rightVal)  right[i] = rightVal;
            else right[i] = -1;
            rightVal = Math.max(nums[i], rightVal);
        }
        for (int i = 0; i < nums.length; i++) {
            if(left[i] != -1 && right[i] != -1) count++;
        }
        return count;
    }
    public int smallestAbsent(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        for(int num : nums){
            set.add(num);
            sum += num;
        }
        int avg = sum/nums.length;
        int ans = avg > 0 ? avg + 1 : 1;
        while(true){
            if(!set.contains(ans))return ans;
            else ans++;
        }
    }
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int discarded = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> removed = new HashSet<>();
        int right = 0;
        int left = right - w;
        while(right < arrivals.length){
            int rightVal = arrivals[right];
            map.put(rightVal, map.getOrDefault(rightVal, 0)+1);
            if(left>= 0 && !removed.contains(left)){
                int leftVal = arrivals[left];
                map.put(leftVal, map.get(leftVal)-1);
                if(map.get(leftVal) == 0) map.remove(leftVal);
            }
            if(map.getOrDefault(rightVal,0) > m){
                map.put(rightVal, map.get(rightVal)-1);
                removed.add(right);
                discarded++;
            }
            right++;
            left = right -w;
        }
        return discarded;
    }
    public int[][] generateSchedule(int n) {
        int[][] schedule = new int[n*(n-1)][2];
        if(n < 5) return schedule;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        for (int i = 0; i < n; i++) {
            int a = queue.poll(), b = queue.poll();
            schedule[i] = new int[]{a,b};
            queue.add(a);
            queue.add(b);
        }
        
        for (int i = n; i < 2*n; i++) {
            int a = queue.poll(), b = queue.poll();
            schedule[i] = new int[]{b,a};
            queue.add(a);
            queue.add(b);
        }
        return schedule;
    }
    public int evenNumberBitwiseORs(int[] nums) {
        int ans = 0;
        for(int num : nums){
            if(num % 2==0){
                ans = ans | num;
            }
        }
        return ans;
    }
    public long maxTotalValue(int[] nums, int k) {
        long sum = 0;
        long min = (long) Math.pow(10,9);
        long max = 0;
        for(int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        long diff = max - min;
        sum = diff * k;
        return sum;
    }
    public long maxTotalValue2(int[] nums, int k) {
        long sum = 0;

        return sum;
    }
    public void maxTotalValueRec(int[] nums, int k, long sum) {
        if(k == 0) return; 
        
        return;
    }
    
    public static void main(String[] args) {
        Contests con = new Contests();
        System.out.println(con.evenNumberBitwiseORs(new int[]{1,2,3,4,5,6}));
    }
}
