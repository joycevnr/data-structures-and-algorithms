# P3 - Estruturas Hierárquicas (Árvores)

Este módulo aborda estruturas de dados hierárquicas, focando em diferentes tipos de árvores e suas aplicações.

## Material Teórico

### Fundamentos
- **[BST.md](./BST.md)** - Árvores Binárias de Busca
  - Conceitos básicos, operações CRUD
  - Travessias (DFS: pre/in/post-order, BFS)
  - Análise de complexidade

### Árvores Balanceadas  
- **[AVL.md](./AVL.md)** - Árvores AVL Auto-Balanceadas
  - Fator de balanceamento
  - Rotações (simples e duplas)
  - Manutenção automática do balanceamento

- **[Pv.md](./Pv.md)** - Árvores Red-Black (Preto-Vermelho)
  - Propriedades das cores
  - Verificação de validade
  - Altura preta

### Estruturas Especializadas
- **[Heap.md](./Heap.md)** - Heaps e Filas de Prioridade
  - Max/Min Heap
  - Implementação com array
  - HeapSort e Build Heap

- **[BTree.md](./BTree.md)** - Árvores B (Multiway Trees)
  - Otimização para memória secundária
  - Propriedades de ordem M
  - Aplicações em bancos de dados

### Algoritmos e Travessias
- **[labs/outro.md](./labs/outro.md)** - Travessias Completas
  - DFS: In/Pre/Post-Order
  - BFS: Level-Order
  - Implementações iterativas e recursivas

---

## Implementações Práticas

### 📁 [basico/](./basico/)
Implementações fundamentais das estruturas:
- `AVL.java` - Árvore AVL completa
- `BST.java` - Árvore Binária de Busca  
- `Heap.java` - Heap/Fila de Prioridade

### 📁 [labs/](./labs/)
Laboratórios e exercícios práticos:
- **[bst-joycevnr/](./labs/bst-joycevnr/)** - Lab BST
- **[heap-joycevnr/](./labs/heap-joycevnr/)** - Lab Heap
- **[isavl-joycevnr/](./labs/isavl-joycevnr/)** - Lab verificação AVL

### 📁 [tst/](./tst/)
Algoritmos específicos e casos de teste:
- `AlturaBST.java` - Cálculo de altura
- `ArvoresSimilares.java` - Comparação de estruturas
- `BFSDireita.java` - BFS modificado (direita primeiro)
- `RotacaoAVL.java` - Sistema de rotações AVL
- `VerificadorAVL.java` - Validação de propriedades


## 🔍 Principais Algoritmos

### **Busca e Inserção**
- BST: O(log n) médio, O(n) pior caso
- AVL: O(log n) garantido
- Heap: O(log n) inserção, O(1) acesso ao máximo

### **Balanceamento**
- AVL: Rotações automáticas após modificações
- Red-Black: Recoloração e rotações
- Heap: Sift-up/Sift-down

### **Travessias**
- DFS: O(n) tempo, O(h) espaço
- BFS: O(n) tempo, O(w) espaço
- Aplicações específicas para cada tipo

---

##  Comparação de Estruturas

| Estrutura | Busca | Inserção | Remoção | Balanceamento | Uso Principal |
|-----------|-------|----------|---------|---------------|---------------|
| **BST** | O(h)* | O(h)* | O(h)* | Manual | Busca geral |
| **AVL** | O(log n) | O(log n) | O(log n) | Automático | Busca frequente |
| **Red-Black** | O(log n) | O(log n) | O(log n) | Automático | Bibliotecas |
| **Heap** | O(n) | O(log n) | O(log n) | Por estrutura | Fila prioridade |
| **B-Tree** | O(log n) | O(log n) | O(log n) | Automático | Bancos de dados |

*h = altura (log n para balanceada, n para degenerada)


* Material organizado para EDA-LEDA | UFCG*
