# Árvores B (B-Trees)

## Motivação: O Problema do Armazenamento Secundário

### **O Desafio**
- **Memória Secundária** (HDs, SSDs) é **muito mais lenta** que RAM
- **Leitura de blocos/páginas** é uma operação custosa
- Árvores binárias fazem muitos acessos pequenos ao disco

### **A Solução: Árvores B**
- **Nós maiores** que armazenam múltiplas chaves
- **Árvore mais larga e "achatada"** → menor altura
- **Menos acessos ao disco** para encontrar dados

---

## Definição Formal (Bayer e McCreight, 1972)

Para qualquer inteiro positivo par **M**, uma **árvore B de ordem M** é uma árvore com as seguintes propriedades:

### **Propriedades Fundamentais**

1. **Capacidade Máxima**
   - Cada nó contém no máximo **M-1 chaves**
   - Cada nó tem no máximo **M filhos**

2. **Capacidade Mínima**  
   - A raiz contém no mínimo **2 chaves**
   - Demais nós contêm no mínimo **⌈M/2⌉ chaves**
   - Nós internos têm no mínimo **⌈M/2⌉ filhos**

3. **Estrutura Balanceada**
   - Cada nó não-folha tem **um filho para cada chave + 1**
   - **Todos os caminhos** da raiz até folhas têm o **mesmo comprimento**
   - Árvore **perfeitamente balanceada**

4. **Ordenação**
   - Chaves dentro de cada nó estão **ordenadas**
   - Propriedade BST: valores à esquerda < chave < valores à direita

---

## Estrutura e Características

### **Estrutura de um Nó**
```java
class BTreeNode {
    int[] keys;           // Array de chaves (tamanho M-1)
    BTreeNode[] children; // Array de filhos (tamanho M)
    int numKeys;          // Número atual de chaves
    boolean isLeaf;       // Se é folha ou não
}
```

### **Propriedades de Ordem M**

| Propriedade | Valor | Descrição |
|-------------|-------|-----------|
| **Chaves por nó** | 0 a M-1 | Máximo de chaves |
| **Filhos por nó** | 0 a M | Máximo de filhos |
| **Chaves mín. (raiz)** | 2 | Exceção para raiz |
| **Chaves mín. (outros)** | ⌈M/2⌉ | Garante preenchimento |
| **Filhos mín.** | ⌈M/2⌉ | Para nós internos |

### **Exemplo: B-Tree de Ordem 4**
- **Máximo**: 3 chaves, 4 filhos por nó
- **Mínimo**: 2 chaves (raiz), 2 chaves (outros nós)

```
       [10, 20]
     /    |    \
  [5,8]  [15]  [25,30,35]
```

---

## Operações Principais

### **Busca**
```java
public BTreeNode search(BTreeNode node, int key) {
    int i = 0;
    
    // Encontra posição da chave ou primeira maior
    while (i < node.numKeys && key > node.keys[i]) {
        i++;
    }
    
    // Encontrou a chave
    if (i < node.numKeys && key == node.keys[i]) {
        return node;
    }
    
    // Se é folha, chave não existe
    if (node.isLeaf) {
        return null;
    }
    
    // Continua busca no filho apropriado
    return search(node.children[i], key);
}
```

### **Inserção**
**Processo complexo com splits:**

1. **Localizar** posição de inserção (sempre em folha)
2. **Inserir** na folha se há espaço
3. **Split** se nó estiver cheio:
   - Dividir chaves em dois nós
   - Promover chave do meio para pai
   - Recursivamente fazer split no pai se necessário

