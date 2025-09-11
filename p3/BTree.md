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


Resposta: A Árvore B se mantém balanceada principalmente através da sua propriedade fundamental de que todas as folhas devem estar no mesmo nível. As operações de 


split (na inserção) e fusão/redistribuição (na remoção) são projetadas para garantir que essa propriedade nunca seja violada. A árvore cresce "para cima" (uma nova raiz é criada quando a antiga sofre split) e encolhe "de cima" (a raiz é removida quando seus filhos se fundem), mantendo assim todas as folhas sempre na mesma profundidade.