import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BitManipulation {
    public int singleNumber(int[] nums){
        int ans = 0;
        for(int num : nums){
            ans ^= num;
        }
        return ans;
    }
    public int hammingWeight(int n){
        int count = 0;
        while(n > 0){
            n = n & n-1;
            count++;
        }
        return count;
    }
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            int temp = i;
            while(temp > 0){
                temp = temp & temp-1;
                count++;
            }
            ans[i] = count;
        }

        return ans;
    }
    public int reverseBits(int n){
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans = ans << 1;
            ans += (n%2);
            n = n>>1;
        }
        return ans;
    }
    public int missingNumber(int[] nums){
        int res = 0;
        for (int i = 0; i <= nums.length; i++) {
            res ^= i;
        }
        for(int num : nums) res ^= num;
        return res;
    }
    public int getSum(int a, int b){
        while(b!=0){
            int temp = (a&b) <<1;
            a = a^b;
            b = temp;
        }
        return a;
    }
    public int reverse(int x){
        int ans = 0;
        while(x != 0){
            if(ans > Integer.MAX_VALUE/10 || ans < Integer.MIN_VALUE/10) return 0;         
            ans *= 10;
            ans += x%10;
            x/=10;
        }
        HashMap<String, Long[]> map = new HashMap<>();
        
        return ans;
    }
    public static void main(String[] args) {
        BitManipulation bm = new BitManipulation();
        List<Long> nums = Arrays.asList(1L, 2L);
        long check = 2;
        nums.add(check);
        System.out.println(nums.get(1));
    }
}
