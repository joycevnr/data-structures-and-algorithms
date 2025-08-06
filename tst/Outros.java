import java.util.Scanner;
import java.util.Stack;

public class SolucaoSimples {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expressao = scanner.nextLine().replace(" ", ""); // Remove espaços
        System.out.println(avaliarExpressao(expressao));
        scanner.close();
    }

    public static int avaliarExpressao(String expressao) {
        Stack<String> pilha = new Stack<>();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (Character.isDigit(c)) {
                // Constrói o número completo
                StringBuilder sb = new StringBuilder();
                while (i < expressao.length() && Character.isDigit(expressao.charAt(i))) {
                    sb.append(expressao.charAt(i++));
                }
                pilha.push(sb.toString());
                i--; // Volta uma posição para não pular o próximo caractere
            } else if (c == ')') {
                // Parêntese de fechamento: avalia a expressão
                int b = Integer.parseInt(pilha.pop());
                char operador = pilha.pop().charAt(0);
                int a = Integer.parseInt(pilha.pop());
                pilha.pop(); // Remove o parêntese de abertura '('

                int resultado = 0;
                switch (operador) {
                    case '+': resultado = a + b; break;
                    case '-': resultado = a - b; break;
                    case '*': resultado = a * b; break;
                    case '/': resultado = a / b; break;
                }
                pilha.push(String.valueOf(resultado));
            } else if (c != '(') {
                // Operador ou parêntese de abertura
                pilha.push(String.valueOf(c));
            }
        }

        // No final, se houver apenas um número na pilha, ele é o resultado
        return Integer.parseInt(pilha.pop());
    }
}
//
//import java.util.Scanner;
//import java.util.Arrays;
//
//public class MinhaSolucaoOtimizada {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        String linhaFila = scanner.nextLine();
//        String[] elementosFilaString = linhaFila.split(" ");
//        int[] fila = new int[elementosFilaString.length];
//        for (int i = 0; i < elementosFilaString.length; i++) {
//            fila[i] = Integer.parseInt(elementosFilaString[i]);
//        }
//
//        int indiceIdosos = scanner.nextInt();
//
//        // Itera sobre os idosos, um de cada vez
//        for (int i = indiceIdosos; i < fila.length; i++) {
//            int idoso = fila[i];
//            int j = i;
//
//            // Desloca os elementos da frente uma posição para a direita
//            while (j > i - (i - indiceIdosos) && fila[j-1] != -1) {
//                fila[j] = fila[j - 1];
//                j--;
//            }
//
//            // Coloca o idoso na posição correta
//            fila[j] = idoso;
//
//            // Imprime o estado do array após a inserção
//            System.out.println(Arrays.toString(fila));
//        }
//
//        scanner.close();
//    }
//}
//
//import java.util.Scanner;
//import java.util.Stack;
//
//public class Solucao {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String frase = scanner.nextLine();
//
//        Stack<String> pilhaDePalavras = new Stack<>();
//        StringBuilder palavraAtual = new StringBuilder();
//
//        for (int i = 0; i < frase.length(); i++) {
//            char c = frase.charAt(i);
//            if (c == ' ') {
//                // Encontrou um espaço, a palavra terminou
//                if (palavraAtual.length() > 0) {
//                    pilhaDePalavras.push(palavraAtual.toString());
//                    palavraAtual.setLength(0); // Limpa o StringBuilder
//                }
//            } else {
//                // Constrói a palavra
//                palavraAtual.append(c);
//            }
//        }
//        // Adiciona a última palavra, caso a frase não termine com espaço
//        if (palavraAtual.length() > 0) {
//            pilhaDePalavras.push(palavraAtual.toString());
//        }
//
//        StringBuilder fraseInvertida = new StringBuilder();
//        while (!pilhaDePalavras.isEmpty()) {
//            fraseInvertida.append(pilhaDePalavras.pop());
//            if (!pilhaDePalavras.isEmpty()) {
//                fraseInvertida.append(" ");
//            }
//        }
//
//        System.out.println(fraseInvertida.toString());
//        scanner.close();
//    }
//}

import java.util.Scanner;
import java.util.Stack;

class Pilha extends Stack<Integer> {

    // Inverte a porção da pilha até o índice especificado
    public void inverte(int index) {
        Stack<Integer> auxiliar = new Stack<>();
        int count = 0;

        // Move os elementos até o índice 'index' para a pilha auxiliar
        while (count <= index) {
            auxiliar.push(this.pop());
            count++;
        }

        // Move os elementos de volta, invertendo a ordem
        while (!auxiliar.isEmpty()) {
            this.push(auxiliar.pop());
        }
    }

