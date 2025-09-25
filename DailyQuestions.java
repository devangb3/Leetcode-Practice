import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DailyQuestions {
    class Spreadsheet {
    int[][] grid;
    public Spreadsheet(int rows) {
        grid = new int[rows][26];
    }
    
    public void setCell(String cell, int value) {
        int[] coOrdinates = parseCell(cell);
        int row = coOrdinates[0], col = coOrdinates[1];
        grid[row][col] = value;
    }
    
    public void resetCell(String cell) {
        setCell(cell, 0);
    }
    
    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] check = formula.split("\\+");
        int num1 = 0;
        if(check[0].charAt(0) >= 'A' && check[0].charAt(0) <= 'Z'){
            int[] coOrdinates = parseCell(check[0]);
            num1 = grid[coOrdinates[0]][coOrdinates[1]];
        }
        else num1 = Integer.parseInt(check[0]);
        int num2 = 0;
        if(check[1].charAt(0) >= 'A' && check[1].charAt(0) <= 'Z'){
            int[] coOrdinates = parseCell(check[1]);
            num2 = grid[coOrdinates[0]][coOrdinates[1]];
        }
        else num2 = Integer.parseInt(check[1]);
        return num1 + num2;
    }
    private int[] parseCell(String Cell){
        int col = Cell.charAt(0) - 'A';

        StringBuilder temp = new StringBuilder();
        for (int i = 1; i < Cell.length(); i++) {
            temp.append(Cell.charAt(i));
        }
        int row = Integer.parseInt(temp.toString());

        return new int[]{row, col};
    }
}
    class Router {

    Queue<List<Integer>> queue;
    HashSet<List<Integer>> set;
    HashMap<Integer, List<Integer>> destinationMap;
    int limit;
    public Router(int memoryLimit) {
        queue = new LinkedList<>();
        set = new HashSet<>();
        destinationMap = new HashMap<>();
        limit = memoryLimit;
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        List<Integer> curr = new ArrayList<>(List.of(source, destination, timestamp));
        if(set.contains(curr)) return false;
        while(queue.size() >= limit){
            List<Integer> removed = queue.poll();
            set.remove(removed);
            int removedDestination = removed.get(1);
            int removedTimestamp = removed.get(2);
            List<Integer> destinationTimeStampList = destinationMap.get(removed.get(1));
            if(destinationTimeStampList != null){
                destinationTimeStampList.remove(Integer.valueOf(removedTimestamp));
                if(destinationTimeStampList.isEmpty()) destinationMap.remove(removedDestination);
            }
            
        }
        queue.add(curr);
        set.add(curr);

        destinationMap.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

        return true;
    }
    
    public int[] forwardPacket() {
        if(queue.isEmpty()) return new int[]{};
        List<Integer> temp = queue.poll();
        set.remove(temp);
        List<Integer> destinationTimeStampList = destinationMap.get(temp.get(1));
        if(destinationTimeStampList != null){
            destinationTimeStampList.remove(temp.get(2));
            if(destinationTimeStampList.isEmpty())  destinationMap.remove(temp.get(1));
        }
        
        return temp.stream().mapToInt(a -> a).toArray();
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> destinationTimeStampList = destinationMap.getOrDefault(destination, new ArrayList<>());
        if(destinationTimeStampList.isEmpty()) return 0;
        int startIndex = binarySearch(destinationTimeStampList, startTime);
        int endIndex = binarySearch(destinationTimeStampList, endTime);
        return endIndex - startIndex;
    }
    public int binarySearch(List<Integer> elements, int target){
        int left = 0, right = elements.size()-1;
        while(left <= right){
            int mid = (left + right)/2;
            if(elements.get(mid) < target) left = mid+1;
            else if(elements.get(mid) > target) right = mid;
        }
        return left;
    }     
}    
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        HashMap<Long, Integer> map = new HashMap<>();
        
        StringBuilder ans = new StringBuilder();

        boolean isNumNegative = numerator < 0;
        boolean isDenNegative = denominator < 0;
        
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        

        boolean isNegative = isNumNegative || isDenNegative;
        if(isNumNegative && isDenNegative) isNegative = false;

        ans.append(num/den);

        long remainder = num % den;

        if(remainder != 0){
            ans.append(".");
            while(remainder != 0){
                if(map.containsKey(remainder)){
                    int index = map.get(remainder);
                    ans.insert(index, '(');
                    ans.append(")");
                    break;
                }
                else{
                    map.put(remainder, ans.length());
                    long next = (remainder*10)/den;
                    ans.append(next);
                    remainder = (remainder*10) % den;
                }
            }
        }
        if(isNegative) ans.insert(0, "-");
        return ans.toString();
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[][] matrix = new Integer[triangle.size()][triangle.size()];
        
        return minimumTotalRec(triangle, matrix, 0, 0);
    }
    private int minimumTotalRec(List<List<Integer>> triangle, Integer[][] matrix, int row, int col){
        if(row == triangle.size()-1){
            return triangle.get(row).get(col);
        }
        
        if(matrix[row][col] != null) return matrix[row][col];
        
        int tempSum = triangle.get(row).get(col) +Math.min(
                        minimumTotalRec(triangle, matrix, row+1, col),
                        minimumTotalRec(triangle, matrix, row+1, col+1)
                    );
        
        matrix[row][col] = tempSum;
        return tempSum;
    }
    public static void main(String[] args) {
            DailyQuestions d = new DailyQuestions();
            List<List<Integer>> triangle = List.of(List.of(2), List.of(3,4), List.of(6,5,1), List.of(4,1,8,3));
            System.out.println(d.minimumTotal(triangle));
        }
}
