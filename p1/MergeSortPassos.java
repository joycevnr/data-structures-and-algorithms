package leda;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSortPassos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leitura da entrada
        String[] input = scanner.nextLine().split(" ");
        int[] array = new int[input.length];

        // Conversão para inteiros
        for (int i = 0; i < input.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        //System.out.println("Array inicial: " + Arrays.toString(array));

        // Chamada inicial do Merge Sort
        mergeSort(array, 0, array.length - 1);

        scanner.close();
    }

    public static void mergeSort(int[] array, int ini, int fim) {
        // Condição de parada: subarray com 0 ou 1 elemento já está ordenado
        if (ini < fim) {
            int meio = (ini + fim) / 2;

            System.out.println("Dividindo: " +
                    Arrays.toString(Arrays.copyOfRange(array, ini, fim + 1)));

            // Ordena recursivamente a primeira metade
            mergeSort(array, ini, meio);

            // Ordena recursivamente a segunda metade
            mergeSort(array, meio + 1, fim);

            // Combina as duas metades ordenadas
            merge(array, ini, meio, fim);
        }
    }

    private static void merge(int[] array, int ini, int meio, int fim) {
        // Cria array auxiliar para a combinação
        int[] auxiliar = new int[array.length];

        // Copia o trecho que será combinado para o array auxiliar
        for (int i = ini; i <= fim; i++) {
            auxiliar[i] = array[i];
        }

        System.out.println("Combinando: " +
                Arrays.toString(Arrays.copyOfRange(array, ini, fim + 1)));

        // Inicializa ponteiros:
        int ponteiroEsq = ini;       // Começo da primeira metade
        int ponteiroDir = meio + 1;  // Começo da segunda metade
        int atual = ini;             // Posição atual no array original

        // Enquanto houver elementos em ambas as metades
        while (ponteiroEsq <= meio && ponteiroDir <= fim) {
            // Compara elementos das duas metades
            if (auxiliar[ponteiroEsq] <= auxiliar[ponteiroDir]) {
                array[atual] = auxiliar[ponteiroEsq];
                ponteiroEsq++;
            } else {
                array[atual] = auxiliar[ponteiroDir];
                ponteiroDir++;
            }
            atual++;
        }

        // Copia os elementos restantes da primeira metade (se houver)
        while (ponteiroEsq <= meio) {
            array[atual] = auxiliar[ponteiroEsq];
            atual++;
            ponteiroEsq++;
        }

        System.out.println("Resultado: " +
                Arrays.toString(Arrays.copyOfRange(array, ini, fim + 1)));
    }
}