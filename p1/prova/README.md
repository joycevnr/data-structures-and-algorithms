**`~~~~Quantos passos? (2.5)

Busca binária é reconhecidamente um algoritmo eficiente de busca. Para demonstrar isso, normalmente um professor
apresenta o número de passos que o algoritmo levou até encontrar um determinar do valor e o compara com o número de
passos de uma busca linear.

É isso que você vai fazer nessa prova. Você vai implementar ambos os algoritmos, busca linear iterativa e busca binária
recursiva, mas ao invés de retornar o índice do elemento encontrado (ou -1 se não encontrar), você vai retornar o número
de passos efetuados pelos algoritmos.

Para simplificar, no nosso caso, o número de passos da busca binária recursiva será a quantidade de chamadas recursivas
efetuadas, enquanto a busca linear será a quantidade de vezes que a parte interna do laço é executada.

Veja os testes disponibilizados para entender bem o que deve ser feito.

QuickSelect (2.5)

O Quick Select é um algoritmo para encontrar o k-ésimo menor elemento em um vetor desordenado. A ideia é semelhante ao
Quicksort: usar particionamento. Contudo, seu objetivo não é ordenar a sequência, basta achar o k-ésimo menor. Portanto,
há mudanças na decisão da(s) chamada(s) recursivas. Use essa estratégia para implementar o método quickSelect(int[] v,
int k) que receba um vetor de inteiros v e uma posição int k, e retorne o k-ésimo menor elemento (sendo k = 1 o menor
elemento).

Você pode considerar que não há elementos repetidos e que k é sempre válido.

Um aluno desavisado pode ler o teste AnaliseAlgoritmosBuscaTest.testPrimeiro() e chegar a conclusão de que o método de
busca linear é mais eficiente que o de busca binária. Explique porquê isso não é verdade e onde ele pode estar errando
na análise dele. Argumente baseado nos diversos pontos sobre análise de algoritmos que discutimos em sala de aula.77

Ele está errada, pois enquanto o de busca linear para localizar um elemento em uma sequência ordenada precisa varer todo o array
até encontrar o elemento, na busca binária, ele trabalha na ordem de log n, subdividindo o array ao meio, e chamando recursivamente 
com seu novo início e fim, finalizando esse processo em tempo de execução log n.

Suponha que você precise ordenar um vetor de alguns milhões de números inteiros quase ordenados (isto é, apenas algumas
trocas fora de lugar). Que algoritmo da classe quadrática você usaria? Por que? Justifique sua resposta como um
cientista da computação, usando argumentos concretos sobre a análise de desempenho do algoritmo que você escolheu.
Eu usaria o insertion sort, pois ele iria comparar os elementos vizinhos e fazer algumas poucas trocas, pois comparando
com o selection sort, se fosse usá-lo, ele iria guarda o menor elemento e sempre sair comparando, para poder encontrar 
um menor que aquele elemento. Comparando asssim o custo, com o insertion que iria fazer trocas entre os vizinhos que, 
seria menos trocas por a array já está quase ordenado, então considerando o custo de trocas e comparações, optar pelo
uso do insertion é melhor;


insertion --> for com um while interno, faz mais trocas pois compara elemento com elemento
selection --> dois for -- compara um elemento da com todos os outros , dois for, faz mais comparação e menos trocas, 
pois salva o indice do menor

Discuta detalhadamente a análise de desempenho do algoritmo quickselect da questão prática 2. Não é preciso demonstrar
formalmente, mas debata como um cientista da computação, usando argumentos concretos sobre desempenho do algoritmo que
você escolheu.**~~~~
`