```java
public void insert(int key) {
    if (root.numKeys == MAX_KEYS) {
        // Raiz cheia - criar nova raiz
        BTreeNode newRoot = new BTreeNode();
        newRoot.children[0] = root;
        newRoot.isLeaf = false;
        splitChild(newRoot, 0);
        root = newRoot;
    }
    
    insertNonFull(root, key);
}

private void splitChild(BTreeNode parent, int index) {
    BTreeNode fullChild = parent.children[index];
    BTreeNode newChild = new BTreeNode();
    
    // Move metade das chaves para novo nó
    int midIndex = MIN_KEYS;
    
    // Copia chaves superiores
    for (int i = 0; i < MIN_KEYS; i++) {
        newChild.keys[i] = fullChild.keys[i + midIndex + 1];
    }
    
    // Se não é folha, copia filhos também
    if (!fullChild.isLeaf) {
        for (int i = 0; i <= MIN_KEYS; i++) {
            newChild.children[i] = fullChild.children[i + midIndex + 1];
        }
    }
    
    // Atualiza contadores
    newChild.numKeys = MIN_KEYS;
    fullChild.numKeys = MIN_KEYS;
    newChild.isLeaf = fullChild.isLeaf;
    
    // Move filhos no pai para abrir espaço
    for (int i = parent.numKeys; i > index; i--) {
        parent.children[i + 1] = parent.children[i];
    }
    parent.children[index + 1] = newChild;
    
    // Move chaves no pai
    for (int i = parent.numKeys - 1; i >= index; i--) {
        parent.keys[i + 1] = parent.keys[i];
    }
    
    // Promove chave do meio
    parent.keys[index] = fullChild.keys[midIndex];
    parent.numKeys++;
}
```

### **Remoção**
**Operação mais complexa:**

1. **Casos base**: remoção de folha
2. **Merge**: juntar nós com poucos elementos
3. **Borrow**: "emprestar" de irmão com elementos extras
4. **Manter propriedades** de ordem mínima

---

## Análise de Complexidade

### **Complexidades Temporais**

| Operação | Complexidade | Observação |
|----------|--------------|------------|
| **Busca** | O(log_M n) | Altura logarítmica |
| **Inserção** | O(log_M n) | Pode causar splits |
| **Remoção** | O(log_M n) | Pode causar merges |

### **Altura da Árvore**
- **Altura máxima**: log_⌈M/2⌉(n)
- **Muito menor** que árvore binária: log_2(n)
- **Exemplo**: M=100, n=1.000.000
  - B-Tree: altura ≈ 3
  - BST: altura ≈ 20

---

## Aplicações Práticas

### **Sistemas de Banco de Dados**
- **Índices primários e secundários**
- PostgreSQL, MySQL, Oracle utilizam B+ Trees
- **Páginas de disco** mapeadas para nós da árvore

### **Sistemas de Arquivos**
- **HFS+** (Mac), **NTFS** (Windows)
- Organização eficiente de diretórios
- **Metadados** armazenados em B-Trees

### **Motores de Busca**
- **Índices invertidos** para texto
- Busca rápida por termos
- **Compressão** e otimização de espaço

---

## B-Tree vs Outras Estruturas

| Característica | B-Tree | BST | AVL | Hash |
|----------------|--------|-----|-----|------|
| **Altura** | log_M n | log n* | log n | O(1)* |
| **Acessos disco** | Poucos | Muitos | Muitos | Poucos |
| **Busca ordenada** | ✅ | ✅ | ✅ | ❌ |
| **Range queries** | ✅ | ✅ | ✅ | ❌ |
| **Memória sec.** | ✅ | ❌ | ❌ | ✅ |

*Condições ideais

---

## Variações Importantes

### **B+ Tree**
- **Todas as chaves** nas folhas
- **Nós internos** apenas para navegação
- **Lista ligada** entre folhas → range queries eficientes

### **B* Tree**
- **Nodes 2/3 cheios** (ao invés de 1/2)
- **Maior utilização** de espaço
- **Menos splits** durante inserção

---

## Questões para Prova

### **1. Por que B-Trees foram criadas?**
**R:** Para otimizar acesso a dados em memória secundária (discos), reduzindo o número de operações de I/O através de nós maiores que diminuem a altura da árvore.

### **2. Qual a principal diferença entre B-Tree e BST?**
**R:** B-Trees têm nós com múltiplas chaves (M-1) e filhos (M), resultando em árvores mais largas e baixas. BSTs têm no máximo 2 filhos por nó.

### **3. O que acontece quando um nó fica cheio durante inserção?**
**R:** Ocorre um split: o nó é dividido em dois, a chave do meio é promovida para o pai, e o processo pode se propagar até a raiz.

### **4. Por que a altura é O(log_M n)?**
**R:** Cada nó tem entre ⌈M/2⌉ e M filhos, garantindo que a árvore seja balanceada com altura logarítmica na base M, que é maior que 2.

---

## Exemplo Prático

### **Inserção em B-Tree de Ordem 3 (máximo 2 chaves):**

**Sequência**: 10, 20, 5, 6, 12, 30, 7, 17

