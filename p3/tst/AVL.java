/**
 * Implementação da Árvore AVL (Adelson-Velsky e Landis)
 * Uma árvore AVL é uma árvore binária de busca auto-balanceada, onde a diferença 
 * de altura entre subárvores esquerda e direita de qualquer nó não pode ser maior que 1.
 */
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class AVL {
    // Raiz da árvore AVL
    private Node root;
    // Contador de elementos na árvore
    private int size;

    /**
     * Construtor da árvore AVL
     * Inicializa com tamanho -1 (será incrementado para 0 na primeira adição)
     */
    public AVL() {
        this.size = -1;
    }

    /**
     * Verifica se a árvore está vazia
     * @return true se a árvore não possui elementos, false caso contrário
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Implementação iterativa da adição de um elemento em uma árvore AVL.
     * Este método insere um novo valor na árvore e, em seguida, verifica e corrige
     * qualquer desbalanceamento que possa ter ocorrido.
     * 
     * @param element o valor a ser adicionado na árvore.
     */
    public void add(int element) {
        // Incrementa o contador de elementos
        this.size += 1;
        
        // Se a árvore estiver vazia, o novo elemento se torna a raiz
        if (isEmpty()) this.root = new Node(element);
        else {
            // Inicia a busca pelo local de inserção a partir da raiz
            Node aux = this.root;

            while (aux != null) {
                // Se o elemento for menor que o valor do nó atual, vai para a esquerda
                if (element < aux.value) {
                    // Se não há filho à esquerda, insere o novo nó aqui
                    if (aux.left == null) {
                        Node newNode = new Node(element);
                        aux.left = newNode;       // Conecta o novo nó como filho esquerdo
                        newNode.parent = aux;     // Define o pai do novo nó
                        
                        // Verifica se a árvore ficou desbalanceada após a inserção
                        Node unbalanced = checkBalance(newNode);
                        // Se encontrou um nó desbalanceado, realiza a rotação adequada
                        if (unbalanced != null) callBestRotation(unbalanced);

                        return;
                    }
                    // Continua a busca pela subárvore esquerda
                    aux = aux.left;
                } else {
                    // Se o elemento for maior ou igual, vai para a direita
                    // Se não há filho à direita, insere o novo nó aqui
                    if (aux.right == null) {
                        Node newNode = new Node(element);
                        aux.right = newNode;      // Conecta o novo nó como filho direito
                        newNode.parent = aux;     // Define o pai do novo nó
                        
                        // Verifica se a árvore ficou desbalanceada após a inserção
                        Node unbalanced = checkBalance(newNode);
                        // Se encontrou um nó desbalanceado, realiza a rotação adequada
                        if (unbalanced != null) callBestRotation(unbalanced);

                        return;
                    }
                    // Continua a busca pela subárvore direita
                    aux = aux.right;
                }
            }
        }
    }

    /**
     * Checa do node passado até a raíz da árvore se existe um desbalanceamento.
     * Este método verifica cada nó no caminho do nó informado até a raiz,
     * procurando por qualquer nó que não esteja balanceado conforme a propriedade
     * da árvore AVL (diferença de altura entre subárvores ≤ 1).
     * 
     * @param node node de onde se começa a checagem
     * @return o primeiro node desbalanceado encontrado ou null caso a árvore esteja balanceada
     */
    public Node checkBalance(Node node) {
        // Começa a verificação a partir do nó informado
        Node aux = node;

        // Percorre o caminho do nó até a raiz
        while (aux != null) {
            // Se o nó não estiver balanceado (diferença de altura > 1), retorna-o
            if (!aux.isBalanced()) {
                return aux;
            } 
            // Se não for a raiz, verifica o nó pai
            else if (aux.parent != null) {
                aux = aux.parent;
            } 
            // Se chegou à raiz, encerra a verificação
            else {
                break;
            }
        }

        // Se não encontrou nenhum nó desbalanceado, retorna null
        return null;
    }

    /**
     * Implementação recursiva do método de adição.
     * Esta é uma alternativa à implementação iterativa do método add().
     * 
     * @param element elemento a ser adicionado na árvore.
     */
    public void recursiveAdd(int element) {
        // Se a árvore estiver vazia, cria um novo nó como raiz
        if (isEmpty())
            this.root = new Node(element);
        else {
            // Senão, inicia a adição recursiva a partir da raiz
            Node aux = this.root;
            recursiveAdd(aux, element);
        }
        // Incrementa o contador de elementos na árvore
        this.size += 1;
    }

    /**
     * Método auxiliar para implementação recursiva do método de adição.
     * Este método percorre a árvore recursivamente para encontrar a posição
     * correta para inserção e rebalanceia a árvore se necessário.
     * 
     * @param node o nó atual da recursão (começa pela raiz).
     * @param element elemento a ser adicionado.
     */
    public void recursiveAdd(Node node, int element) {
        // Se o elemento for menor que o valor do nó atual, vai para a esquerda
        if (element < node.value) {
            // Se não houver filho à esquerda, insere o novo nó aqui
            if (node.left == null) {
                Node newNode = new Node(element);
                node.left = newNode;      // Conecta o novo nó como filho esquerdo
                newNode.parent = node;    // Define o pai do novo nó
                
                // Verifica e corrige o balanceamento a partir do nó atual
                rebalance(node);
                return;
            }
            // Continua a busca recursivamente pela subárvore esquerda
            recursiveAdd(node.left, element);
            // Rebalanceia ao voltar da recursão (backtracking)
            rebalance(node);
        } else {
            // Se o elemento for maior ou igual, vai para a direita
            // Se não houver filho à direita, insere o novo nó aqui
            if (node.right == null) {
                Node newNode = new Node(element);
                node.right = newNode;     // Conecta o novo nó como filho direito
                newNode.parent = node;    // Define o pai do novo nó
                
                // Verifica e corrige o balanceamento a partir do nó atual
                rebalance(node);
                return;
            }
            // Continua a busca recursivamente pela subárvore direita
            recursiveAdd(node.right, element);
            // Rebalanceia ao voltar da recursão (backtracking)
            rebalance(node);
        }
    }

    /**
     * Método privado para verificar e corrigir o balanceamento de um nó.
     * Se o fator de balanceamento for maior que 1 ou menor que -1,
     * aplica a rotação apropriada para corrigir o desbalanceamento.
     * 
     * @param node o nó a ser verificado e potencialmente rebalanceado
     */
    private void rebalance(Node node) {
        // Calcula o fator de balanceamento (diferença de altura entre subárvores)
        int balance = node.balance();

        // Se o fator de balanceamento exceder 1 em qualquer direção, a árvore está desbalanceada
        if (Math.abs(balance) > 1) {
            callBestRotation(node);
        }
    }


    /**
     * Escolhe e executa o melhor caso de rotação a se fazer analisando
     * o nó do qual a rotação deve partir.
     * 
     * Este método identifica qual dos quatro casos de rotação da árvore AVL deve ser aplicado:
     * 1. Rotação simples à direita (caso Left-Left)
     * 2. Rotação simples à esquerda (caso Right-Right)
     * 3. Rotação dupla esquerda-direita (caso Left-Right)
     * 4. Rotação dupla direita-esquerda (caso Right-Left)
     * 
     * @param unbalanced nó do qual a rotação deve partir
     */
    public void callBestRotation(Node unbalanced) {
        Node x = unbalanced;

        // Caso o desbalanceamento esteja para a esquerda (fator de balanceamento > 1)
        if (x.isLeftPending()) {
            Node y = x.left;

            // Caso Left-Left: Rotação simples à direita
            if (y.left != null) rotateRight(x);
            // Caso Left-Right: Rotação dupla (primeiro à esquerda no filho, depois à direita no pai)
            else {
                rotateLeft(y); rotateRight(x);
            }
        } 
        // Caso o desbalanceamento esteja para a direita (fator de balanceamento < -1)
        else {
            Node y = x.right;

            // Caso Right-Right: Rotação simples à esquerda
            if (y.right != null) rotateLeft(x);
            // Caso Right-Left: Rotação dupla (primeiro à direita no filho, depois à esquerda no pai)
            else {
                rotateRight(y); rotateLeft(x);
            }
        }
    }

    /**
     * Rotaciona o nó à direita.
     * 
     * Implementação da rotação simples à direita (caso Left-Left):
     * 1. O filho esquerdo do nó se torna a nova raiz da subárvore
     * 2. O filho direito da nova raiz se torna o filho esquerdo do nó original
     * 3. O nó original se torna o filho direito da nova raiz
     * 4. As referências dos pais são ajustadas adequadamente
     * 
     * @param node nó a partir de onde a rotação ocorre
     */
    public void rotateRight(Node node) {
        // O filho esquerdo será a nova raiz da subárvore
        Node newRoot = node.left;
        // O pai da nova raiz será o mesmo pai do nó original
        newRoot.parent = node.parent;

        // O filho direito da nova raiz se torna o filho esquerdo do nó original
        node.left = newRoot.right;
        // O nó original se torna o filho direito da nova raiz
        newRoot.right = node;
        // Atualiza o pai do nó original para a nova raiz
        node.parent = newRoot;

        // Se o nó original tinha um pai, atualiza a referência do pai para apontar para a nova raiz
        if (newRoot.parent != null) {
            if (newRoot.parent.left == node)
                newRoot.parent.left = newRoot;
            else
                newRoot.parent.right = newRoot;
        } 
        // Se o nó original era a raiz da árvore, a nova raiz se torna a raiz da árvore
        else
            this.root = newRoot;
    }

    /**
     * Rotaciona o nó à esquerda.
     * 
     * Implementação da rotação simples à esquerda (caso Right-Right):
     * 1. O filho direito do nó se torna a nova raiz da subárvore
     * 2. O filho esquerdo da nova raiz se torna o filho direito do nó original
     * 3. O nó original se torna o filho esquerdo da nova raiz
     * 4. As referências dos pais são ajustadas adequadamente
     * 
     * @param node nó a partir de onde a rotação ocorre
     */
    public void rotateLeft(Node node) {
        // O filho direito será a nova raiz da subárvore
        Node newRoot = node.right;
        // O pai da nova raiz será o mesmo pai do nó original
        newRoot.parent = node.parent;

        // O filho esquerdo da nova raiz se torna o filho direito do nó original
        node.right = newRoot.left;
        // O nó original se torna o filho esquerdo da nova raiz
        newRoot.left = node;
        // Atualiza o pai do nó original para a nova raiz
        node.parent = newRoot;

        // Se o nó original tinha um pai, atualiza a referência do pai para apontar para a nova raiz
        if (newRoot.parent != null) {
            if (newRoot.parent.right == node)
                newRoot.parent.right = newRoot;
            else
                newRoot.parent.left = newRoot;
        } 
        // Se o nó original era a raiz da árvore, a nova raiz se torna a raiz da árvore
        else
            this.root = newRoot;
    }

    /**
     * Percorre a árvore em largura (Breadth-First Search).
     * 
     * Este método visita todos os nós da árvore por níveis, começando pela raiz,
     * depois todos os nós no segundo nível, e assim por diante.
     * Usa uma fila para implementar a travessia em largura.
     * 
     * @return Uma lista com os elementos percorridos em largura.
     */
    public ArrayList<Integer> bfs() {
        ArrayList<Integer> list = new ArrayList<>(); // Lista para armazenar os valores dos nós visitados
        Deque<Node> queue = new LinkedList<>();      // Fila para controlar a ordem de visita dos nós

        // Se a árvore não estiver vazia
        if (!isEmpty()) {
            // Inicia pela raiz
            queue.addLast(this.root);
            
            // Enquanto houver nós na fila
            while (!queue.isEmpty()) {
                // Remove o primeiro nó da fila
                Node current = queue.removeFirst();

                // Adiciona o valor do nó atual à lista de resultados
                list.add(current.value);

                // Adiciona o filho esquerdo à fila (se existir)
                if (current.left != null)
                    queue.addLast(current.left);
                    
                // Adiciona o filho direito à fila (se existir)
                if (current.right != null)
                    queue.addLast(current.right);
            }
        }
        return list;
    }

    /**
     * Retorna o número de elementos na árvore.
     * 
     * @return o tamanho da árvore (quantidade de nós).
     */
    public int size() {
        return this.size;
    }
}

