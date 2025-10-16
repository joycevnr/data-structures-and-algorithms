package sorting;

import java.util.Arrays;

public class RadixSort {

    // Você pode assumir que todos os valores possuem a mesma quantidade de dígitos
    // Caso precise do counting sort, use o que você já implementou na outra classe.
    //um pouco confuso
    public int[] radixSort(int[] v) {
        int numDigitos = String.valueOf(v[0]).length();
        // controlar a casa decimal (1 = unidades, 10 = dezenas..)
        int exp = 1;

        // faz uma rodada para cada dígito.
        for (int i = 0; i < numDigitos; i++) {
            int[] c = new int[v.length];
            int[] b = new int[10];      // contagem- sempre com tamanho 10 para os dígitos de 0 a 9.

            // frequência
            // Em vez de usar o número todo 'v[i]', pega apenas o dígito da vez.
            for (int j = 0; j < v.length; j++) {
                int digito = (v[j] / exp) % 10; // Ex: (321 / 10) % 10 = 2
                b[digito]++;
            }

            // Cumulativa - igual
            for (int j = 1; j < 10; j++) {
                b[j] += b[j - 1];
            }

            // Ordenação (igual, mas usando o 'digito')
            // Percorre de trás para frente para ser estável.
            for (int j = v.length - 1; j >= 0; j--) {
                int digito = (v[j] / exp) % 10;
                c[b[digito] - 1] = v[j];
                b[digito]--;
            }

            // Copia o resultado da rodada de volta para o array original 'v'
            // para que a próxima rodada já pegue a lista semi-ordenada.
            for (int j = 0; j < v.length; j++) {
                v[j] = c[j];
            }

            // prepara para a próxima rodada (dezenas, centenas...)
            exp *= 10;
        }
// meus questionamentos:
//		Por que Nem Todo Loop Dentro de Loop é O(n2)?
//		Um algoritmo é O(n 2) quando o loop externo roda n vezes E o loop interno
//		também roda n vezes para cada iteração do externo. O trabalho total é n * n.
        //no radix Sort:
//		        n: É a quantidade de itens que você quer ordenar.
//				d: É o número de dígitos do seu maior número.
//	   A complexidade total do Radix Sort é: (Número de Rodadas) x (Custo de cada Rodada)
//		Loop Externo (As Rodadas): Ele roda d vezes, d não depende de n. Se tem 100 números para ordenar ou 1 milhão de números,
//		d continua o mesmo se o maior número for 999 (d=3).
//		Loop Interno (O Custo da Rodada): Dentro de cada rodada, nós executamos o Counting Sort.
//		A complexidade do Counting Sort é O(n + k), onde k é o range (neste caso, k=10 para os dígitos 0-9).
//		Como k é uma constante pequena, podemos dizer que o custo de cada rodada é basicamente O(n).


        return v;
    }
}