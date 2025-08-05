public class LFUCache {

    private int capacidade;
    private LinkedList cache;

    // o tamanho da sua linkedlist não pode passar de `capacidade`.
    public LFUCache(int capacidade) {
        this.capacidade = capacidade;
        this.cache = new LinkedList();
    }

    public boolean isEmpty() {
    	return cache.size() == 0;
    }

    public boolean isFull() {
        return cache.size() == this.capacidade;
    }

    // deve ser O(1)
    public void addFirst(String chave) {
        //TODO implementar;
    }

    // deve ser O(n)
    // retorna o próprio valor se encontrar ou null.
    // Caso não encontre, adicione o elemento no início da fila e
    // toma as providências necessárias em relação à frequência
    public String get(String value) {
        // Busca o nó na lista (O(n))
        Node node = cache.getNode(value);

        if (node != null) {
            // --- HIT ---
            node.frequency++;
            cache.sortByFrequency(node);
            return node.value; // Retorna o valor se encontrou
        } else {
            // --- MISS ---
            // Se o cache está cheio, remove o menos frequente (o primeiro da lista)
            if (isFull()) {
                cache.removeFirst();
            }
            // Adiciona o novo elemento no início (frequência 1 por padrão)
            cache.addFirst(value);
            return null; // Retorna null para indicar um "miss"
        }
    }

    // O(1)
    public String getFirst() {
        if (cache.size() > 0) {
            return cache.getFirst();
        }

        return null;
    }

    // O(1)
    public String getLast() {
        if (cache.size() > 0) {
            return cache.getLast();
        }

        return null;
    }

    // deve retornar uma string representando a fila. 
    public String toString() {
        return cache.toString();
    }
    
    public int size() {
        return cache.size();
    }
}
