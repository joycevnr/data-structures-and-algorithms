### Resumo: Estruturas de Dados e Algoritmos - Árvores Binárias de Pesquisa
É uma estrutura de dados para organizar informações com base em uma regra simples: para qualquer nó, todos os valores à esquerda são menores e todos os valores à direita são maiores.

Características Principais:

Busca Rápida: Encontrar, inserir e remover elementos é extremamente rápido (complexidade O(log n)) se a árvore estiver balanceada.

Sempre Ordenada: A estrutura se mantém ordenada automaticamente. Um percurso específico (em-ordem) retorna todos os elementos em ordem crescente.

Dinâmica: Cresce e diminui de tamanho facilmente, sem precisar definir um limite.

Sensível à Inserção: Sua eficiência depende da ordem de inserção. Se os dados forem inseridos já ordenados, ela se torna lenta e ineficiente (O(n)), como uma lista simples.

  Principais Conceitos e Definições:  

*   Definição:   Uma BST é uma árvore binária baseada em nós onde, para cada nó, todos os valores na subárvore à esquerda são menores que o valor do nó, e todos os valores na subárvore à direita são maiores.
*   Nó:   A unidade básica da árvore, que armazena um valor e possui referências para os filhos esquerdo (`left`) e direito (`right`), além de uma referência para o pai (`parent`).
*   Raiz:   O nó especial a partir do qual todos os outros nós podem ser acessados.
*   Grau de um Nó:   O número de filhos que um nó possui (0, 1 ou 2). Nós com grau 0 são chamados de   folhas  .
*   Altura:   O maior caminho entre a raiz e uma folha. A eficiência das operações em uma BST é diretamente dependente de sua altura, sendo idealmente $O(\log n)$ em uma árvore balanceada e $O(h)$ no geral, onde $h$ é a altura.
*   Árvore Completa:   Uma árvore onde todos os nós internos têm grau 2 e todas as folhas estão no mesmo nível, resultando na menor altura possível para a quantidade de nós.
*   Sucessor e Predecessor:   O sucessor de um nó é o menor valor maior que ele na árvore. O predecessor é o maior valor menor que ele.

  Operações Básicas:  

*   Inserção:   Novos elementos são sempre adicionados como folhas. O algoritmo percorre a árvore a partir da raiz, movendo-se para a esquerda se o valor for menor que o nó atual e para a direita se for maior, até encontrar uma posição nula.
*   Busca:   De forma semelhante à inserção, a busca compara o elemento procurado com os nós, navegando pela subárvore esquerda (se menor) ou direita (se maior) até encontrar o elemento ou chegar a um nó nulo.
*   Remoção:   É a operação mais complexa, com três casos a serem considerados:
    1.    O nó é uma folha:   Basta remover a referência do pai para ele.
    2.    O nó tem um filho:   O pai do nó a ser removido passa a apontar para o único filho deste.
    3.    O nó tem dois filhos:   O valor do nó é substituído pelo valor do seu sucessor (ou predecessor), e o nó sucessor é então removido (que cairá no caso 1 ou 2).

  Métodos para Percorrer a Árvore:  

Existem duas estratégias principais para visitar todos os nós de uma árvore:

1.    Em Profundidade (Depth-First Search - DFS):   Explora um ramo da árvore até o fim antes de retroceder.
    *   Pré-ordem:   Visita o nó, depois a subárvore esquerda e, por fim, a subárvore direita.
    *   Em-ordem:   Visita a subárvore esquerda, depois o nó e, por fim, a subárvore direita. Em uma BST, este percurso resulta em uma sequência ordenada dos valores.
    *   Pós-ordem:   Visita a subárvore esquerda, depois a subárvore direita e, por fim, o nó. A raiz é sempre o último elemento visitado.

2.    Em Largura (Breadth-First Search - BFS):   Visita todos os nós de um mesmo nível antes de descer para o próximo nível. Utiliza uma fila como estrutura de dados auxiliar para controlar a ordem de visitação.


  1. O que é uma Árvore Binária de Pesquisa (BST) e qual a sua principal propriedade?  

  *   Resposta:   Uma Árvore Binária de Pesquisa é uma estrutura de dados de árvore binária baseada em nós. Sua principal propriedade é que, para qualquer nó da árvore, todos os valores na subárvore à sua esquerda são menores que o valor do nó, e todos os valores na subárvore à sua direita são maiores.

  2. Qual a diferença entre altura e nível de um nó em uma BST?  

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


