### Parte 1: A Teoria Conceitual das Árvores Red-Black (PV)

#### O que é uma Árvore Red-Black?

 Uma Árvore Red-Black, ou Árvore Preto-Vermelho (PV), é um tipo especial de árvore binária de busca . Sua principal característica é que ela se auto-balanceia para garantir que as operações de busca, inserção e remoção sejam eficientes.  Para isso, cada nó armazena uma informação extra: uma cor (vermelho ou preto) .

 O objetivo dessas cores e das regras associadas a elas é manter a árvore "aproximadamente balanceada" .  Isso significa que o caminho mais longo da raiz até uma folha nunca será mais que o dobro do caminho mais curto, o que garante a complexidade de tempo das operações em $O(\log n)$ .

#### As Propriedades (Invariantes)

 Para que uma árvore seja considerada uma Red-Black válida, ela deve obedecer a 5 regras fundamentais, também chamadas de invariantes :

1.     Propriedade da Cor:   Todo nó é vermelho ou preto .
2.     Propriedade da Raiz:   O nó raiz é sempre preto .
3.     Propriedade da Folha:   Toda folha (representada como NIL ou `null`) é preta .
4.     Propriedade do Nó Vermelho:   Se um nó é vermelho, então ambos os seus filhos são pretos . (Isso impede que existam dois nós vermelhos em sequência).
5.     Propriedade da Altura Preta:   Para cada nó, todos os caminhos simples que partem dele até qualquer uma de suas folhas descendentes contêm o mesmo número de nós pretos .

#### Altura Preta (Black-Height)

 A "altura preta" (ou *black-height*) de um nó é o número de nós pretos em qualquer caminho simples a partir daquele nó até uma folha (sem contar o próprio nó) . A propriedade 5 garante que esse número seja o mesmo para todos os caminhos.  A altura preta da árvore inteira é a altura preta de sua raiz .

---

### Parte 2: Perguntas e Respostas para a Prova

Aqui estão as perguntas e respostas focadas nos conceitos e nos tópicos que seu professor indicou.

####   Pergunta 1: O que é uma Árvore Red-Black (PV) e qual seu principal objetivo?  

  Resposta:  
 É uma árvore binária de busca onde cada nó possui uma cor (vermelho ou preto) .  Seu principal objetivo é manter-se aproximadamente balanceada através de um conjunto de regras (invariantes) .  Esse balanceamento garante que operações como busca, inserção e remoção sejam executadas em tempo logarítmico ($O(\log n)$) .

---

####   Pergunta 2: (Tópico do Professor) Dada uma árvore binária de busca com nós pretos e vermelhos, como verificar se ela é uma Árvore Red-Black válida?  

  Resposta:  
Para verificar se a árvore é uma PV válida, é preciso checar se todas as 5 propriedades são satisfeitas. O processo seria:
1.     Verificar a Raiz:   Checar se a cor do nó raiz é preta .
2.     Verificar os Nós Vermelhos:   Percorrer a árvore e, para cada nó vermelho, verificar se seus dois filhos são pretos . Se encontrar um nó vermelho com um filho vermelho, a árvore é inválida.
3.    Verificar a Altura Preta:   Esta é a verificação mais complexa. Começando da raiz, é preciso calcular a altura preta para a subárvore esquerda e a subárvore direita. Se em qualquer nó da árvore a altura preta dos seus dois filhos for diferente, a árvore é inválida. Esse processo deve ser feito recursivamente para todos os nós.  Todos os caminhos de um nó até suas folhas descendentes devem ter o mesmo número de nós pretos .
4.     Considerar Folhas:   Durante a verificação, os nós `null` (folhas) são sempre considerados pretos .

Se a árvore passar em todas essas verificações, ela é uma Árvore Red-Black válida.

---

####   Pergunta 3: (Tópico do Professor) Dada uma PV, como se determina a sua altura preta?  

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

### Caso de Adição (Inserção)

A inserção combina a inserção de uma BST com um procedimento de correção (`fix-up`) para manter as propriedades da Árvore PV.  Um novo nó tem sempre a cor vermelha inicial .

**Passo a Passo da Inserção e Correção (`fix-up`):**

1.   **Inserção Inicial:** Insira o novo nó `N` na árvore como em uma BST comum .
2.   **Coloração Inicial:** Pinte o novo nó `N` de vermelho .
3.   **Início da Correção:** Se a inserção violar alguma propriedade, inicie o `fix-up`, que considera 5 casos sequenciais .

    * **Caso 1: `N` é a Raiz**
        *  Se o novo nó `N` é a raiz da árvore, sua cor é mudada para preto . Isso garante que a propriedade da raiz preta seja mantida. A correção termina.

    * **Caso 2: O Pai de `N` é Preto**
        *  Se o pai do nó `N` já for preto, a inserção de `N` (vermelho) não viola nenhuma propriedade .  A árvore continua válida e a correção termina .

    * **Caso 3: O Pai e o Tio de `N` são Vermelhos**
        *  Se o pai (`P`) de `N` é vermelho e o tio (`U`, irmão do pai) também é vermelho, uma recoloração é realizada :
            1.   O pai `P` é recolorido para preto .
            2.   O tio `U` é recolorido para preto .
            3.   O avô `G` é recolorido para vermelho .
            4.   O processo de `fix-up` é reiniciado, agora focando no avô `G` como o novo `N` .

    * **Caso 4: O Pai é Vermelho, o Tio é Preto e formam um "Triângulo"**
        *  Esta situação ocorre se o tio de `N` for preto e `N` e seu pai `P` estiverem em lados opostos em relação ao avô `G` (ex: `P` é filho esquerdo e `N` é filho direito) .
        *  Uma rotação é aplicada no pai `P` para transformar a configuração em uma "linha" .
        *  Após a rotação, a correção continua no Caso 5 .

    * **Caso 5: O Pai é Vermelho, o Tio é Preto e formam uma "Linha"**
        *  Esta situação ocorre se o tio de `N` for preto e `N` e seu pai `P` estiverem do mesmo lado em relação ao avô `G` (ex: `P` e `N` são ambos filhos esquerdos) .
        * A correção é feita com uma recoloração e uma rotação:
            1.   O pai `P` é recolorido para preto .
            2.   O avô `G` é recolorido para vermelho .
            3.   Uma rotação é aplicada no avô `G` .
        * Após esses passos, a violação é corrigida e o processo termina.

### Caso de Remoção

 A remoção em uma Árvore PV pode utilizar a mesma lógica de remoção de uma BST, mas deve garantir que as propriedades da árvore sejam preservadas .

**Análise dos Casos de Remoção:**

* **Caso Simples: Remoção de uma Folha Vermelha**
    *  Se o nó removido for uma folha vermelha, nenhuma propriedade da árvore é violada . A altura preta de todos os caminhos permanece a mesma.  A operação é trivial e termina aqui .

* **Caso Problemático: Remoção de um Nó Preto**
    *  A remoção de um nó preto é a principal causa de problemas, pois pode alterar a altura preta de um caminho, violando uma das invariantes fundamentais da árvore .
    *  Quando a remoção de um nó preto causa uma violação na `black-height`, um procedimento de `fix-up` é iniciado .
    *  O `fix-up` da remoção foca no irmão (`sibling`) e no sobrinho mais próximo do nó que foi removido para restaurar o balanceamento .
    *  Este processo é mais complicado que o da inserção e considera vários casos particulares que utilizam rotações e recolorações para garantir que todas as propriedades da Árvore PV voltem a ser satisfeitas .