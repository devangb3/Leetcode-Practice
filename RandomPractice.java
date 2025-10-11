import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new int[]{nums[i], i});
        }
        Collections.sort(list,Comparator.comparingInt(a -> a[0]));
        List<List<int[]>> groupList = new ArrayList<>();
        List<int[]> temp = new ArrayList<>();
        temp.add(list.get(0));
        groupList.add(temp);
        int lastValue = list.get(0)[0], index = 1;
        while(index < list.size()){
            if(list.get(index)[0] - lastValue > limit){
                List<int[]> groupElement = new ArrayList<>();
                groupElement.add(list.get(index));
                groupList.add(groupElement);
            }
            else{
                groupList.get(groupList.size()-1).add(list.get(index));
            }
            lastValue = list.get(index)[0];
            index++;
        }
        for(List<int[]> group : groupList){
            List<Integer> valList = new ArrayList<>();
            List<Integer> indexList = new ArrayList<>();
            for(int[] entry : group){
                valList.add(entry[0]);
                indexList.add(entry[1]);
            }
            Collections.sort(valList);
            Collections.sort(indexList);
            for (int i = 0; i < valList.size(); i++) {
                nums[indexList.get(i)] = valList.get(i);
            }
        }
        return nums;
    }
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int x1 = coordinate1.charAt(0) - 'a' + 1;
        int y1 = coordinate1.charAt(1) - 48 + 1;
        int x2 = coordinate2.charAt(0) - 'a';
        int y2 = coordinate2.charAt(1) - 48;
        if((x1 + y1) % 2 == (x2 + y2) %2) return true;
        return false;
    }

    class Router {
        Queue<List<Integer>> queue;
        HashSet<List<Integer>> storedPackets;
        HashMap<Integer, HashSet<List<Integer>>> destinationMap;
        int size;
        public Router(int memoryLimit) {
            queue = new LinkedList<>();
            storedPackets = new HashSet<>();
            destinationMap = new HashMap<>();
            size = memoryLimit;
        }
        
        public boolean addPacket(int source, int destination, int timestamp) {
            List<Integer> temp = List.of(source, destination, timestamp);
            if(storedPackets.contains(temp)) return false;
            if(queue.size() == size){
                List<Integer> tempRemoved = queue.poll();
                storedPackets.remove(tempRemoved);
            }
            queue.add(temp);
            storedPackets.add(temp);
            destinationMap.computeIfAbsent(destination, k -> new HashSet<>()).add(List.of(source, timestamp));

            return true;
        }
        
        public int[] forwardPacket() {
            while(!queue.isEmpty()){
                List<Integer> temp = queue.poll();
                if(storedPackets.contains(temp)){
                    storedPackets.remove(temp);
                    return temp.stream().mapToInt(a -> a).toArray();
                } 
            }
            return new int[]{};
        }
        
        public int getCount(int destination, int startTime, int endTime) {
            int count = 0;
            HashSet<List<Integer> > destinationSet = destinationMap.get(destination);
            for (List<Integer> p : destinationSet){
                List<Integer> temp = List.of(p.get(0), destination, p.get(1));
                if(!storedPackets.contains(temp)) continue;
                if(p.get(1) >= startTime && p.get(1) <= endTime) count++;
            }
            return count;
        }
    }
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        int i = 0;
        while(i<s.length()){
            if(s.charAt(i) == ' '){
                ans.append(sb.reverse());
                ans.append(" ");
                sb = new StringBuilder();
            }
            else{
                sb.append(s.charAt(i));
            }
            i++;
        }
        ans.append(sb.reverse());
        return ans.toString();
    }
    public String oddString(String[] words) {
        List<Integer> diff = new ArrayList<>();
        String a = words[0], b = words[1], c = words[2];
        List<Integer> aList = getDiffArray(a), bList = getDiffArray(b), cList = getDiffArray(c);
        if(isEqual(aList, bList)) diff = aList;
        else{
            if(isEqual(bList, cList)) return a;
            else if(isEqual(aList, cList)) return b;
        }
        for (int i = 2; i < words.length; i++) {
            List<Integer> curr = getDiffArray(words[i]);
            if(!isEqual(curr, diff)) return words[i];
        }
        return "";
    }
    private boolean isEqual(List<Integer> list1, List<Integer> list2){
        for (int i = 0; i < list1.size(); i++) {
            if(list1.get(i) != list2.get(i)) return false;
        }
        return true;
    }
    private List<Integer> getDiffArray(String s){
        List<Integer> diffArray = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            diffArray.add(s.charAt(i) - s.charAt(i-1));
        }
        return diffArray;
    }
    public static void main(String[] args) {
        RandomPractice rp = new RandomPractice();
        System.out.println(rp.reverseWords("Let's take LeetCode contest"));
    }
}
 