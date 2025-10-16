# P2 - Estruturas de Dados Lineares

Este módulo aborda estruturas de dados lineares fundamentais e suas aplicações práticas.

## Estruturas Implementadas

### **Listas**
- `ArrayList.java` - Lista baseada em array dinâmico
- `LinkedList.java` - Lista ligada simples e dupla (DoubleLinkedList/)

### **Pilhas e Filas** 
- `Pilha.java` - Estrutura LIFO (Last In, First Out)
- `Fila.java` - Estrutura FIFO (First In, First Out)

### **Tabelas Hash**
- `enderHash/` - Implementações de hash
  - `TabelaHashEncadeada.java` - Resolução por encadeamento
  - `TabelaHashEnderecamentoAberto.java` - Endereçamento aberto

### **Estratégias de Cache**
- `cachefifo/` - Cache FIFO (First In, First Out)
- `cachelru/` - Cache LRU (Least Recently Used)  
- `pv/` - Cache LFU (Least Frequently Used)

## Principais Conceitos

### **Listas Dinâmicas**
- Redimensionamento automático
- Acesso por índice vs acesso sequencial
- Trade-offs entre ArrayList e LinkedList

### **Pilhas e Filas**
- Operações básicas (push/pop, enqueue/dequeue)
- Aplicações práticas
- Implementação com arrays e listas

### **Tabelas Hash**
- Funções de hash
- Tratamento de colisões
- Fatores de carga e rehashing

### **Políticas de Cache**
- **FIFO**: Remove o mais antigo
- **LRU**: Remove o menos usado recentemente  
- **LFU**: Remove o menos usado frequentemente

## 🔧 Implementações Destacadas

### **Cache LFU** (Least Frequently Used)
Estratégia sofisticada que mantém elementos baseado na frequência de uso:
- Elementos mais acessados ficam no cache
- Lista ordenada por frequência
- Reposicionamento dinâmico baseado em acessos

### **Tabela Hash com Encadeamento**
Resolve colisões usando listas ligadas:
- Cada posição da tabela aponta para uma lista
- Busca sequencial dentro da lista em caso de colisão
- Eficiente para fatores de carga moderados


*📚 Meus estudos para EDA-LEDA | UFCG*