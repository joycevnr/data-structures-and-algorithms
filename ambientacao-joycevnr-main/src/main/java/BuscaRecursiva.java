import java.io.*;

public class BuscaRecursiva {

    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            // Cabeçalho
           // System.out.println("alg time sample");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int[] lista = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    lista[i] = Integer.parseInt(tokens[i]);
                }

                int repetitions = 30;
                long totalTime = 0;
                // tempo de execucao
                for (int i = 0; i < repetitions; i++) {
                    long start = System.nanoTime();
                   // buscaBinaria(lista, -1, 0, lista.length - 1);
                    buscaLinearRecursivo(lista, -1, 0);
                    long end = System.nanoTime();
                    totalTime += (end - start);
                }

                long averageTime = totalTime / repetitions;
                System.out.println("BuscaLinearRecursiva " + averageTime + " " + lista.length);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static int buscaBinaria(int[] v, int n, int ini, int fim) {
        if (ini <= fim) {
            int meio = (ini + fim) / 2;

            if (v[meio] == n) return meio;

            if (n < v[meio])
                return buscaBinaria(v, n, ini, meio - 1);
            else
                return buscaBinaria(v, n, meio + 1, fim);
        } else {
            return -1;
        }
    }

    public static int buscaLinearRecursivo(int[] lista, int elemento, int index) {
        if (index >= lista.length)
            return -1;
        if (lista[index] == elemento)
            return index;
        return buscaLinearRecursivo(lista, elemento, index + 1);
    }
}

