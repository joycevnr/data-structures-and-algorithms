import java.util.Arrays;

public class Fila {
    private int head;
    private int tail;
    private int[] fila;
    private int size;

    // sua fila deve seguir a abordagem circular que vimos em sala de aula.
    // isso implica em dizer quer adições e remoções são O(1).
    public Fila(int capacidade) {
        this.fila = new int[capacidade];
        this.head = -1; //  fila vazia
        this.tail = -1; // fila vazia
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == -1 && this.tail == -1;
    }

    public boolean isFull() {
        //this.head == this.fila.length;
        return this.size == fila.length;
    }

    // deve lançar exceção caso a fila esteja cheia.
    public void addLast(int valor) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia.");
        }

        if (isEmpty()) { // add o primeiro elemento
            this.head = 0;
        }
        
        tail = (tail + 1) % fila.length;
        fila[tail] = valor;
        size++;
    }

    // Deve lançar exceção caso a fila esteja vazia.
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }

        int valorRemovido = this.fila[this.head];

        if (this.head == this.tail) {//1 elem na fila
            this.head = -1;
            this.tail = -1;
        } else {
            this.head = (this.head + 1) % this.fila.length;
        }
        this.size -= 1;
        return valorRemovido;
    }

    // deve lançar exceção caso a fila esteja vazia. apenas retorna o primeiro da fila, sem
    // remover;
    public int getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        return fila[head];
    }

    // deve lançar exceção caso a fila esteja vazia. apenas retorna o último da fila, sem
    // remover;
    public int getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        return fila[tail];
    }

    // deve retornar uma string representando a fila.
    public String toString() {
        String elementosAtuais = "";
        for (int i = 0; i < this.size; i++) {
            elementosAtuais += fila[(head + i) % fila.length];
//            System.out.println(tail);
//            System.out.println(head);
//            System.out.println(size() - 1);

            if (i != size() - 1) {
                elementosAtuais += ", ";
            }
        }
        return elementosAtuais;

    }

    // Deve retornar a posição da primeira ocorrência do elemento passado como parâmetro. 
    public int indexOf(int valor) {
        for (int i = 0; i < size; i++) {
            if (fila[(head + i) % fila.length] == valor) {
                return i;
            }
        }
        return -1; // Elemento não encontrado
    }

    // Deve retornar a posição da última ocorrência do elemento passado como parâmetro. 
    public int lastIndexOf(int valor) {
        for (int i = size - 1; i >= 0; i--) {
            if (fila[(head + i) % fila.length] == valor) {
                return i;
            }
        }
        return -1; // elem não encontrado
    }

    // Retorna o tamanho atual da fila
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        Fila fila = new Fila(5);
        System.out.println(fila.toString());
        fila.addLast(10);
        System.out.println(fila.toString());
        fila.addLast(20);
        System.out.println(fila.toString());
}
}
