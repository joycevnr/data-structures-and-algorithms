
## A Prova

### Questão 1: LFU (3.0)

Implemente a estratégia Menos Frequentemente Utilizado / Least Frequently Used (LFU) de _cache eviction_. Nessa estratégia, quando o cache está cheio e um elemento precisa entrar, o elemento menos acessado deve ser removido. Os elementos que foram mais acessados vão para o final da lista, pois devem ser removidos por último. 

Funciona assim. Sempre que um elemento é utilizado/acessado, sua frequência de uso deve ser incrementada e, se for o caso, ele deve ser reposicionado na lista para manter a ordem conforme a frequência. Os elementos considerados "menos acessados" são guardados no ínicio da lista e devem ser removidos primeiro, caso preciso, porque são acessados com menos frequência. A lista é sempre mantida ordenada.

Veja que se um elemento for o próximo a sair, mas for acessado muitas vezes, sua frequência aumenta e ele é reposicionado na lista, pois se tornou "mais acessado". Isso garante sua permanência no cache enquanto continuar sendo acessado frequentemente.

Analise o teste abaixo para entender a especificação desse comportamento.

```java
        LFUEvictionStrategy lfucache = new LFUEvictionStrategy(4);
        assertNull(lfucache.getNextEviction());
        assertEquals(0, lfucache.size());

        assertEquals("miss", lfucache.get("a"));
        // cache status: [<"a", 1>, null, null, null]
        assertNull(lfucache.getNextEviction());
        assertEquals(1, lfucache.size());

        assertEquals("hit", lfucache.get("a"));
        assertEquals("hit", lfucache.get("a"));
        // cache status: [<"a", 3>, null, null, null]
        assertEquals(1, lfucache.size());

        assertEquals("miss", lfucache.get("b"));
        assertEquals("miss", lfucache.get("c"));
        // cache status: [<"c", 1>, <"b", 1>, <"a", 3>, null]
        assertNull(lfucache.getNextEviction());

        assertEquals("miss", lfucache.get("d"));
        // cache status: [<"d", 1>, <"c", 1>, <"b", 1>, <"a", 3>]
        assertEquals("d", lfucache.getNextEviction());

        assertEquals("hit", lfucache.get("d"));
        assertEquals("hit", lfucache.get("d"));
        assertEquals("hit", lfucache.get("d"));
        // cache status: [<"c", 1>, <"b", 1>, <"a", 3>, <"d", 4>]
        // "d" foi para o final da lista, pois é o elemento mais acessado
        // e "c" passa a ser o menos acessado.
        assertEquals("c", lfucache.getNextEviction());

        assertEquals("miss", lfucache.get("e"));
        // cache status: [<"e", 1>, <"b", 1>, <"a", 3>, <"d", 4>]
        // "c" teve que sair para entrada do "e", pois "c" é o menos acessado da lista.
        assertEquals("e", lfucache.getNextEviction());
        assertEquals(4, lfucache.size());

        assertEquals("hit", lfucache.get("e"));
        // cache status: [<"b", 1>, <"e", 2>, <"a", 3>, <"d", 4>]
        // trocamos o "e" e "b", pois "e" foi mais acessado que "b"
        // e "b" passa a ser o elemento menos acessado da lista.
        assertEquals("b", lfucache.getNextEviction());

        assertEquals("miss", lfucache.get("c"));
        // cache status: [<"c", 1>, <"e", 2>, <"a", 3>, <"d", 4>]
        // "b" teve que sair para "c" voltar, pois "b" é o menos acessado da lista.
        assertEquals("c", lfucache.getNextEviction());

        assertEquals("hit", lfucache.get("e"));
        assertEquals("hit", lfucache.get("e"));
        // cache status: [<"c", 1>, <"a", 3>, <"e", 4>, <"d", 4>]
        assertEquals("c", lfucache.getNextEviction());
        
        assertEquals("hit", lfucache.get("c"));
        assertEquals("hit", lfucache.get("c"));
        assertEquals("hit", lfucache.get("c"));
        assertEquals("hit", lfucache.get("c"));
        // cache status: [<"a", 3>, <"e", 4>, <"d", 4>, <"c", 5>]
        // "c" foi para o final da lista, pois é o elemento mais acessado
        // e "a" passa a ser o menos acessado.
        assertEquals("a", lfucache.getNextEviction());
        assertEquals(4, lfucache.size());
```

