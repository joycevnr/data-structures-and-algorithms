import java.util.Scanner;

public class FilaComArray {

    private int[] fila;
    private int head;
    private int tail;
    private int size;

    // Construtor
    public FilaComArray(int capacidade) {
        this.fila = new int[capacidade];
        this.head = -1;
        this.tail = -1;
        this.size = 0;
    }

    // Verifica se a fila está cheia
    public boolean isFull() {
        return this.size == this.fila.length;
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Adiciona um elemento ao final da fila
    public void add(int valor) {
        if (isFull()) {
            System.out.println("full");
            return;
        }
        if (isEmpty()) {
            this.head = 0;
        }
        this.tail = (this.tail + 1) % this.fila.length;
        this.fila[this.tail] = valor;
        this.size++;
    }

    // Remove o elemento do início da fila
    public void remove() {
        if (isEmpty()) {
            System.out.println("empty");
            return;
        }

        // Remove o elemento na posição head
        this.head = (this.head + 1) % this.fila.length;
        this.size--;

        // Reseta os ponteiros se a fila ficar vazia
        if (this.size == 0) {
            this.head = -1;
            this.tail = -1;
        }
    }

    // Imprime o elemento inicial da fila
    public void element() {
        if (isEmpty()) {
            System.out.println("empty");
        } else {
            System.out.println(this.fila[this.head]);
        }
    }

    // Retorna a representação da fila como uma string
    @Override
    public String toString() {
        if (isEmpty()) {
            return "empty";
        }
        String arr = "";
        for (int i = 0; i < this.size; i++) {
            int index = (this.head + i) % this.fila.length;
            arr += this.fila[index];
            if (i < this.size - 1) {
                arr += " ";
            }
        }
        return arr;
    }

    // Método principal para processar a entrada e a saída
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tamanho = scanner.nextInt();

        FilaComArray fila = new FilaComArray(tamanho);

        String linha;
        while (!(linha = scanner.nextLine()).equals("end")) {
            String[] comando = linha.split(" ");

            if (comando[0].equals("add")) {
                int valor = Integer.parseInt(comando[1]);
                fila.add(valor);
            } else if (comando[0].equals("remove")) {
                fila.remove();
            } else if (comando[0].equals("element")) {
                fila.element();
            } else if (comando[0].equals("print")) {
                System.out.println(fila.toString());
            }
        }
        scanner.close();
    }
}