# Heap - Filas de Prioridade

## O Problema: Limitações das Estruturas Lineares

Ao implementar filas de prioridade com estruturas lineares, enfrentamos um **dilema de eficiência**:

| Estrutura | Inserção | Remoção do Máximo |
|-----------|----------|-------------------|
| **Lista Ordenada** | `O(n)` - lenta | `O(1)` - rápida |
| **Lista Desordenada** | `O(1)` - rápida | `O(n)` - lenta |

### **A Solução: Heap**
O Heap resolve esse problema oferecendo **inserção e remoção em `O(log n)`** - eficiente para ambas as operações!

---

## Definições e Propriedades Fundamentais

Um **Heap** é uma árvore binária que obedece a **duas propriedades essenciais**:

### **Propriedade de Ordem (Heap Property)**

#### **Max Heap** (Foco Principal)
- Valor de um nó é sempre **≥** aos valores de seus filhos
- **Garantia**: O maior elemento está sempre na **raiz**

#### **Min Heap**  
- Valor de um nó é sempre **≤** aos valores de seus filhos
- **Garantia**: O menor elemento está sempre na **raiz**

### **Propriedade da Forma (Shape Property)**
- Heap deve ser uma **árvore binária completa ou quase-completa**
- Nós do último nível preenchidos **da esquerda para direita**
- **Sem "buracos"** na estrutura

#### **Importância da Shape Property**
- Garante altura mínima possível: `h = O(log n)`
- Fundamenta a eficiência das operações
- Permite implementação elegante com array

---

## Implementação com Array

### **Mapeamento**
Diferente de outras árvores, o Heap usa um **array** graças à propriedade de ser completo/quase-completo.

```
Árvore:       1
            /   \
           3     2  
          / \   /
         7   8 4

Array: [1, 3, 2, 7, 8, 4]
Index:  0  1  2  3  4  5
```

### **Navegação por Fórmulas**
Para um nó no índice `i`:

| Relação | Fórmula |
|---------|---------|
| **Pai** | `(i - 1) / 2` |
| **Filho Esquerdo** | `2 * i + 1` |
| **Filho Direito** | `2 * (i + 1)` ou `2 * i + 2` |

---

## Operações Principais

### **Inserção (`add`) - O(log n)**

#### **Processo: Sift-Up ("Subir")**

1. **Adicionar**: Novo elemento na primeira posição livre (`tail + 1`)
2. **Comparar**: Com o pai
3. **Trocar**: Se filho > pai (em Max Heap)
4. **Repetir**: Até elemento < pai ou chegar à raiz

```java
public void add(int element) {
    heap[++tail] = element;  // Adiciona no final
    shiftUp(tail);            // Restaura propriedade de ordem
}

private void shiftUp(int index) {
    while (index > 0) {
        int parentIndex = (index - 1) / 2;
        if (heap[index] <= heap[parentIndex]) break;
        
        swap(index, parentIndex);
        index = parentIndex;
    }
}
```

### **Remoção (`remove/extractMax`) - O(log n)**

#### **Processo: Heapify ("Descer")**

1. **Salvar**: Valor da raiz (máximo) para retorno  
2. **Mover**: Último elemento para posição da raiz
3. **Decrementar**: Tamanho (`tail--`)
4. **Heapify**: Restaurar propriedade a partir da raiz

```java
public int extractMax() {
    int max = heap[0];           // Salva máximo
    heap[0] = heap[tail--];      // Move último para raiz
    heapify(0);                  // Restaura propriedade
    return max;
}

private void heapify(int index) {
    while (true) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        if (left <= tail && heap[left] > heap[largest])
            largest = left;
        if (right <= tail && heap[right] > heap[largest])
            largest = right;
            
        if (largest == index) break;
        
        swap(index, largest);
        index = largest;
    }
}
```

---

## Build Heap - Construção Eficiente

### **Problema**: Criar heap a partir de array desordenado

### **Solução**: Build Heap em `O(n)`

```java
public void buildHeap(int[] array) {
    this.heap = array;
    this.tail = array.length - 1;
    
    // Heapify de baixo para cima, começando do último pai
    for (int i = (tail - 1) / 2; i >= 0; i--) {
        heapify(i);
    }
}
```

