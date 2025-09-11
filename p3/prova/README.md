# Identificação

* Nome: 
* Email (@ccc): 
* Matrícula: 

# Prova Prática 4

## O template

> Não altere o arquivo build.gradle

> O código java estará (ou você terá que colocar) no diretório **/src/main/java/**

> Os testes estarão (ou você terá que incluir) no diretório **/src/test/java/**

> Não mude nenhuma assinatura de método, nome de classe ou localização dos arquivos. Mas você pode/deve criar outros métodos e classes, desde que passem nos testes.

> Compilando: `gradle compileJava` na raiz do projeto.

> Executando os testes públicos: `gradle test` na raiz do projeto.


## A Prova

## Conta menores

Implementar o método `contaMenores(int k)` que conta os valores menores do que k em uma BST.

Veja os testes para ter clareza da definição. 
Pode considerar que não há elementos repetidos na BST.
Você **não** pode considerar que k é um valor presente na BST.

## Max-heap to Min-heap

Dado que a implementação fornecida da classe Heap é para max-heap, implemente o método (está comentado no código) `toMinHeap()` que transforma o heap em um min-heap, ou seja, todo
e qualquer nó é menor que o seus filhos.

Restrições: **você não pode criar nenhum método adicional.** Você apenas pode modificar os métodos existentes (conteúdo e nome) na classe Heap.

### Teoria


* (2.0) Qual o número mínimo de nós de um heap com altura h? e o máximo?

    A quantidade de nós é definida pelo fato de o heap ser uma árvore binária completa ou quase completa.

    * **Número Máximo de Nós:**
        O número máximo de nós ocorre quando o heap é uma **árvore binária perfeita**, com todos os níveis, incluindo o nível `h`, completamente preenchidos. A soma dos nós em cada nível ($2^0 + 2^1 + ... + 2^h$) resulta em um total de $\bf 2^{h+1} - 1$ nós.

    * **Número Mínimo de Nós:**
        O número mínimo ocorre quando a árvore está preenchida até o nível `h-1` e possui **apenas um nó** no último nível, `h`. Isso consiste em uma árvore perfeita de altura `h-1` (que tem $2^h - 1$ nós) mais um nó adicional, totalizando $\bf 2^h$ nós.

* (1.0) Explique com base no desempenho porque Heap é uma boa solução para implementação da política de fila de prioridade.

    O Heap é uma solução excelente para filas de prioridade devido a um equilíbrio de desempenho ideal para esta finalidade, superando outras estruturas de dados simples.

    1.  **Acesso Rápido ao Máximo (Prioridade):** Em um Max-Heap, o elemento de maior prioridade está sempre na raiz. Acessá-lo é uma operação de tempo constante, **$O(1)$**.

    2.  **Operações de Inserção e Remoção Eficientes:** As operações de adicionar um novo elemento ou remover o elemento de maior prioridade reorganizam a árvore em um caminho de, no máximo, sua altura. Isso resulta em um custo de **$O(\log n)$** para ambas as operações.

    **O Ponto Chave: O Equilíbrio de Desempenho**
    A superioridade do Heap fica clara quando comparado a alternativas: um array ordenado teria inserção em $O(n)$, e um array desordenado teria busca e remoção do máximo em $O(n)$. O Heap oferece o melhor dos dois mundos: acesso instantâneo à prioridade máxima e manutenção da estrutura em tempo logarítmico.

* (1.0) Dado que não há repetições em um Heap-max e que há pelo menos 2 elementos nesse heap, o segundo maior elemento é sempre filho da raiz. Isso é verdade? Justifique sua resposta.

    Sim, a afirmação é **verdadeira**. A justificativa vem de uma dedução lógica baseada na propriedade fundamental do Max-Heap, e não de um exemplo específico.

    1.  Seja **R** a raiz da árvore. Pela definição de Max-Heap, **R** é o maior elemento de toda a árvore.
    2.  Seja **S** o segundo maior elemento da árvore.
    3.  Como todo nó (exceto a raiz) precisa ter um pai, **S** tem um pai. Vamos chamá-lo de **P**.
    4.  Pela propriedade do Max-Heap (e como não há repetições), o pai deve ser estritamente maior que seus filhos: **P > S**.
    5.  Sabemos que **R** é o **único** elemento em toda a árvore que é maior que **S** (pois **S** é, por definição, o segundo maior).
    6.  Se **P > S**, e o único elemento que satisfaz essa condição é **R**, então **P** tem que ser **R**.

    Portanto, o pai do segundo maior elemento é obrigatoriamente a raiz, o que significa que o segundo maior elemento é sempre um filho direto da raiz.


## Entregando a prova

> Passo 0. Modifique o arquivo README. Coloque seu nome, email @ccc e matrícula nos lugares indicados. Se você não fizer isso, não considero que sua prova foi assinada e, portanto, não vou corrigir. Não mude a formatação da linha. Apenas inclua seus dados.

> Passo 1. Certifique-se **NO TERMINAL** de que sua solução compila e passa nos testes. Isso deve ser feito com os comandos do gradle (compileJava e test).

> Passo 2. Submeta sua solução para o repositório

  * `git pull`
  * `git commit -m "entregando a prova"`
  * `git push origin main`

## Importante

* A correção da prova não é automática. Os testes que são executados quando você faz push são apenas testes de sanidade.

* Vou considerar sempre a última submissão antes do deadline final. 

* A nota será dada pelos testes no servidor e depois da correção que eu efetuar. Sempre tento corrigir o mais rápido possível. Portanto, tenha paciência.

* A nota será calculada a partir dos testes e análise manual do código. Essa análise manual vai considerar se a solução é eficiente, se não tem loops desnecessários etc.

* Só serão corrigidas as provas dos alunos que assinaram a lista de presença física no laboratório.
