import java.util.Arrays;

/**
 * Classe Heap baseada no material do Prof. João Arthur Brunet,
 * com a adição do algoritmo Heapsort.
 */
public class Heap {

    private int[] heap;
    private int tail; // Aponta para o último elemento do heap.

    /**
     * Construtor para criar um heap a partir de um array existente.
     * Automaticamente constrói o heap (Fase 1 do Heapsort).
     */
    public Heap(int[] array) {
        this.heap = array;
        this.tail = array.length - 1;
        this.buildHeap();
    }

    public boolean isEmpty() {
        return this.tail == -1;
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public int left(int index) {
        return 2 * index + 1;
    }

    public int right(int index) {
        return 2 * (index + 1);
    }
    
    private void swap(int i, int j) {
        int aux = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = aux;
    }

    /**
     * Fase 1 do Heapsort: Transforma o array em um Max-Heap.
     */
    private void buildHeap() {
        // Começa do pai do último elemento e vai até a raiz.
        for (int i = parent(this.tail); i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Rotina heapify padrão, que considera o heap inteiro (até 'tail').
     */
    private void heapify(int index) {
        int l = left(index);
        int r = right(index);
        int largest = index;

        // Verifica se o filho esquerdo é maior que a raiz
        if (l <= tail && heap[l] > heap[largest]) {
            largest = l;
        }

        // Verifica se o filho direito é maior que o maior até agora
        if (r <= tail && heap[r] > heap[largest]) {
            largest = r;
        }

        // Se o maior não for a raiz, troca e continua o heapify para baixo
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    // ===================================================================
    // HEAPSORT - Início da Lógica de Ordenação
    // ===================================================================
    
    /**
     * Fase 2 do Heapsort: Ordena o array que já é um heap.
     * Este método modifica o array interno para que fique ordenado.
     */
    public void sort() {
        // O buildHeap() já foi chamado no construtor.
        // Agora, extraímos o máximo (raiz) repetidamente.
        for (int i = this.tail; i > 0; i--) {
            // 1. Move a raiz atual (maior elemento) para o fim da
            //    parte não ordenada do array.
            swap(0, i);

            // 2. Chama o heapify na heap reduzida. O novo tamanho do heap é 'i'.
            //    A raiz a ser corrigida é sempre o índice 0.
            heapifyForSort(i, 0);
        }
    }

    /**
     * Versão do heapify adaptada para o Heapsort.
     * @param heapSize O tamanho atual do heap a ser considerado.
     * @param index    A raiz da sub-árvore a ser corrigida.
     */
    private void heapifyForSort(int heapSize, int index) {
        int largest = index;
        int l = left(index);
        int r = right(index);

        // A condição de parada agora é 'heapSize', não 'tail'.
        if (l < heapSize && heap[l] > heap[largest]) {
            largest = l;
        }

        if (r < heapSize && heap[r] > heap[largest]) {
            largest = r;
        }

        if (largest != index) {
            swap(index, largest);
            // Continua o heapify para baixo na heap reduzida.
            heapifyForSort(heapSize, largest);
        }
    }

    /**
     * Retorna o array (potencialmente ordenado).
     */
    public int[] getHeapArray() {
        return this.heap;
    }

    // ===================================================================
    // Método Principal para Demonstração
    // ===================================================================

    public static void main(String[] args) {
        int[] arrayDesordenado = {40, 87, 2, 90, 1, 100, 30, 20};
        
        System.out.println("Array Original: " + Arrays.toString(arrayDesordenado));

        // 1. Cria um objeto Heap a partir do array.
        //    O construtor automaticamente chama buildHeap() (Fase 1).
        Heap heapParaOrdenar = new Heap(arrayDesordenado);
        
        System.out.println("Array após buildHeap (Max-Heap): " + Arrays.toString(heapParaOrdenar.getHeapArray()));

        // 2. Chama o método de ordenação (Fase 2).
        heapParaOrdenar.sort();

        System.out.println("Array Final Ordenado: " + Arrays.toString(heapParaOrdenar.getHeapArray()));
    }
}