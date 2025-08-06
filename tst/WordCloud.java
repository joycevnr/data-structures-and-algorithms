import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parte 1: Leitura do texto e cálculo da frequência das palavras.
        String texto = scanner.nextLine();
        // Converte o texto para minúsculas e divide em palavras
        String[] palavras = texto.toLowerCase().split(" ");

        // Usa um HashMap para armazenar as palavras e suas frequências.
        // A chave é a palavra (String) e o valor é a frequência (Integer).
        Map<String, Integer> frequencias = new HashMap<>();

        for (String palavra : palavras) {
            // Se a palavra já está no mapa, incrementa a frequência.
            if (frequencias.containsKey(palavra)) {
                frequencias.put(palavra, frequencias.get(palavra) + 1);
            }
            // Se a palavra é nova, adiciona-a com a frequência 1.
            else {
                frequencias.put(palavra, 1);
            }
        }

        // Parte 2: Processamento das consultas do usuário.
        String consulta;
        while (!(consulta = scanner.nextLine()).equals("fim")) {
            // Verifica se a palavra consultada está no mapa.
            if (frequencias.containsKey(consulta)) {
                // Se sim, imprime a frequência.
                System.out.println(frequencias.get(consulta));
            } else {
                // Se não, a frequência é 0.
                System.out.println(0);
            }
        }

        scanner.close();
    }
}