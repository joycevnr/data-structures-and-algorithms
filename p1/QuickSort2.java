package leda;
import java.util.Arrays;

public class QuickSort2 {

    public static void quickSort(int[] arr, int ini, int fim) {
        if (ini < fim) { //verifica se ainda há elementos para ordenar
            int pivotIndex = particiona(arr, ini, fim); // Pivot é sempre o elemento 'ini'
            quickSort(arr, ini, pivotIndex - 1); // Ordena a parte esquerda
            quickSort(arr, pivotIndex + 1, fim);  // Ordena a parte direita
        }
    }

    /*Passo 1: Verificação de Caso Base
    if (ini < fim) - Esta condição verifica se ainda há elementos para ordenar
    Se ini >= fim, significa que o subarray tem 0 ou 1 elemento (já ordenado)

    Passo 2: Particionamento
    particiona(arr, ini, fim) - Divide o array em duas partes:
    Elementos ≤ pivot (esquerda)
    Elementos > pivot (direita)
    Retorna o índice final do pivot

    Passo 3: Chamadas Recursivas
    quickSort(arr, ini, pivotIndex - 1) - Ordena a parte esquerda
    quickSort(arr, pivotIndex + 1, fim) - Ordena a parte direitaParte
*/
    // Particiona o array usando o primeiro elemento como pivot
    private static int particiona(int[] arr, int ini, int fim) {
        int pivot = arr[ini]; // Pivot é o primeiro elemento
        int i = ini + 1; // Ponteiro para elementos menores que o pivot

        for (int j = ini + 1; j <= fim; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j); // Troca com o elemento maior encontrado anteriormente
                i++;
            }
        }

        // Coloca o pivot na posição correta (entre os menores e maiores)
        swap(arr, ini, i - 1);
        return i - 1; // Retorna o índice do pivot
    }

    // Método auxiliar para trocar elementos
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Teste
    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};

        System.out.println("Antes: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Depois: " + Arrays.toString(arr));
    }
}