import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
    public static void main(String[] args) {
        MathGeometry mg = new MathGeometry();
        mg.setZeroes(new int[][]{{1,1,1}, {1,0,1}, {1,1,1}});
    }
}
