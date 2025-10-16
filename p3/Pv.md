# Árvores Red-Black (Preto-Vermelho)

## O que é uma Árvore Red-Black?

Uma **Árvore Red-Black (PV)** é um tipo especial de **árvore binária de busca auto-balanceada**. Sua característica distintiva é que **cada nó armazena uma cor adicional: vermelho ou preto**.

### **Objetivo das Cores**
- Manter a árvore **"aproximadamente balanceada"**
- Garantir que o **caminho mais longo** nunca seja mais que o **dobro do caminho mais curto**
- Assegurar complexidade **O(log n)** para todas as operações

---

## As 5 Propriedades Fundamentais (Invariantes)

Para que uma árvore seja uma **Red-Black válida**, deve obedecer a **5 regras fundamentais**:

### **Propriedade da Cor**
> Todo nó é **vermelho** ou **preto**

### **Propriedade da Raiz**  
> O nó **raiz** é sempre **preto**

### **Propriedade da Folha**
> Toda **folha** (representada como NIL ou `null`) é **preta**

### **Propriedade do Nó Vermelho**
> Se um nó é **vermelho**, então **ambos os seus filhos são pretos**
> 
> *(Impede dois nós vermelhos consecutivos)*

### **Propriedade da Altura Preta**
> Para cada nó, **todos os caminhos** até suas folhas descendentes contêm o **mesmo número de nós pretos**

---

## Altura Preta (Black-Height)

### **Definição**
A **altura preta** de um nó é o **número de nós pretos** em qualquer caminho simples até uma folha (sem contar o próprio nó).

### **Importância**
- **Propriedade 5** garante que esse número seja igual para todos os caminhos
- A **altura preta da árvore** = altura preta da raiz
- Fundamenta a garantia de balanceamento

### **Exemplo Visual**
```
       B(5)     ← altura preta = 2
      /    \
   R(3)    B(8)  ← altura preta = 1
   /  \    /  \
 B(1) B(4) NIL NIL ← altura preta = 0
```

---

## Questões Fundamentais para Prova

### **1. O que é uma Árvore Red-Black e qual seu objetivo?**

**Resposta:**
É uma **árvore binária de busca** onde cada nó possui uma **cor (vermelho ou preto)**. Seu objetivo é **manter-se aproximadamente balanceada** através de um conjunto de **regras (invariantes)**, garantindo operações em tempo **O(log n)**.

---

### **2. Como verificar se uma árvore é Red-Black válida?**

**Resposta:**
Verificar se **todas as 5 propriedades** são satisfeitas:

1. **✅ Verificar a Raiz**: Cor da raiz = preta
2. **🔍 Verificar Nós Vermelhos**: Para cada nó vermelho, ambos filhos são pretos
3. **📏 Verificar Altura Preta**: Calcular recursivamente - todos caminhos de qualquer nó até folhas devem ter o mesmo número de nós pretos
4. **🍃 Considerar Folhas**: Nós `null` são sempre pretos

```java
public boolean isValidRedBlack(Node node) {
    // Verificação da raiz
    if (root.color != BLACK) return false;
    
    // Verificação recursiva
    return checkRedBlackProperties(root) != -1;
}

private int checkRedBlackProperties(Node node) {
    // Folha (NIL) - altura preta = 0
    if (node == null) return 0;
    
    // Verifica propriedade do nó vermelho
    if (node.color == RED) {
        if ((node.left != null && node.left.color == RED) ||
            (node.right != null && node.right.color == RED)) {
            return -1; // Violação: vermelho com filho vermelho
        }
    }
    
    // Calcula altura preta recursivamente
    int leftHeight = checkRedBlackProperties(node.left);
    int rightHeight = checkRedBlackProperties(node.right);
    
    // Se alguma subárvore é inválida
    if (leftHeight == -1 || rightHeight == -1) return -1;
    
    // Verifica se altura preta é igual
    if (leftHeight != rightHeight) return -1;
    
    // Retorna altura preta + 1 se nó atual é preto
    return leftHeight + (node.color == BLACK ? 1 : 0);
}
```

---

### **3. Como determinar a altura preta de uma PV?**

**Resposta:**
A **altura preta** é determinada contando os **nós pretos** em qualquer caminho da raiz até uma folha (excluindo a raiz):

```java
public int blackHeight(Node node) {
    if (node == null) return 0;
    
    // Escolhe qualquer caminho (esquerda por convenção)
    int height = blackHeight(node.left);
    
    // Adiciona 1 se o nó atual é preto
    return height + (node.color == BLACK ? 1 : 0);
}
```

