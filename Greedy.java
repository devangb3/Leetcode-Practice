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

    public int canCompleteCircuit(int[] gas, int[] cost){
        int[] effect = new int[gas.length];
        int total = 0;
        for (int i = 0; i < effect.length; i++) {
            effect[i] = gas[i] - cost[i];
            total += effect[i];
        }
        if(total < 0) return -1;
        
        int newTotal = 0;
        int beginIndex = 0;
        int index = 0;
        while(index < effect.length){
            if(newTotal<0){
                newTotal = 0;
                beginIndex = index;
            }
            newTotal += effect[index];
            index++;
        }
        return beginIndex;
    }
    
    public static void main(String[] args) {
        Greedy g = new Greedy();
        
        System.out.println(g.canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1}));
        
    }
}
