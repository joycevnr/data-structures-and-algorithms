import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tabelas Hash: comparando os métodos da Divisão e da Multiplicação com resolução
 * de colisões por encadeamento.
 */
public class AnaliseTabelaHash {

    private static final int TAMANHO_TABELA = 53;
    private static final double CONSTANTE_A = 0.6180339887;
    private static final String CAMINHO_ARQUIVO = "data/keys.txt";

    public static void main(String[] args) {

        // Cada tabela é uma lista de listas. A lista externa representa os índices (0-52)
        // e cada lista interna representa a "corrente" de chaves que colidiram naquele índice.
        List<List<Integer>> tabelaDivisao = new ArrayList<>();
        List<List<Integer>> tabelaMultiplicacao = new ArrayList<>();

        // Inicializa as tabelas com 53 listas vazias.
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            tabelaDivisao.add(new ArrayList<>());
            tabelaMultiplicacao.add(new ArrayList<>());
        }

        // Contadores para as colisões.
        int colisoesDivisao = 0;
        int colisoesMultiplicacao = 0;

        // LEITURA DO ARQUIVO E PROCESSAMENTO DAS CHAVES
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            Scanner scanner = new Scanner(arquivo);

            // Loop para ler cada chave do arquivo.
            while (scanner.hasNextInt()) {
                int chave = scanner.nextInt();

                // --- Processamento para o Método da Divisão ---
                int indiceDivisao = hashDivisao(chave);
                // Uma colisão ocorre se tentamos inserir em uma lista que já não está vazia.
                if (!tabelaDivisao.get(indiceDivisao).isEmpty()) {
                    colisoesDivisao++;
                }
                tabelaDivisao.get(indiceDivisao).add(chave);

                // --- Processamento para o Método da Multiplicação ---
                int indiceMultiplicacao = hashMultiplicacao(chave);
                // Mesma lógica de contagem de colisão.
                if (!tabelaMultiplicacao.get(indiceMultiplicacao).isEmpty()) {
                    colisoesMultiplicacao++;
                }
                tabelaMultiplicacao.get(indiceMultiplicacao).add(chave);
            }
            scanner.close();

            // --- 3. APRESENTAÇÃO DOS RESULTADOS ---
            System.out.println("--- ESTADO FINAL (MÉTODO DA DIVISÃO) ---");
            imprimirEstadoTabela(tabelaDivisao);

            System.out.println("\n--- ESTADO FINAL (MÉTODO DA MULTIPLICAÇÃO) ---");
            imprimirEstadoTabela(tabelaMultiplicacao);

            System.out.println("\n--- NÚMERO DE COLISÕES ---");
            System.out.println("Método da Divisão: " + colisoesDivisao);
            System.out.println("Método da Multiplicação: " + colisoesMultiplicacao);


        } catch (FileNotFoundException e) {
            System.err.println("ERRO: O arquivo " + CAMINHO_ARQUIVO + " não foi encontrado.");
            System.err.println("Verifique se o arquivo está no lugar certo e se você está executando o programa do diretório raiz do projeto.");
            e.printStackTrace();
        }
    }

    /**
     * Calcula o hash de uma chave usando o método da divisão.
     * hash(k) = k mod m
     * @param chave A chave a ser processada.
     * @return O índice da tabela (hash).
     */
    private static int hashDivisao(int chave) {
        return chave % TAMANHO_TABELA;
    }

    /**
     * Calcula o hash de uma chave usando o método da multiplicação.
     * hash(k) = floor(m * (k * A mod 1))
     * @param chave A chave a ser processada.
     * @return O índice da tabela (hash).
     */
    private static int hashMultiplicacao(int chave) {
        // (chave * CONSTANTE_A) % 1  -> Pega a parte fracionária da multiplicação.
        double parteFracionaria = (chave * CONSTANTE_A) % 1;
        // Multiplica pelo tamanho da tabela e pega a parte inteira.
        return (int) (parteFracionaria * TAMANHO_TABELA);
    }

    /**
     * Imprime o estado final de uma tabela hash no formato "índice, [lista de chaves]".
     * @param tabela A tabela a ser impressa.
     */
    private static void imprimirEstadoTabela(List<List<Integer>> tabela) {
        for (int i = 0; i < tabela.size(); i++) {
            System.out.println(i + ", " + tabela.get(i));
        }
    }
}