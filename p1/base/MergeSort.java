package leda;

import java.util.Arrays;

public class MergeSort {

    // Método principal que inicia a ordenação
    public static void mergeSort(int[] arr, int ini, int fim) {
        if (ini < fim) {
            // 1. DIVISÃO: Calcula o ponto médio
            int meio = (ini + fim) / 2;

            // 2. CONQUISTA: Ordena recursivamente cada metade
            mergeSort(arr, ini, meio);      // Metade esquerda
            mergeSort(arr, meio + 1, fim);  // Metade direita

            // 3. COMBINAÇÃO: Junta as duas metades ordenadas
            merge(arr, ini, fim);
        }
    }

    // Método para combinar duas metades ordenadas
    private static void merge(int[] arr, int ini, int fim) {
        int meio = (ini + fim) / 2;
        int[] auxiliar = new int[arr.length];  // Array temporário

        // Copia o trecho que será ordenado para o array auxiliar
        for(int i = ini; i <= fim; i++) {
            auxiliar[i] = arr[i];
        }
        //no merge sempre tenho a divisão no meio, por isso tem metade da esquerda e metade da direita

        // Ponteiros para navegação
        int ponteiroEsq = ini;        // Início da metade esquerda
        int ponteiroDir = meio + 1;   // Início da metade direita
        int ponteiroArr = ini;        // Posição atual no array original

        // Combina as duas metades ordenadas
        while (ponteiroEsq <= meio && ponteiroDir <= fim) {
            if (auxiliar[ponteiroEsq] <= auxiliar[ponteiroDir]) {
                arr[ponteiroArr++] = auxiliar[ponteiroEsq++];
            } else {
                arr[ponteiroArr++] = auxiliar[ponteiroDir++];
            }
        }

        // Copia elementos restantes da metade esquerda (se houver)
        while (ponteiroEsq <= meio) {
            arr[ponteiroArr++] = auxiliar[ponteiroEsq++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        System.out.println("Array original: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Array ordenado: " + Arrays.toString(arr));
    }
}