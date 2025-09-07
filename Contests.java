import java.util.HashMap;

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
    public static void main(String[] args) {
        Contests con = new Contests();
        System.out.println(con.bowlSubarrays2(new int[]{5,1,2,3,4}));
    }
}
