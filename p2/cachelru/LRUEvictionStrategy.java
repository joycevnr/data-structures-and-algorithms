public class LRUEvictionStrategy {
//é o "rosto" público do seu cache, delegando o trabalho para LRUCache.

// deve ter uma linkedlist como atributo
    //referência para o "cérebro" do sistema.
    private LRUCache cache;

    public LRUEvictionStrategy(int capacidade) {
        this.cache = new LRUCache(capacidade);
    }

    /*
     * retorna "hit" se estiver no cache.
     * retorna "miss" se não estiver no cache e adiciona no mesmo.
     * Note que essa é uma simplificação. Idealmente, esse método retornaria o
     * objeto com a chave passada como parâmetro.
     **/
    public String get(String chave) {
        String value = cache.get(chave);

        if (value != null) {
            return "hit"; // Encontrou no cache
        } else {
            cache.addLast(chave); // Não encontrou, adiciona
            return "miss";
        }
    }

    /*
     * retorna o próximo que deve sair do cache caso entre mais alguém e ele esteja cheio.
     * se não for sair ninguém, ou seja, se ainda não estiver cheio, retorna null.
     * esse método não altera o estado da fila.
     **/
    public String getNextEviction() {
        // Se estiver cheio, o próximo a sair é o da cabeça (menos usado)
        return cache.isFull() ? cache.getFirst() : null;
    }

    //retorna a quantidade de elementos no cache.
    public int size() {
        return cache.size();
    }
}
