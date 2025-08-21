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
    public static void main(String[] args) {
        BitManipulation bm = new BitManipulation();
        System.out.println(bm.hammingWeight(13));
    }
}
