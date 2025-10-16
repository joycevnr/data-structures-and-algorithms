package leda;

public class QuickSort {

    // Método principal para ordenação
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // Particiona o array e obtém o índice do pivot
            int pivotIndex = partition(arr, left, right);

            // Ordena recursivamente os subarrays à esquerda e direita do pivot
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    // Particionamento de Lomuto com mediana de três
    private static int partition(int[] arr, int left, int right) {
        // Escolhe o pivot usando a mediana de três
        int pivotIndex = pickMedianOfThreePivot(arr, left, right);
        int pivotValue = arr[pivotIndex];

        // Move o pivot para a posição inicial (necessário para o algoritmo de Lomuto)
        swap(arr, left, pivotIndex);

        int i = left;

        // Percorre o subarray e organiza os elementos menores que o pivot
        for (int j = left + 1; j <= right; j++) {
            if (arr[j] <= pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }

        // Coloca o pivot na posição correta
        swap(arr, left, i);

        return i; // Retorna o índice do pivot
    }

    // Escolhe o pivot como a mediana entre primeiro, meio e último elementos
    private static int pickMedianOfThreePivot(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;

        // Ordena os três valores implicitamente
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }

        return mid; // O pivot é o elemento do meio (mediana)
    }

    // Método auxiliar para trocar elementos
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Método para teste
    public static void main(String[] args) {
        int[] arr = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};

        System.out.println("Array antes da ordenação:");
        System.out.println(Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Array após a ordenação:");
        System.out.println(Arrays.toString(arr));
    }
}