package leda;

import java.util.Arrays;
import java.util.*;

class SelectionSortPassoPasso {
    public static void main(String[] var0) {
        Scanner entrada = new Scanner(System.in);
        String[] var2 = entrada.nextLine().split(" ");
        int[] var3 = new int[var2.length];
        for(int i = 0; i < var2.length; ++i) {
            var3[i] = Integer.parseInt(var2[i]);
        }


        for(int i = 0; i < var3.length - 1; ++i) {
            int menorIndice = i;
            for(int j = i + 1; j < var3.length; j++){
                if(var3[menorIndice] > var3[j])
                    menorIndice = j;
            }
            swap(var3, i, menorIndice);
            System.out.println(Arrays.toString(var3));
        }
    }
    public  static void swap(int[] v, int i, int j){
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }
}