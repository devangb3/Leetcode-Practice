import java.util.Arrays;

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
    public static void main(String[] args) {
        MathGeometry mg = new MathGeometry();
        mg.rotate(new int[][]{{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}});
    }
}
