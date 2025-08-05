
import java.util.ArrayList;
import java.util.Iterator;

import util.Aluno;

/**
 * Esta classe representa a implementação de uma Tabela Hash que usa endereçamento fechado (encadeamento)
 * para resolver colisões.
 *
 * A implementação abaixo apenas lida com objetos do tipo aluno. Essa foi uma decisão para fins didáticos. 
 * Naturalmente, por ser uma estrutura de propósito geral, uma tabela hash deve ser capaz de manipular objetos
 * de qualquer tipo.
 *
 * Esta é uma versão simplificada para fins didáticos. Não lidamos com resize e rehash aqui. Isso é assunto de um exercício específico.
 *
 * @author João Arthur
 * @see <a href="http://joaoarthurbm.github.io/eda">http://joaoarthurbm.github.io/eda</a>
 * Computação @ UFCG
 */
public class TabelaHashEncadeada {

    private ArrayList<Aluno>[] tabela;
    private final int CAPACIDADE_DEFAULT = 23;
    private final double A = 0.6180339887;

    /**
     * Cria uma nova tabela com a capacidade default 20.
     */
    public TabelaHashEncadeada() {
        this.tabela = new ArrayList[CAPACIDADE_DEFAULT];
    }

    /**
     * Cria uma nova tabela com a capacidade passada como parâmetro.
     *
     * @param capacidade O número de posições da tabela.
     */
    public TabelaHashEncadeada(int capacidade) {
        this.tabela = new ArrayList[capacidade];
    }

    /**
     * Calcula o hash de uma determinada chave. A função de hash é simples
     * e usa o método da divisão.
     * @param chave A chave para a qual se deseja calcular o hash.
     * @return O hash calculado tendo como base a chave e o tamanho da tabela.
     */
    private int hash(Integer chave) {
        return chave % this.tabela.length;
    }

    /**
     * Adiciona o par chave, valor na tabela. Se a chave já existir, o valor
     * antigo é atualizado.
     *
     * @param chave a matrícula do aluno a ser adicionado.
     * @param valor o objeto Aluno a ser adicionado na tabela.
     */
    public void put(Integer chave, Aluno valor) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];

        if (alunos == null) {
            alunos = new ArrayList<Aluno>();
            alunos.add(valor);
            this.tabela[hash] = alunos;

        } else {
            for (int i = 0; i < alunos.size(); i++) {
                if (alunos.get(i).getMatricula() == chave) {
                    alunos.set(i, valor);
                    return;
                }
            }
            alunos.add(valor);
        }
    }

    /**
     * Recupera o aluno cuja chave é igual a passada como parâmetro.
     *
     * @param chave a matrícula do aluno.
     * @return o aluno com a matrícula passada como parâmetro. null caso nenhum
     * aluno presente na tabela tenha a matrícula igual a passada como
     * parâmetro.
     */
    public Aluno get(Integer chave) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];

        if (alunos == null || alunos.isEmpty()) {
            return null;
        }

        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(chave)) {
                return aluno;
            }
        }

        return null;
    }

    /**
     * Verifica se a tabela contém uma determinada chave.
     *
     * @param chave A chave a ser verificada.
     * @return true se a chave existe, false caso contrário.
     */
    public boolean containsKey(Integer chave) {
        return get(chave) != null;
    }

    /**
     * Verifica se a tabela contém um determinado valor (aluno).
     *
     * @param aluno O aluno a ser verificado.
     * @return true se o aluno existe, false caso contrário.
     */
    public boolean containsValue(Aluno aluno) {
        if (aluno == null) {
            return false;
        }

        // Itera por toda a tabela
        for (ArrayList<Aluno> bucket : this.tabela) {
            // Usa o método contains do ArrayList, que por sua vez usa o .equals() do Aluno
            if (bucket != null && bucket.contains(aluno)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove o aluno cuja matrícula é igual a chave passada como parâmetro.
     *
     * @param chave A matrícula do aluno a ser removido.
     * @return O aluno a ser removido. null caso não haja aluno com a matrícula
     * passada como parâmetro.
     */
    public Aluno remove(int chave) {
        int hash = hash(chave);
        ArrayList<Aluno> alunos = this.tabela[hash];

        // verifica se é null antes de iterar
        if (alunos == null) {
            return null;
        }
        int it = 0;

        while (it < alunos.size()) {
            if(alunos.get(it).getMatricula().equals(chave)){//pega posição e a matrícula interna
                Aluno aluno = alunos.get(it);
                alunos.remove(it);
                return aluno;

            }
            it++;
        }

        return null;
    }

}
