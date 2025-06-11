import java.io.*;

public class Exemplo {

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
                int totalTime = 0;

                // medindo tempo de execução 
                for (int i = 0; i < repetitions; i++) {
                    long start = System.nanoTime();
                    //comparaExtremos(tokens);
                    indexOf(tokens, "b"); 
                    long end = System.nanoTime();
                    totalTime += (end - start);
                }
                // TODO incluir aqui chamada para o método sob análise        
                
                // saída padrão: método tempo tamanho_da_entrada
                long averageTime = totalTime / repetitions;
                System.out.println("IndexOf " + averageTime + " " + tokens.length);

            }
        } catch (IOException ioe) {}

    }


    public static boolean comparaExtremos(String[] tokens) {
        return tokens[0].equals(tokens[tokens.length - 1]); 
        
    }

    public static int indexOf(String[] tokens, String key) {
        for(int i = 0; i < tokens.length; i++){
            if(tokens[i].equals(key)){
                return i;
            }
        }
        return -1;
        
    }

}
