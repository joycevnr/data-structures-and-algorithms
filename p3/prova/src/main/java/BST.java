
public class BST {

    private Node root;
    private int size;

    public boolean isEmpty() {
        return this.root == null;
    }

    public void add(int[] v) {
        for (int i = 0; i < v.length; i++)
            this.add(v[i]);
    }

    public int contaMenores(int k) {
        return contaMenores(this.root, k);
    }

    private int contaMenores(Node node, int k) {
        if (node == null)
            return 0;
        if (node.value < k) {
            return 1 + contaMenores(node.left, k) + contaMenores(node.right, k);
        } else {
            return contaMenores(node.left, k);
        }
    }

    /**
     * Implementação iterativa da adição de um elemento em uma árvore binária de
     * pequisa.
     * 
     * @param element o valor a ser adicionado na árvore.
     */
    public void add(int element) {
        this.size += 1;
        if (isEmpty())
            this.root = new Node(element);
        else {

            Node aux = this.root;

            while (aux != null) {

                if (element < aux.value) {
                    if (aux.left == null) {
                        Node newNode = new Node(element);
                        aux.left = newNode;
                        newNode.parent = aux;
                        return;
                    }

                    aux = aux.left;
                } else {
                    if (aux.right == null) {
                        Node newNode = new Node(element);
                        aux.right = newNode;
                        newNode.parent = aux;
                        return;
                    }

                    aux = aux.right;
                }
            }
        }

    }

    /**
     * Retorna o nó que contém o valor máximo da árvore. Implementação recursiva.
     * 
     * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver
     *         vazia.
     */
    public Node min() {
        if (isEmpty())
            return null;
        return min(this.root);
    }

    /**
     * Retorna o nó que contém o valor máximo da árvore cuja raiz é passada como
     * parâmetro. Implementação recursiva.
     * 
     * @param a raiz da árvore.
     * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver
     *         vazia.
     */
    private Node min(Node node) {
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    /**
     * Retorna o nó que contém o valor máximo da árvore. Implementação iterativa.
     * 
     * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver
     *         vazia.
     */
    public Node max() {
        if (isEmpty())
            return null;

        Node node = this.root;
        while (node.right != null)
            node = node.right;

        return node;
    }

    /**
     * Retorna o nó que contém o valor máximo da árvore cuja raiz é passada como
     * parâmetro. Implementação recursiva.
     * 
     * @param raiz da árvore.
     * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver
     *         vazia.
     */

    private Node max(Node node) {
        if (node.right == null)
            return node;
        else
            return max(node.right);
    }

    /**
     * Busca o nó cujo valor é igual ao passado como parâmetro. Essa é a
     * implementação
     * iterativa clássica da busca binária em uma árvore binária de pesquisa.
     * 
     * @param element O elemento a ser procurado.
     * @return O nó contendo o elemento procurado. O método retorna null caso
     *         o elemento não esteja presente na árvore.
     */
    public Node search(int element) {
        return null;
    }

}

class Node {

    int value;
    Node left;
    Node right;
    Node parent;

    Node(int v) {
        this.value = v;
    }

    public String toString() {
        return this.value + "";
    }

    public boolean hasOnlyLeftChild() {
        return (this.left != null && this.right == null);
    }

    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

}
