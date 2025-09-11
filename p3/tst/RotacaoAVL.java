import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RotacaoAVL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(" ");

        AVL avl = new AVL();
        for (String s : entrada)
            avl.add(Integer.parseInt(s));

        if (!avl.rotacionou) System.out.println("balanceada");
    }

    public static class AVL {
        private Node root;
        private int size;
        private boolean rotacionou;

        public AVL() {
            this.size = -1;
            rotacionou = false;
        }

        public boolean isEmpty() {
            return this.root == null;
        }

        public void add(int element) {
            this.size += 1;
            if (isEmpty()) this.root = new Node(element);
            else {
                Node aux = this.root;
                while (aux != null) {
                    if (element < aux.value) {
                        if (aux.left == null) {
                            Node newNode = new Node(element);
                            aux.left = newNode;
                            newNode.parent = aux;
                            rebalance(newNode);
                            return;
                        }
                        aux = aux.left;
                    } else {
                        if (aux.right == null) {
                            Node newNode = new Node(element);
                            aux.right = newNode;
                            newNode.parent = aux;
                            rebalance(newNode);
                            return;
                        }
                        aux = aux.right;
                    }
                }
            }
        }

        private void rebalance(Node node) {
            Node desbalanceado = checkBalance(node);
            if (desbalanceado != null) {
                callBestRotation(desbalanceado);
            }
        }

        public Node checkBalance(Node node) {
            Node aux = node;
            while (aux != null) {
                if (!aux.isBalanced()) return aux;
                else if (aux.parent != null) aux = aux.parent;
                else break;
            }
            return null;
        }

        public void callBestRotation(Node desbalanceado) {
            Node x = desbalanceado;
            if (x.isLeftPending()) {
                Node y = x.left;
                if (y.left != null) rotateRight(x);
                else {
                    rotateLeft(y);
                    rotateRight(x);
                }
            } else {
                Node y = x.right;
                if (y.right != null) rotateLeft(x);
                else {
                    rotateRight(y);
                    rotateLeft(x);
                }
            }

            rotacionou = true;
        }

        public void rotateLeft(Node node) {
            System.out.println("rot_esq(" + node.value + ")");
            Node newRoot = node.right;
            newRoot.parent = node.parent;

            node.right = newRoot.left;
            newRoot.left = node;

            node.parent = newRoot;

            if (newRoot.parent != null) {
                if (newRoot.parent.right == node) newRoot.parent.right = newRoot;
                else newRoot.parent.left = newRoot;
            } else root = newRoot;

            preOrder();
        }

        public void rotateRight(Node node) {
            System.out.println("rot_dir(" + node.value + ")");
            Node newRoot = node.left;
            newRoot.parent = node.parent;

            node.left = newRoot.right;
            newRoot.right = node;

            node.parent = newRoot;

            if (newRoot.parent != null) {
                if (newRoot.parent.left == node) newRoot.parent.left = newRoot;
                else newRoot.parent.right = newRoot;
            } else root = newRoot;

            preOrder();
        }

        public void preOrder() {
            List<Integer> result = new ArrayList<>();
            preOrder(this.root, result);
            System.out.println(result);
        }

        private void preOrder(Node node, List<Integer> result) {
            if (node != null) {
                result.add(node.value);
                preOrder(node.left, result);
                preOrder(node.right, result);
            }
        }
    }

    public static class Node {
        private int value;
        Node left;
        Node right;
        Node parent;

        public Node(int v) {
            value = v;
        }

        public int height() {
            if (left == null && right == null) return 0;
            else if (left == null) return 1 + right.height();
            else if (right == null) return 1 + left.height();
            else return 1 + Math.max(left.height(), right.height());
        }

        public boolean isBalanced() {
            int left = this.left == null ? -1 : this.left.height();
            int right = this.right == null ? -1 : this.right.height();
            return Math.abs(left - right) <= 1;
        }

        public boolean isLeftPending() {
            int left = this.left == null ? -1 : this.left.height();
            int right = this.right == null ? -1 : this.right.height();
            return left - right >= 1;
        }
    }
}