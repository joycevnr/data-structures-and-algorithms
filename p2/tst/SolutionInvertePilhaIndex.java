import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Classe que implementa uma Pilha (Stack) baseada em array.
 * Esta implementação é necessária para seguir as restrições do problema.
 */
class Pilha {
    private int[] data;
    private int top;

    public Pilha(int capacidade) {
        data = new int[capacidade];
        top = -1; // A pilha começa vazia
    }

    // Adiciona um elemento no topo da pilha
    public void push(int valor) {
        if (top < data.length - 1) {
            data[++top] = valor;
        }
    }

    // Remove e retorna o elemento do topo da pilha
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        return data[top--];
    }


    // Verifica se a pilha está vazia
    public boolean isEmpty() {
        return top == -1;
    }
    public String toString() {
    StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}


/**
 * Classe principal que contém a solução do problema.
 */
public class SolutionInvertePilhaIndex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tamanhoPilha = scanner.nextInt();
        Pilha pilhaOriginal = new Pilha(tamanhoPilha);

        for (int i = 0; i < tamanhoPilha; i++) {
            pilhaOriginal.push(scanner.nextInt());
        }

        int indexLimite = scanner.nextInt();

        // 2. Chamar o método para inverter a pilha
        inverter(pilhaOriginal, indexLimite);
        System.out.println("Pilha invertida:" + pilhaOriginal.toString());

        // 3. Imprimir a pilha invertida, esvaziando-a
        while (!pilhaOriginal.isEmpty()) {
            System.out.println(pilhaOriginal.pop());
        }

        scanner.close();
    }

    /**
     * Inverte os elementos de uma pilha usando uma fila auxiliar.
     * @param pilha A pilha a ser invertida.
     */
    public static void inverter(Pilha pilha, int indexLimite) {
        // A interface Queue é implementada pela classe LinkedList
        Queue<Integer> filaAuxiliar = new LinkedList<>(); //fila

        // Passo 1: Mover todos os elementos da pilha para a fila.
        // A ordem na fila será a ordem em que os elementos são removidos da pilha (LIFO).
//        while (!pilha.isEmpty() ) {
//
//            filaAuxiliar.add(pilha.pop());
//        }
        if(pilha.isEmpty()){
            throw new RuntimeException("Pilha está vazia");
        }

        for(int i = 0; i <= indexLimite; i++){
            filaAuxiliar.add(pilha.pop());
        }

        // Passo 2: Mover todos os elementos da fila de volta para a pilha.
        // A ordem de remoção da fila (FIFO) fará com que a pilha seja remontada de forma invertida.
        while (!filaAuxiliar.isEmpty()) {
            pilha.push(filaAuxiliar.remove());
        }
    }
}