#### **Por que O(n)?**
- Nós nas folhas: não precisam de heapify
- Nós próximos às folhas: pouco trabalho
- Apenas poucos nós (próximos à raiz) fazem trabalho significativo

---

## Aplicações Práticas

### **HeapSort - O(n log n)**
```java
public void heapSort(int[] array) {
    buildHeap(array);              // O(n)
    
    for (int i = tail; i > 0; i--) {
        swap(0, i);                // Move max para posição final
        tail--;                    // Reduz heap
        heapify(0);               // Restaura propriedade
    }
}
```

### **Fila de Prioridade**
```java
public class PriorityQueue {
    private Heap heap = new Heap();
    
    public void enqueue(int element) { heap.add(element); }
    public int dequeue() { return heap.extractMax(); }
    public int peek() { return heap.getRoot(); }
    public boolean isEmpty() { return heap.isEmpty(); }
}
```

### **Top K Elementos**
```java
public int[] topKElements(int[] array, int k) {
    Heap heap = new Heap();
    
    for (int element : array) {
        heap.add(element);
    }
    
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
        result[i] = heap.extractMax();
    }
    
    return result;
}
```

---

## Complexidades

| Operação | Complexidade | Observação |
|----------|--------------|------------|
| **Inserção** | `O(log n)` | Sift-up até raiz |
| **Remoção** | `O(log n)` | Heapify até folhas |
| **Build Heap** | `O(n)` | Construção bottom-up |
| **HeapSort** | `O(n log n)` | Build + n remoções |
| **Busca** | `O(n)` | Sem ordem entre irmãos |

---

## Heap vs Outras Estruturas

| Estrutura | Inserção | Remoção Max | Busca | Uso Ideal |
|-----------|----------|-------------|-------|-----------|
| **Heap** | `O(log n)` | `O(log n)` | `O(n)` | Fila de prioridade |
| **BST** | `O(log n)` | `O(log n)` | `O(log n)` | Busca frequente |
| **Lista Ord.** | `O(n)` | `O(1)` | `O(n)` | Remoção constante |
| **Array Ord.** | `O(n)` | `O(1)` | `O(log n)` | Dados estáticos |

---

## Exemplo Prático

### **Construindo Heap: [4, 10, 3, 5, 1]**

#### **Inserções Passo a Passo:**

1. **Inserir 4**: `[4]`
2. **Inserir 10**: `[10, 4]` (10 sobe)
3. **Inserir 3**: `[10, 4, 3]`
4. **Inserir 5**: `[10, 5, 3, 4]` (5 sobe)
5. **Inserir 1**: `[10, 5, 3, 4, 1]`

#### **Estrutura Final:**
```
     10
   /    \
  5      3
 / \
4   1
```

#### **Remoção do Máximo:**
1. **Remove 10**: Move 1 para raiz → `[1, 5, 3, 4]`
2. **Heapify**: 1 desce → `[5, 4, 3, 1]`

```
     5
   /   \
  4     3
 /
1
```

---

*Material de estudo para EDA-LEDA | UFCG*

  c) Construção de um Heap (`buildHeap`)  

É possível transformar um array qualquer em um Heap de forma eficiente.
*   Algoritmo:   O método `buildHeap` aplica a rotina `heapify` sucessivamente, começando pelo   pai da última folha   e movendo-se para trás, até chegar à raiz (índice `0`).
*   Justificativa:   Iniciar nesse ponto garante que, quando o `heapify` é chamado em um nó, suas subárvores já são Heaps válidos. Não é necessário começar pelas folhas, pois elas, por si só, já satisfazem a propriedade de Heap.


-----

