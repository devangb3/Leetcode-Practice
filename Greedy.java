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
        int maxReach = nums[0];
        for (int i = 0; i < nums.length; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            if(maxReach >= nums.length-1) return true;
            if(i == maxReach) return false;
        }
        return false;
    }

    public int jump(int[] nums) {
       int numberOfSteps = 0;
       int currentReach = 0;
       int bestJump = 0;
       for (int i = 0; i < nums.length-1; i++) {
            bestJump = Math.max(bestJump, i + nums[i]);
            if(i == currentReach){
                numberOfSteps++;
                currentReach = bestJump;
            }
       }
       return numberOfSteps;
    }

    
    public static void main(String[] args) {
        Greedy g = new Greedy();
        
        System.out.println(g.jump(new int[]{2,3,1,1,4}));
        
    }
}
