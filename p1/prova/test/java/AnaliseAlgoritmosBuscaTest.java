import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AnaliseAlgoritmosBuscaTest {

    AnaliseAlgoritmosBusca analisador = new AnaliseAlgoritmosBusca();
    int[] v = new int[]{1, 8, 9, 12, 14, 18, 20};

    @Test
    public void testPrimeiro() {
        // uma iteração efetuada até encontrar o 1
        assertEquals(1, analisador.contaPassosLinear(v, 1));

        // duas chamadas recursivas efetuadas até encontrar o 1

        assertEquals(2, analisador.contaPassosBinaria(v, 0, v.length - 1, 1));

    }

    @Test
    void testMeio() {
        // quatro iterações até encontrar o 12
        assertEquals(4, analisador.contaPassosLinear(v, 12));

        // nenhuma chamada recursiva. encontra o 12 na primeira execução
        assertEquals(0, analisador.contaPassosBinaria(v, 0, v.length - 1, 12));
    }
}