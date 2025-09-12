### Parte 1: A Teoria Conceitual das Árvores Red-Black (PV)

#### O que é uma Árvore Red-Black?

 Uma Árvore Red-Black, ou Árvore Preto-Vermelho (PV), é um tipo especial de árvore binária de busca[cite: 7]. Sua principal característica é que ela se auto-balanceia para garantir que as operações de busca, inserção e remoção sejam eficientes.  Para isso, cada nó armazena uma informação extra: uma cor (vermelho ou preto)[cite: 7].

 O objetivo dessas cores e das regras associadas a elas é manter a árvore "aproximadamente balanceada"[cite: 8].  Isso significa que o caminho mais longo da raiz até uma folha nunca será mais que o dobro do caminho mais curto, o que garante a complexidade de tempo das operações em $O(\log n)$[cite: 108, 110].

#### As Propriedades (Invariantes)

 Para que uma árvore seja considerada uma Red-Black válida, ela deve obedecer a 5 regras fundamentais, também chamadas de invariantes[cite: 12]:

1.     Propriedade da Cor:   Todo nó é vermelho ou preto[cite: 13].
2.     Propriedade da Raiz:   O nó raiz é sempre preto[cite: 15].
3.     Propriedade da Folha:   Toda folha (representada como NIL ou `null`) é preta[cite: 14].
4.     Propriedade do Nó Vermelho:   Se um nó é vermelho, então ambos os seus filhos são pretos[cite: 16]. (Isso impede que existam dois nós vermelhos em sequência).
5.     Propriedade da Altura Preta:   Para cada nó, todos os caminhos simples que partem dele até qualquer uma de suas folhas descendentes contêm o mesmo número de nós pretos[cite: 17, 18].

#### Altura Preta (Black-Height)

 A "altura preta" (ou *black-height*) de um nó é o número de nós pretos em qualquer caminho simples a partir daquele nó até uma folha (sem contar o próprio nó)[cite: 18, 19]. A propriedade 5 garante que esse número seja o mesmo para todos os caminhos.  A altura preta da árvore inteira é a altura preta de sua raiz[cite: 75].

---

### Parte 2: Perguntas e Respostas para a Prova

Aqui estão as perguntas e respostas focadas nos conceitos e nos tópicos que seu professor indicou.

####   Pergunta 1: O que é uma Árvore Red-Black (PV) e qual seu principal objetivo?  

  Resposta:  
 É uma árvore binária de busca onde cada nó possui uma cor (vermelho ou preto)[cite: 7].  Seu principal objetivo é manter-se aproximadamente balanceada através de um conjunto de regras (invariantes)[cite: 8].  Esse balanceamento garante que operações como busca, inserção e remoção sejam executadas em tempo logarítmico ($O(\log n)$)[cite: 110].

---

####   Pergunta 2: (Tópico do Professor) Dada uma árvore binária de busca com nós pretos e vermelhos, como verificar se ela é uma Árvore Red-Black válida?  

  Resposta:  
Para verificar se a árvore é uma PV válida, é preciso checar se todas as 5 propriedades são satisfeitas. O processo seria:
1.     Verificar a Raiz:   Checar se a cor do nó raiz é preta[cite: 15].
2.     Verificar os Nós Vermelhos:   Percorrer a árvore e, para cada nó vermelho, verificar se seus dois filhos são pretos[cite: 16]. Se encontrar um nó vermelho com um filho vermelho, a árvore é inválida.
3.    Verificar a Altura Preta:   Esta é a verificação mais complexa. Começando da raiz, é preciso calcular a altura preta para a subárvore esquerda e a subárvore direita. Se em qualquer nó da árvore a altura preta dos seus dois filhos for diferente, a árvore é inválida. Esse processo deve ser feito recursivamente para todos os nós.  Todos os caminhos de um nó até suas folhas descendentes devem ter o mesmo número de nós pretos[cite: 17, 18].
4.     Considerar Folhas:   Durante a verificação, os nós `null` (folhas) são sempre considerados pretos[cite: 14].

Se a árvore passar em todas essas verificações, ela é uma Árvore Red-Black válida.

---

####   Pergunta 3: (Tópico do Professor) Dada uma PV, como se determina a sua altura preta?  

  Resposta:  
 A altura preta de uma Árvore Red-Black é a altura preta de seu nó raiz[cite: 75].  Para calcular a altura preta de um nó qualquer, basta contar o número de nós pretos em um caminho simples a partir dele até uma folha descendente (NIL)[cite: 17, 18]. Devido à propriedade 5, esse número será o mesmo para todos os caminhos.

 Por exemplo, na árvore da página 7, a altura preta do nó 7 (a raiz) é 2, pois qualquer caminho da raiz até uma folha (NIL) passa por 2 nós pretos (sem contar a própria raiz)[cite: 74].

---

####   Pergunta 4: (Tópico do Professor) Dado um nó recém-adicionado em uma PV, qual caso da inserção teria que ser aplicado?  

  Resposta:  
 A análise do caso de inserção depende da configuração local do novo nó (N), seu pai (P), seu avô (G) e seu tio (U)[cite: 340].
