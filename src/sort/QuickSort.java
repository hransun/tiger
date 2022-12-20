package sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {9, 2, 6, 4, 3, 5, 7};
        System.out.println(array);
    }
    
    public static void sort(int[] array) {
        quicksort(array,0,array.length-1);
    }

    private static void quicksort(int[] array, int left, int right) {
        if (left >= right) { // left > right  or only one number stop.
            return;
        }
        int pivot = array[(left + right)/2];
        int index = partition(array,left,right,pivot); // return dividing index
        quicksort(array,left,index-1);
        quicksort(array,index,right);

    }

    private static int partition(int[] array, int left, int right, int pivot) {
        while (left <= right) {
            while(array[left] < array[pivot]) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(array,left,right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

}
