import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalsPractice {
    public int[][] insert(int[][] intervals, int[] newInterval){
        List<int[]> newIntervals = new ArrayList<>();
        for(int i=0; i<intervals.length; i++){
            int[] interval = intervals[i];
            if(interval[0] > newInterval[1]){
                newIntervals.add(newInterval);
                while(i<intervals.length){
                    newIntervals.add(intervals[i]);
                    i++;
                }  
                return newIntervals.toArray(new int[newIntervals.size()][]);
            }
            if(interval[1] < newInterval[0]) newIntervals.add(interval);
            else{
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        newIntervals.add(newInterval);
        return newIntervals.toArray(new int[newIntervals.size()][]);
    }
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        int i = 1;
        int[] curr = intervals[0];
        while(i < intervals.length){
            int[] interval = intervals[i];
            if(interval[1] < curr[0] || interval[0] > curr[1]){
                res.add(curr);
                curr = interval;
            }
            else{
                curr[0] = Math.min(interval[0], curr[0]);
                curr[1] = Math.max(interval[1], curr[1]);
            }
            i++;
        }
        res.add(curr);
        return res.toArray(new int[res.size()][]);
    }
    public int eraseOverlapIntervals(int[][] intervals){
        int count = 0;
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        int endValue = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < endValue){
                endValue = Math.min(intervals[i][1], endValue);
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        IntervalsPractice ip = new IntervalsPractice();
        int[][] res = ip.merge(new int[][]{{2,3},{4,5},{6,7},{8,9},{1,10}});
        for(int[] num : res) System.out.println(Arrays.toString(num));
    }
}
