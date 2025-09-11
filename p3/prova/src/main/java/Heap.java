import java.util.Arrays;

public class Heap {
    
    private int[] heap;
    private int tail;

    // construtor que já recebe um max-heap válido. feito para facilitar os testes.
    public Heap(int[] heap) {
        this.heap = heap;
        this.tail = this.heap.length - 1;
    }
    public Heap(int capacidade) {
        this.heap = new int[capacidade];
        this.tail = -1;
    }


  // Método para verificar se o heap está em ordem de min-heap
    public boolean ehMinHeap() {
        for (int i = 0; i < tail / 2; i++) {
            int esquerda = 2 * i + 1;
            int direita = 2 * i + 2;

            // Verifica se o pai é menor que os filhos
            if (esquerda <=tail && heap[i] > heap[esquerda]) {
                return false;
            }

            if (direita <= tail && heap[i] > heap[direita]) {
                return false;
            }
        }
        return true;
    }



    public boolean isEmpty() {
        return this.tail == -1;
    }

    public int left(int i) {
        return 2*i+1;
    }

    public int right(int i) {
        return (i+1)*2;
    }

    public int parent(int i) {
        return Math.floorDiv(i-1, 2);
    }

    // TODO implementar
    public void toMinHeap() {
        for (int i = parent(tail); i >= 0; i--) {
            heapify(i);
        }

    }
        
    private void heapify(int index) {
        if (isLeaf(index) || !isValidIndex(index)) 
            return;
        
        // compares index, left and right to find max
        int index_min = min_index(index, left(index), right(index));
        
        // if current index is not greater than its children, 
        // swap and keep heapifying.
        if (index_min != index) {
                swap(index, index_min);
                heapify(index_min);
        }
    } 
    
    private int min_index(int index, int left, int right) {
        if (this.heap[index] < this.heap[left]) {
            if (isValidIndex(right)) {
                if (this.heap[index] > this.heap[right])
                    return right;
            }
            return index;
        
        } else {
            if (isValidIndex(right)) {
                if (this.heap[left] > this.heap[right])
                    return right;
            } 
            
            return left;
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= tail;
    }
    
    private boolean isLeaf(int index) {
        return index > parent(tail) && index <= tail; 
    } 
    
    private void swap(int i, int j) {
        int aux = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = aux;
    }

    private void resize() {
        int[] novoHeap = new int[this.heap.length * 2];
        for (int i = 0; i <= tail; i++)
            novoHeap[i] = this.heap[i];
        
        this.heap = novoHeap;
    }
    
    public int size() {
        return this.tail + 1;
    }
    
    public String toString() {
        return Arrays.toString(this.heap);
    }

}
