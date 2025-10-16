package leda;
import java.util.Scanner;

class Top-n {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] numeros = sc.nextLine().split(" ");
        int n = Integer.parseInt(sc.nextLine());
        String out = "";

        for (int i = 0; i < numeros.length; i++) {

            out += Integer.toString((Integer.parseInt(numeros[i])*n)) + " " ;
            }
        System.out.println(out.trim());
        }
}