    // Encontra o maior elemento entre o topo e o índice especificado
    public int getMax(int index) {
        Stack<Integer> auxiliar = new Stack<>();
        int maior = Integer.MIN_VALUE;
        int count = 0;

        // Itera sobre a porção da pilha, movendo os elementos para a auxiliar
        while (count <= index) {
            int elemento = this.pop();
            if (elemento > maior) {
                maior = elemento;
            }
            auxiliar.push(elemento);
            count++;
        }

        // Restaura a pilha original
        while (!auxiliar.isEmpty()) {
            this.push(auxiliar.pop());
        }

        return maior;
    }
}
//
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        int tamanho = scanner.nextInt();
//        scanner.nextLine(); // Consome a quebra de linha
//
//        Pilha pilha = new Pilha();
//        String[] numeros = scanner.nextLine().split(" ");
//        for (String num : numeros) {
//            pilha.push(Integer.parseInt(num));
//        }
//
//        ordenaPilha(pilha);
//
//        System.out.println("-");
//        while (!pilha.isEmpty()) {
//            System.out.println(pilha.pop());
//        }
//
//        scanner.close();
//    }
//
//    public static void ordenaPilha(Pilha pilha) {
//        int tamanho = pilha.size();
//        Stack<Integer> auxiliar = new Stack<>();
//
//        for (int i = 0; i < tamanho; i++) {
//            int maior = Integer.MIN_VALUE;
//            int posicaoMaior = -1;
//
//            // Encontra o maior elemento e sua posição na porção não ordenada
//            for (int j = 0; j < tamanho - i; j++) {
//                int elemento = pilha.pop();
//                if (elemento > maior) {
//                    maior = elemento;
//                    posicaoMaior = j;
//                }
//                auxiliar.push(elemento);
//            }
//
//            // Restaura a pilha principal, pulando o maior elemento
//            while (!auxiliar.isEmpty()) {
//                int elemento = auxiliar.pop();
//                if (elemento != maior) {
//                    pilha.push(elemento);
//                }
//            }
//
//            // Adiciona o maior elemento no fundo da pilha, garantindo a ordem
//            // Adicionamos os elementos de volta da pilha principal para a auxiliar
//            while (!pilha.isEmpty()) {
//                auxiliar.push(pilha.pop());
//            }
//            // Adiciona o maior elemento
//            auxiliar.push(maior);
//            // Move tudo de volta
//            while(!auxiliar.isEmpty()){
//                pilha.push(auxiliar.pop());
//            }
//        }
//    }
//}
//
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//public class Solucao {
//
//    // --- Sua classe Node ---
//    static class Node {
//        int value;
//        Node prev;
//        Node next;
//
//        Node(int v) {
//            this.value = v;
//        }
//    }
//
//    // --- Sua classe LinkedList ---
//    static class LinkedList {
//        private Node head;
//        private Node tail;
//        private int size;
//
//        public LinkedList() {
//            this.head = null;
//            this.tail = null;
//            this.size = 0;
//        }
//
//        public boolean isEmpty() {
//            return this.head == null;
//        }
//
//        public void addLast(int valor) {
//            Node newNode = new Node(valor);
//            if (isEmpty()) {
//                this.head = newNode;
//                this.tail = head;
//            } else {
//                this.tail.next = newNode;
//                newNode.prev = this.tail;
//                this.tail = newNode;
//            }
//            this.size += 1;
//        }
//
//        // Método remove(int index) que já está implementado e é a chave da solução
//        public int remove(int index) {
//            if (index < 0 || index >= size) {
//                throw new IndexOutOfBoundsException("Índice inválido: " + index);
//            }
//            if (index == 0) return removeFirst();
//            if (index == size - 1) return removeLast();
//
//            Node aux = this.head;
//            for (int i = 0; i < index; i++) {
//                aux = aux.next;
//            }
//            aux.prev.next = aux.next;
//            aux.next.prev = aux.prev;
//            size -= 1;
//            return aux.value;
//        }
//
//        // Métodos auxiliares para remove(int index)
//        public int removeFirst() {
//            if (isEmpty()) throw new NoSuchElementException("A lista está vazia.");
//            int v = this.head.value;
//            if (this.head.next == null) {
//                this.head = null;
//                this.tail = null;
//            } else {
//                this.head = this.head.next;
//                this.head.prev = null;
//            }
//            size -= 1;
//            return v;
//        }
//
//        public int removeLast() {
//            if (isEmpty()) throw new NoSuchElementException("A lista está vazia.");
//            int v = this.tail.value;
//            if (this.head.next == null) {
//                this.head = null;
//                this.tail = null;
//            } else {
//                this.tail = this.tail.prev;
//                this.tail.next = null;
//            }
//            size -= 1;
//            return v;
//        }
//
//        public String toString() {
//            if (isEmpty()) return "";
//
//            Node aux = this.head;
//            StringBuilder sb = new StringBuilder();
//            while (aux != null) {
//                sb.append(aux.value);
//                if (aux.next != null) {
//                    sb.append(" ");
//                }
//                aux = aux.next;
//            }
//            return sb.toString();
//        }
//    }
//
//    // --- Método main para executar o programa ---
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        LinkedList list = new LinkedList();
//
//        // 1. Lê a sequência de números e adiciona na lista
//        String[] numerosString = scanner.nextLine().split(" ");
//        for (String numStr : numerosString) {
//            list.addLast(Integer.parseInt(numStr));
//        }
//
//        // 2. Lê o índice a ser removido
//        int indexParaRemover = scanner.nextInt();
//
//        // 3. Chama o método de remoção
//        list.remove(indexParaRemover);
//
//        // 4. Imprime a lista resultante
//        System.out.println(list.toString());
//
//        scanner.close();
//    }
//}
//
//import java.util.Scanner;
//import java.util.NoSuchElementException;
//
//public class Solucao {
//
//    // --- Sua classe Node ---
//    static class Node {
//        int value;
//        Node prev;
//        Node next;
//
//        Node(int v) {
//            this.value = v;
//        }
//    }
//
//    // --- Sua classe LinkedList ---
//    static class LinkedList {
//        private Node head;
//        private Node tail;
//        private int size;
//
//        public LinkedList() {
//            this.head = null;
//            this.tail = null;
//            this.size = 0;
//        }
//
//        public boolean isEmpty() {
//            return this.head == null;
//        }
//
//        public void addLast(int valor) {
//            Node newNode = new Node(valor);
//            if (isEmpty()) {
//                this.head = newNode;
//                this.tail = head;
//            } else {
//                this.tail.next = newNode;
//                newNode.prev = this.tail;
//                this.tail = newNode;
//            }
//            this.size += 1;
//        }
//
//        /**
//         * Remove todas as ocorrências de um valor na lista.
//         */
//        public void removeAllByValue(int value) {
//            Node aux = this.head;
//            while (aux != null) {
//                Node proximo = aux.next; // Guarda o próximo nó antes de mexer no atual
//
//                if (aux.value == value) {
//                    if (aux.prev == null) { // É o primeiro elemento (head)
//                        this.head = aux.next;
//                        if (this.head != null) {
//                            this.head.prev = null;
//                        } else { // A lista ficou vazia
//                            this.tail = null;
//                        }
//                    } else if (aux.next == null) { // É o último elemento (tail)
//                        this.tail = aux.prev;
//                        this.tail.next = null;
//                    } else { // É um elemento no meio
//                        aux.prev.next = aux.next;
//                        aux.next.prev = aux.prev;
//                    }
//                    this.size--;
//                }
//                aux = proximo; // Continua para o próximo nó
//            }
//        }
//
//        public String toString() {
//            if (isEmpty()) {
//                return "vazia";
//            }
//
//            Node aux = this.head;
//            StringBuilder sb = new StringBuilder();
//            while (aux != null) {
//                sb.append(aux.value);
//                if (aux.next != null) {
//                    sb.append(" ");
//                }
//                aux = aux.next;
//            }
//            return sb.toString();
//        }
//    }
//
//    // --- Método main para executar o programa ---
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        LinkedList list = new LinkedList();
//
//        // 1. Lê a sequência de números e adiciona na lista
//        String[] numerosString = scanner.nextLine().split(" ");
//        for (String numStr : numerosString) {
//            list.addLast(Integer.parseInt(numStr));
//        }
//
//        // 2. Lê o valor a ser removido
//        int valorParaRemover = scanner.nextInt();
//
//        // 3. Chama o método de remoção
//        list.removeAllByValue(valorParaRemover);
//
//        // 4. Imprime a lista resultante
//        System.out.println(list.toString());
//
//        scanner.close();
//    }
//}
//
//        ### Pilha (Stack)
//
//Uma pilha segue a regra **LIFO (Last-In, First-Out)** e as operações são realizadas no topo. A sua implementação se assemelha a um array dinâmico, mas com acesso restrito.
//
//        * **`push()` (Adicionar no topo):** O(1)
//    * Adicionar um elemento no topo é uma operação muito rápida. No caso de uma implementação baseada em array, a única exceção é quando a capacidade do array precisa ser expandida (o que pode levar a O(n), mas é um evento raro e o custo médio ainda é O(1)).
//        * **`pop()` (Remover do topo):** O(1)
//    * Remover o elemento do topo é tão rápido quanto adicioná-lo. Você simplesmente decrementa o índice do topo.
//* **`peek()` (Verificar o topo):** O(1)
//    * Acessar o elemento no topo é instantâneo.
//
//---
//
//        ### Fila (Queue)
//
//Uma fila segue a regra **FIFO (First-In, First-Out)** e as operações ocorrem nas duas extremidades.
//
//* **`enqueue()` ou `add()` (Adicionar no final):** O(1)
//    * Se a fila for implementada com uma `LinkedList` (como no seu método `inverter`), adicionar ao final é uma operação de tempo constante, pois a cauda (`tail`) é conhecida.
//        * **`dequeue()` ou `remove()` (Remover do início):** O(1)
//    * Remover do início também é de tempo constante, pois o início (`head`) é conhecido.
//        * **`peek()` (Verificar o início):** O(1)
//    * Acessar o elemento no início é instantâneo.
//
//---
//
//        ### Array List
//
//Um `ArrayList` armazena elementos em um array, permitindo acesso rápido por índice, mas com custos mais altos para inserções e remoções no meio da lista.
//
//* **`get(index)` (Acesso por índice):** O(1)
//    * Acesso direto a qualquer elemento, já que os arrays são estruturas de memória contígua. Essa é a principal vantagem de um `ArrayList`.
//        * **`add(element)` (Adicionar no final):** O(1)
//    * Adicionar no final é rápido, a menos que o array esteja cheio e precise ser redimensionado. Nesses casos, o custo é O(n), mas o custo médio (amortizado) é O(1).
//        * **`add(index, element)` (Adicionar no meio):** O(n)
//    * Inserir no meio é caro, pois todos os elementos a partir do `index` precisam ser deslocados uma posição para a direita.
//        * **`remove(index)` (Remover):** O(n)
//    * Remover um elemento de uma posição específica é igualmente caro, pois todos os elementos após o `index` precisam ser deslocados uma posição para a esquerda.
//
//        ---
//
//        ### Linked List (Lista Ligada)
//
//Uma `LinkedList` armazena elementos em nós, com ponteiros para o próximo (e anterior, em uma lista duplamente ligada).
//
//        * **`addFirst()` ou `addLast()` (Adicionar no início/final):** O(1)
//    * Adicionar nas extremidades é muito eficiente, pois você só precisa ajustar alguns ponteiros.
//        * **`removeFirst()` ou `removeLast()` (Remover do início/final):** O(1)
//    * Similarmente, remover das extremidades é uma operação rápida de tempo constante.
//        * **`get(index)` (Acesso por índice):** O(n)
//    * Acessar um elemento por índice é o ponto fraco de uma `LinkedList`. Você precisa percorrer a lista do início (ou do final) até encontrar a posição desejada.
//        * **`add(index, element)` ou `remove(index)` (Adicionar/Remover no meio):** O(n)
//    * Embora a operação de ajuste dos ponteiros seja O(1), o tempo gasto para **encontrar** o `index` é o fator dominante, tornando o custo total O(n).
//        * **`removeByValue()` (Remover por valor):** O(n)
//    * Você precisa percorrer a lista inteira para encontrar o elemento, tornando o custo de tempo linear.

