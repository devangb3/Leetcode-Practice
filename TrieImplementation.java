import java.util.ArrayList;
import java.util.List;

public class TrieImplementation{
    
    class TrieNode{
        TrieNode[] children;
        boolean isEndOfWord;
        int frequency = 0;
        public TrieNode(){
            children = new TrieNode[26];
            isEndOfWord = false;
            frequency = 0;
        }
    }
    class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void addWord(String word){
            TrieNode curr = root;
            for(int i=0; i<word.length(); i++){
                Character c = word.charAt(i);
                int index = c - 'a';
                if(curr.children[index] == null){
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.isEndOfWord = true;
            curr.frequency += 1;
        }
        public boolean search(String word){
            TrieNode curr = root;
            for(Character ch : word.toCharArray()){
                int index = ch - 'a';
                if(curr.children[index] == null) return false;
                curr = curr.children[index];
            }
            return curr.isEndOfWord;
        }
        public List<String> getWords(String prefix){
            List<String> words = new ArrayList<>();
            TrieNode curr = root;
            for(Character ch : prefix.toCharArray()){
                int index = ch - 'a';
                if(curr.children[index] == null) return words;
                curr = curr.children[index];
            }
            collectWords(words, prefix, curr);
            return words;
        }
        private void collectWords(List<String> words, String currentWord, TrieNode node){
            if(node.isEndOfWord) words.add(currentWord);
            for (int i = 0; i < 26; i++) {
                TrieNode childNode = node.children[i];
                if(childNode != null){
                    char childCharacter = (char) ('a' + i);
                    collectWords(words, currentWord + childCharacter, childNode);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        TrieImplementation ti = new TrieImplementation();
        Trie trie = ti.new Trie();
        trie.addWord("abc");
        trie.addWord("abd");
        System.out.println(trie.getWords("ab"));
    }
}