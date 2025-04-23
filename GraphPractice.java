package leetcodepractice.src.com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;

class Point{
	int[] coordinates;
	int distance;
	public Point(int[] coordinates, int distance){
		this.coordinates = coordinates;
		this.distance = distance;
	}
	public Point(int[] coordinates){
		this.coordinates = coordinates;
	}
}
public class GraphPractice{
	public int minCostConnectPoints(int[][] points){
		int min = 0;
		HashMap<Integer, List<int[]>> map = new HashMap<>();
		int n = points.length;
		for (int i = 0; i < points.length; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				int dist = calculateManhattanDist(points[i],points[j]);
				map.get(i).add(new int[]{dist, j});
				map.get(j).add(new int[]{dist, i});
			}
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[0] - b[0]);
		queue.add(new int[]{0, 0});
		HashSet<Integer> visited = new HashSet<>();
		while(visited.size() != n){
			int[] curr = queue.poll();
			if(!visited.contains(curr[1])){
				visited.add(curr[1]);
				min += curr[0];
				List<int[]> neighList = map.get(curr[1]);
				queue.addAll(neighList);
			}
		}
		return min;
	}
	
	
	private int calculateManhattanDist(int[] a, int[] b){
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
	/* public int swimWater(int[][] grid){
		int time=0;
		
		return time;
	}
	private int swimWaterRec(int[][] grid, int time, int i, int j, int rows, int cols){
		if(i == rows || i < 0 || j == cols || j<0){
			if(i== rows && j == cols) {
				return time;
			}
		}
		return Math.min(
			swimWaterRec(grid, time, i+1, j, rows, cols),
			swimWaterRec(grid, time, i-1, j, rows, cols),
			swimWaterRec(grid, time, i, j+1, rows, cols),
			swimWaterRec(grid, time, i, j-1, rows, cols)
		);
	} */
	
	public static void main(String[] args){
		GraphPractice gp = new GraphPractice();
		int[][] points = new int[][]{{0,0}, {2,2}, {3,10}, {5,2}, {7,0}};
		System.out.println(gp.minCostConnectPoints(points));

 }
}
