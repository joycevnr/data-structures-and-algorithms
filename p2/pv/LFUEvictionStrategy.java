public class LFUEvictionStrategy {

    private LFUCache cache;

    public LFUEvictionStrategy(int capacidade) {
        this.cache = new LFUCache(capacidade);
    }

    /**
     * Retorna "hit" se estiver no cache.
     * Retorna "miss" se não estiver no cache e adiciona no mesmo.
     */
    public String get(String chave) {
        String resultado = cache.get(chave);

        return resultado != null ? "hit" : "miss";
    }

    /**
     * Retorna o próximo que deve sair do cache caso entre mais alguém e ele esteja cheio.
     * Se não for sair ninguém, ou seja, se ainda não estiver cheio, retorna null.
     */
    public String getNextEviction() {
        // Se o cache está cheio, o próximo a sair é o da cabeça (menos frequente).
        return cache.isFull() ? cache.getFirst() : null;
    }

    // Retorna a quantidade de elementos no cache.
    public int size() {
        return cache.size();
    }
}