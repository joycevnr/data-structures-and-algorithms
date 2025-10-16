import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuickSelectTest {

    QuickSelect qs = new QuickSelect();

    @Test
    public void testQuickSelectMiddleElement() {
        int[] v = {7, 2, 1, 6, 8, 5, 3, 4};
        assertEquals(4, qs.quickSelect(v, 4)); // 4º menor elemento
    }

    @Test
    public void testQuickSelectFirstElement() {
        int[] v = {10, 3, 5, 1, 2};
        assertEquals(1, qs.quickSelect(v, 1)); // menor elemento
    }

    @Test
    public void testQuickSelectLastElement() {
            int[] v = {4, 6, 2, 9, 1};
            assertEquals(9, qs.quickSelect(v, 5)); // maior elemento
    }

    @Test
    public void testQuickSelectSingleElement() {
        int[] v = {42};
        assertEquals(42, qs.quickSelect(v, 1));
    }

    @Test
    public void testQuickSelectWithNegativeNumbers() {
        int[] v = {-10, -20, 0, 5, 1};
        assertEquals(-10, qs.quickSelect(v, 2));
    }
}