
public class QuickSelect {
    //k - posicao
    public int quickSelect(int[] v, int k) {
        int ini = 0;
        int fim = v.length - 1;
        int pivot = particiona(v, ini, fim);
        if (pivot == k) return v[pivot];
        else if (pivot > k) return particiona(v, ini, pivot - 1);
        else return particiona(v, pivot + 1, fim);

    }

        public int particiona ( int[] v, int ini, int fim){
            int i = ini + 1;
            int pivot = v[ini];

            for (int j = ini + 1; j <= fim; j++) {
                if (v[j] < pivot) {
                    swap(v, i, j);
                    i++;
                }
            }
            // Coloca o pivô na posição correta
            swap(arr, pivot, i );

            return i - 1; // Retorna o índice final do pivô
        }
        private static void swap ( int[] arr, int i, int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

}
}