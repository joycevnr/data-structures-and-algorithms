import java.util.Scanner;
import java.util.Stack;

public class ElementAtPilha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lendo os elementos da pilha
        String[] elementosString = scanner.nextLine().split(" ");
        Stack<Integer> pilha = new Stack<>();
        for(int i = elementosString.length - 1; i >= 0; i--){
            pilha.push(Integer.parseInt(elementosString[i]));
        }

        int indice = scanner.nextInt();

        if (pilha.isEmpty() || indice < 0 || indice >= pilha.size()) {
            System.out.println("indice invalido");
        }else{
            System.out.println(elementoAtPilha(pilha, indice));
        }


        scanner.close();
    }

    public static int elementoAtPilha(Stack<Integer> pilha, int indice) {

        Stack<Integer> auxiliar = new Stack<>();
        int contador = 0;

        while (!pilha.isEmpty()) {
            int elemento = pilha.pop();
            if (contador == indice) {
                return elemento;
            }
            contador++;
            auxiliar.push(elemento);
        }

        // Restaura a pilha original
        while (!auxiliar.isEmpty()) {
            pilha.push(auxiliar.pop());
        }
        return -1;

    }
}