Implemente os métodos das classes **LFUEvictionStrategy**, **LFUCache** e **LinkedList** (`sortByFrequency`). Note que a classe Node já foi modificada para armazenar não somente o valor, mas sua frequência também.

Importante: entenda os testes. Leia. Compreenda. Faça mais testes. Testes são uma excelente forma de especificação de como seu algoritmo deve funcionar.


#### Restrições

    - Você deve implementar com uma LinkedList e apenas essa estrutura. O código foi disponibilizado, exceto pelo método `sortByFrequency`, que você deve implementar.
    
    - A busca por um elemento no cache é O(n) ainda porque não estamos usando outra estrutura além da LinkedList.
    
    - Adição e remoção de um elemento da linkedlist **deve ser O(1)**.

    - Inserção de um elemento ordenado pela frequência na linkedlist **deve ser O(n)**.

    - Seu cache deve ter o tamanho fixo, mesmo sendo implementado com uma linked list.

Ah...confira (sempre!) se você passa nos testes: `gradle test`.


### Questão 2: Parênteses (3.0)

Implemente o método `boolean checkParenteses(String parenteses)` que verifica se uma sequência de parênteses é bem formada.
Você não precisa implementar uma pilha. Pode usar a classe LinkedList de java para apoiar a sua decisão. 

