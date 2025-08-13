import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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
     public class Interval {
        public int start, end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
        public int getStart(){
            return this.start;
        }
    }
    public boolean canAttendMeetings(List<Interval> intervals) {
        if(intervals.isEmpty()) return true;
        intervals.sort(Comparator.comparingInt(interval -> interval.start));
        int last = intervals.get(0).start;
        for (int i = 1; i < intervals.size(); i++) {
            if(intervals.get(i).start < last) return false;
            last = intervals.get(i).end;
        }
        return true;
    }
    public int minMeetingRooms(List<Interval> intervals){
        if(intervals.isEmpty()) return 0;
        int maxCount = 0, count = 0;
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int i =0, j=0;
        while(i < start.length && j<end.length){
            if(start[i] < end[j]){
                count++;
                i++;
            }
            else{
                count--;
                j++;
            }
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }
    
    public static void main(String[] args) {
        IntervalsPractice ip = new IntervalsPractice();
        List<Interval> intervals = new ArrayList<>(Arrays.asList(ip.new Interval(0, 40), ip.new Interval(5, 10), ip.new Interval(15, 20), ip.new Interval(45, 50)));
        System.out.println(ip.minMeetingRooms(intervals));
    }
}
