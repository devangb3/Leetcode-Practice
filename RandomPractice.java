import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class RandomPractice {
    public boolean isValidSudoku(char[][] board) {
        HashMap<Integer, List<Character>> rowDict = new HashMap<>();
        HashMap<Integer, List<Character>> colDict = new HashMap<>();
        HashMap<String, List<Character>> squareDict = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                    if(board[i][j] != '.'){
                        String squareKey = (i / 3) + "," + (j / 3);
                        if(rowDict.getOrDefault(i, new ArrayList<>()).contains(board[i][j]) ||
                        colDict.getOrDefault(j, new ArrayList<>()).contains(board[i][j]) ||
                        squareDict.getOrDefault(squareKey, new ArrayList<>()).contains(board[i][j]))
                            return false;
                        else{
                            rowDict.computeIfAbsent(i,k -> new ArrayList<>()).add(board[i][j]);
                            colDict.computeIfAbsent(j, v-> new ArrayList<>()).add(board[i][j]);
                            squareDict.computeIfAbsent(squareKey,v -> new ArrayList<>()).add(board[i][j]);
                        }
                }
            }
        }
        return true;
    }
    public int numberOfPairs(int[][] points) {
        int count  = 0;
        for (int i = 0; i < points.length; i++) {
            int ax = points[i][0], ay = points[i][1];
            for (int j = 0; j < points.length; j++) {
                if(points[i] == points[j]) continue;
                int bx = points[j][0], by = points[j][1];
                if(!(ax <= bx && ay >= by)) continue;
                boolean skip = false;
                for (int k = 0; k < points.length; k++) {
                    if(points[k] == points[i] || points[k] == points[j]) continue;
                    int cx = points[k][0], cy = points[k][1];
                    if((cx >= ax && cx <= bx) && (cy <= ay && cy >= by)){
                        skip = true;
                        break;
                    }
                }
                if(!skip) count++;
            }
        }
        return count;
    }
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int temp = 1;
        while(candies > 0){
            for (int i = 0; i < ans.length; i++) {
                if(candies >= temp){
                    ans[i] += temp;
                    candies -= temp;
                    temp++;
                }
                else{
                    ans[i] += candies;
                    candies = 0;
                }
            }
        }
        return ans;
    }
    public String smallestNumber(String pattern) {
        Stack<String> stack = new Stack<>();
        String res = "";
        for (int i = 0; i <= pattern.length(); i++) {
           stack.add(Integer.toString(i+1));
           if(i == pattern.length() || pattern.charAt(i) == 'I'){
                while(!stack.isEmpty()){
                    res += stack.pop();
                }
           }
        }
        return res;
    }
    public int findClosest(int x, int y, int z) {
        int diff1 = Math.abs(x-z);
        int diff2 = Math.abs(y-z);
        if(diff1 == diff2) return 0;
        else if(diff1 < diff2) return 1;
        else return 2;
    }
    public static void main(String[] args) {
        RandomPractice rp = new RandomPractice();
        System.out.println(rp.smallestNumber("IIIDIDDD"));        
    }
}
 