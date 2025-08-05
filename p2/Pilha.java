import java.util.Arrays;

public class Pilha {
    private int[] pilha;
    private int top;//armazena o índice do último elemento inserido no array

    public Pilha(int capacidade) {
        this.pilha = new int[capacidade];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == pilha.length - 1;
    }

    // deve lançar exceção caso a pilha esteja cheia.
    //Adiciona um elemento ao topo da pilha. Custo: O(1)
    public void push(int valor) {
        if (isFull()) {
            throw new IllegalStateException("A pilha está cheia.");
        }
        pilha[++top] = valor; 
    }

    // deve lançar exceção caso a pilha esteja vazia.
    // Remove e retorna o elemento que está no topo da pilha. Custo: O(1)
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }                                                                        
        return pilha[top--];
    }

    // deve lançar exceção caso a pilha esteja vazia.
    //Retorna o elemento do topo da pilha sem removê-lo. Custo: O(1)
    public int peek() { //para saber quem está no topo da pilha sem remover
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        return pilha[top];
    }

    public int size() {
        return top + 1;
    }

    // deve retornar uma string representando a pilha. Veja os testes para a especificação
    // detalhada. Não é permitido iterar diretamente sobre o array ou criar arrays. Crie outra pilha, se preciso. Use as operações push, pop,
    // isEmpty etc.
    public String toString() {
        if(isEmpty()){
            return "";              
        }
        //StringBuilder sb = new StringBuilder();
        int valor = top;
        String arr = "";
        Pilha aux = new Pilha(size());
        while(!isEmpty()){
            aux.push(pop());
            arr += aux.peek();
//            if (!isEmpty()) {
//                sb.append(", ");
//            }
            if(aux.top != valor){
                arr +=  ", ";
            }
        }
        while(!aux.isEmpty()){
            push(aux.pop()); //desempilha
        }
        return arr;
    }
    // Deve retornar a posição da primeira ocorrência do elemento passado como parâmetro. Note que
    // o topo sempre está na primeira posição (0), abaixo do topo é a posição 1 etc. Não confunda
    // com os índices do array. Interprete os testes para a especificação mais detalhada.
    // Não é permitido iterar diretamente sobre o array. Use as operações push, pop,
    // isEmpty etc.
    public int indexOf(int valor) {
        int indice = -1;
        Pilha aux  =  new Pilha(size());
        while(!isEmpty()){
           if(peek() == valor){
               indice = aux.size();
               break;
           }
           //add o elem pilha
           aux.push(pop());
       }
        //desempilho
       while(!aux.isEmpty()){
           push(aux.pop());
       }

        return indice;
    }

    public static void main(String[] args) {
        Pilha pilha = new Pilha(5);
        pilha.push(10);
        pilha.push(20);
        System.out.println(pilha.toString());
    }

    

}
