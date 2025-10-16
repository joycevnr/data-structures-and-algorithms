package leda;
import java.util.Scanner;

class WarmUp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //leitura da primeira linha
        int n = Integer.parseInt(sc.nextLine());
        //leitura da segunda linha
        String[] numeros = sc.nextLine().split(" ");
        String out = "";

        for (int i = 0; i < numeros.length; i++) {
            out += Integer.toString((Integer.parseInt(numeros[i])*n)) + " " ;
            }
        System.out.println(out.trim());
        }
}
