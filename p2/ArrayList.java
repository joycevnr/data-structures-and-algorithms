public class ArrayList {

    private int[] elementos;
    private int tamanho; // número de elementos na lista

    public ArrayList(int capacidadeInicial) {
        this.elementos = new int[capacidadeInicial];
        this.tamanho = 0;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    // Desloca todos os elementos uma posição para a direita a partir de um determinado índice
    private void shiftRight(int index) {
        if (index == this.elementos.length - 1)
            throw new IndexOutOfBoundsException("Não há espaço para "
                    + "efetuar o shift à direita.");

        for (int i = this.tamanho; i > index; i--) {
            this.elementos[i] = this.elementos[i-1];
        }
    }

    // Desloca todos os elementos uma posição para a esquerda a partir de um determinado índice
    private void shiftLeft(int index) {
        for (int i = index; i < this.tamanho - 1; i++) {
            this.elementos[i] = this.elementos[i+1];
        }
    }

    public void addFirst(int valor) {
        assegureCapacidade(tamanho + 1);
        shiftRight(0);  // Desloca todos os elementos para a direita
        elementos[0] = valor;
        tamanho++;
    }

    public void addLast(int valor) {
        assegureCapacidade(tamanho + 1);
        elementos[tamanho] = valor;
        tamanho++;
    }
//    public boolean add(Aluno aluno) {
//        assegureCapacidade(this.tamanho + 1);
//        this.lista[tamanho++] = aluno;
//        return true;
//    }

//    public void remove(int index) {
//        if (index < 0 || index >= tamanho) {
//            throw new IndexOutOfBoundsException();
//        }
//        shiftLeft(index);
//        tamanho--;
//    }

//    public int removeFirst() {
//        if (isEmpty()) {
//            throw new IllegalStateException();
//        }
//        int valor = elementos[0];
//        shiftLeft(0);
//        tamanho--;
//        return valor;
//    }


    // adiciona um valor no índice passado como parâmetro
    public void add(int index, int valor) {
        if (index < 0 || index > tamanho) {
            throw new IndexOutOfBoundsException();
        }
        assegureCapacidade(tamanho + 1);
        shiftRight(index);
        elementos[index] = valor;
        tamanho++;
    }

    // você vai precisar desse método quando tentar adicionar e a fila já estiver cheia
    private void resize(int novaCapacidade) {
        int[] novoArray = new int[novaCapacidade];
        for (int i = 0; i < elementos.length; i++) {
            novoArray[i] = elementos[i];
        }
        elementos = novoArray;
    }

    public void assegureCapacidade(int capacidadePretendida) {
        if (capacidadePretendida > elementos.length) {
            resize(Math.max(elementos.length * 2, capacidadePretendida));
        }
    }

    public int getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return elementos[0];
    }

    public int getLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return elementos[tamanho - 1];
    }

    // retorna o elemento no índice passado como parâmetro
    public int get(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException();
        }
        return elementos[index];
    }

    // deve lançar exceção caso a fila esteja vazia.
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        int valor = elementos[0];
        for (int i = 0; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        tamanho--;
        return valor;
    }

    // deve lançar exceção caso a fila esteja vazia.
    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        tamanho--;
    }

    // remove o valor no índice passado como parâmetro.
    // lançar exceção se o índice não for válido.
    public void remove(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException();
        }
        shiftLeft(index);
        tamanho--;
    }

    // remove a primeira ocorrência do elemento cujo valor foi passado como parâmetro.
    // se não encontrar, não faça nada.
    public void removeByValue(int value) {
        int index = indexOf(value);
        if (index != -1) {
            remove(index);
        }
    }
//    public boolean remove(Aluno aluno) {
//        if (aluno == null) return false;
//
//        for (int i = 0; i < tamanho; i++) {
//            if (this.lista[i].equals(aluno)) {
//                this.remove(i);
//                return true;
//            }
//        }
//
//        return false;
//    }


    // retorna o índice da primeira ocorrência do valor passado como parâmetro.
    public int indexOf(int value) {
        for (int i = 0; i < tamanho; i++) {
            if (elementos[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // Deve retornar a posição da última ocorrência do elemento passado como parâmetro.
    public int lastIndexOf(int valor) {
        for (int i = tamanho - 1; i >= 0; i--) {
            if (elementos[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    // deve retornar uma string representando a lista.
    public String toString() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append(elementos[i]);
            if (i < tamanho - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public int size() {
        return tamanho;
    }
}
