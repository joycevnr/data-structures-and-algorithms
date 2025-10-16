package leda;
import java.util.Scanner;

public class VerificacaoElementosDuplicados {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        String[] elementos = entrada.split(" ");

        for (int i = 0; i < elementos.length; i++) {
            for (int j = i + 1; j < elementos.length; j++) {
                if (elementos[i].equals(elementos[j])) {
                    System.out.println("true");
                    return;
                }
            }
        }
        System.out.println("false");
    }
}
