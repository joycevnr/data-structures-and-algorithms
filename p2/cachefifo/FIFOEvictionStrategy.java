import java.util.HashSet;

public class FIFOEvictionStrategy {

    //FIFO (First-In-First-Out) :  o 1° elemento que entrou deve ser o 1° a sair quando a fila estiver cheia.

    private FIFOCache fila;
    private HashSet<String> cache;

    // deve ter uma FIFOCache como atributo
    public FIFOEvictionStrategy(int capacidade) {
        this.fila = new FIFOCache(capacidade);
        this.cache = new HashSet<>();
    }

    /*
     * retorna "hit" se estiver no cache.
     * retorna "miss" se não estiver no cache e adiciona no mesmo.
     * Note que essa é uma simplificação. Idealmente, esse método retornaria o
     * objeto com a chave passada como parâmetro.
     **/
    //PASSOS:
    // 1. Procurar na tabela hash
    public String get(String chave) {
        if (cache.contains(chave)) { // Custo O(1)
            return "hit";
        }
        // 2. Se a fila está cheia, remover o mais antigo:
        if (fila.isFull()) {// Custo O(1)
            String removido = fila.removeFirst(); // remove do início da fila
            cache.remove(removido);
        }
        // 3. Adicionar o novo elemento:
        fila.addLast(chave); // add no final da fila
        cache.add(chave);
        return "miss";
    }

    //Verifica qual item SERIA removido antes da remoção acontecer.
    public String getNextEviction() {
        return fila.isFull() ? fila.getFirst() : null;
    }

    public int size() {
        return fila.size();
    }
}
