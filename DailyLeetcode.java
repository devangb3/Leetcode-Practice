import java.util.HashSet;

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
    public static void main(String[] args) {
        DailyLeetcode dc = new DailyLeetcode();
        int[][] queries = new int[][]{{0,2}};
        System.out.println(dc.isZeroArray(new int[]{1,0,1},queries));
    }
}