Exemplos:

    (()) -> true
    ))(( -> false


#### Restrições

- Você deve, necessariamente, usar LinkedList como uma pilha.
- Não há restrições em relação ao número de instâncias de LinkedList que você precisa criar.


### Teoria

* (1.0) Quais as principais diferenças entre ArrayList e LinkedList?

  ```diff
  A diferença fundamental entre ArrayList e LinkedList reside em suas estruturas de dados internas, o que impacta diretamente a performance de cada operação.

    1. Na Estrutura Interna de Armazenamento:
       ArrayList: Utiliza um vetor (array) dinâmico por baixo dos panos. Isso significa que todos os seus elementos são armazenados em um único e contínuo bloco de memória, um ao lado do outro, como em [elem1, elem2, elem3, ...].
  
  LinkedList: Utiliza uma estrutura de nós encadeados. Cada elemento é encapsulado em um objeto Node que contém o valor do elemento e dois ponteiros (endereços de memória): um que aponta para o Node anterior (prev) e outro que aponta para o Node seguinte (next). Esses nós não precisam estar juntos na memória.
  
  2. No Acesso a um Elemento por Índice (get(int index)):
     ArrayList: A performance é O(1) (tempo constante). Como os elementos estão em um bloco contíguo, a localização de qualquer item pode ser calculada matematicamente de forma instantânea: endereço_do_item = endereço_inicial_do_array + (índice * tamanho_de_cada_elemento).
  
  LinkedList: A performance é O(n) (tempo linear). Para encontrar o elemento no índice i, é necessário partir do primeiro nó (head) e seguir os ponteiros .next i vezes. Não há atalho; é preciso percorrer a lista.
  
  3. Na Adição de um Elemento no Final (add(elemento)):
     ArrayList: A performance é O(1) amortizado. Se o array interno ainda tiver espaço livre, a operação é O(1). Contudo, se o array estiver cheio, ele precisa ser redimensionado: um novo array (geralmente maior) é criado, todos os elementos do antigo são copiados para o novo, e só então o novo elemento é adicionado. Essa operação de cópia custa O(n). Como isso acontece com pouca frequência, o custo médio (amortizado) é considerado O(1).
  
  LinkedList: A performance é O(1). A lista mantém um ponteiro direto para o último nó (tail). Para adicionar um novo elemento, basta criar um novo nó e ajustar os ponteiros do antigo tail e do novo nó, o que é um número fixo e pequeno de operações.
  
  4. Na Adição ou Remoção no Início ou no Meio (add(index, elemento) ou remove(index)):
     ArrayList: A performance é O(n). Para inserir um elemento no meio, todos os elementos à direita daquele ponto precisam ser deslocados ("empurrados") uma posição para a frente. Para remover, os elementos à direita são deslocados para trás para preencher o espaço. Esse deslocamento em massa é o que torna a operação cara.
  
  LinkedList: A performance também é O(n), mas por um motivo diferente. A operação é dividida em duas fases:
  
  Busca (O(n)): Primeiro, é preciso percorrer a lista para encontrar o nó na posição desejada.
  
  Modificação (O(1)): Uma vez encontrado, a alteração dos ponteiros para inserir ou remover o nó é extremamente rápida.
  O custo total é dominado pela fase de busca, resultando em uma complexidade final de O(n).
  
  5. No Uso de Memória:
     ArrayList: Pode desperdiçar memória. Se uma ArrayList tem capacidade para 1.000 itens, mas armazena apenas 50, o espaço para os 950 restantes está alocado e não utilizado.
  
  LinkedList: É mais precisa no uso de memória, alocando espaço apenas para os nós que existem. Contudo, cada nó tem um custo extra de memória (overhead) para armazenar os ponteiros prev e next.
    
  ```

* (2.0) 
* a) Compare e contraste os métodos de encadeamento (chaining) e endereçamento aberto (open addressing) para o tratamento de colisões, analisando suas vantagens e desvantagens; 
* b) Explique como o fator de carga (load factor) de uma tabela hash influencia a probabilidade de colisões e o desempenho geral da estrutura.

  ```diff
  ### Comparando os Métodos de Tratamento de Colisões

  Os métodos de encadeamento e endereçamento aberto são as duas principais estratégias para lidar com colisões em tabelas hash, cada uma com suas próprias características, vantagens e desvantagens.
  
  ---
  
  #### Encadeamento (Chaining)
  
  Neste método, cada posição na tabela hash armazena uma **lista (encadeada, array ou similar)**. Quando uma colisão ocorre (duas chaves mapeiam para o mesmo índice), o novo elemento é simplesmente adicionado ao final da lista existente nesse índice.
  
  **Vantagens:**
  * **Simples de implementar**: A lógica é bastante direta. Não é necessário procurar outro espaço vazio na tabela.
    * **Fator de carga maior que 1**: A tabela pode armazenar mais elementos do que sua capacidade (`n > m`). O fator de carga ($α = n/m$) pode ser maior que 1, pois as listas podem crescer indefinidamente.
    * **Menos sensível a chaves ruins**: Mesmo com uma função de hash que causa muitas colisões, o método ainda funciona, embora o desempenho seja degradado.
  
  **Desvantagens:**
  * **Consumo de memória extra**: Cada lista encadeada exige uma quantidade de memória adicional para os ponteiros, o que pode ser significativo.
    * **Degradação de desempenho**: Se o fator de carga for muito alto, as listas ficam longas. As operações de busca, inserção e remoção se tornam lineares ($O(L)$), onde $L$ é o tamanho da lista, o que pode se aproximar do pior caso $O(n)$ se a função de hash for ruim.
  
  ---
  
  #### Endereçamento Aberto (Open Addressing)
  
  Neste método, não há listas. Quando ocorre uma colisão, o algoritmo procura por um outro slot **vazio na própria tabela** para armazenar o novo elemento. A forma como o algoritmo procura por esse slot é chamada de **sondagem** (linear, quadrática, etc.).
  
  **Vantagens:**
  * **Uso eficiente da memória**: Não há o overhead de armazenamento de ponteiros para as listas, o que pode ser mais eficiente em termos de memória, especialmente com elementos pequenos.
    * **Melhor desempenho para chaves boas**: Quando o fator de carga é baixo e a função de hash é boa, o desempenho pode ser ligeiramente superior ao encadeamento devido à maior localidade de cache (elementos próximos uns dos outros na memória).
  
  **Desvantagens:**
  * **Fator de carga limitado**: O fator de carga ($α = n/m$) deve ser **sempre menor que 1** ($α < 1$). A tabela nunca pode ficar completamente cheia.
    * **Mais complexo de implementar**: O algoritmo de sondagem precisa ser implementado cuidadosamente, incluindo o tratamento de remoções com uma "flag" (como o objeto `APAGADO`) para não interromper cadeias de busca.
    * **Efeito "clustering"**: Chaves que colidem tendem a se agrupar, formando blocos de elementos ocupados. Isso pode aumentar o número de sondagens para encontrar um slot vazio, degradando o desempenho.
  
  ---
  
  ### A Influência do Fator de Carga
  
  O **fator de carga (α)** é a métrica mais importante para o desempenho de uma tabela hash. Ele representa a relação entre o número de elementos ($n$) e o tamanho da tabela (m): α = n/m.
  
  * **Probabilidade de Colisões**: Quanto maior o fator de carga, maior a probabilidade de uma colisão ocorrer.
    * Imagine que a tabela tem 100 posições e já 50 elementos (α = 0.5). A probabilidade de um novo elemento colidir é de 50%.
    * Se a tabela já tem 90 elementos (α = 0.9), a probabilidade de colisão aumenta para 90%.
  
    * **Desempenho Geral**: O fator de carga afeta diretamente o tempo de execução das operações:
      * **Com encadeamento**: À medida que α cresce, o tamanho médio das listas aumenta. O tempo de busca, inserção e remoção se torna O(1 + α), o que pode se aproximar de um desempenho linear se α for muito alto.
      * **Com endereçamento aberto**: Um α elevado significa que a tabela está cheia e os slots vazios são mais difíceis de encontrar. O tempo médio das operações aumenta significativamente porque o algoritmo de sondagem precisa percorrer mais posições para encontrar um slot livre ou um elemento. Se α se aproxima de 1, o desempenho pode ser muito ruim.
  
  Para manter o desempenho, as tabelas hash precisam fazer um **`resize`** (aumentar o tamanho do array) quando o fator de carga atinge um limite pré-definido (por exemplo, 0.75). Isso reduz $α$ e a probabilidade de novas colisões, garantindo que o tempo de execução permaneça próximo de O(1).
  ```

