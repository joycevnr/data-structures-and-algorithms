import java.util.NoSuchElementException;

public class LinkedList {
    private Node head; // cabeça da lista
    private Node tail; // cauda da lista
    private int size;   // qtd - acesso rápido O(1).

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;// Um contador para saber o número de elementos
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    // add valor no início da lista.
    public void addFirst(int valor) {
        Node newNode = new Node(valor); // novo nó com o valor fornecido.
        if (isEmpty()) {
            this.head = newNode; // cabeça e cauda apontam para o novo e único nó.
            this.tail = newNode;
        } else {
            newNode.next = this.head; // O "próximo" do novo nó aponta para a antiga cabeça.
            this.head.prev = newNode; // O "anterior" da antiga cabeça aponta de volta para o novo nó.
            this.head = newNode;      // A cabeça da lista é atualizada para ser o novo nó.
        }
        size += 1;
    }

    public void addLast(int valor) {
        Node newNode = new Node(valor);
        if (isEmpty()) {
            this.head = newNode; // cabeça e cauda se tornam o novo nó.
            this.tail = head;
        } else {
            this.tail.next = newNode; // O "próximo" da cauda atual aponta para o novo nó.
            newNode.prev = this.tail; // O "anterior" do novo nó aponta para a antiga cauda.
            this.tail = newNode;      // A cauda da lista é atualizada para ser o novo nó.
        }
        this.size += 1;
    }

    // adiciona um valor na posição passada como parâmetro
    public void add(int index, int valor) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            this.addFirst(valor);
        } else if (index == size) {
            this.addLast(valor);
            // Caso geral: inserir no meio da lista.
        } else {
            Node aux = this.head; // Começa a busca pelo nó anterior à posição de inserção.
            // Loop para caminhar na lista até o nó na posição (index - 1).
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            Node newNode = new Node(valor); // Cria o novo nó a ser inserido.

            // Reorganiza os ponteiros para "encaixar" o novo nó.
            newNode.next = aux.next;  // O próximo do novo nó será o antigo próximo do nó auxiliar.
            newNode.prev = aux;       // O anterior do novo nó será o nó auxiliar.
            aux.next.prev = newNode;  // O anterior do nó que estava na frente agora aponta para o novo nó.
            aux.next = newNode;       // O próximo do nó auxiliar agora é o novo nó.

            size += 1;
        }
    }

    // Retorna o valor do primeiro elemento.
    public int getFirst() {
        if (isEmpty()) throw new NoSuchElementException("A lista está vazia.");
        return this.head.value;
    }

    // Retorna o valor do último elemento.
    public int getLast() {
        if (isEmpty()) throw new NoSuchElementException("A lista está vazia.");
        return this.tail.value;
    }

    // retorna o elemento na posição  passada como parâmetro
    // deve lançar IndexOutOfBoundsException se o índice não for válido.
    public int get(int index) {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node aux = this.head;//referência de head
        //Diferente de um array, não é possível "pular" diretamente para um índice. É preciso percorrer a lista desde o início.
        for (int i = 0; i < index; i++)
            aux = aux.next;

        return aux.value;


    }


    // deve lançar exceção caso a fila esteja vazia.
    public int removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("A lista está vazia.");
        int v = this.head.value; // Salva o valor do nó a ser removido.

        // Verifica se há apenas um elemento na lista.
        if (this.head.next == null) {
            this.head = null; // A lista fica vazia.
            this.tail = null;// Move para o nó anterior.
        } else { // Se houver mais de um elemento:
            this.head = this.head.next; // A nova cabeça passa a ser o segundo elemento.
            this.head.prev = null;      // O ponteiro "anterior" da nova cabeça vira nulo.
        }
        size -= 1;
        return v; //  valor removido.
    }

    // Remove e retorna o último valor.
    public int removeLast() {
        if (isEmpty()) throw new NoSuchElementException("A lista está vazia.");
        int v = this.tail.value; // Salva o valor do nó a ser removido.

        if (this.head.next == null) { // Caso com apenas um elemento.
            this.head = null;
            this.tail = null;
        } else { // Se houver mais de um:
            this.tail = this.tail.prev; // A nova cauda passa a ser o penúltimo elemento.
            this.tail.next = null;      // O ponteiro "próximo" da nova cauda vira nulo.
        }

        size -= 1;
        return v;
    }

    // remove a primeira ocorrência do elemento cujo valor foi passado como parâmetro.
    // se não encontrar, não faça nada. true se remover, false se não remover.
    public boolean removeByValue ( int value){
        Node aux = this.head;
        for (int i = 0; i < this.size; i++) {
            if (aux.value == value) {
                if (i == 0) removeFirst();
                else if (i == size - 1) removeLast();
                else {
                    aux.prev.next = aux.next;
                    aux.next.prev = aux.prev;
                    size -= 1;
                }

                return true;
            }
            aux = aux.next;
        }

        return false;
    }

    // Remove o valor no índice passado como parâmetro e retorna o valor removido.
    public int remove(int index) {
        if (index < 0 || index >= size) { // Valida o índice.
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node aux = this.head; // Começa a busca pelo nó a ser removido.
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        // "Pula" o nó aux, ligando o anterior a ele com o próximo dele.
        aux.prev.next = aux.next;
        aux.next.prev = aux.prev;
        size -= 1; // Decrementa o tamanho.
        return aux.value; // Retorna o valor do nó que foi removido.
    }

  

    // retorna a posição da primeira ocorrência do valor passado como parâmetro.       
    public int indexOf(int value) {
        Node aux = this.head; // Inicia um nó auxiliar na cabeça.
        int index = 0; // Inicia um contador de índice.
        while (aux != null) { // Percorre a lista até o final.
            if (aux.value == value) return index; // Se encontrar, retorna o índice atual.
            aux = aux.next; // Avança para o próximo nó.
            index++; // Incrementa o contador de índice.
        }
        return -1;
    }

    // Verifica se a lista contém o valor. Reusa o método indexOf.
    public boolean contains(int v) {
        return indexOf(v) != -1;
    }

    // Deve retornar a posição da última ocorrência do elemento passado como parâmetro.
    public int lastIndexOf(int valor) {
        Node aux = this.tail;
        // de trás para frente
        for(int i = size - 1; i >= 0; i--) {
            if(aux.value == valor) return i; // Se encontrar, retorna o índice.
            aux = aux.prev; // Move para o nó anterior.
        }
        return -1;
    }

            // deve retornar uma string representando a lista.
            public String toString () {
                if (isEmpty()) return "";

                Node aux = this.head;
                String out = "";
                while (aux != null) {
                    out += aux.value + ", ";
                    aux = aux.next;
                }
                return out.substring(0, out.length() - 2);
            }

            public int size () {
                return  this.size;
            }
        }

        class Node {

            int value;
            Node prev; // referência para o próximo
            Node next;  // referência para o anterior

            Node(int v) {
                this.value = v;
            }

        }
