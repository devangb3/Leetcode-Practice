public class Greedy {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int temp = 0;
        for(int num : nums){
            temp += num;
            sum = Math.max(sum, temp);
            if(temp < 0)  temp = 0;
        }
        return sum;
    }
    public boolean canJump(int[] nums) {
        if(nums.length == 1) return true;
        if(nums[0] == 0) return false;

        int maxReach = nums[0];
        for (int i = 0; i < nums.length; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            if(maxReach >= nums.length-1) return true;
            if(i == maxReach) return false;
        }
        return false;
    }
    public static void main(String[] args) {
        Greedy g = new Greedy();
        System.out.println(g.canJump(new int[]{1,2,3}));
    }
}