* (1.0) Considere uma tabela hash com as seguintes características:

    * Tamanho da tabela: 100 posições
    * Conjunto de chaves a inserir: keys = [200, 205, 210, 215, ..., 495, 500] (valores de 200 a 500, com incremento de 5)
    * Função de hash utilizada: h(k) = k % 100 (resto da divisão por 100)
    * Método de resolução de colisões: endereçamento fechado (usando listas encadeadas)

    Qual a quantidade total de colisões que ocorrerão ao inserir todas essas chaves na tabela hash? O que você faria para diminuir esse número?

  ```diff
  HASH : 00  -Colisões 3, pois há  300, 400 e 500 em que o hash é 00;
  HASH : 10 - Colisões 2;
  HASH : 15 - Colisões 2;
  HASH : 20 - Colisões 2;
  HASH : 05 - Colisões 2;
  HASH : 25 - Colisões 2;
  HASH : 30 - Colisões 2;
  HASH : 35 - Colisões 2;
  HASH : 40 - Colisões 2;
  HASH : 45 - Colisões 2;
  HASH : 50 - Colisões 2;
  HASH : 55 - Colisões 2;
  HASH : 60 - Colisões 2;
  HASH : 65 - Colisões 2;
  HASH : 70 - Colisões 2;
  HASH : 75 - Colisões 2;
  HASH : 80 - Colisões 2;
  HASH : 90 - Colisões 2;
  HASH : 85 - Colisões 2;
  HASH : 95 - Colisões 2;
  19*2 = 38 + 3 = 41
  
  Hash 0: As chaves que geram hash 0 são 200,300,400,500.

  Primeira chave (200): inserção sem colisão.
  
  Segunda chave (300): colisão.
  
  Terceira chave (400): colisão.
  
  Quarta chave (500): colisão.
  
  Total de colisões para o hash 0: 3
  
  Hash 5: As chaves que geram hash 5 são 205,305,405.
  
  Primeira chave (205): inserção sem colisão.
  
  Segunda chave (305): colisão.
  
  Terceira chave (405): colisão.
  
  Total de colisões para o hash 5: 2
  
  Hash 10: As chaves são 210,310,410.
  
  Primeira chave (210): inserção sem colisão.
  
  Segunda chave (310): colisão.
  
  Terceira chave (410): colisão.
  
  Total de colisões para o hash 10: 2
  
  Para diminuir esse número, eu faria a escolha de um tamanho da tabela diferença, usando assim um número primo, já que só possui 1 e ele mesmo de 
  divisor, assim quando fosse feito o mod, a distribuição dos números seria de maneira mais dispersa na tabela;
  
  Se o tamanho da tabela fosse 101 (um número primo), a função de hash seria h(k)=kmod101.

  Para o conjunto de chaves keys=[200,205,…,500], a função h(k)=kmod101 distribuiria os elementos de forma muito mais uniforme. Por exemplo:
  
  200mod101=99
  
  205mod101=4
  
  210mod101=9
  
  301mod101=98
  
  402mod101=99
  
  Note que 200 e 402 colidiram, mas a distribuição seria muito melhor do que o agrupamento em múltiplos de 5. Com um tamanho primo, a chance de chaves próximas colidirem é significativamente menor.
  
  
  ```

