# Travessias de Árvores

## As Três Travessias Clássicas (DFS)

### **1. In-Order (Em-Ordem)**
**Ordem:** Esquerda → **Raiz** → Direita

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

---

### **2. Pre-Order (Pré-Ordem)**  
**Ordem:** **Raiz** → Esquerda → Direita

```java
public void preOrder() {
    preOrder(this.root);
}

private void preOrder(Node node) {
    if (node != null) {
        System.out.println(node.value); // 1. Processa o nó atual
        preOrder(node.left);           // 2. Visita subárvore esquerda
        preOrder(node.right);          // 3. Visita subárvore direita
    }
}
```

**Uso:** Copiar/clonar árvores, análise de expressões

---

### **3. Post-Order (Pós-Ordem)**
**Ordem:** Esquerda → Direita → **Raiz**

```java
public void postOrder() {
    postOrder(this.root);
}

private void postOrder(Node node) {
    if (node != null) {
        postOrder(node.left);          // 1. Visita subárvore esquerda
        postOrder(node.right);         // 2. Visita subárvore direita
        System.out.println(node.value);  // 3. Processa o nó atual
    }
}
```

**Uso:** Deletar árvores, calcular tamanho, liberação de memória

---

## Travessia em Largura (BFS)

### **Level-Order (Por Nível)**
**Estratégia:** Visita todos nós de um nível antes de passar ao próximo

```java
public void levelOrder() {
    if (root == null) return;
    
    Queue<Node> fila = new LinkedList<>();
    fila.add(root);
    
    while (!fila.isEmpty()) {
        Node atual = fila.poll();
        System.out.println(atual.value);
        
        // Adiciona filhos na fila (esquerda primeiro)
        if (atual.left != null) fila.add(atual.left);
        if (atual.right != null) fila.add(atual.right);
    }
}
```

**Uso:** Busca por nível, impressão por níveis, algoritmos de caminho mínimo

---

## Exemplo Visual Completo

### **Árvore de Exemplo:**
```
       4
     /   \
    2     6
   / \   / \
  1   3 5   7
```

### **Resultados das Travessias:**

| Travessia | Resultado | Característica |
|-----------|-----------|----------------|
| **In-Order** | `1, 2, 3, 4, 5, 6, 7` | Ordem crescente (BST) |
| **Pre-Order** | `4, 2, 1, 3, 6, 5, 7` | Raiz primeiro |
| **Post-Order** | `1, 3, 2, 5, 7, 6, 4` | Raiz último |
| **Level-Order** | `4, 2, 6, 1, 3, 5, 7` | Nível por nível |

---

## Implementações Avançadas

### **1. Travessia Iterativa (In-Order)**
```java
public void inOrderIterative() {
    Stack<Node> stack = new Stack<>();
    Node atual = root;
    
    while (atual != null || !stack.isEmpty()) {
        // Vai para o nó mais à esquerda
        while (atual != null) {
            stack.push(atual);
            atual = atual.left;
        }
        
        // Processa nó atual
        atual = stack.pop();
        System.out.println(atual.value);
        
        // Move para subárvore direita
        atual = atual.right;
    }
}
```

### **2. BFS com Separação por Níveis**
```java
public void levelOrderWithLevels() {
    if (root == null) return;
    
    Queue<Node> fila = new LinkedList<>();
    fila.add(root);
    
    while (!fila.isEmpty()) {
        int nivelSize = fila.size();
        
        // Processa todos nós do nível atual
        for (int i = 0; i < nivelSize; i++) {
            Node atual = fila.poll();
            System.out.print(atual.value + " ");
            
            if (atual.left != null) fila.add(atual.left);
            if (atual.right != null) fila.add(atual.right);
        }
        System.out.println(); // Nova linha para próximo nível
    }
}
```