**📝 Processo:**
1. Começar da raiz
2. Seguir qualquer caminho até folha
3. Contar apenas nós pretos
4. Propriedade 5 garante que todos caminhos têm a mesma contagem

---

### **4. Qual a relação entre altura da árvore e altura preta?**

**Resposta:**
- **Altura total** ≤ **2 × altura preta**
- **Demonstração**: No pior caso, alternamos nós vermelhos e pretos
- **Exemplo**: altura preta = 3 → altura total ≤ 6

```
Caminho alternado máximo:
B → R → B → R → B → R → NIL
(3 pretos, altura total = 6)
```

---

### **5. Por que nós vermelhos não podem ter filhos vermelhos?**

**Resposta:**
- **Evita desbalanceamento excessivo**
- **Controla altura máxima** da árvore
- **Garante** que altura ≤ 2 × altura preta
- **Simplifica algoritmos** de inserção e remoção

---

## Operações de Balanceamento

### **Recoloração**
Mudança de cores para restaurar propriedades:
```java
private void recolor(Node node) {
    node.color = BLACK;
    node.parent.color = RED;
    getUncle(node).color = BLACK;
}
```

### **Rotações**
Reestruturação física da árvore:
```java
private void rotateLeft(Node node) {
    Node newRoot = node.right;
    node.right = newRoot.left;
    newRoot.left = node;
    
    // Atualizar cores conforme casos específicos
    updateColorsAfterRotation(node, newRoot);
}
```

---

## Casos de Inserção

### **Caso 1: Pai é Preto**
- **Ação**: Nenhuma (propriedades mantidas)

### **Caso 2: Pai e Tio são Vermelhos**
- **Ação**: Recoloração (pai e tio → preto, avô → vermelho)

### **Caso 3: Pai Vermelho, Tio Preto**
- **Ação**: Rotação + recoloração baseada na configuração

---

## Complexidades Garantidas

| Operação | Complexidade | Observação |
|----------|--------------|------------|
| **Busca** | O(log n) | Altura limitada |
| **Inserção** | O(log n) | + rebalanceamento |
| **Remoção** | O(log n) | + rebalanceamento |
| **Altura máxima** | 2 × log(n+1) | Prova matemática |

---

## Red-Black vs AVL

| Característica | Red-Black | AVL |
|----------------|-----------|-----|
| **Balanceamento** | Aproximado | Rigoroso |
| **Altura máxima** | 2 × log n | 1.44 × log n |
| **Rotações inserção** | ≤ 2 | ≤ 2 |
| **Rotações remoção** | ≤ 3 | ≤ log n |
| **Uso prático** | Bibliotecas | Busca intensiva |

---

## Aplicações Práticas

### **Bibliotecas Padrão**
- **C++ STL**: `std::map`, `std::set`
- **Java**: `TreeMap`, `TreeSet`  
- **Linux Kernel**: Scheduler, memory management

### **Bancos de Dados**
- **Índices secundários**
- **Estruturas de cache**
- **Otimização de consultas**

---

## Exemplo Prático de Verificação

### **Árvore para Análise:**
```
       B(10)
      /     \
   R(5)     B(15)
   /  \     /   \
 B(3) B(7) R(12) R(20)
```

### **Verificação Passo a Passo:**

1. **✅ Raiz preta**: 10 é preto ✓
2. **✅ Nós vermelhos**: 5, 12, 20 têm filhos pretos ✓
3. **Altura preta**:
   - Caminho 10→5→3: 2 pretos
   - Caminho 10→5→7: 2 pretos  
   - Caminho 10→15→12: 2 pretos
   - Caminho 10→15→20: 2 pretos ✓

**Resultado**: Árvore Red-Black válida!

---

### **Estratégia de Verificação**
1. Verificar raiz (propriedade 2)
2. Percorrer árvore verificando nós vermelhos (propriedade 4)
3. Calcular altura preta de todos caminhos (propriedade 5)
4. Confirmar igualdade das alturas pretas

---

*Material de estudo para EDA-LEDA | UFCG*

####  Dada uma PV, como se determina a sua altura preta?  
  Resposta:  
 A altura preta de uma Árvore Red-Black é a altura preta de seu nó raiz .  Para calcular a altura preta de um nó qualquer, basta contar o número de nós pretos em um caminho simples a partir dele até uma folha descendente (NIL) . Devido à propriedade 5, esse número será o mesmo para todos os caminhos.

 Por exemplo, na árvore da página 7, a altura preta do nó 7 (a raiz) é 2, pois qualquer caminho da raiz até uma folha (NIL) passa por 2 nós pretos (sem contar a própria raiz) .

---

