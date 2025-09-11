import java.util.Arrays;

public class Heap {
    // Um Heap é uma estrutura de dados especial baseada em uma árvore binária
    // completa que satisfaz a propriedade do heap. No seu caso, é um Max-Heap,
    // onde:

    // Propriedade principal: Todo nó pai tem valor maior ou igual aos seus filhos
    // Estrutura: Árvore binária completa (preenchida da esquerda para a direita)
    // Representação: Implementado usando um array

    private int[] heap;
    private int tail;

    public Heap(int capacidade) {
        this.heap = new int[capacidade];
        this.tail = -1;
        // onde está o último elemento válido no array, para
        // saber quantos elementos temos e onde inserir o próximo
    }

    public Heap(int[] heap) {
        this.heap = heap;
        this.tail = this.heap.length - 1;
        this.buildHeap();
    }

    public boolean isEmpty() {
        return this.tail == -1;
    }

    /**
     * Retorna o índice do filho à esquerda de um nó.
     * 
     * @param i O índice do nó pai.
     * @return O índice do filho à esquerda.
     */
    public int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Retorna o índice do filho à direita de um nó.
     * 
     * @param i O índice do nó pai.
     * @return O índice do filho à direita.
     */
    public int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Retorna o índice do nó pai.
     * 
     * @param i O índice do nó filho.
     * @return O índice do nó pai.
     */
    public int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Adiciona um novo elemento ao Heap, mantendo suas propriedades.
     * O elemento é adicionado ao final e "flutua" para a posição correta
     * (bubble-up).
     * 
     * @param n O inteiro a ser adicionado.
     */
    public void add(int n) { //Complexidade: O(log n) por elemento
        // 1. Se o array estiver cheio, aumenta sua capacidade.
        if (tail >= this.heap.length - 1) {
            resize();
        }
        // 2. Adiciona o novo elemento na primeira posição livre.
        this.tail++;
        this.heap[this.tail] = n;

        // 3. Inicia o processo de "subir" o elemento (bubble-up).
        int i = this.tail;
        //Se o filho é maior que o pai, a propriedade do heap está violada
        // Enquanto não for a raiz e o filho for maior que o pai, continue subindo.
        while (i > 0 && this.heap[i] > this.heap[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Organiza um array para que ele satisfaça a propriedade de Max-Heap.
     * Faz isso aplicando o heapify de baixo para cima.
     */
    private void buildHeap() { //Complexidade: O(n) para o array inteiro
        // O loop começa no pai do último elemento e sobe até a raiz (índice 0).
        for (int i = parent(this.tail); i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int index) {
        if (isLeaf(index) || !isValidIndex(index))
            return;

        // Compara o nó atual (pai) com seus filhos para encontrar o maior.
        int index_max = max_index(index, left(index), right(index));

        // Se o maior elemento não for o pai, troca-os de lugar.
        if (index_max != index) {
            swap(index, index_max);
            // Continua o processo recursivamente a partir da nova posição do elemento.
            heapify(index_max);
        }
    }

    public int remove() {
        if (isEmpty())
            throw new RuntimeException("Empty");
        int element = this.heap[0];
        this.heap[0] = this.heap[tail];
        this.tail -= 1;

        this.heapify(0);

        return element;
    }

    private int max_index(int index, int left, int right) {
        // Primeiro, verifica se o filho da esquerda é válido e maior que o pai
        if (isValidIndex(left) && this.heap[left] > this.heap[index]) {
            // Se sim, o 'maior' por enquanto é o da esquerda
            index = left;
        }

        // Depois, verifica se o filho da direita é válido e maior que o 'maior' atual
        if (isValidIndex(right) && this.heap[right] > this.heap[index]) {
            // Se sim, o 'maior' final é o da direita
            index = right;
        }

        return index;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= tail;
    }

    private boolean isLeaf(int index) {
        // Um nó é folha se não tiver filho esquerdo (pois a árvore é preenchida da
        // esquerda pra direita)
        return index > parent(tail) && index <= tail;
        // return !isValidIndex(left(index));
    }

    private void swap(int i, int j) {
        int aux = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = aux;
    }

    private void resize() {
        int[] novoHeap = new int[this.heap.length * 2];
        for (int i = 0; i <= tail; i++) {
            novoHeap[i] = this.heap[i];
        }
        this.heap = novoHeap;
    }

    public int size() {
        return this.tail + 1;
    }

    /**
     * Retorna o maior elemento sem removê-lo
     * @return O elemento de maior prioridade (raiz)
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Empty heap");
        }
        return this.heap[0];
    }

    public String toString() {
        return Arrays.toString(this.heap);
    }

    // public String toString() {
    // int[] heapContent = Arrays.copyOf(this.heap, this.size());
    // return Arrays.toString(heapContent);
    // }

}