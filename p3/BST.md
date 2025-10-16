# Árvores Binárias de Busca (BST)

## Definição

Uma **Árvore Binária de Busca (BST)** é uma estrutura de dados hierárquica para organizar informações com base em uma regra fundamental:

> **Para qualquer nó, todos os valores à esquerda são menores e todos os valores à direita são maiores.**

## Características Principais

### **Busca Rápida**
- Encontrar, inserir e remover elementos é eficiente
- **Complexidade**: `O(log n)` se balanceada, `O(n)` no pior caso

### **Sempre Ordenada**  
- A estrutura se mantém ordenada automaticamente
- Travessia **em-ordem** retorna elementos em ordem crescente

### **Dinâmica**
- Cresce e diminui facilmente, sem limite predefinido

### **Sensível à Inserção**
- Eficiência depende da ordem de inserção
- Dados ordenados podem gerar árvore degenerada (lista linear)

---

## Conceitos Fundamentais

### **Definições Básicas**

| Conceito | Descrição |
|----------|-----------|
| **Nó** | Unidade básica com valor e referências (left, right, parent) |
| **Raiz** | Nó especial de onde todos os outros são acessíveis |
| **Grau** | Número de filhos de um nó (0, 1 ou 2) |
| **Folha** | Nó com grau 0 (sem filhos) |
| **Altura** | Maior caminho entre raiz e folha |
| **Nível** | Distância de um nó até a raiz |

### **Propriedades Estruturais**

- **Árvore Completa**: Todos nós internos têm grau 2, folhas no mesmo nível
- **Sucessor**: Menor valor maior que o nó atual
- **Predecessor**: Maior valor menor que o nó atual

---

## Operações Básicas

### 1️⃣ **Inserção**
```java
// Novos elementos sempre como folhas
// Navega comparando valores até encontrar posição nula
if (elemento < atual.valor) → vai para esquerda
else → vai para direita
```

### 2️⃣ **Busca**  
```java
// Similar à inserção
// Compara elemento procurado com nós atuais
// Navega até encontrar elemento ou nó nulo
```

### 3️⃣ **Remoção** (Mais Complexa)
**Três casos:**

| Caso | Ação |
|------|------|
| **Folha** | Remove referência do pai |
| **Um filho** | Pai aponta para o único filho |
| **Dois filhos** | Substitui por sucessor/predecessor |

---

## Métodos de Travessia

### **Em Profundidade -(DFS)Depth-First Search **:   Explora um ramo da árvore até o fim antes de retroceder.
  
#### **Pré-ordem** (Raiz → Esquerda → Direita)
```java
private void preOrder(Node node) {
    if (node != null) {
        visit(node);           // 1. Processa nó
        preOrder(node.left);   // 2. Subárvore esquerda  
        preOrder(node.right);  // 3. Subárvore direita
    }
}
```

#### **Em-ordem** (Esquerda → Raiz → Direita)
```java
private void inOrder(Node node) {
    if (node != null) {
        inOrder(node.left);    // 1. Subárvore esquerda
        visit(node);           // 2. Processa nó ← ORDEM CRESCENTE!
        inOrder(node.right);   // 3. Subárvore direita
    }
}
```

#### **Pós-ordem** (Esquerda → Direita → Raiz)
```java
private void postOrder(Node node) {
    if (node != null) {
        postOrder(node.left);  // 1. Subárvore esquerda
        postOrder(node.right); // 2. Subárvore direita
        visit(node);           // 3. Processa nó
    }
}
```

### **Em Largura (BFS)**
```java
public void levelOrder() {
    Queue<Node> fila = new LinkedList<>();
    fila.add(root);
    
    while (!fila.isEmpty()) {
        Node atual = fila.poll();
        visit(atual);
        
        if (atual.left != null) fila.add(atual.left);
        if (atual.right != null) fila.add(atual.right);
    }
}
```

---

## Altura vs Nível

### **Definições Formais**

- **Altura**: Número de **arestas** no caminho mais longo da raiz até folha
- **Nível**: Distância de um nó até a raiz (raiz = nível 0)

### **Convenções**
- Árvore vazia: altura = -1
- Nó único: altura = 0  
- Árvore com altura `h` tem `h + 1` níveis

---

## Questões Teóricas Importantes

### **1. Definição e Propriedade Principal**
**R:** BST é uma árvore binária onde, para qualquer nó, valores à esquerda são menores e à direita são maiores.

### **2. Altura vs Nível**
**R:** 
- **Nível**: distância até a raiz (raiz = 0)
- **Altura**: maior caminho até folha (medido em arestas)

### **3. Impacto do Desbalanceamento** 
**R:** Árvore desbalanceada → altura aumenta → operações degradam de `O(log n)` para `O(n)`

### **4. Diferença entre Travessias**
**R:**
- **Pré-ordem**: nó → esquerda → direita
- **Em-ordem**: esquerda → nó → direita (BST = ordem crescente!)
- **Pós-ordem**: esquerda → direita → nó

### **5. BFS em BST**
**R:** Explora nível por nível usando fila, visitando todos filhos antes de descer ao próximo nível.

---

## Exercícios Práticos

### **Exemplo: Inserção [50, 30, 70, 20, 40, 60, 80]**

```
         50
       /    \
      30      70
     /  \    /  \
    20  40  60  80
```

### **Sucessores e Predecessores**
- Sucessor de 30: **40** (mínimo da subárvore direita)
- Predecessor de 70: **60** (máximo da subárvore esquerda)

### **Resultado das Travessias**
- **Pré-ordem**: 50, 30, 20, 40, 70, 60, 80
- **Em-ordem**: 20, 30, 40, 50, 60, 70, 80 ← Ordenado!
- **Pós-ordem**: 20, 40, 30, 60, 80, 70, 50