####   Pergunta 4: (Tópico do Professor) Dado um nó recém-adicionado em uma PV, qual caso da inserção teria que ser aplicado?  

  Resposta:  
 A análise do caso de inserção depende da configuração local do novo nó (N), seu pai (P), seu avô (G) e seu tio (U) .
1.     Coloração Inicial:   O novo nó é sempre inserido com a cor   vermelha   .
2.    Análise dos Casos:  
    *   Caso 1:   Se o novo nó (N) é a   raiz   da árvore.  A correção é simplesmente mudar sua cor para preto .
    *   Caso 2:   Se o pai (P) do novo nó é   preto  . Nenhuma propriedade é violada, e a árvore continua válida.  Nenhuma correção é necessária .
    *   Caso 3:   Se o pai (P) é   vermelho   e o tio (U) também é   vermelho  .  A correção envolve recolorir o pai e o tio para preto, o avô (G) para vermelho, e reavaliar a árvore a partir do avô .
    *   Caso 4 e 5:   Se o pai (P) é   vermelho   e o tio (U) é   preto  . A correção dependerá se o novo nó (N) forma uma "linha" ou um "triângulo" com seu pai e avô.  Isso exigirá   rotações   e recolorações para balancear a árvore .  O Caso 4 transforma um "triângulo" em uma "linha" através de uma rotação   , e o Caso 5 resolve a "linha" com outra rotação e recoloração .

Portanto, para determinar o caso, você deve verificar a cor do pai e do tio do nó recém-adicionado.
 --------ALTURA 

A altura preta de um nó é o número de nós pretos contidos em qualquer caminho simples daquele nó até uma de suas folhas descendentes (NIL). A folha NIL em si não é contada na soma

Uniformidade Forçada: A Propriedade 5 diz que, de qualquer nó, o número de nós pretos até qualquer folha descendente é sempre o mesmo. Isso força uma uniformidade estrutural. Não importa qual ramo você siga para baixo, você sempre passará pela mesma quantidade de "marcos" pretos.


Limitando a Diferença de Altura: Agora, vamos combinar isso com a Propriedade 4 (um nó vermelho deve ter filhos pretos ).

O caminho mais curto possível de um nó até uma folha seria um caminho contendo apenas nós pretos. A altura desse caminho seria exatamente a altura preta (bh).

O caminho mais longo possível seria um caminho que alterna entre nós pretos e vermelhos (ex: Preto -> Vermelho -> Preto -> Vermelho...). Como não podemos ter dois nós vermelhos seguidos, o número máximo de nós vermelhos em um caminho é, no máximo, igual ao número de nós pretos.

Consequência: Isso significa que o caminho mais longo (comprimento bh de nós pretos + até bh de nós vermelhos) nunca é mais do que o dobro do caminho mais curto (comprimento bh de nós pretos).

É essa garantia matemática que impede a árvore de se tornar desbalanceada (como uma lista encadeada). Ao forçar a quantidade de nós pretos a ser constante em todos os caminhos e limitando como os nós vermelhos podem ser adicionados, a árvore mantém sua altura total próxima de O(logn), garantindo a eficiência das operações.

Esta propriedade é crucial porque estabelece uma restrição estrutural rígida na árvore. Ao forçar que todos os caminhos de um nó a suas folhas tenham o mesmo número de nós pretos, ela garante que a diferença de altura entre o caminho mais longo e o mais curto seja limitada. O caminho mais longo pode ter, no máximo, o dobro da altura do caminho mais curto. Isso impede que a árvore se degenere e assegura que a sua altura total seja logarítmica em relação ao número de nós, o que é a base para a eficiência de O(logn) de suas operações.

### O que é uma Árvore Preto-Vermelho (PV)?

 Uma Árvore Preto-Vermelho (ou Rubro-Negra) é um tipo de árvore binária de busca que se auto-balanceia .  Cada nó armazena uma informação extra: uma cor, que pode ser preta ou vermelha .

 Através de um conjunto de regras (propriedades ou invariantes), a árvore garante uma configuração aproximadamente balanceada, onde a altura de um filho nunca é mais que o dobro da altura do outro . Para ser uma Árvore PV válida, ela deve seguir 5 propriedades:

*  **Propriedade 1:** Cada nó é preto ou vermelho .
*  **Propriedade 2:** A raiz é preta.
*  **Propriedade 3:** Toda folha (NIL) é preta .
*  **Propriedade 4:** Se um nó é vermelho, todos os seus filhos são pretos .
*  **Propriedade 5:** Todo caminho de um nó a uma folha.

### Pesquisa (Busca)

 A pesquisa em uma Árvore PV é executada de forma idêntica à de uma árvore binária de busca (BST)[cite: 265].  A cor dos nós não interfere no algoritmo de busca[cite: 265]. O processo segue os seguintes passos:
