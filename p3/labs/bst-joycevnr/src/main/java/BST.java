
public class BST { //Árvore Binária de Pesquisa (BST)

    private Node root; // A raiz da árvore.
    private int size;  // O número de nós na árvore.

    // Verifica se a árvore não possui nós.
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Implementação iterativa da adição de um elemento em uma árvore binária de pequisa.
     * @param element o valor a ser adicionado na árvore.
     */
    public void add(int element) {
        this.size += 1; // 1. Atualiza o tamanho da árvore.
        
        // 2. Se a árvore estiver vazia, o novo elemento se torna a raiz.
        if (isEmpty()) {
            this.root = new Node(element);
        } else {
            Node aux = this.root;
            while (aux != null) {
                // 3. Se o elemento for menor, vá para a esquerda.
                if (element < aux.value) {
                    // 4. Se a esquerda estiver vazia, insira aqui.
                    if (aux.left == null) { 
                        Node newNode = new Node(element);
                        aux.left = newNode;
                        newNode.parent = aux;
                        return; // Encerra após inserir.
                    }
                    aux = aux.left;
                // 5. Se o elemento for maior ou igual, vá para a direita.
                } else {
                    // 6. Se a direita estiver vazia, insira aqui.
                    if (aux.right == null) { 
                        Node newNode = new Node(element);
                        aux.right = newNode;
                        newNode.parent = aux;
                        return; // Encerra após inserir.
                    }
                    aux = aux.right;
                }
            }
        }
    }
    
    /**
     * Busca o nó cujo valor é igual ao passado como parâmetro. Essa é a implementação 
     * iterativa clássica da busca binária em uma árvore binária de pesquisa.
     * @param element O elemento a ser procurado.
     * @return O nó contendo o elemento procurado. O método retorna null caso
     * o elemento não esteja presente na árvore.
     */
    public Node search(int element) {
        Node aux = this.root;

        // 1. Comece pela raiz e desça até encontrar o elemento ou chegar em null.
        while (aux != null) {
            // 2. Se encontrou, retorne o nó.
            if (aux.value == element) return aux;
            // 3. Se o elemento for menor, procure na subárvore esquerda.
            if (element < aux.value) aux = aux.left;
            // 4. Se for maior, procure na subárvore direita.
            if (element > aux.value) aux = aux.right;
        }

        // 5. Se o loop terminar, o elemento não existe.
        return null;
    }

    /**
     * Remove um elemento (método público).
     */
    public void remove(int value) {
        // 1. Encontra o nó a ser removido.
        Node toRemove = search(value);
        // 2. Se o nó existir, chama a lógica de remoção e atualiza o tamanho.
        if (toRemove != null) {
            remove(toRemove);
            this.size--;
        }
    }
    
    /**
     * Lógica de remoção (método privado).
     */
    private void remove(Node toRemove) {
        // Caso 1: O nó é uma folha (sem filhos).
        if (toRemove.isLeaf()) {
            // Se for a raiz, a árvore fica vazia.
            if (toRemove == this.root) this.root = null;
            // Senão, simplesmente remove a referência do pai para este nó.
            else if (toRemove.value < toRemove.parent.value) toRemove.parent.left = null;
            else toRemove.parent.right = null;

        // Caso 2: O nó tem apenas um filho (à esquerda).
        } else if (toRemove.hasOnlyLeftChild()) {
            // Se for a raiz, o filho se torna a nova raiz.
            if (toRemove == this.root) {
                this.root = toRemove.left;
                this.root.parent = null;
            // Senão, o avô "adota" o neto.
            } else {
                toRemove.left.parent = toRemove.parent;
                if (toRemove.value < toRemove.parent.value) toRemove.parent.left = toRemove.left;
                else toRemove.parent.right = toRemove.left;
            }
        
        // Caso 3: O nó tem apenas um filho (à direita).
        } else if (toRemove.hasOnlyRightChild()) {
            // Se for a raiz, o filho se torna a nova raiz.
            if (toRemove == this.root) {
                this.root = toRemove.right;
                this.root.parent = null;
            // Senão, o avô "adota" o neto.
            } else {
                toRemove.right.parent = toRemove.parent;
                if (toRemove.value < toRemove.parent.value) toRemove.parent.left = toRemove.right;
                else toRemove.parent.right = toRemove.right;
            }

        // Caso 4: O nó tem dois filhos.
        } else {
            // 1. Encontra o sucessor (o menor nó da subárvore direita).
            Node sucessor = sucessor(toRemove);
            // 2. Copia o valor do sucessor para o nó atual.
            toRemove.value = sucessor.value;
            // 3. Remove o nó sucessor (que agora é um caso mais simples).
            remove(sucessor);
        }
    }

    /**
     * Retorna a altura da árvore.
     */
    public int height() {
        // Chama a função recursiva a partir da raiz.
        return height(this.root);
    }
    private int height(Node node) {
        // Caso base: uma árvore nula tem altura -1.
        if (node == null) return -1;
        // Altura é 1 + a maior altura entre as subárvores.
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Retorna o número de folhas da árvore.
     */
    public int contaFolhas() {
        return contaFolhas(this.root);
    }
    private int contaFolhas(Node node) {
        // Caso base 1: subárvore nula não tem folhas.
        if (node == null) return 0;
        // Caso base 2: se o nó é uma folha, conte 1.
        if (node.isLeaf()) return 1;
        // Soma as folhas da esquerda e da direita.
        return contaFolhas(node.left) + contaFolhas(node.right);
    }

    /**
     * Verifica se duas árvores são idênticas.
     */
    public boolean equals(BST outra) {
        // Otimização: se os tamanhos são diferentes, não podem ser iguais.
        if (this.size != outra.size()) return false;
        // Chama a verificação recursiva.
        return equals(this.root, outra.root);
    }
    private boolean equals(Node node1, Node node2) {
        // Se ambos são nulos, são iguais neste ponto.
        if (node1 == null && node2 == null) return true;
        // Se apenas um é nulo, são diferentes.
        if (node1 == null || node2 == null) return false;
        // Se os valores são diferentes, as árvores são diferentes.
        if (node1.value != node2.value) return false;
        // Verifica se as subárvores esquerda E direita também são iguais.
        return equals(node1.left, node2.left) && equals(node1.right, node2.right);
    }
    
    /**
     * Encontra o nó com o menor valor.
     */
    public Node min() {
        if (isEmpty()) return null;
        return min(this.root);
    }
    private Node min(Node node) {
        // O menor valor é o nó mais à esquerda da árvore.
        if (node.left == null) return node;
        return min(node.left);
    }
    
    /**
     * Encontra o sucessor de um nó.
     */
    public Node sucessor(Node node) {
        // Se houver subárvore direita, o sucessor é o mínimo de lá.
        if (node.right != null) {
            return min(node.right);
        }
        // Senão, suba na árvore até encontrar um ancestral que seja maior.
        Node aux = node.parent;
        while (aux != null && aux.value < node.value) {
            aux = aux.parent;
        }
        return aux;
    }

    // Retorna o tamanho.
    public int size() {
        return this.size;
    }
}

/**
 * Classe que representa um nó da árvore.
 */
class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    Node(int v) {
        this.value = v;
    }

    // Verifica se o nó é uma folha.
    boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
    // Verifica se tem apenas o filho esquerdo.
    boolean hasOnlyLeftChild() {
        return this.left != null && this.right == null;
    }

    // Verifica se tem apenas o filho direito.
    boolean hasOnlyRightChild() {
        return this.left == null && this.right != null;
    }
}