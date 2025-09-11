/**
 * Fila de Prioridade implementada usando Heap como estrutura interna.
 * 
 * Características:
 * - Elementos são processados por prioridade (maior valor = maior prioridade)
 * - Operações enqueue/dequeue são O(log n)
 * - Ideal para sistemas que precisam processar por importância
 */
public class PriorityQueue {
    private Heap heap; // Heap como "motor" interno
    
    /**
     * Construtor que cria uma fila de prioridade vazia
     * @param capacidade Capacidade inicial da fila
     */
    public PriorityQueue(int capacidade) {
        this.heap = new Heap(capacidade);
    }
    
    /**
     * Construtor que cria uma fila a partir de array existente
     * @param elementos Array com elementos para inicializar a fila
     */
    public PriorityQueue(int[] elementos) {
        this.heap = new Heap(elementos);
    }
    
    /**
     * Adiciona elemento na fila com base em sua prioridade
     * @param priority O valor da prioridade (maior valor = maior prioridade)
     */
    public void enqueue(int priority) {
        heap.add(priority);
    }
    
    /**
     * Remove e retorna o elemento de maior prioridade
     * @return O elemento de maior prioridade
     * @throws RuntimeException se a fila estiver vazia
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Fila de prioridade vazia!");
        }
        return heap.remove();
    }
    
    /**
     * Visualiza o próximo elemento sem removê-lo
     * @return O elemento de maior prioridade
     * @throws RuntimeException se a fila estiver vazia
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Fila de prioridade vazia!");
        }
        return heap.peek(); // Usa o novo método peek() do Heap
    }
    
    /**
     * Verifica se a fila está vazia
     * @return true se vazia, false caso contrário
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    /**
     * Retorna o número de elementos na fila
     * @return Tamanho da fila
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Representação textual da fila
     * @return String representando a fila
     */
    @Override
    public String toString() {
        return "PriorityQueue{" + 
               "size=" + size() + 
               ", next=" + (isEmpty() ? "none" : peek()) + 
               ", heap=" + heap + "}";
    }
}
