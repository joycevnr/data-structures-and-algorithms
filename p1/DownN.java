package leda;

import java.util.Scanner;
import java.util.Arrays;

class DownN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(" ");
        int menores = Integer.parseInt(sc.nextLine());//qtd que quero

        int[] v = new int[entrada.length];
        for (int i = 0; i < entrada.length; i++) {
            v[i] = Integer.parseInt(entrada[i]);
        }
        int[] array = new int[menores];
        selectionSort(v);
        for (int i = 0; i < menores; i++) {
            array[i] = v[i];
        }
//        System.out.println(Arrays.toString(array));
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < menores; i++) {
            output.append(array[i]);
            if (i < menores - 1) {
                output.append(" ");
            }
        }
        System.out.println(output.toString());
    }

    public static void selectionSort(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[menor]) {
                    menor = j;
                }
            }
            swap(v, i, menor);

        }
    }

    public static void swap(int[] v, int i, int j) {
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }
}