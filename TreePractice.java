import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreePractice {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }
    private void inOrderRec(TreeNode curr, List<Integer> list){
        if(curr == null) return;
        inOrderRec(curr.left, list);
        list.add(curr.val);
        inOrderRec(curr.right, list);
    }
    public List<TreeNode> generateTrees(int n) {    
        return generateTreesRec(1, n);
    }
    private List<TreeNode> generateTreesRec(int start, int end){
        List<TreeNode> ans  = new ArrayList<>();
        if(start > end){
            List<TreeNode> list = new ArrayList<>();
            list.add(null);
            return list;
        } 
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtrees = generateTreesRec(start, i-1);
            List<TreeNode> rightSubtrees = generateTreesRec(i+1, end);
            for(var leftNode : leftSubtrees){
                for(var rightNode : rightSubtrees){
                    TreeNode node = new TreeNode(i, leftNode, rightNode);
                    ans.add(node);
                }
            }
        }
        return ans;
    }
    public int numTrees(int n){
        return numTreesRec(1, n);
    }
    private int numTreesRec(int start, int end){
        if(end > start) return 0;
        int count = 0;
        for (int i = start; i < end; i++) {
            count += numTreesRec(0, i-1);
            count += numTreesRec(i+1, end);
        }
        return count;
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return hasPathSumRec(root, targetSum, root.val);
    }
    private boolean hasPathSumRec(TreeNode curr, int targetSum, int sumSoFar){
        if(sumSoFar == targetSum && curr != null && curr.left == null && curr.right == null) return true;
        if(curr == null) return false;
        int leftSumSoFar =  curr.left == null ? 0 : curr.left.val;
        leftSumSoFar += sumSoFar;
        int rightSumSoFar = curr.right == null ? 0 : curr.right.val;
        rightSumSoFar += sumSoFar;
        return hasPathSumRec(curr.left, targetSum, leftSumSoFar) || hasPathSumRec(curr.right, targetSum, rightSumSoFar);
    }
    public static void main(String[] args) {
        TreePractice tp = new TreePractice();
        TreeNode root = tp.new TreeNode(1);
        //root.left = tp.new TreeNode(2);
        //root.right.left = tp.new TreeNode(3);
        System.out.println(tp.hasPathSum(root, 1));
    }
    
}
