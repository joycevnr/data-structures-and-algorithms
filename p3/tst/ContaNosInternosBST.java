import java.util.Scanner;

class ContaNosInternosBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(" ");

        BST bst = new BST();
        for (String s : entrada) {
            bst.add(Integer.parseInt(s));
        }

        System.out.println(bst.contaNosInternos());
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
                    if (current.value > element) {
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

        public int contaNosInternos() {
            if (isEmpty())
                return 0;
            else
                return contaNosInternos(root);
        }

        private int contaNosInternos(Node node) {
            if (node == null || node.isLeaf())
                return 0;
            return 1 + contaNosInternos(node.left) + contaNosInternos(node.right);
        }

        public static class Node {
            int value;
            Node left;
            Node right;
            Node parent;

            public Node(int v) {
                value = v;
            }

            public boolean isLeaf() {
                return left == null && right == null;
            }
        }
    }
}