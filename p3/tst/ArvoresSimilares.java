import java.util.Scanner;

class ArvoresSimilares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
 
        BST arvore1 = new BST();
        for (int i = 0; i < n; i++) {
            int elemento = Integer.parseInt(sc.nextLine());
            arvore1.add(elemento);
        }
        
        BST arvore2 = new BST();
        for (int i = 0; i < n; i++) {
            int elemento = Integer.parseInt(sc.nextLine());
            arvore2.add(elemento);
        }
        
        if (arvore1.isSimilar(arvore2)) {
            System.out.println("Arvores similares.");
        } else {
            System.out.println("Arvores com estruturas diferentes.");
        }
        
        sc.close();
    }

    public static class BST {
        Node root;
        private int size;

        public boolean isEmpty() {
            return this.root == null;
        }

        public void add(int element) {
            this.size++;
            if (isEmpty())
                this.root = new Node(element);
            else {
                Node current = this.root;
                while (current != null) {
                    if (element < current.value) {
                        if (current.left == null) {
                            Node newNode = new Node(element);
                            current.left = newNode;
                            newNode.parent = current;
                            return;
                        }
                        current = current.left;
                    } else {
                        if (current.right == null) {
                            Node newNode = new Node(element);
                            current.right = newNode;
                            newNode.parent = current;
                            return;
                        }
                        current = current.right;
                    }
                }
            }
        }

        /**
         * Verifica se duas árvores são similares (mesma estrutura).
         * Duas árvores são similares quando têm a mesma estrutura de nós,
         * independente dos valores armazenados.
         */
        public boolean isSimilar(BST outra) {
            return isSimilar(this.root, outra.root);
        }
        
        private boolean isSimilar(Node node1, Node node2) {
            if (node1 == null && node2 == null) return true;
            if (node1 == null || node2 == null) return false;
            return isSimilar(node1.left, node2.left) && 
                   isSimilar(node1.right, node2.right);
        }
    }

    public static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int v) {
            value = v;
        }
    }
}