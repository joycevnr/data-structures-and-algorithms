public class FIFOCache {
    private int head;
    private int tail;
    private String[] fila;
    private int size;

    // sua fila deve seguir a abordagem circular que vimos em sala de aula.
    // isso implica em dizer quer adições e remoções são O(1).
    public FIFOCache(int capacidade) {
        this.fila = new String[capacidade];
        this.tail = -1; // fila vazia
        this.head = -1; // fila vazia
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        // a maneira correta de verificar se a fila está cheia
        // é comparando o tamanho atual com a capacidade total.
        return this.size == this.fila.length;
    }

    // O comentário "// deve sobrescerver o mais antigo caso a fila esteja cheia."
    // descreve o comportamento do *cache*, que é implementado na classe
    // FIFOEvictionStrategy (que primeiro remove e depois adiciona).
    // A fila em si só precisa adicionar ou lançar exceção se estiver cheia e não for tratada.
    public void addLast(String chave) {
        if (isFull()) {
            throw new IllegalStateException("Fila cheia");
        }

        if (isEmpty()) {
            head = 0;
        }
        tail = (tail + 1) % fila.length;
        fila[tail] = chave;
        size++;
    }

    // deve lançar exceção caso a fila esteja vazia.
    public String removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila está vazia");
        }

        String removido = fila[head];
        fila[head] = null;
        head = (head + 1) % fila.length;
        size--;

        //  Se a fila se tornou vazia após a remoção,
        // devemos resetar os ponteiros head e tail.
        if (this.size == 0) {
            head = -1;
            tail = -1;
        }
        return removido;
    }

    // deve lançar exceção caso a fila esteja vazia. apenas retorna o primeiro da fila, sem
    // remover;
    public String getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila está vazia");
        }
        return fila[head];
    }

    // deve lançar exceção caso a fila esteja vazia. apenas retorna o último da fila, sem
    // remover;
    public String getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila está vazia");
        }
        return fila[tail];
    }

    public String toString() {
        if (isEmpty()) {
            return "";
        }
        String elementos = "";
        for (int i = 0; i < this.size; i++) {
            elementos += fila[(head + i) % fila.length];
            if (i != size - 1) {
                elementos += ", ";
            }
        }
        return elementos;
    }

    public int size() {
        return this.size;
    }

    public int length() {
        return this.fila.length;
    }

    public int getHead() {
        return this.head;
    }

}