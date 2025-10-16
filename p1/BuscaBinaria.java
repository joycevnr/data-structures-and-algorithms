package leda;

public class BuscaBinaria {
    public static void main(String[] args) {}

    public int indexOf(int[] v, int n, int ini, int fim) {
        if (ini <= fim) {
            int meio = (ini + fim) / 2;

            if (v[meio] == n) return meio;

            if (n < v[meio])
                return indexOf(v, n, ini, meio - 1);
            else
                return indexOf(v, n, meio + 1, fim);
        } else {
            return -1;
        }
    }
}
