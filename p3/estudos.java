public class estudos {


 public void add(int elemento){
    this.size += 1;

    if(isEmpty()){
     this.root = new Node(elemento);
    } else{

        Node aux = this.root;
        while(aux != null){
            if(elemento < aux.value){
                if(aux.left == null){
                    Node newNode = new Node(elemento);
                    aux.left = newNode;
                    newNode.parent =  aux;
                    return;
                }
                aux = aux.left;
            }
            else{
                if(aux.right == null){
                    Node newNode = new Node(elemento);
                    aux.right = newNode;
                    newNode.parent =  aux;
                    return;
                }
                aux = aux.right;
            }
        }
    }
 }

 public Node search(int elemento){
    Node aux = this.root;
    while(aux != null)){
        if(aux.value == elemento) return aux;
        if(elemento < aux.value) aux = aux.left;
        if(elemento > aux.value) aux = aux.right;
    }
    return null;
 }
}
