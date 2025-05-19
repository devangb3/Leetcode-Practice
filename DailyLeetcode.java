import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
    public static void main(String[] args) {
        
    }
}
