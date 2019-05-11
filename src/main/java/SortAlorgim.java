import java.util.Arrays;

/**
 * @Author renzhiqiang
 * @Description
 * @Date 2019-04-30
 **/
public class SortAlorgim {

    public static void  bubbleSort(int[] arr) {
        int temp = 0;
        int range = arr.length - 1;

        for(int i =0; i < arr.length; i++) {
            for(int j = 0; j < range; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            range--;
        }

        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i]);
        }
    }

    public static void insertSort(int[] arr) {
        int temp=0;
        int j=0;
        for (int i=1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) {
                temp = arr[i];
                for (j =i-1; j>0&&temp<arr[j]; j--) {
                    arr[j+1] = arr[j];
                }
                arr[j+1] =temp;
            }
        }
    }

    public static void selectSort(int arr[]) {
        int temp=0;
        int minIndex=-1;

        for(int i=0; i<arr.length; i++){
            minIndex = i;

            for (int j=i; j<arr.length-1; j++) {
                if (arr[minIndex] > arr[j+1]){
                    minIndex=j+1;
                }
            }
            temp = arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex] = temp;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args){
        bubbleSort(new int[]{3, 5, 1});
        selectSort(new int[]{3, 5, 1});
    }

}
