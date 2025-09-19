import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MockInterviewPractice {
    class RandomizedSet{
        HashMap<Integer, HashSet<Integer>> map;
        List<Integer> list;

        public RandomizedSet(){
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public void insert(int value){
            
            list.add(value);
            HashSet<Integer> set;
            if(!map.containsKey(value)) set = new HashSet<>();
            else set = map.get(value);
            set.add(list.size()-1);
            map.put(value, set);
        }
        public void remove(int value){
            int index = map.get(value).iterator().next();
            int lastElementIndex = list.size()-1;
            int lastElement = list.get(lastElementIndex);
            list.set(index, lastElement);
            list.removeLast();

            HashSet<Integer> removedValueSet = map.get(value);
            removedValueSet.remove(index);

            if(removedValueSet.isEmpty()) map.remove(value);
            else map.put(value, removedValueSet);
            
            HashSet<Integer> set = map.get(lastElement);
            set.add(index);
            set.remove(lastElementIndex);
            map.put(lastElement, set);
        }
        public int getRandom(){
            if(list.isEmpty()) return -1;
            Random rand = new Random();
            int chosenIndex = rand.nextInt(list.size());
            return list.get(chosenIndex);
        }
    }
    public static void main(String[] args) {
        MockInterviewPractice mp = new MockInterviewPractice();
        RandomizedSet h =  mp. new RandomizedSet();
        h.insert(1);
        h.insert(3);
        h.insert(3);
        
        h.remove(3);
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            int r = h.getRandom();
            countMap.put(r, countMap.getOrDefault(r, 0) + 1);
        }
        for(var key : countMap.keySet()) System.out.println(key + ":" + countMap.get(key) );
    }
}
