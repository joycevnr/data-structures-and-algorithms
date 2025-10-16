class Estudo2 {
    public void quickSort(int[] v, int k) {
        quickSelect(v, 0, v.length - 1, k);
    }
    public void quickSelect(int[] v, int ini, int fim, int k) {
        //pivot é o primeiro elemento
        int pivot = partition(v, ini, fim);
        //particiona
        if(pivot > k) {
            quickSelect(v, ini, pivot - 1, k);
        }else {
            quickSelect(v, pivot + 1, fim, k);
        }
        //chamada recursiva
    }

    public int partition(int[] v, int ini, int fim) {
        int meio =(ini + fim)/2;
        int pivot = v[meio];
        int i = ini;
        for(int j = ini + 1; j <= fim; j++){
            if(v[j] < pivot){
                swap(v,i,j);
                i+=1;
            }
        }
        //troca o pivit com o posição do meio
        swap(v, pivot, i);
        return i -1;
    }
    public void swap(int[] v, int i, int j) {
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 6, 11, 8, 10};
        Estudo2 estudo = new Estudo2();
        estudo.quickSort(array, 5);
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}