/**
 * Classe que representa um nó na árvore AVL.
 * Cada nó contém um valor, referências para seus filhos (esquerdo e direito),
 * seu pai, e uma altura que é usada para verificar o balanceamento.
 */
class Node {
    // O valor armazenado no nó
    int value;
    // A altura do nó na árvore (usada para cálculos de balanceamento)
    int height;
    // Referência para o filho esquerdo
    Node left;
    // Referência para o filho direito
    Node right;
    // Referência para o nó pai
    Node parent;

    /**
     * Construtor do nó.
     * Inicializa um novo nó com o valor fornecido e altura zero.
     * 
     * @param v o valor a ser armazenado no nó
     */
    Node(int v) {
        this.height = 0;     // Inicialmente, a altura é zero (folha)
        this.value = v;      // Armazena o valor
    }

    /**
     * Verifica se o nó tem apenas um filho esquerdo.
     * 
     * @return true se o nó tem apenas filho esquerdo, false caso contrário
     */
    public boolean hasOnlyLeftChild() {
        return (this.left != null && this.right == null);
    }

    /**
     * Verifica se o nó tem apenas um filho direito.
     * 
     * @return true se o nó tem apenas filho direito, false caso contrário
     */
    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }

    /**
     * Verifica se o nó é uma folha (não tem filhos).
     * 
     * @return true se o nó não tem filhos, false caso contrário
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    /**
     * Calcula recursivamente a altura do nó.
     * A altura de uma folha é 0. A altura de um nó interno é 1 + a altura máxima de seus filhos.
     * 
     * @return a altura do nó na árvore
     */
    public int height() {
        // Se o nó é uma folha, sua altura é 0
        if (this.left == null && this.right == null)
            return 0;
        // Se só tem filho direito, a altura é 1 + a altura do filho direito
        else if (this.left == null) {
            return 1 + this.right.height();
        } 
        // Se só tem filho esquerdo, a altura é 1 + a altura do filho esquerdo
        else if (this.right == null) {
            return 1 + this.left.height();
        } 
        // Se tem ambos os filhos, a altura é 1 + o máximo das alturas dos filhos
        else {
            return 1 + max(this.left.height(), this.right.height());
        }
    }

    /**
     * Retorna o maior valor entre dois inteiros.
     * Auxiliar para o cálculo da altura.
     * 
     * @param height1 primeiro valor a ser comparado
     * @param height2 segundo valor a ser comparado
     * @return o maior valor entre os dois
     */
    private int max(int height1, int height2) {
        if (height1 >= height2)
            return height1;
        return height2;
    }

    /**
     * Calcula o fator de balanceamento do nó.
     * O fator de balanceamento é definido como (altura da subárvore esquerda - altura da subárvore direita).
     * Em uma árvore AVL balanceada, este valor deve estar entre -1 e 1 para todos os nós.
     * 
     * @return o fator de balanceamento do nó
     */
    public int balance() {
        // Calcula a altura da subárvore esquerda (-1 se não existir)
        int leftHeight = this.left == null ? -1 : this.left.height();
        // Calcula a altura da subárvore direita (-1 se não existir)
        int rightHeight = this.right == null ? -1 : this.right.height();
        // Retorna a diferença (fator de balanceamento)
        return leftHeight - rightHeight;
    }

    /**
     * Verifica se o nó está desbalanceado para a esquerda.
     * Ocorre quando a altura da subárvore esquerda é pelo menos 1 maior que a direita.
     * 
     * @return true se o nó estiver pendendo para a esquerda, false caso contrário
     */
    public boolean isLeftPending() {
        // Calcula a altura da subárvore esquerda (-1 se não existir)
        int leftHeight = this.left == null ? -1 : this.left.height();
        // Calcula a altura da subárvore direita (-1 se não existir)
        int rightHeight = this.right == null ? -1 : this.right.height();
        // Verifica se a diferença é maior ou igual a 1
        return leftHeight - rightHeight >= 1;
    }

    /**
     * Verifica se o nó está desbalanceado para a direita.
     * Ocorre quando a altura da subárvore direita é pelo menos 1 maior que a esquerda.
     * 
     * @return true se o nó estiver pendendo para a direita, false caso contrário
     */
    public boolean isRightPending() {
        // Calcula a altura da subárvore esquerda (-1 se não existir)
        int leftHeight = this.left == null ? -1 : this.left.height();
        // Calcula a altura da subárvore direita (-1 se não existir)
        int rightHeight = this.right == null ? -1 : this.right.height();
        // Verifica se a diferença é menor ou igual a -1
        return leftHeight - rightHeight <= -1;
    }

    /**
     * Verifica se o nó está balanceado segundo a propriedade AVL.
     * Um nó está balanceado quando a diferença de altura entre suas subárvores
     * esquerda e direita está entre -1 e 1 (inclusive).
     * 
     * @return true se o nó estiver balanceado, false caso contrário
     */
    public boolean isBalanced() {
        // Calcula a altura da subárvore esquerda (-1 se não existir)
        int leftHeight = this.left == null ? -1 : this.left.height();
        // Calcula a altura da subárvore direita (-1 se não existir)
        int rightHeight = this.right == null ? -1 : this.right.height();
        // Verifica se a diferença está entre -1 e 1
        return leftHeight - rightHeight >= -1 && leftHeight - rightHeight <= 1;
    }
}