1.     Coloração Inicial:   O novo nó é sempre inserido com a cor   vermelha  [cite: 336].
2.    Análise dos Casos:  
    *   Caso 1:   Se o novo nó (N) é a   raiz   da árvore.  A correção é simplesmente mudar sua cor para preto[cite: 352, 353].
    *   Caso 2:   Se o pai (P) do novo nó é   preto  . Nenhuma propriedade é violada, e a árvore continua válida.  Nenhuma correção é necessária[cite: 363, 364].
    *   Caso 3:   Se o pai (P) é   vermelho   e o tio (U) também é   vermelho  .  A correção envolve recolorir o pai e o tio para preto, o avô (G) para vermelho, e reavaliar a árvore a partir do avô[cite: 375, 376, 383, 386].
    *   Caso 4 e 5:   Se o pai (P) é   vermelho   e o tio (U) é   preto  . A correção dependerá se o novo nó (N) forma uma "linha" ou um "triângulo" com seu pai e avô.  Isso exigirá   rotações   e recolorações para balancear a árvore[cite: 397, 426].  O Caso 4 transforma um "triângulo" em uma "linha" através de uma rotação [cite: 404, 410] , e o Caso 5 resolve a "linha" com outra rotação e recoloração[cite: 458].

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

Com base no material "Arvore B.pdf" fornecido, as operações básicas em uma **Árvore B** (Pesquisa, Inserção e Remoção) ocorrem da seguinte maneira:

### Pesquisa (Busca)

[cite_start]A pesquisa em uma Árvore B é uma generalização da busca em uma árvore binária de busca (BST)[cite: 1009]. [cite_start]O processo se adapta ao fato de que cada nó, também chamado de página, pode conter múltiplas chaves ordenadas[cite: 923, 1011, 930].

O algoritmo funciona assim:
1.  A busca começa no nó raiz.
2.  [cite_start]Dentro do nó atual, é feita uma pesquisa (geralmente linear) para verificar se o elemento procurado está entre as chaves do nó[cite: 1012, 1013].
3.  [cite_start]Se o elemento for encontrado, a busca termina, retornando o nó e a posição da chave nele[cite: 1019].
4.  Se o elemento não for encontrado no nó atual, o algoritmo determina qual filho seguir. [cite_start]Ele usa as chaves ordenadas como separadores para decidir a sub-árvore correta[cite: 1014, 930]. [cite_start]Por exemplo, para encontrar um valor `V`, o algoritmo desce para o filho que está entre as chaves `x1` e `x2`, onde `x1 < V < x2`[cite: 1015].
5.  [cite_start]O processo se repete recursivamente até que o elemento seja encontrado ou até que se chegue a um nó folha sem o elemento, indicando que ele não está na árvore[cite: 1051].

### Inserção

[cite_start]As inserções em uma Árvore B acontecem sempre nos nós folha[cite: 1065]. [cite_start]O objetivo é manter a árvore balanceada, garantindo que todas as folhas permaneçam no mesmo nível[cite: 921, 928].

O algoritmo de inserção segue estes passos:
1.  [cite_start]Primeiro, o algoritmo localiza o nó folha apropriado para a inserção do novo elemento, usando um procedimento de busca[cite: 1066].
2.  [cite_start]**Caso 1: O nó folha não está cheio.** Se o nó folha tem espaço livre, o novo elemento é simplesmente inserido em sua posição correta, mantendo a ordem das chaves[cite: 1067].
3.  [cite_start]**Caso 2: O nó folha está cheio.** Se o nó não tem espaço, ocorre um processo de divisão chamado **split**[cite: 1069].
    * [cite_start]A chave mediana do conjunto (chaves existentes + nova chave) é selecionada[cite: 1075].
    * [cite_start]Essa chave mediana é movida para o nó pai, um processo chamado **promote** (promoção)[cite: 1070].
    * [cite_start]O nó original é dividido em dois novos nós: um com as chaves menores que a mediana e outro com as chaves maiores[cite: 1076, 1077].
    * [cite_start]Se a promoção da chave mediana fizer com que o nó pai também fique cheio, o processo de split e promote se repete recursivamente, subindo na árvore[cite: 1071]. [cite_start]Se o split ocorrer na raiz, uma nova raiz é criada, e a altura da árvore aumenta[cite: 1080].

### Remoção

[cite_start]A remoção é análoga à inserção, mas com mais casos a serem considerados, pois a chave pode ser removida de qualquer nó (folha ou interno)[cite: 1148, 1149]. [cite_start]O principal desafio é garantir que, após a remoção, nenhum nó viole a regra do número mínimo de chaves (condição de **underflow**)[cite: 1158].

O processo geral depende de onde a chave está:

1.  **Remoção em um Nó Folha:**
    * [cite_start]A chave é removida do nó[cite: 1164].
    * Se o nó ainda tiver o número mínimo de chaves, a operação termina.
    * [cite_start]Se a remoção causar **underflow**, a árvore precisa ser rebalanceada[cite: 1165]. Isso é feito de duas maneiras:
        * [cite_start]**Redistribuição:** Se um nó irmão adjacente tiver mais chaves que o mínimo, uma chave do irmão é movida para o pai, e uma chave do pai é movida para o nó com underflow[cite: 1165].
        * [cite_start]**Concatenação (Fusão):** Se os nós irmãos também estiverem no limite mínimo de chaves, o nó com underflow é fundido com um de seus irmãos[cite: 1165]. [cite_start]Uma chave separadora do nó pai desce para se juntar ao novo nó fundido[cite: 1170]. Essa operação no pai pode, por sua vez, causar um underflow nele, propagando o problema para cima.

2.  **Remoção em um Nó Interno:**
    * [cite_start]A remoção de uma chave em um nó interno é mais complexa porque essa chave atua como um separador para suas sub-árvores[cite: 1157, 1171].
    * [cite_start]A chave a ser removida é substituída por um novo separador, que geralmente é o seu sucessor imediato (o menor valor na sub-árvore direita) ou seu predecessor (o maior valor na sub-árvore esquerda)[cite: 1251].
    * [cite_start]A chave substituta é então removida de sua posição original (que será em um nó folha), transformando o problema em um caso de remoção em nó folha, que pode então levar a redistribuições ou fusões[cite: 1252].