1. **Inserir 10**: `[10]`
2. **Inserir 20**: `[10, 20]`
3. **Inserir 5**: `[5, 10, 20]` → **SPLIT!**
   ```
      [10]
     /    \
   [5]    [20]
   ```
4. **Inserir 6**: `[5, 6]` e `[20]`
5. **Inserir 12**: `[5, 6]` e `[12, 20]`
6. **Inserir 30**: `[5, 6]` e `[12, 20, 30]` → **SPLIT!**
   ```
       [10, 20]
      /    |    \
    [5,6] [12] [30]
   ```

**Resultado**: Árvore balanceada com altura mínima para os dados inseridos.

---

*Material de estudo para EDA-LEDA | UFCG*

Um nó interno com 

k filhos contém exatamente k-1 chaves.

Consequentemente, todo nó (exceto a raiz) tem entre ⌈m/2⌉ - 1 e m-1 chaves.


Ordenação: As chaves dentro de cada nó são mantidas em ordem crescente e funcionam como "separadores" para as sub-árvores (filhos).

Operações Principais

Busca: A busca é similar à de uma árvore binária. Começa na raiz, procura linearmente pela chave dentro do nó. Se não encontra, segue pelo ponteiro do filho que está entre as duas chaves que "cercam" o valor buscado.




Inserção: A inserção sempre ocorre em um nó folha.

Se a folha não está cheia, a chave é simplesmente adicionada em ordem.

Se a folha está cheia, ocorre um 

split (divisão): o nó é dividido em dois, a chave mediana é promovida (sobe) para o nó pai, e esse processo pode se propagar até a raiz. Se a raiz sofre split, uma nova raiz é criada e a altura da árvore aumenta em 1.


Remoção: É mais complexa, pois a chave pode estar em qualquer nó. A remoção pode causar 

underflow (um nó ficar com menos chaves que o mínimo permitido). Para corrigir, a árvore realiza duas operações possíveis:



Redistribuição: "Pega emprestada" uma chave de um nó irmão adjacente que tenha chaves sobrando.


Concatenação (Fusão): Se os irmãos também estiverem no limite mínimo, o nó se funde com um irmão e com uma chave "descida" do pai.



Parte 2: Perguntas e Respostas Teóricas
Pergunta 1: Qual foi a principal motivação para a criação das Árvores B e como sua estrutura resolve esse problema?


Resposta: A principal motivação foi o alto custo de acesso a dados em memórias secundárias, como discos rígidos. A Árvore B resolve isso utilizando nós (páginas) que armazenam um grande número de chaves. Isso torna a árvore mais larga e com menor altura, reduzindo drasticamente o número de acessos a disco necessários para encontrar um dado.



Pergunta 2: O que significa dizer que um nó em uma Árvore B de ordem m=5 está em "underflow"?
Resposta: A ordem m=5 significa que um nó pode ter no máximo 5 filhos e 4 chaves. O número mínimo de chaves para um nó (que não seja a raiz) é ⌈m/2⌉ - 1, ou seja, ⌈5/2⌉ - 1 = 3 - 1 = 2. Um nó está em "underflow" quando, após uma remoção, ele fica com menos de 2 chaves, violando as propriedades da Árvore B.

Pergunta 3: Descreva o que acontece durante a inserção em uma Árvore B quando o nó folha de destino já está cheio. Quais são os nomes dos dois processos principais envolvidos?
Resposta: Quando o nó folha está cheio, ele precisa ser dividido. Os dois processos são:


Split (Divisão): O conjunto de chaves (as existentes mais a nova) é ordenado, e a chave mediana é escolhida. As chaves menores que a mediana formam um novo nó, e as maiores formam outro.



Promote (Promoção): A chave mediana sobe para o nó pai para servir como separador entre os dois novos nós criados. Se o pai também ficar cheio, o processo se repete recursivamente.


Pergunta 4: Como uma Árvore B se mantém sempre balanceada?


Resposta: A Árvore B se mantém balanceada principalmente através da sua propriedade fundamental de que todas as folhas devem estar no mesmo nível. As operações de  split (na inserção) e fusão/redistribuição (na remoção) são projetadas para garantir que essa propriedade nunca seja violada. A árvore cresce "para cima" (uma nova raiz é criada quando a antiga sofre split) e encolhe "de cima" (a raiz é removida quando seus filhos se fundem), mantendo assim todas as folhas sempre na mesma profundidade.

