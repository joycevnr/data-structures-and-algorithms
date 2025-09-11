import java.util.Arrays;
import java.util.Scanner;

/**
 * Programa que implementa o algoritmo Build Heap.
 * 
 * O algoritmo Build Heap transforma um array arbitrário em um heap máximo.
 * Um heap máximo é uma árvore binária completa onde cada nó pai é maior 
 * ou igual aos seus filhos.
 * 
 * Complexidade de tempo: O(n), onde n é o número de elementos
 * Complexidade de espaço: O(1) - trabalha in-place no array original
 * 
 * Como funciona:
 * 1. Começa pelo último nó que não é folha (parent(tail))
 * 2. Aplica heapify em cada nó, indo de baixo para cima até a raiz
 * 3. O heapify garante que cada nó respeite a propriedade de heap máximo
 * 
 * Lê uma sequência de inteiros e transforma em um heap máximo.
 */
class BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(" ");

        Heap heap = new Heap(entrada.length);
        for (String s : entrada) {
            heap.add(Integer.parseInt(s));
        }

        heap.buildHeap();
        System.out.println(Arrays.toString(heap.getArray()));
        
        sc.close();
    }

    public static class Heap {
        int[] heap;
        int tail;

        public Heap(int capacidade) {
            heap = new int[capacidade];
            tail = -1;
        }

        public int left(int i) {
            return 2 * i + 1;
        }

        public int right(int i) {
            return 2 * (i + 1);
        }

        public int parent(int i) {
            return (i - 1) / 2;
        }

        public void add(int valor) {
            if (tail >= heap.length - 1) {
                throw new RuntimeException("Heap cheio");
            }
            tail++;
            heap[tail] = valor;
        }

        public void buildHeap() {
            for (int i = parent(tail); i >= 0; i--) {
                heapify(i);
            }
        }

        public void heapify(int i) {
            if (i > tail)
                return;
                
            int index_max = max_index(i, left(i), right(i));
            
            if (index_max != i) {
                swap(i, index_max);
                heapify(index_max);
            }
        }

        public int max_index(int i, int left, int right) {
            if (i <= tail && heap[left] > heap[i] && left <= tail) {
                i = left;
            }
            if (i <= tail && heap[right] > heap[i] && right <= tail) {
                i = right;
            }
            return i;
        }

        public void swap(int i, int j) {
            int aux = heap[i];
            heap[i] = heap[j];
            heap[j] = aux;
        }

        public int[] getArray() {
            int[] resultado = new int[tail + 1];
            for (int i = 0; i <= tail; i++) {
                resultado[i] = heap[i];
            }
            return resultado;
        }
    }
}