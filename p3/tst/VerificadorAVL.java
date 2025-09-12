public class VerificadorAVL {

    /**
     * Função principal que inicia a verificação.
     * @param root A raiz da árvore a ser verificada.
     * @return true se a árvore for uma AVL válida, false caso contrário.
     */
    public boolean isBalanced(Node root) {
        // Se a função checkHeight retornar -1, significa que um desbalanceamento foi encontrado.
        return checkHeight(root) != -1;
    }

    /**
     * Função recursiva que verifica o balanceamento e calcula a altura simultaneamente.
     * @param node O nó atual.
     * @return A altura do nó se a subárvore for balanceada, ou -1 se não for.
     */
    private int checkHeight(Node node) {
        // Caso base: uma árvore nula é balanceada e tem altura -1.
        if (node == null) {
            return -1;
        }

        // 1. Verifica recursivamente a subárvore esquerda.
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1; // Propaga o erro para cima.
        }

        // 2. Verifica recursivamente a subárvore direita.
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1; // Propaga o erro para cima.
        }

        // 3. Calcula a diferença de altura para o nó ATUAL.
        int heightDiff = Math.abs(leftHeight - rightHeight);

        // 4. Se estiver desbalanceado, retorna o sinal de erro.
        if (heightDiff > 1) {
            return -1;
        
        // 5. Se estiver balanceado, retorna a altura correta do nó atual.
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    class Node {
        int key;
        Node left, right;
    
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }
    

    // Para um teste completo, você também precisaria verificar a propriedade de BST,
    // mas o foco da AVL está na altura. A verificação acima é a parte central.
}