### **3. Travessia com Retorno de Lista**
```java
public List<Integer> inOrderList() {
    List<Integer> result = new ArrayList<>();
    inOrderList(root, result);
    return result;
}

private void inOrderList(Node node, List<Integer> result) {
    if (node != null) {
        inOrderList(node.left, result);
        result.add(node.value);
        inOrderList(node.right, result);
    }
}
```

---

## Aplicações Práticas

### **Análise e Processamento**
- **In-Order**: Obter elementos ordenados de BST
- **Pre-Order**: Serialização/clonagem de árvores
- **Post-Order**: Calcular altura, tamanho, deletar árvore
- **Level-Order**: Impressão por níveis, busca por amplitude

### **Algoritmos de Busca**
```java
// Busca DFS (Pre-Order)
public boolean searchDFS(int target) {
    return searchDFS(root, target);
}

private boolean searchDFS(Node node, int target) {
    if (node == null) return false;
    if (node.value == target) return true;
    
    return searchDFS(node.left, target) || 
           searchDFS(node.right, target);
}

// Busca BFS
public boolean searchBFS(int target) {
    if (root == null) return false;
    
    Queue<Node> fila = new LinkedList<>();
    fila.add(root);
    
    while (!fila.isEmpty()) {
        Node atual = fila.poll();
        if (atual.value == target) return true;
        
        if (atual.left != null) fila.add(atual.left);
        if (atual.right != null) fila.add(atual.right);
    }
    
    return false;
}
```

---

## Complexidades

| Travessia | Tempo | Espaço | Observações |
|-----------|-------|---------|-------------|
| **DFS (Recursiva)** | `O(n)` | `O(h)` | h = altura da árvore |
| **DFS (Iterativa)** | `O(n)` | `O(h)` | Usa stack explícita |
| **BFS** | `O(n)` | `O(w)` | w = largura máxima |

**Onde:**
- `n` = número de nós
- `h` = altura da árvore (log n para balanceada, n para degenerada)
- `w` = largura máxima (máximo de nós em um nível)

---

## Exercícios e Padrões

### **1. Validação de BST (In-Order)**
```java
public boolean isValidBST() {
    List<Integer> inorder = inOrderList();
    for (int i = 1; i < inorder.size(); i++) {
        if (inorder.get(i) <= inorder.get(i-1)) return false;
    }
    return true;
}
```

### **2. Soma de Caminhos (DFS)**
```java
public int sumPaths(Node node, int targetSum) {
    if (node == null) return 0;
    
    int count = 0;
    if (node.value == targetSum) count = 1;
    
    count += sumPaths(node.left, targetSum - node.value);
    count += sumPaths(node.right, targetSum - node.value);
    
    return count;
}
```

### **3. Encontrar Nível de um Nó (BFS)**
```java
public int findLevel(int target) {
    if (root == null) return -1;
    
    Queue<Node> fila = new LinkedList<>();
    fila.add(root);
    int nivel = 0;
    
    while (!fila.isEmpty()) {
        int size = fila.size();
        
        for (int i = 0; i < size; i++) {
            Node atual = fila.poll();
            if (atual.value == target) return nivel;
            
            if (atual.left != null) fila.add(atual.left);
            if (atual.right != null) fila.add(atual.right);
        }
        nivel++;
    }
    
    return -1; // Não encontrado
}
```

---

## Provas

### **Pontos Importantes**
1. **In-Order em BST = Ordem Crescente**
2. **BFS usa Fila, DFS usa Stack (ou recursão)**
3. **Pre-Order: raiz primeiro, Post-Order: raiz último**
4. **Level-Order processa nível completo por vez**

### **Perguntas Comuns**
- "Qual travessia retorna elementos ordenados em BST?" → **In-Order**
- "Como imprimir árvore nível por nível?" → **Level-Order (BFS)**
- "Como clonar uma árvore?" → **Pre-Order**
- "Como calcular altura recursivamente?" → **Post-Order**

---

*Material de estudo para EDA-LEDA | UFCG*

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
