import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PublicTests {

    @Test
    void testContaMenores() {
        BST bst = new BST();
        assertEquals(0, bst.contaMenores(10), 
            "Should return 0 for an empty tree");
        
        bst = new BST();
        int[] elements = {1, 2, 3, 4, 5};
        bst.add(elements);
        assertEquals(5, bst.contaMenores(6));

        bst = new BST();
        elements = new int[]{10, 15, 20, 25};
        bst.add(elements);
        assertEquals(0, bst.contaMenores(5));

   
        bst = new BST();
        elements = new int[]{10, 5, 15, 3, 7, 12, 18};
        bst.add(elements);
        assertEquals(3, bst.contaMenores(8));
        

        bst = new BST();
        bst.add(10);
        assertEquals(0, bst.contaMenores(5));
        assertEquals(1, bst.contaMenores(15));
    }


     @Test
    void testConverterMaxHeapParaMinHeap() {
        int[] arrayMaxHeap = {100, 19, 36, 17, 3, 25, 1, 2, 7};
        Heap heap = new Heap(arrayMaxHeap);

        heap.toMinHeap();

        assertTrue(heap.ehMinHeap(), "Heap deve ser um min-heap após a conversão");
        
        int[] arrayVazio = {};
        heap = new Heap(arrayVazio);

        // Act
        heap.toMinHeap();

        // Assert
        assertEquals(0, heap.size(), "Heap vazio deve permanecer vazio");
        
        int[] arrayUmElemento = {42};
        heap = new Heap(arrayUmElemento);

        // Act
        heap.toMinHeap();

        // Assert
        assertTrue(heap.ehMinHeap(), "Heap com um elemento deve ser um min-heap");
        
        int[] arrayMinHeap = {1, 3, 6, 5, 9, 8};
        heap = new Heap(arrayMinHeap);

        // Act
        heap.toMinHeap();

        // Assert
        assertTrue(heap.ehMinHeap(), "Heap já em ordem de min-heap deve permanecer inalterado");
    }

}
