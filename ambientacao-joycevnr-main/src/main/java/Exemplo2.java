import java.io.*;
import java.util.*;

public class Exemplo2 {

    public static void main(String[] args) {

        // lendo da entrada padrão
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            // Cabeçalho
            //System.out.println("alg time sample");
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int repetitions = 30;
                long totalTime = 0;

                // medindo tempo de execução 
                for (int i = 0; i < repetitions; i++) {
                    long start = System.nanoTime();
                    //buscaDuplicados(tokens);
                    buscaQuadrada(tokens); 
                    long end = System.nanoTime();
                    totalTime += (end - start);
                }
                // TODO incluir aqui chamada para o método sob análise        
                
                // saída padrão: método tempo tamanho_da_entrada
                long averageTime = totalTime / repetitions;
                System.out.println("BuscaQuadrada " + averageTime + " " + tokens.length);

            }
        } catch (IOException ioe) {}

    }


    public static boolean buscaDuplicados(String[] tokens) {
        HashSet<String> set = new HashSet<String>();
        for(int i = 0; i < tokens.length; i++){
                if(set.contains(tokens[i])){
                    return true;
                } else {
                    set.add(tokens[i]);
                }
            }
        return false;
    }

    public static boolean buscaQuadrada(String[] tokens) {
        for(int i = 0; i < tokens.length; i++){
            for(int j = i + 1; j < tokens.length; j++){
                if(tokens[i].equals(tokens[j])){
                    return true;
            }
        }
    }
        return false;   
    }
}
