/**
 * Exemplo demonstrando o funcionamento do HeapSort passo a passo
 */
public class HeapSortExample {
    
    public static void main(String[] args) {
        exemploBasico();
        System.out.println("\n" + "=".repeat(60) + "\n");
        exemploComparacaoComplexidade();
    }
    
    /**
     * Exemplo básico mostrando o HeapSort passo a passo
     */
    public static void exemploBasico() {
        System.out.println("🔢 HEAPSORT - EXEMPLO PASSO A PASSO");
        
        int[] array = {4, 10, 3, 5, 1, 8, 2, 7, 6, 9};
        
        System.out.println("Array original: " + java.util.Arrays.toString(array));
        System.out.println("\n" + "─".repeat(50));
        
        // Executar HeapSort com prints detalhados
        int[] ordenado = HeapSort.heapSort(array);
        
        System.out.println("\n" + "─".repeat(50));
        System.out.println("✅ Resultado final: " + java.util.Arrays.toString(ordenado));
        System.out.println("📊 Verificação: " + (HeapSort.isOrdenado(ordenado) ? "ORDENADO ✓" : "ERRO ✗"));
    }
    
    /**
     * Exemplo comparando diferentes tamanhos de array
     */
    public static void exemploComparacaoComplexidade() {
        System.out.println("⚡ HEAPSORT - ANÁLISE DE PERFORMANCE");
        
        int[] tamanhos = {10, 100, 1000};
        
        for (int n : tamanhos) {
            System.out.println("\n📏 Testando array de tamanho " + n + ":");
            
            // Gerar array aleatório
            int[] array = gerarArrayAleatorio(n);
            
            // Medir tempo
            long inicio = System.nanoTime();
            int[] ordenado = HeapSort.heapSort(array);
            long fim = System.nanoTime();
            
            double tempoMs = (fim - inicio) / 1_000_000.0;
            
            // Mostrar apenas início e fim do array se for grande
            String arrayStr = n <= 10 ? 
                java.util.Arrays.toString(array) : 
                "[" + array[0] + ", " + array[1] + ", ..., " + 
                array[n-2] + ", " + array[n-1] + "]";
                
            String ordenadoStr = n <= 10 ? 
                java.util.Arrays.toString(ordenado) : 
                "[" + ordenado[0] + ", " + ordenado[1] + ", ..., " + 
                ordenado[n-2] + ", " + ordenado[n-1] + "]";
            
            System.out.println("  Original:  " + arrayStr);
            System.out.println("  Ordenado:  " + ordenadoStr);
            System.out.println("  Tempo:     " + String.format("%.3f ms", tempoMs));
            System.out.println("  Status:    " + (HeapSort.isOrdenado(ordenado) ? "✓ Correto" : "✗ Erro"));
            
            // Cálculo teórico da complexidade
            double complexidadeT = n * Math.log(n) / Math.log(2);
            System.out.println("  O(n log n): ~" + String.format("%.0f", complexidadeT) + " operações teóricas");
        }
    }
    
    /**
     * Demonstração das diferenças entre Heap, PriorityQueue e HeapSort
     */
    public static void demonstracaoDiferencas() {
        System.out.println("🔍 COMPARAÇÃO: HEAP vs PRIORITY QUEUE vs HEAPSORT");
        
        int[] dados = {5, 2, 8, 1, 9, 3};
        System.out.println("Dados de teste: " + java.util.Arrays.toString(dados));
        
        System.out.println("\n1️⃣ HEAP (estrutura de dados):");
        Heap heap = new Heap(dados.clone());
        System.out.println("   Heap criado: " + heap);
        System.out.println("   Remove maior: " + heap.remove()); // 9
        System.out.println("   Remove maior: " + heap.remove()); // 8
        System.out.println("   Heap após remoções: " + heap);
        
        System.out.println("\n2️⃣ PRIORITY QUEUE (interface):");
        PriorityQueue pq = new PriorityQueue(dados.clone());
        System.out.println("   Fila criada: " + pq);
        System.out.println("   Próximo: " + pq.peek());
        System.out.println("   Atender: " + pq.dequeue()); // 9
        System.out.println("   Atender: " + pq.dequeue()); // 8
        System.out.println("   Fila após atendimentos: " + pq);
        
        System.out.println("\n3️⃣ HEAPSORT (algoritmo):");
        int[] original = dados.clone();
        System.out.println("   Array original: " + java.util.Arrays.toString(original));
        int[] ordenado = HeapSort.heapSort(original);
        System.out.println("   Array ordenado: " + java.util.Arrays.toString(ordenado));
        System.out.println("   Original inalterado: " + java.util.Arrays.toString(original));
    }
    
    /**
     * Gera array com números aleatórios
     */
    private static int[] gerarArrayAleatorio(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = (int) (Math.random() * 100) + 1; // 1 a 100
        }
        return array;
    }
}
