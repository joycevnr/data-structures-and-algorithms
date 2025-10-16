package leda;

import java.util.Scanner;
import java.util.Arrays;

class MoveN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(" ");
        int[] v = new int[entrada.length];

        for (int i = 0; i < entrada.length; i++) {
            v[i] = Integer.parseInt(entrada[i]);
        }

        insetionSort(v);
        //System.out.println(Arrays.toString(v));

    }

    //insetionSort
    public static void insetionSort(int[] v) {
        for (int i = 1; i < v.length; i++) {
            int j = i;//de tras para frente
            while(j > 0 && v[j] < v[j-1]){
                swap(v, j, j-1);
                System.out.println(Arrays.toString(v));
                j--;
            }

        }
    }

    public static void swap(int[] v, int i, int j) {
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }
}