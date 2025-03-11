import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    @Test
    void testEmprestimo() {
        Livro livro = new Livro("Java para Iniciantes", "Salatiel Marinho", "12345");
        Membro membro = new Membro("João Teste", 1, "joaotest@fiap.com");
        Date dataEmpretimo = new Date();

        Emprestimo emprestimo = new Emprestimo(livro, membro, dataEmpretimo);

        assertEquals(livro, emprestimo.getLivro());
        assertEquals(membro, emprestimo.getMembro());
        assertEquals(dataEmpretimo, emprestimo.getDataEmprestimo());
    }

    @Test
    void testToString() {
        Livro livro = new Livro("Java para Iniciantes", "Salatiel Marinho", "12345");
        Membro membro = new Membro("João Teste", 1, "joaotest@fiap.com");
        Date dataEmprestimo = new Date(100000L);

        Emprestimo emprestimo = new Emprestimo(livro, membro, dataEmprestimo);

        String expected = "Emprestimo{livro=" + livro + ", membro=" + membro + ", dataEmprestimo=" + dataEmprestimo + "}";
        assertEquals(expected, emprestimo.toString());
    }

}