import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class MathGeometry {
    public void rotate(int[][] matrix){
        int left = 0, right = matrix.length-1;
            while(left < right){
                for (int i = 0; i < right-left; i++) {
                    int top = left, bottom = right;
                    int topLeft = matrix[top][left+i];
                    matrix[top][left+i] = matrix[bottom-i][left];

                    matrix[bottom-i][left] = matrix[bottom][right-i];

                    matrix[bottom][right-i] = matrix[top+i][right];
                    matrix[top+i][right] = topLeft;
                }
                left++;
                right--;
            }     

        for(int[] row : matrix) System.out.println(Arrays.toString(row));
    }
    
    public List<Integer> spiralOrder(int[][] matrix){
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = matrix[0].length;
        int top = 0, bottom = matrix.length;
        while(left < right && top < bottom){
            for (int i = left; i < right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i < bottom; i++) {
                ans.add(matrix[i][right-1]);
            }
            right--;
            if (left >= right || top >= bottom) {
                break;
            }
            
            for (int i = right-1; i >= left; i--) {
                ans.add(matrix[bottom-1][i]);
            }
            bottom--;
            for (int i = bottom-1; i >= top; i--) {
                ans.add(matrix[i][left]);
            }
            left++;
        }
        return ans;
    }
    
    public void setZeroes(int[][] matrix){
        int rows = matrix.length, cols = matrix[0].length;
        HashSet<Integer> rowsToBeCleared = new HashSet<>();
        HashSet<Integer> colsToBeCleared = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0){
                    rowsToBeCleared.add(i);
                    colsToBeCleared.add(j);
                }
            }
        }
        for(int row : rowsToBeCleared){
            for (int i = 0; i < cols; i++) {
                matrix[row][i] = 0;
            }
        }
        for(int col : colsToBeCleared){
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = 0;
            }
        }
        for(int[] row : matrix) System.out.println(Arrays.toString(row));
    }
    public boolean isHappy(int n){
        HashSet<Integer> set = new HashSet<>();
        while(!set.contains(n)){
            set.add(n);
            if(n == 1) return true;
            int ogNum = n;
            n = 0;
            while(ogNum > 0){
                int digit = ogNum%10;
                ogNum = ogNum/10;
                n += digit * digit;
            }
        }
        return false;
    }
    public int[] plusOne(int[] digits){
        int length = digits.length;
        for (int i = length-1; i >= 0; i--) {
            if(digits[i] != 9)   {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] ans = new int[length+1];
        ans[0] = 1;
        return ans;
    }
    public double myPow(double x, int n){
        double ans = myPowRec(x, Math.abs(n));
        return n<0 ? 1/ans : ans;
    }
    private double myPowRec(double x, int n){
        if(n==0) return 1;
        if(x==0) return 0;
        double res = myPowRec(x, n/2);
        res = res * res;
        return n%2==0 ? res : res*x;
    }
    public String multiply(String num1 , String num2){
        if(num1.length() > num2.length()){
            int diff = num1.length()-num2.length();
            for (int i = 0; i < diff; i++) {
                num2 = '0' + num2;
            }
        }
        else if(num2.length() > num1.length()){
            int diff = num2.length()-num1.length();
            for (int i = 0; i < diff; i++) {
                num1 = '0' + num1;
            }
        }
        int[] multList = new int[num1.length() + num2.length()];
        for (int i = num1.length()-1; i >= 0; i--) {
            for (int j = num2.length()-1; j >= 0; j--) {
                int number1 = num1.charAt(i) - 48, number2 = num2.charAt(j)-48;
                int mult = number1 * number2;
                int val = mult + multList[i+j+1];
                multList[i+j] = multList[i+j] + val/10;
                multList[i+j+1] = val%10;
            }
        }
        StringBuilder ans = new StringBuilder();
        int index = 0;
        
        while(index<multList.length){
            if(multList[index] != 0)
                ans.append(multList[index]);
            index++;
        }
        return ans.isEmpty() ? "0" : ans.toString();
    }
    class DetectSquares {
    HashMap<List<Integer>, Integer> map;
    public DetectSquares() {
        map = new HashMap<>();
    }
    
    public void add(int[] point) {
        List<Integer> key = Arrays.stream(point).boxed().toList();
        map.put(key, map.getOrDefault(key, 0)+1);
    }
    
    public int count(int[] point) {
        int count =0;
        for(List<Integer> key : map.keySet()){
            
            if(point[0] != key.get(0) && point[1] != key.get(1) && Math.abs(point[0] - key.get(0)) == Math.abs(point[1] - key.get(1))){
                List<Integer> key2 = new ArrayList<>(Arrays.asList(point[0], key.get(1))), key3 = new ArrayList<>(Arrays.asList(key.get(0), point[1]));
                if(map.containsKey(key2) && map.containsKey(key3)){
                    count+= map.get(key3) * map.get(key2) * map.get(key);
                }
            }
        }
        return count;
    }
}

    public static void main(String[] args) {
        MathGeometry mg = new MathGeometry();
        HashMap<List<Integer>, Integer> map = new HashMap<>();
        int[] point = new int[]{1,2};
        
        List<Integer> key = Arrays.stream(point).boxed().toList();
        map.put(key, 1);
        
        
    }
}
