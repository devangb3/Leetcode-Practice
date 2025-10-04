import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
            if (check[0].charAt(0) >= 'A' && check[0].charAt(0) <= 'Z') {
                int[] coOrdinates = parseCell(check[0]);
                num1 = grid[coOrdinates[0]][coOrdinates[1]];
            } else
                num1 = Integer.parseInt(check[0]);
            int num2 = 0;
            if (check[1].charAt(0) >= 'A' && check[1].charAt(0) <= 'Z') {
                int[] coOrdinates = parseCell(check[1]);
                num2 = grid[coOrdinates[0]][coOrdinates[1]];
            } else
                num2 = Integer.parseInt(check[1]);
            return num1 + num2;
        }

        private int[] parseCell(String Cell) {
            int col = Cell.charAt(0) - 'A';

            StringBuilder temp = new StringBuilder();
            for (int i = 1; i < Cell.length(); i++) {
                temp.append(Cell.charAt(i));
            }
            int row = Integer.parseInt(temp.toString());

            return new int[] { row, col };
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
            if (set.contains(curr))
                return false;
            while (queue.size() >= limit) {
                List<Integer> removed = queue.poll();
                set.remove(removed);
                int removedDestination = removed.get(1);
                int removedTimestamp = removed.get(2);
                List<Integer> destinationTimeStampList = destinationMap.get(removed.get(1));
                if (destinationTimeStampList != null) {
                    destinationTimeStampList.remove(Integer.valueOf(removedTimestamp));
                    if (destinationTimeStampList.isEmpty())
                        destinationMap.remove(removedDestination);
                }

            }
            queue.add(curr);
            set.add(curr);

            destinationMap.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

            return true;
        }

        public int[] forwardPacket() {
            if (queue.isEmpty())
                return new int[] {};
            List<Integer> temp = queue.poll();
            set.remove(temp);
            List<Integer> destinationTimeStampList = destinationMap.get(temp.get(1));
            if (destinationTimeStampList != null) {
                destinationTimeStampList.remove(temp.get(2));
                if (destinationTimeStampList.isEmpty())
                    destinationMap.remove(temp.get(1));
            }

            return temp.stream().mapToInt(a -> a).toArray();
        }

        public int getCount(int destination, int startTime, int endTime) {
            List<Integer> destinationTimeStampList = destinationMap.getOrDefault(destination, new ArrayList<>());
            if (destinationTimeStampList.isEmpty())
                return 0;
            int startIndex = binarySearch(destinationTimeStampList, startTime);
            int endIndex = binarySearch(destinationTimeStampList, endTime);
            return endIndex - startIndex;
        }

        public int binarySearch(List<Integer> elements, int target) {
            int left = 0, right = elements.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (elements.get(mid) < target)
                    left = mid + 1;
                else if (elements.get(mid) > target)
                    right = mid;
            }
            return left;
        }
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        HashMap<Long, Integer> map = new HashMap<>();

        StringBuilder ans = new StringBuilder();

        boolean isNumNegative = numerator < 0;
        boolean isDenNegative = denominator < 0;

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        boolean isNegative = isNumNegative || isDenNegative;
        if (isNumNegative && isDenNegative)
            isNegative = false;

        ans.append(num / den);

        long remainder = num % den;

        if (remainder != 0) {
            ans.append(".");
            while (remainder != 0) {
                if (map.containsKey(remainder)) {
                    int index = map.get(remainder);
                    ans.insert(index, '(');
                    ans.append(")");
                    break;
                } else {
                    map.put(remainder, ans.length());
                    long next = (remainder * 10) / den;
                    ans.append(next);
                    remainder = (remainder * 10) % den;
                }
            }
        }
        if (isNegative)
            ans.insert(0, "-");
        return ans.toString();
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[][] matrix = new Integer[triangle.size()][triangle.size()];

        return minimumTotalRec(triangle, matrix, 0, 0);
    }

    private int minimumTotalRec(List<List<Integer>> triangle, Integer[][] matrix, int row, int col) {
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }

        if (matrix[row][col] != null)
            return matrix[row][col];

        int tempSum = triangle.get(row).get(col) + Math.min(
                minimumTotalRec(triangle, matrix, row + 1, col),
                minimumTotalRec(triangle, matrix, row + 1, col + 1));

        matrix[row][col] = tempSum;
        return tempSum;
    }

    public double largestTriangleArea(int[][] points) {
        double maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    maxArea = Math.max(maxArea, calculateTriangleArea(points[i], points[j], points[k]));
                }
            }
        }
        return maxArea;
    }

    private double calculateTriangleArea(int[] pointA, int[] pointB, int[] pointC) {
        int x1 = pointA[0], y1 = pointA[1];
        int x2 = pointB[0], y2 = pointB[1];
        int x3 = pointC[0], y3 = pointC[1];
        double area = 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
        return area;
    }

    public int largestPerimeter(int[] nums) {
        int maxPerimeter = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            int a = nums[i], b = nums[i - 1], c = nums[i - 2];
            if (isTriangle(a, b, c)) {
                return a + b + c;
            }
        }
        return maxPerimeter;
    }

    private boolean isTriangle(int a, int b, int c) {
        if (b + c > a)
            return true;
        return false;
    }

    public int triangularSum(int[] nums) {
        int n = nums.length;
        while(n > 1){
            for (int i = 0; i < n-1; i++) {
                nums[i] = (nums[i] + nums[i+1])% 10;
            }
            n--;
        }
        return nums[0];
    }

    public int maxTurbulenceSize(int[] arr){
        int maxLen = 0;
        boolean shouldBeGreater;
        for (int i = 0; i < arr.length-1; i++) {
            int j = i;
            shouldBeGreater = arr[j] > arr[j+1];
            while(j < arr.length-1){
                if(shouldBeGreater){
                    if(arr[j] <= arr[j+1]) break;
                }
                else{
                    if(arr[j] >= arr[j+1]) break;
                }
                j++;
                shouldBeGreater = !shouldBeGreater;
            }
            maxLen = Math.max(maxLen, j-i+1);
        }
        return maxLen;
    }
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles;
        int empty = numBottles;
        int full = 0;
        while(empty >= numExchange)   {
           full = empty / numExchange;
           sum += full;
           empty = (empty % numExchange) + full;
        }
        return sum;
    }
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int bottlesDrank = 0;
        int fullBottles = numBottles;
        int emptyBottles = 0;
        while(true){
            bottlesDrank += fullBottles;
            emptyBottles += fullBottles;
            fullBottles = 0;
            if(numExchange > emptyBottles) break;
            emptyBottles -= numExchange;
            fullBottles++;
            numExchange++;
        }
        return bottlesDrank;
    }
    class Point{
        int x, y;
        int height;
        public Point(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        int rows = heightMap.length, cols = heightMap[0].length;
        boolean[][] visitedSet = new boolean[rows][cols];
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));
        for (int j = 0; j < cols; j++) {
            Point p = new Point(0, j, heightMap[0][j]);
            queue.add(p);
            visitedSet[0][j] = true;
        }
        for (int i = 1; i < rows; i++) {
            Point p = new Point(i, 0, heightMap[i][0]);
            queue.add(p);
            visitedSet[i][0] = true;
        }
        for (int j = 1; j < cols; j++) {
            Point p = new Point(rows-1, j, heightMap[rows-1][j]);
            queue.add(p);
            visitedSet[rows-1][j] = true;
        }
        for (int i = 1; i < rows; i++) {
            Point p = new Point(i, cols-1, heightMap[i][cols-1]);
            queue.add(p);
            visitedSet[i][cols-1] = true;
        }
        int count = 0;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for(int[] dir : dirs){
                if(p.x + dir[0] >= rows || p.x + dir[0] < 0 || p.y + dir[1] >= cols || p.y + dir[1] < 0) continue;
                Point neighbour = new Point(p.x + dir[0], p.y + dir[1], heightMap[p.x+dir[0]][p.y + dir[1]]);
                if(visitedSet[neighbour.x][neighbour.y]) continue;
                visitedSet[neighbour.x][neighbour.y] = true;
                if(neighbour.height < p.height) count += p.height - neighbour.height;
                neighbour.height = Math.max(neighbour.height, p.height);
                queue.add(neighbour);
            }
        }
        return count;
    }
    public static void main(String[] args) {
        DailyQuestions d = new DailyQuestions();
        System.out.println(d.maxBottlesDrunk(10, 3));
    }
}
