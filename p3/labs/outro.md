## **As Três Travessias Clássicas**

### **1. In-Order (Em-Ordem)**
**Ordem:** Esquerda → Raiz → Direita

```java
public void inOrder() {
    inOrder(this.root);
}

private void inOrder(Node node) {
    if (node != null) {
        inOrder(node.left);           // 1. Visita subárvore esquerda
        System.out.println(node.value); // 2. Processa o nó atual
        inOrder(node.right);          // 3. Visita subárvore direita
    }
}
```

**Resultado em BST:** Os elementos aparecem em **ordem crescente**!

### **2. Pre-Order (Pré-Ordem)**
**Ordem:** Raiz → Esquerda → Direita

```java
public void preOrder() {
    preOrder(this.root);
}

private void preOrder(Node node) {
    if (node != null) {
        System.out.println(node.value); // 1. Processa o nó atual
        preOrder(node.left);            // 2. Visita subárvore esquerda
        preOrder(node.right);           // 3. Visita subárvore direita
    }
}
```

### **3. Post-Order (Pós-Ordem)**
**Ordem:** Esquerda → Direita → Raiz

```java
public void posOrder() {
    posOrder(this.root);
}

private void posOrder(Node node) {
    if (node != null) {
        posOrder(node.left);            // 1. Visita subárvore esquerda
        posOrder(node.right);           // 2. Visita subárvore direita
        System.out.println(node.value); // 3. Processa o nó atual
    }
}
```

## **Exemplo Visual**

Para a BST:
```
      10
     /  \
    5    15
   / \   /
  3   7 12
```

### **Resultados das Travessias:**

- **In-Order**: 3, 5, 7, 10, 12, 15 (ordem crescente!)
- **Pre-Order**: 10, 5, 3, 7, 15, 12 (raiz primeiro)
- **Post-Order**: 3, 7, 5, 12, 15, 10 (raiz por último)

## **Variantes do In-Order**

### **1. In-Order com Lista (para predecessor/sucessor)**
```java
public List<Integer> inOrderList() {
    List<Integer> result = new ArrayList<>();
    inOrderList(this.root, result);
    return result;
}

private void inOrderList(Node node, List<Integer> result) {
    if (node != null) {
        inOrderList(node.left, result);
        result.add(node.value);         // Adiciona à lista em vez de imprimir
        inOrderList(node.right, result);
    }
}
```

### **2. In-Order Iterativo (sem recursão)**
```java
public void inOrderIterativo() {
    Stack<Node> stack = new Stack<>();
    Node current = this.root;
    
    while (current != null || !stack.isEmpty()) {
        // Vai para o extremo esquerdo
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        
        // Processa o nó atual
        current = stack.pop();
        System.out.println(current.value);
        
        // Move para a subárvore direita
        current = current.right;
    }
}
```

### **3. In-Order Reverso (Decrescente)**
```java
public void inOrderReverso() {
    inOrderReverso(this.root);
}

private void inOrderReverso(Node node) {
    if (node != null) {
        inOrderReverso(node.right);     // Direita primeiro
        System.out.println(node.value);
        inOrderReverso(node.left);      // Esquerda depois
    }
}
```
**Resultado:** 15, 12, 10, 7, 5, 3 (ordem decrescente)

### **4. In-Order com Condição (busca específica)**
```java
public boolean inOrderBusca(int target) {
    return inOrderBusca(this.root, target);
}

private boolean inOrderBusca(Node node, int target) {
    if (node == null) return false;
    
    // Busca na subárvore esquerda
    if (inOrderBusca(node.left, target)) return true;
    
    // Verifica o nó atual
    if (node.value == target) return true;
    
    // Busca na subárvore direita
    return inOrderBusca(node.right, target);
}
```

### **5. In-Order com Range (valores entre min e max)**
```java
public void inOrderRange(int min, int max) {
    inOrderRange(this.root, min, max);
}

private void inOrderRange(Node node, int min, int max) {
    if (node != null) {
        // Só vai para esquerda se pode haver valores >= min
        if (node.value > min) {
            inOrderRange(node.left, min, max);
        }
        
        // Processa se está no range
        if (node.value >= min && node.value <= max) {
            System.out.println(node.value);
        }
        
        // Só vai para direita se pode haver valores <= max
        if (node.value < max) {
            inOrderRange(node.right, min, max);
        }
    }
}
```

## **Aplicações Práticas do In-Order**

### **1. Para Predecessor/Sucessor:**
```java
public Node predecessor(int value) {
    List<Integer> inOrder = inOrderList();
    for (int i = 1; i < inOrder.size(); i++) {
        if (inOrder.get(i) == value) {
            return search(inOrder.get(i-1)); // Elemento anterior
        }
    }
    return null;
}

public Node sucessor(int value) {
    List<Integer> inOrder = inOrderList();
    for (int i = 0; i < inOrder.size()-1; i++) {
        if (inOrder.get(i) == value) {
            return search(inOrder.get(i+1)); // Próximo elemento
        }
    }
    return null;
}
```

### **2. Para Validar BST:**
```java
public boolean isBST() {
    List<Integer> inOrder = inOrderList();
    for (int i = 1; i < inOrder.size(); i++) {
        if (inOrder.get(i) <= inOrder.get(i-1)) {
            return false; // Não está em ordem crescente
        }
    }
    return true;
}
```

### **3. Para Encontrar k-ésimo Menor:**
```java
public int kthSmallest(int k) {
    List<Integer> inOrder = inOrderList();
    if (k > 0 && k <= inOrder.size()) {
        return inOrder.get(k-1); // k-ésimo menor (índice k-1)
    }
    return -1; // Não existe
}
```

## **Resumo das Variantes**

| Variante | Uso Principal | Complexidade |
|----------|---------------|--------------|
| **Recursivo** | Geral, simples | O(n) tempo, O(h) espaço |
| **Iterativo** | Sem recursão | O(n) tempo, O(h) espaço |
| **Com Lista** | Predecessor/sucessor | O(n) tempo, O(n) espaço |
| **Reverso** | Ordem decrescente | O(n) tempo, O(h) espaço |
| **Com Range** | Busca em faixa | O(k) onde k = elementos no range |

public void callBestRotation(Node unbalanced) {
    Node x = unbalanced; // 'x' é o nosso "Avô" desbalanceado

    // O problema está no lado ESQUERDO? (fator de balanço positivo)
    if (x.isLeftPending()) {
        Node y = x.left; // 'y' é o "Pai"

        // CASO 1: Rotação Simples à Direita (Esquerda-Esquerda)
        if (y.left != null) rotateRight(x);
        // CASO 2: Rotação Dupla (Esquerda-Direita)
        else {
            rotateLeft(y);  // 1º Passo: Rotação à esquerda no Pai 'y'
            rotateRight(x); // 2º Passo: Rotação à direita no Avô 'x'
        }

    // O problema está no lado DIREITO? (fator de balanço negativo)
    } else {
        Node y = x.right; // 'y' é o "Pai"

        // CASO 3: Rotação Simples à Esquerda (Direita-Direita)
        if (y.right != null) rotateLeft(x);
        // CASO 4: Rotação Dupla (Direita-Esquerda)
        else {
            rotateRight(y); // 1º Passo: Rotação à direita no Pai 'y'
            rotateLeft(x);  // 2º Passo: Rotação à esquerda no Avô 'x'
        }
    }
}
