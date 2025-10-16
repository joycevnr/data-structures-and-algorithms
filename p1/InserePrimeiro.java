package leda;

import java.util.Scanner;
import java.util.Arrays;

class InserePrimeiro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] entrada = sc.nextLine().split(" ");
        int[] numeros = new int[entrada.length];
        for (int i = 0; i < entrada.length; i++) {
            numeros[i] = Integer.parseInt(entrada[i]);
        }
        int j = 0;
        while (j < numeros.length - 1 && numeros[j] > numeros[j + 1]) {
            swap(numeros, j, j + 1);
            j++;
        }
        System.out.println(Arrays.toString(numeros));
    }
    public static void swap (int[] v,int i, int j){
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }
}
