Árvores B
Definição (Bayer e McCreight, 1972):  Para qualquer inteiro positivo par M, uma árvore B (B-tree) de ordem M é uma árvore com as seguintes propriedades:
cada nó contém no máximo M−1 chaves,
a raiz contém no mínimo 2 chaves e cada um dos demais nós contém no mínimo M/2 chaves,
cada nó que não seja uma folha tem um filho para cada uma de suas chaves,
todos os caminhos da raiz até uma folha têm o mesmo comprimento (ou seja, a árvore é perfeitamente balanceada).

Qual foi a principal motivação para a criação das Árvores B e como sua estrutura resolve esse problema?
Resposta: A principal motivação foi o alto custo de acesso a dados em memórias secundárias, como discos rígidos. A Árvore B resolve isso utilizando nós (páginas) que armazenam um grande número de chaves. Isso torna a árvore mais larga e com menor altura, reduzindo drasticamente o número de acessos a disco necessários para encontrar um dado.

O que é e por que existe?
A Árvore B (ou B-Tree) é uma estrutura de dados em árvore, auto-balanceada, que generaliza a árvore binária de busca, permitindo que os nós tenham mais de dois filhos.

Sua principal motivação foi otimizar o acesso a dados em 

memória secundária (como HDs), que é muito mais lento que o acesso à memória principal (RAM). A leitura de um "bloco" ou "página" do disco é uma operação cara. A solução da Árvore B foi criar nós (chamados de 

páginas) que armazenam um grande número de chaves. Isso resulta em duas grandes vantagens:


Redução de Acessos a Disco: Como cada nó tem muitas chaves, a árvore se torna mais "larga" e "achatada", diminuindo sua altura. Menor altura significa menos nós para ler do disco para encontrar uma informação.

Eficiência: A estrutura garante que a árvore permaneça sempre balanceada, mantendo a eficiência das operações em tempo logarítmico.

Propriedades de uma Árvore B de Ordem m
A "ordem" 

m define o número máximo de filhos que um nó pode ter. Todas as Árvores B de uma dada ordem devem seguir estas regras:



Todas as folhas estão no mesmo nível: Esta é a propriedade que garante o balanceamento perfeito da árvore.


Número de Filhos:

Todo nó tem no 

máximo m filhos.

Todo nó, exceto a raiz, tem no mínimo ⌈m/2⌉ filhos. (O seu material usa 

m/2 - 1, que é incomum. A definição padrão é 

teto de m/2).

A raiz tem no mínimo 2 filhos, a menos que seja uma folha.

Número de Chaves:

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
