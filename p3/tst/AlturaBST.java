import java.util.*;

class AlturaBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        
        // Cria a lista de adjacências para representar o grafo (árvore não-direcionada)
        List<List<Integer>> grafo = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            grafo.add(new ArrayList<>());
        }
        
        // Lê as N-1 arestas e constrói o grafo
        for (int i = 0; i < n - 1; i++) {
            String[] aresta = sc.nextLine().split(" ");
            int no1 = Integer.parseInt(aresta[0]);
            int no2 = Integer.parseInt(aresta[1]);
            
            // Adiciona a conexão nos dois sentidos (grafo não-direcionado)
            grafo.get(no1).add(no2);
            grafo.get(no2).add(no1);
        }
        
        // Aplica o algoritmo das duas BFS para encontrar a altura
        ArvoreAltura arvore = new ArvoreAltura(grafo, n);
        arvore.encontrarAltura();
        
        sc.close();
    }
    
    static class ArvoreAltura {
        private List<List<Integer>> grafo;
        private int numNos;
        
        public ArvoreAltura(List<List<Integer>> grafo, int numNos) {
            this.grafo = grafo;
            this.numNos = numNos;
        }
        
        public void encontrarAltura() {
            // Primeira BFS: encontra o nó mais distante do nó 0
            ResultadoBFS primeira = bfs(0);
            
            // Segunda BFS: encontra o nó mais distante do resultado da primeira BFS
            ResultadoBFS segunda = bfs(primeira.noMaisDistante);
            
            // Garante que X <= Y na saída
            int x = Math.min(primeira.noMaisDistante, segunda.noMaisDistante);
            int y = Math.max(primeira.noMaisDistante, segunda.noMaisDistante);
            
            System.out.println("de -> " + x + " para -> " + y + " altura -> " + segunda.distanciaMaxima);
        }
        
        // BFS modificada que retorna o nó mais distante e a distância máxima
        private ResultadoBFS bfs(int noInicial) {
            // Array para marcar nós visitados
            boolean[] visitado = new boolean[numNos];
            // Array para armazenar a distância de cada nó em relação ao nó inicial
            int[] distancia = new int[numNos];
            
            // Fila para a BFS
            Queue<Integer> fila = new LinkedList<>();
            
            // Inicializa a BFS
            fila.add(noInicial);
            visitado[noInicial] = true;
            distancia[noInicial] = 0;
            
            int noMaisDistante = noInicial;
            int distanciaMaxima = 0;
            
            // Executa a BFS
            while (!fila.isEmpty()) {
                int noAtual = fila.poll();
                
                // Verifica todos os vizinhos do nó atual
                for (int vizinho : grafo.get(noAtual)) {
                    if (!visitado[vizinho]) {
                        visitado[vizinho] = true;
                        distancia[vizinho] = distancia[noAtual] + 1;
                        fila.add(vizinho);
                        
                        // Atualiza o nó mais distante se necessário
                        if (distancia[vizinho] > distanciaMaxima) {
                            distanciaMaxima = distancia[vizinho];
                            noMaisDistante = vizinho;
                        }
                    }
                }
            }
            
            return new ResultadoBFS(noMaisDistante, distanciaMaxima);
        }
    }
    
    // Classe auxiliar para armazenar o resultado da BFS
    static class ResultadoBFS {
        int noMaisDistante;
        int distanciaMaxima;
        
        public ResultadoBFS(int noMaisDistante, int distanciaMaxima) {
            this.noMaisDistante = noMaisDistante;
            this.distanciaMaxima = distanciaMaxima;
        }
    }
}