import java.util.Scanner;

class  RadixSort{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lendo
        String[] entrada = sc.nextLine().split(" ");
        int[] arr = new int[entrada.length];
        for (int i = 0; i < entrada.length; i++) {
            arr[i] = Integer.parseInt(entrada[i]);
        }

        //  maior elemento do array
        int k = Integer.parseInt(sc.nextLine());

        countingSortPassoAPasso(arr, k);

        sc.close();
    }

    public static void countingSortPassoAPasso(int[] arr, int k) {
        int n = arr.length;

        //  contagem
        int[] count = new int[k + 1];

        // Calculando a frequência de cada elemento
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
            System.out.println(arrayToString(count)); // Imprime após cada modificação
        }

        // Calculando a cumulativa do vetor de contagem
        for (int i = 1; i <= k; i++) {
            count[i] += count[i - 1];
        }
        System.out.println("Cumulativa do vetor de contagem - " + arrayToString(count));

        // Criando o array de saída
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int position = count[arr[i]] - 1;  // Ajusta para índice 0
            output[position] = arr[i];
            count[arr[i]]--;
        }

        // Imprimindo o vetor de contagem final
        System.out.println(arrayToString(count));

        // Imprimindo o array ordenado
        System.out.println(arrayToString(output));
    }

    // Método auxiliar para converter array em string
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}