import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

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
    
    public boolean isNStraightHand(int[] hand, int groupSize){
        if(hand.length % groupSize != 0) return false;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int card : hand){
            if(!map.containsKey(card)) queue.add(card);
            map.put(card, map.getOrDefault(card, 0) + 1);
        }
        int prev = queue.peek();
        
        while(!queue.isEmpty()){
            map.put(prev, map.get(prev)-1);
            if(map.get(prev) == 0) {
                map.remove(prev);
                if(prev !=queue.poll()) return false;
            }
            for (int i = 0; i < groupSize-1; i++) {
                if(map.containsKey(prev+1)){
                    map.put(prev+1, map.get(prev+1)-1);
                    if(map.get(prev+1) == 0){
                        map.remove(prev+1);
                        if(prev+1 != queue.poll()) return false;
                    }  
                    prev++;
                }
                else{
                    return false;
                } 
            }
            if(!queue.isEmpty()) prev = queue.peek();
        }
        return true;
    }
    
    public boolean mergeTriplets(int[][] triplets, int[] target){
        int[] source = new int[]{0,0,0};
        for (int i = 1; i < triplets.length; i++) {
            int[] current = triplets[i];
            boolean canOperate = true;
            for (int j = 0; j < 3; j++) {
             if(source[j] > target[j] || current[j] > target[j]) canOperate = false;  
            }
            if(canOperate){
                source[0] = Math.max(source[0], current[0]);
                source[1] = Math.max(source[1], current[1]);
                source[2] = Math.max(source[2], current[2]);
            }
        }
        return Arrays.compare(source, target) == 0;
    }
    public static void main(String[] args) {
        Greedy g = new Greedy();
        
        System.out.println(g.mergeTriplets(new int[][]{{2,5,3}, {1,8,4}, {1,7,5}}, new int[]{2,7,5}));
        
    }
}
