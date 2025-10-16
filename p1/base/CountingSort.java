package sorting;

import java.util.Arrays;
import java.util.Scanner;

public class CountingSort {
    public void main(String[] args) {
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
        classicCountingSort(array, 20);

        scanner.close();
    }

    /**
     * Implemente a versão clássica do counting sort que vimos em sala de aula. Você pode
     * criar métodos auxiliares se precisar.
     */
    /*
     *passos:
     tenho o array 'a'
     crio o array 'b' -guarda a frequencia o - tamanho dele é k
     crio um primeiro for para o armazenamento -  b[a[i] -1] += 1
     calculo a frequencia que é armazenada em b - faz a aculativa também, soma a frequencia atual com a anterior, for começa em 1: b[i] += b[i-1]
     - proximo for calcula armazenada a ordenação com base de b,
   c[ b[ a[i]- 1] -1]

     * */
    public int[] classicCountingSort(int[] a, int k) {
        int[] b = new int[k]; //para cálculo
        int[] c = new int[a.length];//final

        //frequencia
        for (int i = 0; i < b.length; i++) {
            b[a[i] - 1] += 1;// é o valor -1, ele contabiliza, toda vez que tenho
            // aquele valor -1, que é o indíce que vai ser
            // armazenado
        }
        //cumulativa
        for (int i = 1; i < b.length; i++) {
            b[i] += b[i - 1];
        }
        //ordenacao
        for (int i = a.length - 1; i >= 0; i--) { //de tras para frente
            c[b[a[i] - 1] - 1] = a[i];
            b[a[i] - 1] -= 1;

        }
        return c;
    }

    /**
     * Implemente uma versão do counting sort que aceita valor 0 na coleção original.
     */
    public int[] zeroCountingSort(int[] a, int k) {

        int[] b = new int[k];
        int[] c = new int[a.length];

        //frequencia
        for (int i = 0; i < b.length; i++) {
            b[a[i]] += 1;
        }
        //cumulativa
        for (int i = 1; i < b.length; i++) {
            b[i] += b[i - 1];
        }
        //ordenacao
        for (int i = a.length; i >= 0; i--) { //de tras para frente
            c[b[a[i]]] = a[i];
            b[a[i]] -= 1;
        }
        return c;
    }

    /**
     * Implemente uma versão do counting sort que aceita valores negativos na coleção original. Você
     * vai precisar identificar o menor elemento do array. FAça isso no início do método.
     */
    public int[] negativosCountingSort(int[] a, int k) {
        int menor = procuraMenor(a);
        int[] b = new int[k + menor]; //o tamanho do array será ele mais o
        //valor do menor encontrado
        int[] c = new int[a.length];

        //frequencia
        for (int i = 0; i <= b.length; i++) {
            b[a[i + menor]] += 1;
        }

        //cumulativa
        for (int i = 1; i <= b.length; i++) {
            b[i + menor] += b[i + menor - 1];
        }
        //ordenacao
        for (int i = b.length; i >= 0; i--) {
            c[b[a[i] - 1] - 1 + menor] = a[i];
            b[a[i] + menor] -= 1;
        }
        return c;
    }

    public int procuraMenor(int[] v) {
        int menor = v[0];
        for (int i = 0; i < v.length; i++) {
            if (v[i] < menor) {
                menor = v[i];
            }
            return -menor;

        }
    }
}


