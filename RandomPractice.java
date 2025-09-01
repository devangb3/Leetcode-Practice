import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
    private int getInteger(Character c){
        return c -'1';
    }
    public static void main(String[] args) {
        RandomPractice rp = new RandomPractice();
        char a = '1';
        int num = a-48;
        System.out.println(num);        
    }
}
