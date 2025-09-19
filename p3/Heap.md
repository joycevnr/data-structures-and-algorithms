## Heap

#### 1. O Problema: Filas de Prioridade com Listas/Arrays

Ao implementar uma fila de prioridade com estruturas lineares, enfrentamos um dilema de eficiência:
*   Lista Ordenada:   A inserção é lenta (`O(n)`) porque precisa encontrar a posição correta, mas a remoção do elemento de maior prioridade é rápida (`O(1)`).
*   Lista Desordenada:   A inserção é rápida (`O(1)`), mas a remoção é lenta (`O(n)`) porque exige uma busca por toda a lista para encontrar o elemento de maior prioridade.

O Heap resolve esse problema, oferecendo tanto a inserção quanto a remoção em tempo `O(log n)`.

#### 2. Definições e Propriedades Fundamentais do Heap

Um Heap é uma árvore binária que obedece a duas propriedades essenciais:

1.    Propriedade de Ordem do Heap (Heap Property):  
    * Em um   Heap Máximo   (foco do artigo), o valor de um nó é sempre   maior ou igual   ao valor de seus filhos. Isso garante que o maior elemento da estrutura esteja sempre na raiz.
    * Em um   Heap Mínimo  , a regra é inversa: o valor de um nó é sempre menor ou igual ao de seus filhos.

2.    Propriedade da Forma (Shape Property):  
    * O Heap deve ser uma   árvore binária completa ou quase-completa  , com os nós do último nível preenchidos da esquerda para a direita, sem "buracos".
    *   Importância:   Essa propriedade garante que a altura da árvore (`h`) seja sempre a mínima possível, ou seja, `O(log n)`. É essa característica que fundamenta a eficiência das suas operações.

#### 3. Implementação com Array

Diferente de outras árvores que usam nós e ponteiros, o Heap é elegantemente implementado com um   array  . Isso é possível graças à sua propriedade de ser uma árvore completa ou quase-completa.

*   Mapeamento:   A árvore é representada no array através de um percurso em largura. A raiz fica no índice `0`, seus filhos nos índices `1` e `2`, os nós do próximo nível nos índices `3, 4, 5, 6`, e assim por diante.
*   Navegação por Fórmulas:   A navegação (pai, filho esquerdo, filho direito) é feita matematicamente a partir de um índice `i`:
    *   Pai:   `(i - 1) / 2`
    *   Filho Esquerdo:   `2 * i + 1`
    *   Filho Direito:   `2 * (i + 1)`

#### 4. Operações Principais

  a) Inserção (`add`) - Complexidade `O(log n)`  

O processo, conhecido como   sift-up   ou "subir", segue dois passos:
1.  O novo elemento é adicionado na primeira posição livre do array (índice `tail + 1`), garantindo a propriedade da forma.
2.  Para restaurar a propriedade de ordem, o novo elemento é comparado com seu pai. Se for maior, eles trocam de lugar. Esse processo se repete, "subindo" o elemento na árvore, até que ele seja menor que seu pai ou chegue à raiz.

  b) Remoção (`remove` ou `extractMax`) - Complexidade `O(log n)`  

A remoção em um Heap sempre retira o elemento de maior prioridade, ou seja, a   raiz  . O processo envolve uma rotina crucial chamada   heapify  :
1.  O valor da raiz (o máximo) é salvo para ser retornado.
2.  O   último elemento   do heap (no índice `tail`) é movido para a posição da raiz (índice `0`).
3.  O tamanho do heap é decrementado (`tail--`).
4.  A propriedade de ordem é restaurada através do   heapify   (ou   sift-down   / "descer") a partir da nova raiz:
    * O nó é comparado com seus filhos. Se for menor que um deles, é trocado pelo   maior dos filhos  .
    * Esse processo de "descer" continua recursivamente até que o nó seja maior que ambos os filhos ou se torne uma folha.

  c) Construção de um Heap (`buildHeap`)  

