import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lendo os nós
        String[] nos = scanner.nextLine().split(" ");
        int n = nos.length;

        // Lendo a matriz de adjacência e preenchendo a tabela hash
        Map<String, Integer> tabelaHash = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int peso = scanner.nextInt();
                if (peso != 0 && i <= j) { // Adiciona apenas uma vez para grafos não dirigidos
                    String aresta = nos[i] + nos[j];
                    tabelaHash.put(aresta, peso);
                }
            }
        }
        scanner.nextLine(); // Consome a quebra de linha restante

        // Lendo as pesquisas por arestas
        String pesquisa;
        while (!(pesquisa = scanner.nextLine()).equals("fim")) {
            String no1 = String.valueOf(pesquisa.charAt(0));
            String no2 = String.valueOf(pesquisa.charAt(1));

            // Normaliza a ordem para encontrar a aresta (ex: "ba" se torna "ab")
            if (no1.compareTo(no2) > 0) {
                String temp = no1;
                no1 = no2;
                no2 = temp;
            }
            String arestaNormalizada = no1 + no2;

            // Verifica a existência e imprime o resultado
            if (tabelaHash.containsKey(arestaNormalizada)) {
                System.out.println(tabelaHash.get(arestaNormalizada));
            } else {
                System.out.println("aresta inexistente.");
            }
        }

        scanner.close();
    }
}