import java.util.*;

public class Emprestimo {
    private Livro livro;
    private Membro membro;
    private Date dataEmprestimo;

    public Emprestimo(Livro livro, Membro membro, Date dataEmprestimo) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro nao pode ser nulo.");
        }
        if (membro == null) {
            throw new IllegalArgumentException("Membro não pode ser nulo.");
        }
        if (dataEmprestimo == null) {
            throw new IllegalArgumentException("Data de emprestimo inválida.");
        }
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = dataEmprestimo;
    }

    public Livro getLivro() {
        return livro;
    }
    public Membro getMembro() {
        return membro;
    }
    public Date getDataEmprestimo() { return dataEmprestimo; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo emprestimo = (Emprestimo) o;
        return livro.equals(emprestimo.livro) &&
                membro.equals(emprestimo.membro) &&
                dataEmprestimo.equals(emprestimo.dataEmprestimo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, membro, dataEmprestimo);
    }
}