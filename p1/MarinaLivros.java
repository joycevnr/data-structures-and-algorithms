import java.util.Scanner;

class MarinaLivros {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] entrada = sc.nextLine().split(",");

        insertionSort(entrada);

        sc.close();
    }

    public static void insertionSort(String[] v) {
        System.out.println(String.join(", ", v));

        for (int i = 1; i < v.length; i++) {
            int j = i;
            while (j > 0 && v[j].compareTo(v[j - 1]) < 0) {
                swap(v, j, j - 1);
                j--;
            }
            System.out.println(String.join(", ", v));
        }
    }

    public static void swap(String[] v, int i, int j) {
        String aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }
}