### Parte 1: Principais Perguntas de Prova sobre Heap


  1. Quais são as duas propriedades fundamentais que definem um Heap? Explique a importância de cada uma.  

  *   Resposta:  
    1.    Propriedade de Ordem (Heap Property):   Em um Max-Heap, o valor de um nó é sempre   maior ou igual   ao de seus filhos. A importância é que essa regra garante que o elemento de maior valor (ou maior prioridade) estará sempre na raiz da árvore, permitindo acesso a ele em tempo constante `O(1)`.
    Propriedade da Forma (Shape Property):   O Heap deve ser uma   árvore binária completa ou quase-completa  , preenchida da esquerda para a direita. A importância é que essa forma garante que a altura da árvore será sempre a mínima possível (`O(log n)`), o que torna as operações de inserção e remoção extremamente eficientes.
    


    2. Por que a implementação de um Heap é geralmente feita com um array em vez de nós com ponteiros?  

    *   Resposta:   A implementação com array é possível e mais eficiente graças à   Propriedade da Forma  . Como a árvore é sempre completa ou quase-completa, não há "buracos" na sua estrutura. Isso permite mapear a árvore perfeitamente para um array (percorrendo-a em largura), o que economiza memória (não há necessidade de ponteiros `left`, `right`, `parent`) e permite calcular a posição de pais e filhos com fórmulas matemáticas simples. Além disso, essa implementação resolve o problema de custo das estruturas lineares: em um array desordenado, a inserção tem custo `O(1)` mas a remoção custa `O(n)` (é necessário percorrer toda a lista para encontrar o elemento de maior prioridade); já em um array ordenado, a inserção custa `O(n)` (precisa encontrar a posição correta) mas a remoção é `O(1)` (sempre remove o último elemento). O Heap com array oferece `O(log n)` para ambas as operações.

  1. Descreva o algoritmo para inserir um novo elemento em um Max-Heap. Qual sua complexidade?

  *   Resposta:
    1.  O novo elemento é adicionado na primeira posição livre ao final do array, mantendo a árvore quase-completa.
    2.  Para restaurar a ordem, o novo elemento "sobe" na árvore (processo de   sift-up  ): ele é comparado com seu pai e, se for maior, eles trocam de lugar.
    3.  Esse processo se repete até que o elemento seja menor que seu pai ou chegue à raiz.
      * A complexidade é   `O(log n)`  , pois, no pior caso, o elemento sobe todo o caminho da base até a raiz, um caminho igual à altura da árvore.

  1. Como funciona a remoção do elemento máximo de um Max-Heap? Qual a função da rotina `heapify` nesse processo?  

  *   Resposta:  
    1.  A remoção sempre retira o elemento máximo, que está na raiz (índice 0).
    2.  Para não quebrar a estrutura da árvore, o   último elemento   do heap é movido para a posição da raiz.
    3.  Agora, a Propriedade de Ordem está violada. A função do   `heapify`   (ou   sift-down  ) é corrigir isso: a nova raiz "desce" na árvore. Ela é comparada com seus filhos e trocada pelo   maior deles  .
    4.  Esse processo se repete até que o elemento seja maior que seus filhos ou se torne uma folha. A complexidade total é   `O(log n)`  .

-----

### Parte 2: A Relação com Filas de Prioridade

  O que é uma Fila de Prioridade?  

Uma fila normal funciona no sistema "primeiro a entrar, primeiro a sair" (FIFO). Uma   Fila de Prioridade   é diferente: a ordem de saída não é baseada na chegada, mas sim na   prioridade   de cada elemento. Elementos com maior prioridade são "atendidos" primeiro, não importa quando chegaram.

  Como o Heap implementa uma Fila de Prioridade?  

O Heap é a estrutura de dados   perfeita   para implementar uma Fila de Prioridade de forma eficiente. A relação é direta:

  *   `enqueue(elemento)` (Adicionar na Fila):   Corresponde ao método `add(elemento)` do Heap. O processo de `sift-up` garante que o novo elemento seja posicionado corretamente de acordo com sua prioridade em tempo `O(log n)`.
  *   `dequeue()` (Remover da Fila):   Corresponde ao método `remove()` do Heap. Ele sempre remove e retorna o elemento de maior prioridade (a raiz) e reorganiza a fila eficientemente em tempo `O(log n)`.
  *   `peek()` (Espiar o Próximo):   Corresponde a simplesmente olhar o valor na raiz do Heap (`heap[0]`), o que é uma operação instantânea (`O(1)`).

  Exemplo de Implementação (Conceitual):  

```java
public class PriorityQueue<T extends Comparable<T>> {
    
    private Heap<T> heap;

    public PriorityQueue(int capacidade) {
        this.heap = new Heap<>(capacidade);
    }

    // Adiciona um elemento com sua prioridade na fila
    public void enqueue(T elemento) {
        heap.add(elemento); // O(log n)
    }

    // Remove e retorna o elemento de maior prioridade
    public T dequeue() {
        return heap.remove(); // O(log n)
    }

    // Retorna o elemento de maior prioridade sem remover
    public T peek() {
        return heap.root(); // O(1)
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
```