//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
//
//// Classe auxiliar para armazenar pares chave-valor
//class Pair {
//    int key;
//    String value;
//
//    public Pair(int key, String value) {
//        this.key = key;
//        this.value = value;
//    }
//
//    @Override
//    public String toString() {
//        return "<" + key + ", " + value + ">";
//    }
//}
//
//// Classe que implementa a Tabela Hash com endereçamento aberto e probing linear
//class TabelaHash {
//    private Pair[] tabela;
//    private int tamanho;
//    private int size;
//
//    public TabelaHash(int tamanho) {
//        this.tamanho = tamanho;
//        this.tabela = new Pair[tamanho];
//        this.size = 0;
//    }
//
//    private int hash(int key) {
//        return key % tamanho;
//    }
//
//    public void put(int key, String value) {
//        int sondagem = 0;
//        int index = -1;
//
//        // Procura uma posição vazia ou com a mesma chave
//        while (sondagem < tamanho) {
//            index = (hash(key) + sondagem) % tamanho;
//
//            // Se a posição estiver vazia, pode inserir aqui.
//            if (tabela[index] == null) {
//                // Se a tabela estiver cheia, não adiciona e retorna.
//                if (size == tamanho) {
//                    printTableState();
//                    return;
//                }
//                tabela[index] = new Pair(key, value);
//                size++;
//                printTableState();
//                return;
//            }
//            // Se a chave já existe, atualiza o valor.
//            else if (tabela[index].key == key) {
//                tabela[index].value = value;
//                printTableState();
//                return;
//            }
//            sondagem++;
//        }
//        // Se o loop terminar, a tabela está cheia e não houve atualização.
//        printTableState();
//    }
//
//    public void remove(int key) {
//        int sondagem = 0;
//        int index;
//
//        while (sondagem < tamanho) {
//            index = (hash(key) + sondagem) % tamanho;
//
//            // A busca para se encontrar um slot null
//            if (tabela[index] == null) {
//                break;
//            }
//
//            if (tabela[index].key == key) {
//                tabela[index] = null;
//                size--;
//                break;
//            }
//            sondagem++;
//        }
//        printTableState();
//    }
//
//    public List<Integer> getKeys() {
//        List<Integer> keys = new ArrayList<>();
//        for (int i = 0; i < tamanho; i++) {
//            if (tabela[i] != null) {
//                keys.add(tabela[i].key);
//            }
//        }
//        Collections.sort(keys);
//        return keys;
//    }
//
//    public List<String> getValues() {
//        List<String> values = new ArrayList<>();
//        for (int i = 0; i < tamanho; i++) {
//            if (tabela[i] != null) {
//                values.add(tabela[i].value);
//            }
//        }
//        Collections.sort(values);
//        return values;
//    }
//
//    public void printTableState() {
//        System.out.print("[");
//        for (int i = 0; i < tamanho; i++) {
//            System.out.print(tabela[i]);
//            if (i < tamanho - 1) {
//                System.out.print(", ");
//            }
//        }
//        System.out.println("]");
//    }
//}
//
//// Classe principal para execução
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int tamanhoTabela = scanner.nextInt();
//        TabelaHash tabelaHash = new TabelaHash(tamanhoTabela);
//        scanner.nextLine(); // Consumir a quebra de linha
//
//        String linha;
//        while (scanner.hasNextLine() && !(linha = scanner.nextLine().trim()).equals("end")) {
//            if (linha.isEmpty()) {
//                continue;
//            }
//
//            String[] partes = linha.split(" ");
//            String comando = partes[0];
//
//            switch (comando) {
//                case "put":
//                    int keyPut = Integer.parseInt(partes[1]);
//                    String valuePut = partes[2];
//                    tabelaHash.put(keyPut, valuePut);
//                    break;
//                case "remove":
//                    int keyRemove = Integer.parseInt(partes[1]);
//                    tabelaHash.remove(keyRemove);
//                    break;
//                case "keys":
//                    System.out.println(tabelaHash.getKeys().toString().replace(" ", ""));
//                    break;
//                case "values":
//                    System.out.println(tabelaHash.getValues().toString().replace(" ", ""));
//                    break;
//            }
//        }
//        scanner.close();
//    }
//}
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
//
//// Classe auxiliar para armazenar pares chave-valor
//class Pair {
//    int key;
//    String value;
//
//    public Pair(int key, String value) {
//        this.key = key;
//        this.value = value;
//    }
//
//    public int getKey() {
//        return key;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    @Override
//    public String toString() {
//        return "<" + key + ", " + value + ">";
//    }
//}
//
//// Classe que implementa a Tabela Hash com encadeamento
//class TabelaHash {
//    private ArrayList<Pair>[] tabela;
//    private int tamanho;
//
//    public TabelaHash(int tamanho) {
//        this.tamanho = tamanho;
//        this.tabela = new ArrayList[tamanho];
//        for (int i = 0; i < tamanho; i++) {
//            tabela[i] = new ArrayList<>();
//        }
//    }
//
//    private int hash(int key) {
//        return key % tamanho;
//    }
//
//    public void put(int key, String value) {
//        int index = hash(key);
//        ArrayList<Pair> lista = tabela[index];
//
//        for (int i = 0; i < lista.size(); i++) {
//            if (lista.get(i).getKey() == key) {
//                lista.set(i, new Pair(key, value));
//                printTableState();
//                return;
//            }
//        }
//
//        lista.add(new Pair(key, value));
//        printTableState();
//    }
//
//    public void remove(int key) {
//        int index = hash(key);
//        ArrayList<Pair> lista = tabela[index];
//
//        for (int i = 0; i < lista.size(); i++) {
//            if (lista.get(i).getKey() == key) {
//                lista.remove(i);
//                printTableState();
//                return;
//            }
//        }
//    }
//
//    public List<Integer> getKeys() {
//        List<Integer> keys = new ArrayList<>();
//        for (ArrayList<Pair> lista : tabela) {
//            for (Pair par : lista) {
//                keys.add(par.getKey());
//            }
//        }
//        Collections.sort(keys);
//        return keys;
//    }
//
//    public List<String> getValues() {
//        List<String> values = new ArrayList<>();
//        for (ArrayList<Pair> lista : tabela) {
//            for (Pair par : lista) {
//                values.add(par.getValue());
//            }
//        }
//        Collections.sort(values);
//        return values;
//    }
//
//    public void printTableState() {
//        System.out.print("[");
//        for (int i = 0; i < tamanho; i++) {
//            System.out.print(tabela[i]);
//            if (i < tamanho - 1) {
//                System.out.print(", ");
//            }
//        }
//        System.out.println("]");
//    }
//}
//
//// Classe principal para execução
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int tamanhoTabela = scanner.nextInt();
//        TabelaHash tabelaHash = new TabelaHash(tamanhoTabela);
//        scanner.nextLine(); // Consumir a quebra de linha
//
//        String linha;
//        while (scanner.hasNextLine() && !(linha = scanner.nextLine().trim()).equals("end")) {
//            if (linha.isEmpty()) {
//                continue;
//            }
//
//            String[] partes = linha.split(" ");
//            String comando = partes[0];
//
//            switch (comando) {
//                case "put":
//                    int keyPut = Integer.parseInt(partes[1]);
//                    String valuePut = partes[2];
//                    tabelaHash.put(keyPut, valuePut);
//                    break;
//                case "remove":
//                    int keyRemove = Integer.parseInt(partes[1]);
//                    tabelaHash.remove(keyRemove);
//                    break;
//                case "keys":
//                    System.out.println(tabelaHash.getKeys().toString().replace(" ", ""));
//                    break;
//                case "values":
//                    System.out.println(tabelaHash.getValues().toString().replace(" ", ""));
//                    break;
//            }
//        }
//        scanner.close();
//    }
//}
//
//import java.util.Scanner;
//import java.util.Arrays;
//
//// Classe que implementa a lógica do HashSet
//class HashSet {
//    private Integer[] set;
//    private int capacity;
//    private int size;
//
//    public HashSet(int capacity) {
//        this.capacity = capacity;
//        this.set = new Integer[capacity];
//        this.size = 0;
//    }
//
//    private int hash(int key) {
//        return key % capacity;
//    }
//
//    public void put(int value) {
//        int sondagem = 0;
//        int index;
//
//        // Se o conjunto estiver cheio, não podemos adicionar.
//        if (isFull()) {
//            printSetState();
//            return;
//        }
//
//        while (sondagem < capacity) {
//            index = (hash(value) + sondagem) % capacity;
//
//            // Se o valor já existe, não fazemos nada.
//            if (set[index] != null && set[index] == value) {
//                printSetState();
//                return;
//            }
//
//            // Se a posição está livre, adicionamos o valor.
//            if (set[index] == null) {
//                set[index] = value;
//                size++;
//                printSetState();
//                return;
//            }
//
//            sondagem++;
//        }
//    }
//
//    public void remove(int value) {
//        int sondagem = 0;
//        int index;
//
//        while (sondagem < capacity) {
//            index = (hash(value) + sondagem) % capacity;
//
//            // A busca para se encontrar um slot null
//            if (set[index] == null) {
//                break;
//            }
//
//            if (set[index] == value) {
//                set[index] = null;
//                size--;
//                break;
//            }
//            sondagem++;
//        }
//        printSetState();
//    }
//
//    public boolean contains(int value) {
//        int sondagem = 0;
//        int index;
//
//        while (sondagem < capacity) {
//            index = (hash(value) + sondagem) % capacity;
//
//            if (set[index] == null) {
//                return false;
//            }
//
//            if (set[index] == value) {
//                return true;
//            }
//
//            sondagem++;
//        }
//        return false;
//    }
//
//    private boolean isFull() {
//        return size == capacity;
//    }
//
//    public void printSetState() {
//        System.out.println(Arrays.toString(set).replace(" ", ""));
//    }
//}
//
//// Classe principal para execução
//public class Solution {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int capacity = scanner.nextInt();
//        HashSet hashSet = new HashSet(capacity);
//        scanner.nextLine(); // Consumir a quebra de linha
//
//        String linha;
//        while (scanner.hasNextLine() && !(linha = scanner.nextLine().trim()).equals("end")) {
//            if (linha.isEmpty()) {
//                continue;
//            }
//
//            String[] partes = linha.split(" ");
//            String comando = partes[0];
//
//            switch (comando) {
//                case "put":
//                    int valuePut = Integer.parseInt(partes[1]);
//                    hashSet.put(valuePut);
//                    break;
//                case "remove":
//                    int valueRemove = Integer.parseInt(partes[1]);
//                    hashSet.remove(valueRemove);
//                    break;
//                case "contains":
//                    int valueContains = Integer.parseInt(partes[1]);
//                    System.out.println(hashSet.contains(valueContains));
//                    break;
//            }
//        }
//        scanner.close();
//    }
//}
//
//import java.util.Scanner;
//import java.util.Stack;
//
//public class Solution {
//
//    public static void main(String[] args) {
//        // Objeto para ler a entrada do usuário
//        Scanner scanner = new Scanner(System.in);
//
//        // Lê a linha inteira da expressão matemática
//        String expressao = scanner.nextLine();
//
//        // Fecha o scanner para evitar vazamento de recursos
//        scanner.close();
//
//        // A pilha é a estrutura de dados central para resolver o problema.
//        // Ela armazenará os números (operandos) da equação.
//        Stack<Double> pilha = new Stack<>();
//
//        // Divide a expressão em um array de strings, usando o espaço como delimitador.
//        // Ex: "5 1 2 + 4 * +" se torna um array com ["5", "1", "2", "+", "4", "*", "+"]
//        String[] elementos = expressao.split(" ");
//
//        // Percorre cada elemento do array
//        for (String elemento : elementos) {
//
//            // O bloco try-catch é usado para diferenciar números de operadores.
//            // O programa tenta converter o elemento para um número.
//            try {
//                // Se a conversão for bem-sucedida, o elemento é um número.
//                double numero = Double.parseDouble(elemento);
//
//                // Coloca o número no topo da pilha.
//                pilha.push(numero);
//
//            } catch (NumberFormatException e) {
//                // Se a conversão falha, significa que o elemento é um operador.
//
//                // Pega o último número que foi colocado na pilha (segundo operando).
//                double operando2 = pilha.pop();
//
//                // Pega o penúltimo número que foi colocado na pilha (primeiro operando).
//                // A ordem é importante para operações como a subtração e a divisão.
//                double operando1 = pilha.pop();
//
//                // Usa a estrutura 'switch' para decidir qual operação fazer.
//                switch (elemento) {
//                    case "+":
//                        // Realiza a soma e coloca o resultado de volta na pilha.
//                        pilha.push(operando1 + operando2);
//                        break;
//                    case "-":
//                        // Realiza a subtração e coloca o resultado de volta na pilha.
//                        pilha.push(operando1 - operando2);
//                        break;
//                    case "*":
//                        // Realiza a multiplicação e coloca o resultado de volta na pilha.
//                        pilha.push(operando1 * operando2);
//                        break;
//                    case "/":
//                        // Realiza a divisão e coloca o resultado de volta na pilha.
//                        pilha.push(operando1 / operando2);
//                        break;
//                }
//            }
//        }
//
//        // Após o loop, a pilha conterá apenas um número: o resultado final.
//        // Pega este resultado e o imprime na saída padrão.
//        System.out.println(pilha.pop());
//    }
//}