1.  Comece a busca no nó raiz.
2.  Compare o valor procurado com a chave do nó atual.
3.  Se o valor for menor, continue a busca na sub-árvore esquerda.
4.  Se o valor for maior, continue a busca na sub-árvore direita.
5.  Se o valor for igual, a chave foi encontrada.
6.  Se chegar a uma folha (NIL) sem encontrar o valor, ele não existe na árvore.

 Devido ao balanceamento da árvore, a complexidade de tempo desta operação é O(log n)[cite: 266].

### Inserção

A inserção combina a inserção de uma BST com um procedimento de correção (`fix-up`) para manter as propriedades da Árvore PV.  Um novo nó tem sempre a cor vermelha inicial.

**Passo a Passo da Inserção e Correção:**

1.   **Inserção Inicial:** Insira o novo nó `N` na árvore como em uma BST comum.
2.   **Coloração Inicial:** Pinte o novo nó `N` de vermelho.
3.   **Início da Correção (`fix-up`):** Inicie o processo de verificação a partir do nó `N`.  O procedimento de correção é dividido nos seguintes casos sequenciais:

    * **Caso 1: `N` é a Raiz**
        *  Se o novo nó `N` é a raiz da árvore, sua cor é mudada para preto. Isso garante que a propriedade da raiz preta seja mantida. A correção termina aqui. Se não for a raiz, prossiga para o Caso 2.

    * **Caso 2: O Pai de `N` é Preto**
        *  Se o pai do nó `N` já for preto, a inserção de `N` (vermelho) não viola nenhuma propriedade. A árvore continua válida e a correção termina. Se o pai for vermelho, prossiga para o Caso 3.

    * **Caso 3: O Pai e o Tio de `N` são Vermelhos**
        *  Se o pai (`P`) de `N` é vermelho e o tio (`U`, irmão do pai) também é vermelho, uma recoloração é realizada:
            1.   O pai `P` é recolorido para preto.
            2.   O tio `U` é recolorido para preto.
            3.   O avô `G` é recolorido para vermelho.
            4.   O processo de `fix-up` é reiniciado, agora focando no avô `G` como o novo `N`.

    * **Caso 4: O Pai é Vermelho, o Tio é Preto e formam um "Triângulo"**
        * Esta situação ocorre se o tio de `N` for preto e `N` e seu pai `P` estiverem em lados opostos em relação ao avô `G` (ex: `P` é filho esquerdo e `N` é filho direito).
        *  Uma rotação é aplicada no pai `P` para transformar a configuração em uma "linha".
        *  Após a rotação, a correção continua no Caso 5.

    * **Caso 5: O Pai é Vermelho, o Tio é Preto e formam uma "Linha"**
        * Esta situação ocorre se o tio de `N` for preto e `N` e seu pai `P` estiverem do mesmo lado em relação ao avô `G` (ex: `P` e `N` são ambos filhos esquerdos).
        * A correção é feita com uma recoloração e uma rotação:
            1.   O pai `P` é recolorido para preto.
            2.   O avô `G` é recolorido para vermelho.
            3.   Uma rotação é aplicada no avô `G`.
        * Após esses passos, a violação é corrigida e o processo termina.

### Remoção

 A remoção em uma Árvore PV também utiliza a lógica de remoção de uma BST, mas requer um procedimento de `fix-up` para garantir que as propriedades não sejam violadas[cite: 650, 651].

**Passo a Passo Geral da Remoção:**

1.  **Remoção Inicial:** Realize a remoção do nó como em uma BST.

2.  **Análise do Caso:**
    *  **Caso Simples: Remoção de uma Folha Vermelha:** Se o nó removido for uma folha vermelha, nenhuma propriedade da árvore é violada[cite: 655, 678]. A altura preta de todos os caminhos permanece a mesma. A operação termina aqui.

    *  **Caso Problemático: Remoção de um Nó Preto:** A remoção de um nó preto é a principal causa de problemas, pois pode alterar a altura preta de um caminho, violando uma das invariantes[cite: 790, 886].
        *  Quando a remoção de um nó preto causa uma violação na altura preta, um procedimento de correção é iniciado[cite: 791].
        *  O `fix-up` da remoção foca no irmão (`sibling`) e no sobrinho mais próximo do nó que foi removido para restaurar o balanceamento[cite: 791, 860].
        *  Este processo de correção é mais complicado que o da inserção e envolve vários casos particulares que utilizam rotações e recolorações para garantir que todas as propriedades da Árvore PV voltem a ser satisfeitas[cite: 887].
