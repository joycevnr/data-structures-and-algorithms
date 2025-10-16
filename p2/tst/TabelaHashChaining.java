import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

    // Classe aninhada para representar o par chave-valor
    static class Pair {
        int key;
        String value;

        public Pair(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "<" + key + ", " + value + ">";
        }
    }

    private ArrayList<Pair>[] tabela;
    private int tamanho;

    // Construtor da Tabela Hash
    public Solution(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new ArrayList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArrayList<>();
        }
    }

    // Função de hash
    private int hash(int key) {
        return key % tamanho;
    }

    // Adiciona um par chave-valor
    public void put(int key, String value) {
        int index = hash(key);
        ArrayList<Pair> lista = tabela[index];

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).key == key) {
                lista.set(i, new Pair(key, value));
                printTableState();
                return;
            }
        }

        lista.add(new Pair(key, value));
        printTableState();
    }

    // Remove um par pela chave
    public void remove(int key) {
        int index = hash(key);
        ArrayList<Pair> lista = tabela[index];

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).key == key) {
                lista.remove(i);
                printTableState();
                return;
            }
        }
    }

    // Obtém as chaves ordenadas
    public List<Integer> getKeys() {
        List<Integer> keys = new ArrayList<>();
        for (ArrayList<Pair> lista : tabela) {
            for (Pair par : lista) {
                keys.add(par.key);
            }
        }
        Collections.sort(keys);
        return keys;
    }

    // Obtém os valores ordenados
    public List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (ArrayList<Pair> lista : tabela) {
            for (Pair par : lista) {
                values.add(par.value);
            }
        }
        Collections.sort(values);
        return values;
    }

    // Imprime o estado da tabela
    public void printTableState() {
        System.out.print("[");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(tabela[i]);
            if (i < tamanho - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tamanhoTabela = scanner.nextInt();
        Solution tabelaHash = new Solution(tamanhoTabela);
        scanner.nextLine(); // Consumir a quebra de linha

        String linha;
        while (scanner.hasNextLine() && !(linha = scanner.nextLine().trim()).equals("end")) {
            if (linha.isEmpty()) {
                continue;
            }

            String[] partes = linha.split(" ");
            String comando = partes[0];

            switch (comando) {
                case "put":
                    int keyPut = Integer.parseInt(partes[1]);
                    String valuePut = partes[2];
                    tabelaHash.put(keyPut, valuePut);
                    break;
                case "remove":
                    int keyRemove = Integer.parseInt(partes[1]);
                    tabelaHash.remove(keyRemove);
                    break;
                case "keys":
                    System.out.println(tabelaHash.getKeys().toString().replace(" ", ""));
                    break;
                case "values":
                    System.out.println(tabelaHash.getValues().toString().replace(" ", ""));
                    break;
            }
        }
        scanner.close();
    }
}