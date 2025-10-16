public class MergeSisu {

    /**
     * Mescla dois arrays ordenados de requisições (representados por inteiros)
     * em um único array ordenado.
     *
     */
    public static int[] mesclarRequisicoes(int[] servidor1, int[] servidor2) {
        int n1 = servidor1.length;
        int n2 = servidor2.length;
        int[] resultado = new int[n1 + n2];

        int i = 0; // Ponteiro para servidor1
        int j = 0; // Ponteiro para servidor2
        int k = 0; // Ponteiro para resultado

        // Percorre ambos os arrays, comparando elementos e adicionando o menor ao resultado.
        while (i < n1 && j < n2) {
            if (servidor1[i] <= servidor2[j]) {
                resultado[k++] = servidor1[i++];
            } else {
                resultado[k++] = servidor2[j++];
            }
        }

        // Se restarem elementos em servidor1, copia-os para o final do resultado.
        while (i < n1) {
            resultado[k] = servidor1[i];
            i++;
            k++;
        }

        // Se restarem elementos em servidor2, copia-os para o final do resultado.
        while (j < n2) {
            resultado[k] = servidor2[j];
            j++;
            k++;
        }

        return resultado;
    }
    /*
    O algoritmo tem complexidade de tempo O(n_1+n_2), onde n_1 e n_2 são os
    tamanhos dos dois arrays. Isso ocorre porque o algoritmo percorre cada elemento dos
    dois arrays de entrada exatamente uma vez para construir o array final.
     */
}