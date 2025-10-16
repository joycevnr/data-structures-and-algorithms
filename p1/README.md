# P1 - Algoritmos de Busca e Ordenação

## Estrutura do Módulo

### Algoritmos de Busca
- **`BuscaBinaria.java`** - Implementação recursiva da busca binária
- **Algoritmos de ordenação com análise de passos**

### Algoritmos de Ordenação Clássicos

#### **QuickSort**
- **`QuickSort2.java`** - Implementação completa com particionamento
- **`QuickSortPassoAPasso.java`** - Versão didática mostrando cada etapa

#### **MergeSort** 
- **`Merge.java`** - Implementação básica do merge
- **`MergeSortPasso.java`** - Versão com visualização de passos
- **`MergeSortPassos.java`** - Análise detalhada do processo
- **`MergeSisu.java`** - Aplicação específica para dados do SISU

#### **Algoritmos de Ordenação por Contagem**
- **`CountingSort.java`** - Ordenação por contagem
- **`RadixSort.java`** - Ordenação radix (dígito por dígito)

#### **Algoritmos Quadráticos**
- **`InsertionSortRecursivo.java`** - Insertion sort recursivo
- **`SelectionSortPassoPasso.java`** - Selection sort com visualização

### Problemas Específicos
- **`Top-n.java`** - Encontrar os N maiores elementos
- **`WarmUp.java`** - Problemas de aquecimento
- **`MarinaLivros.java`** - Aplicação prática de ordenação

### Manipulação de Arrays
- **`InserePrimeiro.java`** - Inserção no início do array
- **`InsereUltimo.java`** - Inserção no final do array
- **`MoveN.java`** - Movimentação de N elementos
- **`DownN.java`** - Operações de descida

### Verificação e Análise
- **`VerificacaoElementosDuplicados.java`** - Detecção de duplicatas
- **`Estudo.java`** e **`Estudo2.java`** - Códigos de estudo e análise

---

## Conceitos Fundamentais

### **Busca Binária**
- **Complexidade:** O(log n)
- **Pré-requisito:** Array ordenado
- **Estratégia:** Divisão e conquista

### **QuickSort**
- **Complexidade:** O(n log n) médio, O(n²) pior caso
- **Estratégia:** Particionamento com pivot
- **Características:** In-place, não estável

### **MergeSort**
- **Complexidade:** O(n log n) garantido
- **Estratégia:** Divisão e conquista + merge
- **Características:** Estável, requer espaço extra

### **Counting Sort**
- **Complexidade:** O(n + k), onde k é o range dos valores
- **Aplicação:** Inteiros em range limitado
- **Características:** Estável, não baseado em comparação

### **Radix Sort**
- **Complexidade:** O(d × (n + k)), onde d é número de dígitos
- **Estratégia:** Ordenação dígito por dígito
- **Aplicação:** Números inteiros ou strings

---

## Análise de Complexidade

### **Comparação dos Algoritmos**

| Algoritmo | Melhor Caso | Caso Médio | Pior Caso | Espaço | Estável |
|-----------|-------------|------------|-----------|---------|---------|
| **QuickSort** | O(n log n) | O(n log n) | O(n²) | O(log n) | Não |
| **MergeSort** | O(n log n) | O(n log n) | O(n log n) | O(n) | Sim |
| **Counting Sort** | O(n + k) | O(n + k) | O(n + k) | O(k) | Sim |
| **Radix Sort** | O(d×n) | O(d×n) | O(d×n) | O(n + k) | Sim |
| **Insertion Sort** | O(n) | O(n²) | O(n²) | O(1) | Sim |
| **Selection Sort** | O(n²) | O(n²) | O(n²) | O(1) | Não |

---

## Aplicações Práticas

### **Quando usar cada algoritmo:**

- **QuickSort:** Dados gerais, memória limitada
- **MergeSort:** Estabilidade necessária, dados grandes
- **Counting Sort:** Inteiros em range pequeno
- **Radix Sort:** Números inteiros ou strings
- **Insertion Sort:** Arrays pequenos ou quase ordenados

### **Problemas Resolvidos:**
- Análise de desempenho de algoritmos de busca
- Implementação de QuickSelect para k-ésimo menor elemento
- Ordenação eficiente para dados quase ordenados
- Comparação prática entre algoritmos quadráticos
