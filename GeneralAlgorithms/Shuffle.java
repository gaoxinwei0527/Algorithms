package GeneralAlgorithms;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Shuffle {
    Random random = new Random();

    public void shuffle(int[] arr){
        for(int i = arr.length - 1; i >= 0; i--){
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public void shuffle(List<Integer> list){
        Collections.shuffle(list);
//        Collections.shuffle(list, random);
    }

    private void swap(int[] arr, int i , int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args){
        int[] arr = new int[100];
        for(int i = 0; i < 100; i++){
            arr[i] = i;
        }

        Shuffle shuffle = new Shuffle();
        shuffle.shuffle(arr);
        for(int i = 0; i < 100; i++){
            System.out.print(arr[i] + ",");
        }
    }
}