É possível transformar um array qualquer em um Heap de forma eficiente.
*   Algoritmo:   O método `buildHeap` aplica a rotina `heapify` sucessivamente, começando pelo   pai da última folha   e movendo-se para trás, até chegar à raiz (índice `0`).
*   Justificativa:   Iniciar nesse ponto garante que, quando o `heapify` é chamado em um nó, suas subárvores já são Heaps válidos. Não é necessário começar pelas folhas, pois elas, por si só, já satisfazem a propriedade de Heap.


-----

### Parte 1: Principais Perguntas de Prova sobre Heap


  1. Quais são as duas propriedades fundamentais que definem um Heap? Explique a importância de cada uma.  

  *   Resposta:  
    1.    Propriedade de Ordem (Heap Property):   Em um Max-Heap, o valor de um nó é sempre   maior ou igual   ao de seus filhos. A importância é que essa regra garante que o elemento de maior valor (ou maior prioridade) estará sempre na raiz da árvore, permitindo acesso a ele em tempo constante `O(1)`.
    2.    Propriedade da Forma (Shape Property):   O Heap deve ser uma   árvore binária completa ou quase-completa  , preenchida da esquerda para a direita. A importância é que essa forma garante que a altura da árvore será sempre a mínima possível (`O(log n)`), o que torna as operações de inserção e remoção extremamente eficientes.

  2. Por que a implementação de um Heap é geralmente feita com um array em vez de nós com ponteiros?  

  *   Resposta:   A implementação com array é possível e mais eficiente graças à   Propriedade da Forma  . Como a árvore é sempre completa ou quase-completa, não há "buracos" na sua estrutura. Isso permite mapear a árvore perfeitamente para um array (percorrendo-a em largura), o que economiza memória (não há necessidade de ponteiros `left`, `right`, `parent`) e permite calcular a posição de pais e filhos com fórmulas matemáticas simples.

  3. Descreva o algoritmo para inserir um novo elemento em um Max-Heap. Qual sua complexidade?  

  *   Resposta:  
    1.  O novo elemento é adicionado na primeira posição livre ao final do array, mantendo a árvore quase-completa.
    2.  Para restaurar a ordem, o novo elemento "sobe" na árvore (processo de   sift-up  ): ele é comparado com seu pai e, se for maior, eles trocam de lugar.
    3.  Esse processo se repete até que o elemento seja menor que seu pai ou chegue à raiz.
    <!-- end list -->
      * A complexidade é   `O(log n)`  , pois, no pior caso, o elemento sobe todo o caminho da base até a raiz, um caminho igual à altura da árvore.

  4. Como funciona a remoção do elemento máximo de um Max-Heap? Qual a função da rotina `heapify` nesse processo?  

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
public class Heapsort {

    public void sort(int[] arr) {
        int n = arr.length;

        // Fase 1: Construir o Max-Heap (reorganizar o array)
        // Começa do pai do último elemento e vai até a raiz
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Fase 2: Extrair elementos um por um do heap
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual (maior elemento) para o fim
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Chama heapify na heap reduzida para restaurar a ordem
            heapify(arr, i, 0);
        }
    }

    /*  
     * Função para aplicar a rotina heapify (sift-down)
     * @param arr O array
     * @param n   O tamanho do heap
     * @param i   O índice da raiz da sub-árvore a ser corrigida
     */
    void heapify(int[] arr, int n, int i) {
        int largest = i;       // Inicializa o maior como a raiz
        int left = 2 * i + 1;  // Índice do filho esquerdo
        int right = 2 * i + 2; // Índice do filho direito

        // Se o filho esquerdo for maior que a raiz
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Se o filho direito for maior que o maior até agora
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // Se o maior não for a raiz
        if (largest != i) {
            // Troca a raiz com o maior
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursivamente aplica heapify na sub-árvore afetada
            heapify(arr, n, largest);
        }
    }
}
```