### **Remoção do Nó 30**
Como tem dois filhos, substitui pelo sucessor (40):

```
         50
       /    \
      40      70
     /       /  \
    20      60  80
```

---

*Material de estudo para EDA-LEDA | UFCG*

  *   Resposta:  
      *   Nível:   O nível de um nó é a sua distância a partir da raiz. A raiz está no nível 0, seus filhos diretos no nível 1, e assim por diante.
      *   Altura:   A altura de um nó é o maior caminho entre ele e uma de suas folhas descendentes. A altura de uma árvore é a altura de sua raiz.

  3. O que acontece com a eficiência das operações em uma BST quando ela se torna desbalanceada?  

  *   Resposta:   Quando uma BST se torna desbalanceada, a altura da árvore aumenta. Como as operações de busca, inserção e remoção têm complexidade de tempo proporcional à altura da árvore (O(h)), uma árvore desbalanceada pode levar a um pior caso de complexidade O(n), onde 'n' é o número de nós, tornando as operações menos eficientes.

  4. Explique a diferença entre os percursos em pré-ordem, em-ordem e pós-ordem.  

  *   Resposta:  
      *   Pré-ordem:   Visita o nó, depois a subárvore esquerda e, por fim, a subárvore direita.
      *   Em-ordem:   Visita a subárvore esquerda, depois o nó e, por fim, a subárvore direita. Em uma BST, este percurso resulta em uma sequência ordenada dos valores.
      *   Pós-ordem:   Visita a subárvore esquerda, depois a subárvore direita e, por fim, o nó. A raiz é sempre o último elemento visitado.

  5. Como funciona o percurso em largura (BFS) em uma BST?  

  *   Resposta:   O percurso em largura explora a árvore nível por nível. Ele visita todos os filhos de um nó antes de descer para os nós do próximo nível. Para isso, utiliza uma fila para armazenar os nós a serem visitados.

### Perguntas Práticas e de Implementação

  6. Dada a seguinte sequência de números: 50, 30, 70, 20, 40, 60, 80. Desenhe a BST resultante da inserção desses números nessa ordem.  

  *   Resposta:  

    ```
         50
       /    \
      30      70
     /  \    /  \
    20  40  60  80
    ```

  7. Na árvore do exercício anterior, qual é o sucessor do nó com valor 30? E o predecessor do nó com valor 70?  

  *   Resposta:  
      *   Sucessor de 30:   O sucessor de um nó com sub-árvore à direita é o mínimo dessa sub-árvore. O sucessor de 30 é o mínimo da sua sub-árvore direita, que é   40  .
      *   Predecessor de 70:   O predecessor de um nó com sub-árvore à esquerda é o máximo dessa sub-árvore. O predecessor de 70 é o máximo da sua sub-árvore esquerda, que é   60  .

  8. Descreva os três casos a serem considerados ao remover um nó de uma BST.  

  *   Resposta:  
    1.    O nó é uma folha:   A referência do pai para o nó é removida.
    2.    O nó tem um filho:   O pai do nó a ser removido passa a apontar para o único filho deste.
    3.    O nó tem dois filhos:   O valor do nó é substituído pelo valor do seu sucessor (ou predecessor), e o nó sucessor é então removido.

  9. Na árvore do exercício 6, o que acontece ao remover o nó com valor 30? Desenhe a árvore resultante.  

  *   Resposta:   O nó 30 tem dois filhos. Para removê-lo, trocamos seu valor pelo de seu sucessor (40) e removemos o nó sucessor. A árvore resultante será:

    ```
         50
       /    \
      40      70
     /       /  \
    20      60  80
    ```

  10. Qual seria o resultado dos percursos em pré-ordem, em-ordem e pós-ordem da árvore do exercício 6?  

  *   Resposta:  
      *   Pré-ordem:   50, 30, 20, 40, 70, 60, 80
      *   Em-ordem:   20, 30, 40, 50, 60, 70, 80
      *   Pós-ordem:   20, 40, 30, 60, 80, 70, 50


A altura de uma árvore binária (e de qualquer árvore em geral) é formalmente definida como o comprimento do caminho mais longo da raiz até uma folha. O "comprimento" desse caminho é medido pelo número de arestas que o compõem.

Seguindo essa definição:

Uma árvore com um único nó (a raiz) tem altura 0, pois não há arestas saindo da raiz para uma folha.

Uma árvore vazia (sem nós) tem, por convenção, altura -1.

A altura de um nó específico é o número de arestas no caminho mais longo desse nó até uma folha. A altura da árvore é, portanto, a altura do seu nó raiz.

Relação com Nós e Níveis
É aqui que a confusão pode se instalar. Embora a altura não seja o número de nós, existe uma relação direta:

Altura baseada em nós: Ocasionalmente, em contextos menos formais ou em algumas publicações mais antigas, a altura pode ser definida como o número de nós no caminho mais longo da raiz até a folha. Nessa convenção, uma árvore de um único nó teria altura 1. No entanto, esta não é a definição padrão adotada na maioria dos livros didáticos e contextos acadêmicos atuais.

Níveis: O conceito de "nível" também está relacionado. O nível da raiz é geralmente definido como 0. Os filhos da raiz estão no nível 1, os netos no nível 2, e assim por diante. Se uma árvore tem altura h (baseada em arestas), ela terá h + 1 níveis (do nível 0 ao nível h). Por exemplo, uma árvore com altura 2 (duas arestas no caminho mais longo) terá nós nos níveis 0, 1 e 2.