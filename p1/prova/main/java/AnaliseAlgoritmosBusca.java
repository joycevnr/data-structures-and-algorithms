public class AnaliseAlgoritmosBusca {
    public int contaPassosLinear(int[] a, int valor) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == valor) {
                return i + 1;
            }
        }
        return 0;
    }
    int contador = 0;
    public int contaPassosBinaria(int[] a, int ini, int fim, int valor) {
        if (ini > fim) return 0;
        int meio = (ini + fim) / 2;
        if (a[meio] == valor) return ini;
        else if (a[meio] < valor) {
            contaPassosBinaria(a, meio + 1, fim, valor);
            contador += 1;
        } else {
            contaPassosBinaria(a, ini, meio - 1, valor);
            contador += 1;
        }
        return contador;
    }

    public static void main(String[] args) {
        AnaliseAlgoritmosBusca analisador = new AnaliseAlgoritmosBusca();
        int[] v = new int[]{1, 8, 9, 12, 14, 18, 20};
        System.out.println(analisador.contaPassosBinaria(v, 0, v.length - 1, 1));

    }

}