-----

### Parte 3: Heapsort

  O que é o Heapsort?

O   Heapsort   é um algoritmo de ordenação eficiente que usa a estrutura de dados Heap para organizar os elementos. Ele é um algoritmo do tipo "in-place" (não precisa de um array auxiliar) e tem uma complexidade de tempo garantida de   `O(n log n)`  .

O algoritmo funciona em duas fases principais:

  Fase 1: Construção do Heap (`buildHeap`)  
Primeiro, o algoritmo transforma o array desordenado em um Max-Heap. Isso organiza o array de tal forma que o maior elemento fica na primeira posição (índice 0).

  Fase 2: Extração e Ordenação  
Depois que o array é um Max-Heap, o algoritmo extrai repetidamente o maior elemento (a raiz) e o coloca em sua posição final no final do array.

1.    Troca:   O maior elemento (`array[0]`) é trocado com o último elemento do heap.
2.    "Bloqueio":   O tamanho do heap é reduzido em 1. Agora, o maior elemento está na sua posição final e correta, e não faz mais parte do heap.
3.    Correção:   O novo elemento na raiz provavelmente quebrou a Propriedade de Ordem. A rotina   `heapify`   é chamada na raiz para corrigir o heap (agora menor).
4.  O processo é repetido até que o heap tenha apenas um elemento. No final, o array estará completamente ordenado.

  Implementação do Heapsort (em Java):  

```java

/**
 * Ordena o array interno usando Heap Sort
 * Preserva o heap original, retorna uma cópia ordenada
 * @return Array ordenado em ordem crescente
 */
public int[] heapSort() {
    if (isEmpty()) {
        return new int[0];
    }
    
    // Cria uma cópia para não modificar o heap original
    int[] sortedArray = Arrays.copyOf(this.heap, this.size());
    int heapSize = this.size();
    
    // Já é um max-heap válido, então vai direto para a ordenação
    for (int i = heapSize - 1; i > 0; i--) {
        // Troca a raiz (maior) com o último elemento da heap
        swapInArray(sortedArray, 0, i);
        
        // Aplica heapify na heap reduzida
        heapifyForSort(sortedArray, i, 0);
    }
    
    return sortedArray;
}

/**
 * Versão destrutiva - converte o próprio heap em array ordenado
 * Após chamar este método, o heap não será mais válido
 * @return Array ordenado
 */
public int[] heapSortDestructive() {
    if (isEmpty()) {
        return new int[0];
    }
    
    int originalSize = this.size();
    
    // Usa o heap atual para fazer a ordenação
    for (int i = this.tail; i > 0; i--) {
        // Troca a raiz com o último elemento
        swap(0, i);
        
        // Reduz o tamanho lógico da heap
        this.tail--;
        
        // Restaura a propriedade do heap
        heapify(0);
    }
    
    // Cria o resultado e reseta o heap
    int[] result = Arrays.copyOf(this.heap, originalSize);
    this.tail = -1;  // Reseta o heap
    
    return result;
}

/**
 * Heapify específico para o heap sort (não modifica o objeto)
 */
private void heapifyForSort(int[] arr, int heapSize, int index) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;
    int largest = index;
    
    // Encontra o maior entre pai e filhos
    if (left < heapSize && arr[left] > arr[largest]) {
        largest = left;
    }
    
    if (right < heapSize && arr[right] > arr[largest]) {
        largest = right;
    }
    
    // Se o maior não é o pai, troca e continua
    if (largest != index) {
        swapInArray(arr, index, largest);
        heapifyForSort(arr, heapSize, largest);
    }
}

/**
 * Swap para arrays externos (não modifica this.heap)
 */
private void swapInArray(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}


```

```java
// Pseudocódigo do Heap Sort
public void heapSort(int[] array) {
    // 1. Construir max-heap
    buildMaxHeap(array);
    
    // 2. Para cada posição do final para o início
    for (int i = array.length - 1; i > 0; i--) {
        // Remove a raiz (maior elemento) e coloca na posição final
        swap(array[0], array[i]);  // Maior elemento vai para posição correta
        
        // Reduz o tamanho da heap (exclui a posição já ordenada)
        heapSize--;
        
        // Restaura a propriedade da heap na raiz
        heapify(array, 0);
    }
}
```