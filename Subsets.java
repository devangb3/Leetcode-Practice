
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Subsets {
    public List<String> getSubstrings(String s){
        List<String> result = new ArrayList<>();
        //getSubstringsRec(s, result, 0, ""); //O(2^n * n) - Non Contiguous Substrings & Contiguous Substrings
        for (int i = 0; i < s.length(); i++) {  //O(n^3) - Contiguous Strings only
            for (int j = i; j < s.length(); j++) {
                result.add(s.substring(i, j+1));
            }
        }
        return result;
    }
    private void getSubstringsRec(String s, List<String> res, int index, String tempString){
        if(index == s.length()){
            res.add(tempString);
            return;
        }
        tempString += s.charAt(index);
        getSubstringsRec(s, res, index+1, tempString);
        tempString = tempString.substring(0, tempString.length()-1);
        getSubstringsRec(s, res, index+1, tempString);

    }

    public List<List<Integer>> getSubsets(List<Integer> ogList){
        List<List<Integer>> res = new ArrayList<>();
        getSubsetsRec(res, new ArrayList<>(), ogList, 0); // O(n*2^n)
        return res;
    }
    private void getSubsetsRec(List<List<Integer>> res, List<Integer> tempList, List<Integer> ogList, int index){
        if(index == ogList.size()){
            res.add(new ArrayList<>(tempList));
            return;
        }
        tempList.add(ogList.get(index));
        getSubsetsRec(res, tempList, ogList, index+1);

        tempList.remove(tempList.size()-1);
        getSubsetsRec(res, tempList, ogList, index+1);
    }
    public static void main(String[] args) {
        Subsets s = new Subsets();
        //System.out.println(s.getSubstrings("abc"));
        System.out.println(s.getSubsets(Arrays.asList(1,2,3)));
    }
}
