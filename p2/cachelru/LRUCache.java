import java.util.HashMap;

public class LRUCache {

    private final int capacidade;
    private LinkedList list;
    private HashMap<String, Node> map;
    // o tamanho da sua linkedlist não pode passar de `capacidade`.
    public LRUCache(int capacidade) {
        this.capacidade = capacidade;
        this.list = new LinkedList();
        this.map = new HashMap<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isFull() {
        return list.size() == this.capacidade;
    }

    /**
     * Adiciona um novo item (miss). Se o cache estiver cheio, remove o menos usado (head) antes de adicionar.
     */
    public void addLast(String chave) {
        if (isFull()) {
            // remove o menos usado (head)
            String evictedKey = list.removeFirst();
            map.remove(evictedKey);
        }

        // add o novo item
        list.addLast(chave);
        map.put(chave, list.getTail()); // Mapeia a chave para o novo nó na cauda
    }

    // deve ser O(n)
    // retorna o próprio valor se encontrar ou null.
    // Encontrando ou não, o elemento deve ser movido para o final da lista
    public String get(String value) {
        Node node = map.get(value);
        if (node == null) {
            return null; // Miss
        }

        // Hit: move o nó acessado para o final da lista (mais recentemente usado)
        list.moveToTail(node);
        return node.value;
    }

    public String getFirst() {
        return list.getFirst();
    }

    public String getLast() {
        return list.getLast();
    }

    // deve retornar uma string representando a fila. 
    public String toString() {
        return list.toString();
    }

    public int size() {
        return list.size();
    }
}
