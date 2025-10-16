package leda;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSortPasso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(" ");
        int[] array = new int[entrada.length];

        // converte
        for (int i = 0; i < entrada.length; i++) {
            array[i] = Integer.parseInt(entrada[i]);
        }
        System.out.println(Arrays.toString(array));
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * Ordena o array recursivamente usando a estratégia "dividir para conquistar".
     */
    public static void mergeSort(int[] array, int inicio, int fim) {
        // Caso recursivo: só divide se o sub-array tiver mais de 1 elemento.
        if (inicio < fim) {
            int meio = (fim - inicio) / 2;
            // Ordena a metade da esquerda.
            mergeSort(array, inicio, meio);
            // Ordena a metade da direita.
            mergeSort(array, meio + 1, fim);

            // Junta -faz o merge - das duas metades ordenadas.
            merge(array, inicio, fim);

        }
    }


    public static void merge(int[] array, int inicio, int fim) {
        int[] helper = new int[array.length];

        for (int i = inicio; i <= fim; i++) {
            helper[i] = array[i];
        }
        System.out.println(Arrays.toString(helper));
        int meioHelper = (fim + inicio) / 2;


        // ponteiros para as duas metades e para o array original.
        int i = 0;              // i: ponteiro da metade esquerda (no helper)
        int j = meioHelper + 1; // j: ponteiro da metade direita (no helper)
        int k = inicio;         // k: ponteiro de escrita no array original

        // Compara elementos das duas metades e copia o menor para o array original.
        while (i <= meioHelper && j < helper.length) {
            if (helper[i] <= helper[j]) {
                array[k++] = helper[i++];
            } else {
                array[k++] = helper[j++];
            }
        }
        // Copia os elementos restantes da primeira metade, se houver.
        while (i <= meioHelper) {
            array[k++] = helper[i++];
        }
    }
}