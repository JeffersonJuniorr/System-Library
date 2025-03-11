import org.junit.Before;
// import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class BibliotecaTest {
    private Biblioteca biblioteca;
    private Livro livro;

    @BeforeEach
    void setup() {
        biblioteca = new Biblioteca();
        livro = new Livro("Titulo Test", "Test Autor", "12345");
    }

    @Test
    void testAdicionarLivro() {
        biblioteca.adicionarLivro(livro);
        assertTrue(biblioteca.getLivros().contains(livro));
    }

    @Test
    void testRemoverLivro() {
        biblioteca.adicionarLivro(livro);
        biblioteca.removerLivro(livro);

        assertFalse(biblioteca.getLivros().contains(livro));
    }

    @Test
    void testRegistrarMembro() {
        Membro membro = new Membro("João", 1, "joaotest@gmail.com");
        biblioteca.registrarMembro(membro);

        assertTrue(biblioteca.getMembros().contains(membro));
    }

    @Test
    void testRegistrarEmprestimo() {
        Livro livro = new Livro("Java para iniciantes", "Salatiel Marinho", "12345");
        Membro membro = new Membro("João Teste", 1, "joaotest@fiap.com");
        Date dataEmprestimo = new Date(100000L);

        Emprestimo emprestimo = new Emprestimo(livro, membro, dataEmprestimo);

        assertEquals(livro, emprestimo.getLivro());
        assertEquals(membro, emprestimo.getMembro());
        assertEquals(dataEmprestimo, emprestimo.getDataEmprestimo());
    }

}