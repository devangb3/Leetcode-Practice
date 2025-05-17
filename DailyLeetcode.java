import java.util.ArrayList;
import java.util.List;

public class DailyLeetcode {
    public String pushDominoes(String dominoes){
        String ans = "";
        List<List<Character>> list = new ArrayList<>();
        for (int i = 0; i < dominoes.length(); i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < dominoes.length(); i++) {
            
            if(dominoes.charAt(i) == 'L'){
                list.get(i).add('L');
                int temp = i;
                while(temp >0){
                    if(dominoes.charAt(temp-1) == '.') list.get(i-1).add('L');
                    temp--;
                }
            }
            else if(dominoes.charAt(i) == 'R'){
                list.get(i).add('R');
                int temp = i;
                while(temp < dominoes.length()-1){
                    if(dominoes.charAt(temp+1) == '.') list.get(i-1).add('R');
                }
            }
            else{
                list.get(i).add('.');
            }
        }
        for (int i = 0; i < list.size(); i++) {

        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}
