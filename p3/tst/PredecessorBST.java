import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PredecessorBST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] entrada = scanner.nextLine().split(" ");
        int value = Integer.parseInt(scanner.nextLine());

        BST bst = new BST();
        for (String s : entrada) {
            bst.add(Integer.parseInt(s));
        }

        System.out.println(bst.predecessor(value));
    }

    public static class BST {
        Node root;

        public BST() {
            root = null;
        }

        public boolean isEmpty() {
            return root == null;
        }

        public void add(int element) {
            if (isEmpty())
                root = new Node(element);
            else {
                Node current = root;
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

        public String predecessor(int element) {
            List<Integer> caminho = new ArrayList<>();
            Node node = search(element);

            if (node.left != null) {
                caminho.add(node.value);
                caminho.addAll(max(node.left));
            } else {
                // Se não tem filho esquerdo, precisa subir na árvore até encontrar um ancestral
                // que seja menor que o nó atual.
                Node aux = node.parent;
                caminho.add(node.value);
                while (aux != null && aux.value > node.value) {
                    caminho.add(aux.value);
                    aux = aux.parent;
                }
                if (aux != null)
                    caminho.add(aux.value);
            }

            return caminho.toString();
        }

        private List<Integer> max(Node node) {
            ArrayList<Integer> saida = new ArrayList<>();
            Node aux = node;

            saida.add(aux.value);
            while (aux.right != null) {
                aux = aux.right;
                saida.add(aux.value);
            }

            return saida;
        }

        private Node search(int value) {
            Node current = root;
            while (current != null) {
                if (current.value == value)
                    return current;
                if (current.value > value)
                    current = current.left;
                if (current.value < value)
                    current = current.right;
            }
            return null;
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