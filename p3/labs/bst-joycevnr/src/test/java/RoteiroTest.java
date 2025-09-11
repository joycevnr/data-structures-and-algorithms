import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoteiroTest {

    private BST bst;

    @BeforeEach
    public void setUp() {
        bst = new BST();
    }

    @Test
    public void testIsEmptyAndSize() {
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        bst.add(10);
        assertFalse(bst.isEmpty());
        assertEquals(1, bst.size());
    }

    @Test
    public void testAddAndSearch() {
        bst.add(50);
        bst.add(30);
        bst.add(70);
        assertNotNull(bst.search(50));
        assertNotNull(bst.search(30));
        assertNull(bst.search(99));
    }

    @Test
    public void testHeight() {
        assertEquals(-1, bst.height());
        bst.add(10);
        assertEquals(0, bst.height());
        bst.add(5);
        bst.add(15);
        assertEquals(1, bst.height());
    }

    @Test
    public void testContaFolhas() {
        assertEquals(0, bst.contaFolhas());
        bst.add(10);
        assertEquals(1, bst.contaFolhas());
        bst.add(5);
        bst.add(15);
        assertEquals(2, bst.contaFolhas());
    }
    
    @Test
    public void testEquals() {
        BST bst2 = new BST();
        assertTrue(bst.equals(bst2));
        bst.add(10);
        bst.add(5);
        bst2.add(10);
        assertFalse(bst.equals(bst2));
        bst2.add(5);
        assertTrue(bst.equals(bst2));
    }
    
    // ===== TESTES DO MÉTODO REMOVE =====

    @Test
    public void testRemoveLeafNode() {
        bst.add(10);
        bst.add(5);
        assertEquals(2, bst.size());

        bst.remove(5);
        
        // CORREÇÃO: Verifica se o filho esquerdo de 10 é agora nulo.
        assertNull(bst.search(10).left, "Filho esquerdo de 10 deveria ser nulo após remoção.");
        assertEquals(1, bst.size());
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        bst.add(20);
        bst.add(10);
        bst.add(15); 
        
        bst.remove(10);

        assertNull(bst.search(10));
        assertEquals(2, bst.size());
        assertEquals(15, bst.search(20).left.value, "15 deveria ser o novo filho esquerdo de 20.");
        assertEquals(20, bst.search(15).parent.value, "O pai de 15 deveria ser 20.");
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        bst.add(50);
        bst.add(30);
        bst.add(20);
        bst.add(40);
        
        bst.remove(30);

        assertNull(bst.search(30));
        assertEquals(3, bst.size());
        assertEquals(40, bst.search(50).left.value);
    }
    
    @Test
    public void testRemoveRoot() {
        bst.add(50);
        bst.add(30);
        bst.add(70);

        bst.remove(50);

        assertNull(bst.search(50));
        assertEquals(70, bst.search(70).value);
        assertNull(bst.search(70).parent);
        assertEquals(30, bst.search(70).left.value);
    }
}