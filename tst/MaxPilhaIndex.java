import java.util.Scanner;

/**
 * Classe que implementa uma Pilha (Stack) baseada em array.
 * Esta implementação é necessária para seguir as restrições do problema.
 */
class Pilha {
    private int[] data;
    private int top;

    public Pilha(int capacidade) {
        this.data = new int[capacidade];
        this.top = -1;
    }

    // Adiciona um elemento no topo da pilha
    public void push(int valor) {
        if (top < data.length - 1) {
            data[++top] = valor;
        }
    }

    // Remove e retorna o elemento do topo da pilha

    // Verifica se a pilha está vazia
    public boolean isEmpty() {
        return top == -1;
    }
}


/**
 * Classe principal que contém a solução para o problema MaxPilhaIndex.
 */
public class MaxPilhaIndex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numerosStr = scanner.nextLine().split(" ");
        int indiceLimite = scanner.nextInt();

        // 2. CRIAR E POPULAR A PILHA ORIGINAL
        // A capacidade pode ser o número de elementos lidos.
        Pilha pilhaOriginal = new Pilha(numerosStr.length);
        for (String numStr : numerosStr) {
            pilhaOriginal.push(Integer.parseInt(numStr));
        }

        System.out.println(encontrarMaiorNoIndice(pilhaOriginal, indiceLimite));

        scanner.close();
    }

    /**
     * Encontra o maior elemento em uma pilha entre o topo (índice 0)
     * e um índice limite, restaurando a pilha ao seu estado original.
     *
     * @param pilha A pilha com os dados.
     * @param indiceLimite O índice final da busca (inclusivo, começando do topo).
     * @return O maior elemento encontrado no intervalo especificado.
     */
    public static int encontrarMaiorNoIndice(Pilha pilha, int indiceLimite) {
        // Estrutura auxiliar para restaurar a pilha original
        Pilha pilhaAuxiliar = new Pilha(indiceLimite + 1);
        int maiorElemento = Integer.MIN_VALUE; // Inicia com o menor valor possível

        // Passo 1: Percorrer a pilha até o índice limite.
        // O topo é o índice 0.
        for (int i = 0; i <= indiceLimite && !pilha.isEmpty(); i++) {
            // Retira um elemento da pilha original
            int elementoAtual = pilha.pop();

            // Compara com o maior elemento encontrado até agora
            if (elementoAtual > maiorElemento) {
                maiorElemento = elementoAtual;
            }

            // Guarda o elemento na pilha auxiliar para não perdê-lo
            pilhaAuxiliar.push(elementoAtual);
        }

        // Passo 2: Restaurar a pilha original.
        while (!pilhaAuxiliar.isEmpty()) {
            pilha.push(pilhaAuxiliar.pop());
        }

        return maiorElemento;
    }

//    import java.util.Scanner;
//import java.util.Stack;
//
//    public class Solution {
//        public static void main(String[] args) {
//            Scanner scanner = new Scanner(System.in);
//
//            // Lendo os elementos da pilha
//            String[] elementosString = scanner.nextLine().split(" ");
//            Stack<Integer> pilha = new Stack<>();
//            for (String s : elementosString) {
//                pilha.push(Integer.parseInt(s));
//            }
//
//            // Lendo o índice limite
//            int indiceLimite = scanner.nextInt();
//
//            // Encontrando e imprimindo o maior elemento
//            int maiorElemento = encontrarMaior(pilha, indiceLimite);
//            System.out.println(maiorElemento);
//
//            scanner.close();
//        }
//
//        /**
//         * Encontra o maior elemento em uma pilha, do topo até um índice limite.
//         * A pilha original é restaurada após a operação.
//         *
//         * @param pilha A pilha de inteiros.
//         * @param indiceLimite O índice máximo a ser considerado (0 é o topo).
//         * @return O maior elemento encontrado ou 0 se a pilha estiver vazia.
//         */
//        public static int encontrarMaior(Stack<Integer> pilha, int indiceLimite) {
//            if (pilha.isEmpty()) {
//                return 0;
//            }
//
//            Stack<Integer> auxiliar = new Stack<>();
//            int maior = Integer.MIN_VALUE;
//            int contador = 0;
//
//            // Itera sobre a pilha até o índice limite, movendo os elementos para a pilha auxiliar
//            while (!pilha.isEmpty() && contador <= indiceLimite) {
//                int elemento = pilha.pop();
//                if (elemento > maior) {
//                    maior = elemento;
//                }
//                auxiliar.push(elemento);
//                contador++;
//            }
//
//            // Restaura a pilha original movendo os elementos de volta da auxiliar
//            while (!auxiliar.isEmpty()) {
//                pilha.push(auxiliar.pop());
//            }
//
//            return maior;
//        }
//    }
}