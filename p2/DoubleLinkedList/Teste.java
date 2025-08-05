//import java.util.NoSuchElementException;
//
//public class LinkedList {
//
//    private Node head;
//    private Node tail;
//    private int size;
//
//    public LinkedList() {
//        this.head = null;
//        this.tail = null;
//        this.size = 0;
//    }
//
//    public boolean isEmpty() {
//        return this.head == null;
//    }
//
//    public void addFirst(int valor) {
//        Node newNode = new Node(valor);
//        if (isEmpty()) {
//            head = tail = newNode;
//        } else {
//            newNode.next = head;
//            head.prev = newNode;
//            head = newNode;
//        }
//        size++;
//    }
//
//    public void addLast(int valor) {
//        Node newNode = new Node(valor);
//        if (isEmpty()) {
//            head = tail = newNode;
//        } else {
//            tail.next = newNode;
//            newNode.prev = tail;
//            tail = newNode;
//        }
//        size++;
//    }
//
//    public void add(int index, int valor) {
//        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
//
//        if (index == 0) addFirst(valor);
//        else if (index == size) addLast(valor);
//        else {
//            Node newNode = new Node(valor);
//            Node curr = getNode(index);
//            newNode.prev = curr.prev;
//            newNode.next = curr;
//            curr.prev.next = newNode;
//            curr.prev = newNode;
//            size++;
//        }
//    }
//
//    public int getFirst() {
//        if (isEmpty()) throw new NoSuchElementException();
//        return head.value;
//    }
//
//    public int getLast() {
//        if (isEmpty()) throw new NoSuchElementException();
//        return tail.value;
//    }
//
//    public int get(int index) {
//        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
//
//        return getNode(index).value;
//    }
//
//    public int removeFirst() {
//        if (isEmpty()) throw new NoSuchElementException();
//
//        int val = head.value;
//        if (head.next == null) {
//            head = tail = null;
//        } else {
//            head = head.next;
//            head.prev = null;
//        }
//        size--;
//        return val;
//    }
//
//    public int removeLast() {
//        if (isEmpty()) throw new NoSuchElementException();
//
//        int val = tail.value;
//        if (head.next == null) {
//            head = tail = null;
//        } else {
//            tail = tail.prev;
//            tail.next = null;
//        }
//        size--;
//        return val;
//    }
//
//    public boolean removeByValue(int value) {
//        Node aux = head;
//        while (aux != null) {
//            if (aux.value == value) {
//                if (aux == head) removeFirst();
//                else if (aux == tail) removeLast();
//                else {
//                    aux.prev.next = aux.next;
//                    aux.next.prev = aux.prev;
//                    size--;
//                }
//                return true;
//            }
//            aux = aux.next;
//        }
//        return false;
//    }
//
//    public int indexOf(int value) {
//        Node curr = head;
//        int i = 0;
//        while (curr != null) {
//            if (curr.value == value) return i;
//            curr = curr.next;
//            i++;
//        }
//        return -1;
//    }
//
//    public boolean contain(int v) {
//        return indexOf(v) != -1;
//    }
//
//    public int lastIndexOf(int value) {
//        Node curr = tail;
//        int i = size - 1;
//        while (curr != null) {
//            if (curr.value == value) return i;
//            curr = curr.prev;
//            i--;
//        }
//        return -1;
//    }
//
//    public String toString() {
//        if (isEmpty()) return "";
//        StringBuilder sb = new StringBuilder();
//        Node curr = head;
//        while (curr != null) {
//            sb.append(curr.value).append(", ");
//            curr = curr.next;
//        }
//        return sb.substring(0, sb.length() - 2);
//    }
//
//    public int size() {
//        return this.size;
//    }
//
//    public int remove(int index) {
//        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
//
//        if (index == 0) return removeFirst();
//        if (index == size - 1) return removeLast();
//
//        Node node = getNode(index);
//        node.prev.next = node.next;
//        node.next.prev = node.prev;
//        size--;
//        return node.value;
//    }
//
//    // Retorna o nó na posição 'index'
//    private Node getNode(int index) {
//        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
//
//        Node current = head;
//        int i = 0;
//        while (i < index) {
//            current = current.next;
//            i++;
//        }
//        return current;
//    }
//
//    // Troca os nós nas posições indexA e indexB
//    public void swap(int indexA, int indexB) {
//        if (indexA == indexB) return; // Nada a trocar
//
//        Node nodeA = getNode(indexA);
//        Node nodeB = getNode(indexB);
//
//        // Salva ponteiros
//        Node aPrev = nodeA.prev;
//        Node aNext = nodeA.next;
//        Node bPrev = nodeB.prev;
//        Node bNext = nodeB.next;
//
//        // Corrige conexões com vizinhos
//        if (aPrev != null) aPrev.next = nodeB;
//        if (aNext != null && nodeA != nodeB.next) aNext.prev = nodeB;
//        if (bPrev != null) bPrev.next = nodeA;
//        if (bNext != null && nodeB != nodeA.next) bNext.prev = nodeA;
//
//        // Corrige ponteiros internos
//        if (nodeA.next == nodeB) { // são vizinhos
//            nodeA.next = bNext;
//            nodeA.prev = nodeB;
//            nodeB.next = nodeA;
//            nodeB.prev = aPrev;
//        } else if (nodeB.next == nodeA) { // são vizinhos
//            nodeB.next = aNext;
//            nodeB.prev = nodeA;
//            nodeA.next = nodeB;
//            nodeA.prev = bPrev;
//        } else {
//            nodeA.next = bNext;
//            nodeA.prev = bPrev;
//            nodeB.next = aNext;
//            nodeB.prev = aPrev;
//        }
//
//        // Corrige head e tail
//        if (nodeA == head) head = nodeB;
//        else if (nodeB == head) head = nodeA;
//
//        if (nodeA == tail) tail = nodeB;
//        else if (nodeB == tail) tail = nodeA;
//    }
//
//    // move o nó da posição index para o início da lista
//    public void moveToHead(int index) {
//        if (index == 0) return; // já é head
//
//        Node node = getNode(index);
//
//        // Remove o nó
//        if (node == tail) tail = node.prev;
//        if (node.prev != null) node.prev.next = node.next;
//        if (node.next != null) node.next.prev = node.prev;
//
//        // Coloca como novo head
//        node.prev = null;
//        node.next = head;
//        head.prev = node;
//        head = node;
//    }
//
//}
//
//class Node {
//    int value;
//    Node prev;
//    Node next;
//
//    Node(int v) {
//        this.value = v;
//    }
//}
