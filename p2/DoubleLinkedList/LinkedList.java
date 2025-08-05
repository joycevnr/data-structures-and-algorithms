import java.util.NoSuchElementException;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;
    
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Verifica se a lista não tem elementos.
    public boolean isEmpty() {
        return this.head == null;
    }

    // Adiciona um valor no início da lista.
    public void addFirst(int valor) {
        Node newNode = new Node(valor);
        
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        
        size += 1;
    }

    // Adiciona um valor no final da lista.
    public void addLast(int valor) {
        Node newNode = new Node(valor);

        if(isEmpty()) {
            this.head = newNode;
            this.tail = head;
        } else {
            this.tail.next = newNode;
            newNode.prev = tail;
            this.tail = newNode;
        }
        this.size += 1;
    }

    // adiciona um valor na posição passada como parâmetro
    public void add(int index, int valor) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        
        Node newNode = new Node(valor);
        
        if (index == 0) {
            this.addFirst(valor);
        
        } else if (index == size) {
            this.addLast(valor);
        
        } else {
            Node aux = this.head;
            
            for (int i = 0; i < index - 1; i++)
                aux = aux.next;
            
            newNode.next = aux.next;
            aux.next = newNode;
            
            size += 1;
        }
    }

    // Retorna o primeiro valor da lista.
    public int getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.head.value;
    }

    // Retorna o último valor da lista.
    public int getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.tail.value;
    }

    // retorna o elemento na posição  passada como parâmetro
    // deve lançar IndexOutOfBoundsException se o índice não for válido.
    public int get(int index) {
         if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        
        Node aux = this.head;
        
        for (int i = 0; i < index; i++)
            aux = aux.next;
        
        return aux.value;
    }

    // deve lançar exceção caso a fila esteja vazia.
    public int removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        
        int v = this.head.value;
        
        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
        
        size -= 1;
        return v;
    }

    // deve lançar exceção caso a fila esteja vazia.
    public int removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        
        int v = this.tail.value;
        
        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }           
        
        size -= 1;
        return v;
    }

    // remove a primeira ocorrência do elemento cujo valor foi passado como parâmetro.
    // se não encontrar, não faça nada. true se remover, false se não remover.
    public boolean removeByValue(int value) {
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

    // retorna a posição da primeira ocorrência do valor passado como parâmetro.
    public int indexOf(int value) {
        Node aux = this.head;
        int index = 0;
        while (aux != null) { 
            if(aux.value == value)
                return index;
            aux = aux.next;
            index += 1;
        }
        
        return -1;
    }

    // Verifica se a lista contém um valor.
    public boolean contain(int v) {
        return indexOf(v) != -1;
    }
   
    // Deve retornar a posição da última ocorrência do elemento passado como parâmetro. 
    public int lastIndexOf(int valor) {
        if (isEmpty()) return -1;
        
        int last = -1;
        Node aux = this.head;
        int i = -1;
        while (aux != null) {
            i += 1;
            if (aux.value == valor)
                last = i;
            aux = aux.next;
        }

        return last;
    }
    
    // deve retornar uma string representando a lista. 
    public String toString() {
        if (isEmpty()) return "";

        Node aux = this.head;
        String out = "";
        while (aux != null) {
            out += aux.value + ", ";
            aux = aux.next;
        }
        return out.substring(0, out.length() - 2);
    }

    // Retorna o tamanho atual da lista.
    public int size() {
        return this.size;
    }

    // remove o valor no índice passado como parâmetro. retorna o valor removido.
    // lançar exceção se o índice não for válido.
    public int remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();
        
        Node aux = this.head;
        for (int i = 0; i < index; i++)
            aux = aux.next;
        
        aux.prev.next = aux.next;
        aux.next.prev = aux.prev;
        size -= 1;
        return aux.value;
    }

    // Método privado para buscar um nó por índice.
    private Node getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current;
    }

    // Move um nó para o início da lista.
    public void moveToHead(int index) {
        if (index == 0) return; // Já é o head
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node node = getNode(index);

        // Remove o nó da posição atual
        if (node == tail) tail = node.prev;
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        // Coloca como novo head
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }

    // Troca dois nós de posição.
    public void swap(int indexA, int indexB) {
        // --- 1. PREPARAÇÃO ---
        // Valida os índices para evitar erros. Se forem inválidos ou iguais, para o método.
        if (indexA == indexB || indexA < 0 || indexB < 0 || indexA >= size || indexB >= size) {
            return;
        }

        // Garante que indexA seja sempre menor que indexB, para simplificar a lógica.
        if (indexA > indexB) {
            int temp = indexA;
            indexA = indexB;
            indexB = temp;
        }

        // Pega as referências dos nós que serão trocados.
        Node nodeA = getNode(indexA);
        Node nodeB = getNode(indexB);

        // Guarda os vizinhos originais de cada nó para não perdê-los durante a troca.
        Node prevA = nodeA.prev, nextA = nodeA.next;
        Node prevB = nodeB.prev, nextB = nodeB.next;

        // --- 2. EXECUÇÃO DA TROCA ---
        // A lógica muda dependendo se os nós são vizinhos ou não.
        if (nextA == nodeB) {
            // --- CASO 1: NÓS SÃO VIZINHOS ---
            // Ajusta os 4 ponteiros principais para inverter A e B.
            nodeA.next = nextB;      // O "próximo" de A agora é o antigo "próximo" de B.
            nodeA.prev = nodeB;      // O "anterior" de A agora é o próprio B.
            nodeB.next = nodeA;      // O "próximo" de B agora é o próprio A.
            nodeB.prev = prevA;      // O "anterior" de B agora é o antigo "anterior" de A.

            // Avisa os vizinhos de fora sobre a mudança.
            if (prevA != null) prevA.next = nodeB; // O vizinho de trás de A agora se conecta com B.
            if (nextB != null) nextB.prev = nodeA; // O vizinho da frente de B agora se conecta com A.

        } else {
            // --- CASO 2: NÓS NÃO SÃO VIZINHOS ---
            // Conecta o nó A no lugar onde o nó B estava.
            nodeA.prev = prevB;      // "Anterior" de A aponta para o antigo "anterior" de B.
            nodeA.next = nextB;      // "Próximo" de A aponta para o antigo "próximo" de B.
            if (prevB != null) prevB.next = nodeA; // O antigo vizinho de trás de B se conecta com A.
            if (nextB != null) nextB.prev = nodeA; // O antigo vizinho da frente de B se conecta com A.

            // Conecta o nó B no lugar onde o nó A estava.
            nodeB.prev = prevA;      // "Anterior" de B aponta para o antigo "anterior" de A.
            nodeB.next = nextA;      // "Próximo" de B aponta para o antigo "próximo" de A.
            if (prevA != null) prevA.next = nodeB; // O antigo vizinho de trás de A se conecta com B.
            if (nextA != null) nextA.prev = nodeB; // O antigo vizinho da frente de A se conecta com B.
        }

        // --- 3. FINALIZAÇÃO ---
        // Se A ou B eram os extremos da lista (head/tail), atualiza os ponteiros principais.
        if (head == nodeA) head = nodeB;
        else if (head == nodeB) head = nodeA;

        if (tail == nodeA) tail = nodeB;
        else if (tail == nodeB) tail = nodeA;
    }
}

// Classe que representa cada elemento (nó) da lista.
class Node {

    int value;  // Valor armazenado
    Node prev;  // Ponteiro para o nó anterior
    Node next;  // Ponteiro para o próximo nó

    Node(int v) {
        this.value = v;
    }

}
