import java.util.Arrays;

class Estudo {
    public int buscaBinaria(int[] v, int n, int ini, int fim) {
        if (ini > fim) {
            return -1;
        }

        int meio = (ini + fim) / 2;

        if (v[meio] == n) {
            return meio;
        }

        if (n < v[meio]) {
            return buscaBinaria(v, n, ini, meio - 1);
        } else {
            return buscaBinaria(v, n, meio + 1, fim);
        }
    }

    public int buscaLinear(int[] v, int n) {
        for (int i = 0; i < v.length; i++) {
            if (n == v[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 6, 11, 8, 10};
        Arrays.sort(array);
        Estudo estudo = new Estudo();

        int numeroProcurado = 11;

        // Executa a busca binária
        int resultadoBinaria = estudo.buscaBinaria(array, numeroProcurado, 0, array.length - 1);
        if (resultadoBinaria != -1) {
            System.out.println("Número encontrado na posição (busca binária): " + resultadoBinaria);
        } else {
            System.out.println("Número não encontrado (busca binária).");
        }

        // Executa a busca linear
        int resultadoLinear = estudo.buscaLinear(array, numeroProcurado);
        if (resultadoLinear != -1) {
            System.out.println("Número encontrado na posição (busca linear): " + resultadoLinear);
        } else {
            System.out.println("Número não encontrado (busca linear).");
        }
    }
}