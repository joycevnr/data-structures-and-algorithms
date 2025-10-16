package leda;

import java.util.Arrays;
import java.util.Scanner;

class Merge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(" ");
        int[] array = new int[entrada.length];

        // Converte de String para int.
        for (int i = 0; i < entrada.length; i++) {
            array[i] = Integer.parseInt(entrada[i]);
        }
        // só to chamando o q quero, array, inicio e fim
        mergeSort(array, 0, array.length - 1);
    }


    public static void mergeSort(int[] array, int inicio, int fim) {

        if(inicio < fim){
            int meio = (inicio + fim) / 2;
            System.out.println(Arrays.toString(array));
            mergeSort(array, inicio, meio);
            mergeSort(array, meio + 1, fim);
            merge(array, inicio, fim);
        }

    }x

    /**
     * Junta dois sub-arrays ordenados (`inicio..meio` e `meio+1..fim`).
     */
    public static void merge(int[] array, int inicio, int fim) {
        int[] helper = new int[array.length];
        int meio = (inicio + fim) / 2;

        //copia os elementos do array original para o array auxiliar
        for(int i = inicio; i < array.length; i++){
            helper[i] = array[i];
        }
        System.out.println(Arrays.toString(array));

        int i = inicio; //para esquerda na comparação
        int j = meio + 1; // para direita na compaação
        int k = inicio; //para a posição do helper
        //compara os dos dois lados
        while(i <= meio && j <= fim){
            if(helper[i] <= helper[j]){
                array[k++] = helper[i++];
            }else{
                array[k++] = helper[j++];
            }
        }
        while(i <= meio){
            array[k++] = helper[i++];
        }
    }
}