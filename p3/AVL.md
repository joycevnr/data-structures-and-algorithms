### Resumo Detalhado: Árvores Balanceadas (AVL)

#### 1\. O Problema: A Limitação da BST Padrão

Uma Árvore Binária de Pesquisa (BST) é eficiente quando sua altura é pequena (`O(log n)`). No entanto, se os dados forem inseridos em ordem (ex: 1, 2, 3, ...), a árvore se degenera em uma lista, e a performance das operações cai para `O(n)`, o que é ineficiente.

#### 2\. A Solução: A Garantia de Balanceamento da AVL

A Árvore AVL é uma BST que se   autobalanceia  . Ela garante que a altura da árvore seja sempre `O(log n)` através de uma regra estrita:

> Para qualquer nó na árvore, a diferença de altura entre sua subárvore esquerda e sua subárvore direita   não pode ser maior que 1  .

Para implementar isso, a estrutura do nó é modificada para armazenar sua própria altura.

  Código: Estrutura do Nó (Node)  
Cada nó precisa de um campo extra para registrar sua altura, que é crucial para os cálculos de balanceamento.

```java
public class Node {
    int value;
    int height; // Altura da subárvore com raiz neste nó
    Node left;
    Node right;
    // Node parent; // Opcional, mas útil

    public Node(int value) {
        this.value = value;
        this.height = 0; // Uma folha tem altura 0
    }
}
```

#### 3\. Mecanismos de Controle: Altura e Fator de Balanço

Para verificar se a árvore está balanceada, calculamos o   fator de balanço   de cada nó após uma inserção ou remoção.

  Código: Calculando a Altura e o Fator de Balanço  

```java
// Retorna a altura de um nó (trata o caso de nó nulo)
private int height(Node node) {
    if (node == null) {
        return -1; // Altura de uma árvore nula é -1
    }
    return node.height;
}

// Calcula o fator de balanço de um nó
private int getBalance(Node node) {
    if (node == null) {
        return 0;
    }
    // Fator = altura(esquerda) - altura(direita)
    return height(node.left) - height(node.right);
}
```

  *   Fator de Balanço `+2` ou `-2`  : Indica que o nó está desbalanceado e precisa de correção.
  *   Fator de Balanço `+1, 0, -1`  : O nó está balanceado.

#### 4\. A Correção: Rotações

Quando um nó fica desbalanceado, o algoritmo aplica   rotações  . Rotações são rearranjos de ponteiros que corrigem o balanço em tempo constante (`O(1)`) sem violar a propriedade de ordenação da BST.

  Código: Exemplo de Rotação Simples à Direita  
Esta rotação é usada quando um nó fica pesado à esquerda (desbalanceamento Esquerda-Esquerda).

```java
/*
 * x (-2)            y (0)
 * / \               / \
 * y (-1) -> T3      z (0)  x (0)
 * / \               / \    / \
 * z (0) T2          T1 T2  T2 T3  <- T2 precisa ser reposicionado
 * / \
 *T1 T2
 */
private Node rotateRight(Node x) {
    Node y = x.left;
    Node T2 = y.right;

    // Realiza a rotação
    y.right = x;
    x.left = T2;

    // Atualiza as alturas (a ordem é importante: primeiro x, depois y)
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));

    // Retorna a nova raiz da subárvore
    return y;
}
```

A   rotação à esquerda   é uma operação espelhada desta.

#### 5\. Algoritmo de Inserção e Detecção dos Casos

A inserção em uma AVL funciona assim:

1.  Insira o novo nó como em uma BST comum.
2.  Suba pela árvore a partir do nó inserido até a raiz.
3.  Em cada nó no caminho, atualize sua altura e calcule seu fator de balanço.
4.  Se um nó estiver desbalanceado, identifique o caso e aplique a rotação apropriada.

  Código (Lógica): Como Identificar o Caso de Rotação  
Após encontrar um nó `z` desbalanceado, analisamos seu fator de balanço e a posição do novo elemento para decidir qual rotação aplicar.

```java
// ... dentro do método de inserção, após inserir e subir na árvore ...

int balance = getBalance(node);

// Caso 1: Desbalanceamento Esquerda-Esquerda
// Fator de balanço > 1 e o novo elemento está na subárvore esquerda do filho esquerdo
if (balance > 1 && getBalance(node.left) >= 0) {
    return rotateRight(node);
}

// Caso 2: Desbalanceamento Direita-Direita
// Fator de balanço < -1 e o novo elemento está na subárvore direita do filho direito
if (balance < -1 && getBalance(node.right) <= 0) {
    return rotateLeft(node);
}

// Caso 3: Desbalanceamento Esquerda-Direita (Rotação Dupla)
// Fator de balanço > 1 e o novo elemento está na subárvore direita do filho esquerdo
if (balance > 1 && getBalance(node.left) < 0) {
    node.left = rotateLeft(node.left); // Primeiro rotaciona o filho
    return rotateRight(node);           // Depois rotaciona o nó
}

// Caso 4: Desbalanceamento Direita-Esquerda (Rotação Dupla)
// Fator de balanço < -1 e o novo elemento está na subárvore esquerda do filho direito
if (balance < -1 && getBalance(node.right) > 0) {
    node.right = rotateRight(node.right); // Primeiro rotaciona o filho
    return rotateLeft(node);             // Depois rotaciona o nó
}
```