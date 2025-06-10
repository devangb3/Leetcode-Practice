import java.util.Arrays;

public class MergeSort{
    public int[] mergeSort(int[] arr){
        int[] temp = new int[arr.length];
        mergeSortRec(arr, temp, 0, arr.length-1);
        return arr;
    }
    private void mergeSortRec(int[] arr, int[] temp, int start, int end){
        if(start >= end) return;
        int mid = (start + end)/2;

        mergeSortRec(arr, temp, start, mid);
        mergeSortRec(arr, temp, mid+1, end);

        merge(arr, temp, start, mid, end);
    }
    private void merge(int[] arr, int[] temp, int start, int mid, int end){
        int left = start, right = mid+1, index = start;

        while(left <= mid && right <= end){
            if(arr[left] <= arr[right]){
                temp[index] = arr[left];
                left++;
            }
            else{
                temp[index] = arr[right];
                right++;
            }
            index++;
        }
        while(left <= mid){
            temp[index] = arr[left];
            index++;
            left++;
        }
        while(right <= end){
            temp[index] = arr[right];
            index++;
            right++;
        }

        for (int i = start; i <= end; i++) {
            arr[i] = temp[i];
        }
    }
    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = new int[]{3,1,7,2,8,45};
        System.out.println(Arrays.toString(ms.mergeSort(arr)));
    }
}