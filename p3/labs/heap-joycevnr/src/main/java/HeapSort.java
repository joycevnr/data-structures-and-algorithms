/**
 * Implementação do algoritmo HeapSort
 * 
 * HeapSort é um algoritmo de ordenação que usa a estrutura de heap
 * para ordenar um array de forma eficiente.
 * 
 * Características:
 * - Complexidade: O(n log n) em todos os casos
 * - In-place: ordena no próprio array
 * - Não estável: pode alterar ordem de elementos iguais
 */
public class HeapSort {
    
    /**
     * Ordena um array usando HeapSort (cria cópia)
     * @param array Array original (não modificado)
     * @return Novo array ordenado
     */
    public static int[] heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array.clone();
        }
        
        int[] resultado = array.clone(); // Não modifica o original
        heapSortInPlace(resultado);
        return resultado;
    }
    
    /**
     * Ordena o array no local (modifica o array original)
     * @param array Array a ser ordenado
     */
    public static void heapSortInPlace(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        
        int n = array.length;
        
        // FASE 1: Construir Max-Heap
        System.out.println("📊 Array inicial: " + java.util.Arrays.toString(array));
        buildMaxHeap(array);
        System.out.println("🏗️  Max-Heap construído: " + java.util.Arrays.toString(array));
        
        // FASE 2: Extrair elementos em ordem decrescente
        System.out.println("\n🔄 Processo de ordenação:");
        for (int i = n - 1; i > 0; i--) {
            // Move o maior elemento (raiz) para a posição final
            System.out.println("  Movendo " + array[0] + " para posição " + i);
            swap(array, 0, i);
            
            // Reduz o tamanho do heap e restaura propriedade
            heapify(array, 0, i); // i = novo tamanho do heap
            System.out.println("  Heap após heapify: " + arrayToString(array, i));
        }
        
        System.out.println("✅ Array ordenado: " + java.util.Arrays.toString(array));
    }
    
    /**
     * Constrói um Max-Heap a partir de um array desordenado
     * @param array Array a ser transformado em heap
     */
    private static void buildMaxHeap(int[] array) {
        int n = array.length;
        
        // Começa no último nó que tem filhos e vai até a raiz
        // parent(n-1) = (n-1-1)/2 = (n-2)/2
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(array, i, n);
        }
    }
    
    /**
     * Restaura a propriedade do heap a partir de um nó
     * @param array Array representando o heap
     * @param index Índice do nó a partir do qual aplicar heapify
     * @param heapSize Tamanho atual do heap
     */
    private static void heapify(int[] array, int index, int heapSize) {
        int left = 2 * index + 1;   // Filho esquerdo
        int right = 2 * index + 2;  // Filho direito
        int largest = index;        // Assume que o pai é o maior
        
        // Verifica se filho esquerdo existe e é maior que o pai
        if (left < heapSize && array[left] > array[largest]) {
            largest = left;
        }
        
        // Verifica se filho direito existe e é maior que o atual maior
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }
        
        // Se o maior não é o pai, troca e continua recursivamente
        if (largest != index) {
            swap(array, index, largest);
            heapify(array, largest, heapSize);
        }
    }
    
    /**
     * Troca dois elementos no array
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    /**
     * Representação do array mostrando parte do heap e parte ordenada
     */
    private static String arrayToString(int[] array, int heapSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (int i = 0; i < array.length; i++) {
            if (i > 0) sb.append(", ");
            
            if (i < heapSize) {
                sb.append(array[i]); // Parte do heap
            } else {
                sb.append("(" + array[i] + ")"); // Parte ordenada
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Método utilitário para calcular índice do pai
     */
    private static int parent(int i) {
        return (i - 1) / 2;
    }
    
    /**
     * Verifica se o array está ordenado (para testes)
     */
    public static boolean isOrdenado(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i-1]) {
                return false;
            }
        }
        return true;
    }
}