Com base no material "Arvore B.pdf" fornecido, as operações básicas em uma **Árvore B** (Pesquisa, Inserção e Remoção) ocorrem da seguinte maneira:

### Pesquisa (Busca)

  A pesquisa em uma Árvore B é uma generalização da busca em uma árvore binária de busca (BST)[cite: 1009].   O processo se adapta ao fato de que cada nó, também chamado de página, pode conter múltiplas chaves ordenadas.

O algoritmo funciona assim:
1.  A busca começa no nó raiz.
2.    Dentro do nó atual, é feita uma pesquisa (geralmente linear) para verificar se o elemento procurado está entre as chaves do nó[cite: 1012, 1013].
3.    Se o elemento for encontrado, a busca termina, retornando o nó e a posição da chave nele[cite: 1019].
4.  Se o elemento não for encontrado no nó atual, o algoritmo determina qual filho seguir.   Ele usa as chaves ordenadas como separadores para decidir a sub-árvore correta[cite: 1014, 930].   Por exemplo, para encontrar um valor `V`, o algoritmo desce para o filho que está entre as chaves `x1` e `x2`, onde `x1 < V < x2`[cite: 1015].
5.    O processo se repete recursivamente até que o elemento seja encontrado ou até que se chegue a um nó folha sem o elemento, indicando que ele não está na árvore[cite: 1051].

### Inserção

  As inserções em uma Árvore B acontecem sempre nos nós folha.   O objetivo é manter a árvore balanceada, garantindo que todas as folhas permaneçam no mesmo nível.

O algoritmo de inserção segue estes passos:
1.    Primeiro, o algoritmo localiza o nó folha apropriado para a inserção do novo elemento, usando um procedimento de busca[cite: 1066].
2.    **Caso 1: O nó folha não está cheio.** Se o nó folha tem espaço livre, o novo elemento é simplesmente inserido em sua posição correta, mantendo a ordem das chaves[cite: 1067].
3.    **Caso 2: O nó folha está cheio.** Se o nó não tem espaço, ocorre um processo de divisão chamado **split**[cite: 1069].
    *   A chave mediana do conjunto (chaves existentes + nova chave) é selecionada[cite: 1075].
    *   Essa chave mediana é movida para o nó pai, um processo chamado **promote** (promoção)[cite: 1070].
    *   O nó original é dividido em dois novos nós: um com as chaves menores que a mediana e outro com as chaves maiores[cite: 1076, 1077].
    *   Se a promoção da chave mediana fizer com que o nó pai também fique cheio, o processo de split e promote se repete recursivamente, subindo na árvore[cite: 1071].   Se o split ocorrer na raiz, uma nova raiz é criada, e a altura da árvore aumenta[cite: 1080].

### Remoção

  A remoção é análoga à inserção, mas com mais casos a serem considerados, pois a chave pode ser removida de qualquer nó (folha ou interno)[cite: 1148, 1149].   O principal desafio é garantir que, após a remoção, nenhum nó viole a regra do número mínimo de chaves (condição de **underflow**).

O processo geral depende de onde a chave está:

1.  **Remoção em um Nó Folha:**
    *   A chave é removida do nó[cite: 1164].
    * Se o nó ainda tiver o número mínimo de chaves, a operação termina.
    *   Se a remoção causar **underflow**, a árvore precisa ser rebalanceada  . Isso é feito de duas maneiras:
        *   **Redistribuição:** Se um nó irmão adjacente tiver mais chaves que o mínimo, uma chave do irmão é movida para o pai, e uma chave do pai é movida para o nó com underflow  .
        *   **Concatenação (Fusão):** Se os nós irmãos também estiverem no limite mínimo de chaves, o nó com underflow é fundido com um de seus irmãos  .   Uma chave separadora do nó pai desce para se juntar ao novo nó fundido[cite: 1170]. Essa operação no pai pode, por sua vez, causar um underflow nele, propagando o problema para cima.

2.  **Remoção em um Nó Interno:**
    *   A remoção de uma chave em um nó interno é mais complexa porque essa chave atua como um separador para suas sub-árvores.
    *   A chave a ser removida é substituída por um novo separador, que geralmente é o seu sucessor imediato (o menor valor na sub-árvore direita) ou seu predecessor (o maior valor na sub-árvore esquerda).
    *   A chave substituta é então removida de sua posição original (que será em um nó folha), transformando o problema em um caso de remoção em nó folha, que pode então levar a redistribuições ou fusões.
