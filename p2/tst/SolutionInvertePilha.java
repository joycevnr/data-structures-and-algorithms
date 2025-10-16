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
        if (!isEmpty()) {
            return data[top--];
        }
        // Em um cenário real, seria melhor lançar uma exceção aqui
        return -1; // Retorno de erro simples
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
public class SolutionInvertePilha {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ler a entrada e criar a pilha original
        int tamanhoPilha = scanner.nextInt();
        Pilha pilhaOriginal = new Pilha(tamanhoPilha);

        for (int i = 0; i < tamanhoPilha; i++) {
            pilhaOriginal.push(scanner.nextInt());
        }

        // 2. Chamar o método para inverter a pilha
        inverter(pilhaOriginal);
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
    public static void inverter(Pilha pilha) {
        // A interface Queue é implementada pela classe LinkedList
        Queue<Integer> filaAuxiliar = new LinkedList<>();

        // Passo 1: Mover todos os elementos da pilha para a fila.
        // A ordem na fila será a ordem em que os elementos são removidos da pilha (LIFO).
        while (!pilha.isEmpty()) {
            filaAuxiliar.add(pilha.pop());
        }

        // Passo 2: Mover todos os elementos da fila de volta para a pilha.
        // A ordem de remoção da fila (FIFO) fará com que a pilha seja remontada de forma invertida.
        while (!filaAuxiliar.isEmpty()) {
            pilha.push(filaAuxiliar.remove());
        }
    }

//    import java.util.Scanner;
//
//    public class Solution {
//        public static void main(String[] args) {
//            Scanner scanner = new Scanner(System.in);
//            String linha = scanner.nextLine();
//            String[] numerosString = linha.split(" ");
//            int[] array = new int[numerosString.length];
//
//            for (int i = 0; i < numerosString.length; i++) {
//                array[i] = Integer.parseInt(numerosString[i]);
//            }
//
//            inverterArray(array);
//            imprimirArray(array);
//
//            scanner.close();
//        }
//
//        public static void inverterArray(int[] array) {
//            int inicio = 0;
//            int fim = array.length - 1;
//
//            while (inicio < fim) {
//                // Troca os elementos das posições 'inicio' e 'fim'
//                int temp = array[inicio];
//                array[inicio] = array[fim];
//                array[fim] = temp;
//
//                // Move os ponteiros para o próximo par de elementos
//                inicio++;
//                fim--;
//            }
//        }
//
//        public static void imprimirArray(int[] array) {
//            for (int i = 0; i < array.length; i++) {
//                System.out.print(array[i]);
//                if (i < array.length - 1) {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//    }
//}
//import java.util.Scanner;
//import java.util.Stack;
//
//    class Pilha extends Stack<Integer> {
//
//        // Inverte a porção da pilha até o índice especificado
//        public void inverte(int index) {
//            Stack<Integer> auxiliar = new Stack<>();
//            int count = 0;
//
//            while (count <= index) {
//                auxiliar.push(this.pop());
//                count++;
//            }
//
//            while (!auxiliar.isEmpty()) {
//                this.push(auxiliar.pop());
//            }
//        }
//
//        // Encontra o maior elemento entre o topo e o índice especificado
//        public int getMax(int index) {
//            Stack<Integer> auxiliar = new Stack<>();
//            int maior = Integer.MIN_VALUE;
//            int count = 0;
//
//            while (count <= index) {
//                int elemento = this.pop();
//                if (elemento > maior) {
//                    maior = elemento;
//                }
//                auxiliar.push(elemento);
//                count++;
//            }
//
//            while (!auxiliar.isEmpty()) {
//                this.push(auxiliar.pop());
//            }
//
//            return maior;
//        }
//    }
//
//    public class Solution {
//        public static void main(String[] args) {
//            Scanner scanner = new Scanner(System.in);
//
//            int tamanho = scanner.nextInt();
//            // Consome a quebra de linha após a leitura do inteiro
//            scanner.nextLine();
//
//            Pilha pilha = new Pilha();
//            String[] numeros = scanner.nextLine().split(" ");
//            for (String num : numeros) {
//                pilha.push(Integer.parseInt(num));
//            }
//
//            ordenaPilha(pilha);
//
//            System.out.println("-");
//            while (!pilha.isEmpty()) {
//                System.out.println(pilha.pop());
//            }
//
//            scanner.close();
//        }
//
//        public static void ordenaPilha(Pilha pilha) {
//            int tamanho = pilha.size();
//            Stack<Integer> auxiliar = new Stack<>();
//
//            // Loop principal que simula o Selection Sort
//            for (int i = 0; i < tamanho; i++) {
//                // Pega o maior elemento da porção não ordenada
//                int maior = pilha.getMax(tamanho - 1 - i);
//
//                // Move os elementos da pilha principal para a auxiliar
//                // até encontrar e remover o maior elemento
//                while (pilha.peek() != maior) {
//                    auxiliar.push(pilha.pop());
//                }
//                pilha.pop(); // Remove o maior elemento
//
//                // Restaura a pilha principal com os elementos da auxiliar
//                while (!auxiliar.isEmpty()) {
//                    pilha.push(auxiliar.pop());
//                }
//
//                // Agora, o maior elemento está no topo.
//                // Movemos ele para a base da porção não ordenada.
//                // Isso requer mover a porção restante para a pilha auxiliar
//                for (int j = 0; j < tamanho - 1 - i; j++) {
//                    auxiliar.push(pilha.pop());
//                }
//
//                pilha.push(maior); // Coloca o maior elemento na base
//
//                // Restaura a porção restante de volta para a pilha principal
//                while (!auxiliar.isEmpty()) {
//                    pilha.push(auxiliar.pop());
//                }
//            }
//        }
//    }
//
