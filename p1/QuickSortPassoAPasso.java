package leda;

import java.util.Scanner;

class QuickSortPassoAPasso {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lê os números da entrada e converte em um array de inteiros
        String[] entrada = sc.nextLine().split(" ");
        int[] numeros = new int[entrada.length];
        for (int i = 0; i < entrada.length; i++) {
            numeros[i] = Integer.parseInt(entrada[i]);
        }

        // Executa o QuickSort nos números
        quickSort(numeros, 0, numeros.length - 1);

        sc.close(); // Fecha o Scanner
    }

    // principal
    public static void quickSort(int[] arr, int ini, int fim) {
        if (ini < fim) {
            // Particiona o array e obtém o índice do pivot
            int pivotIndex = partition(arr, ini, fim);

            // Imprime o estado atual do array após o particionamento
            printArray(arr);

            // Ordena recursivamente os subarrays à esquerda e à direita do pivot
            quickSort(arr, ini, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, fim);
        }
    }

    // Método de particionamento (escolhendo o primeiro elemento como pivot)
    private static int partition(int[] arr, int ini, int fim) {
        int pivot = arr[ini]; // O primeiro elemento é o pivô
        int i = ini + 1;

        for (int j = ini + 1; j <= fim; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        // Coloca o pivô na posição correta
        swap(arr, ini, i - 1);

        return i - 1; // Retorna o índice final do pivô
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Método para imprimir o array
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}