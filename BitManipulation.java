import java.util.Arrays;

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
    public static void main(String[] args) {
        BitManipulation bm = new BitManipulation();
        System.out.println(bm.reverseBits(43261596));
    }
}
