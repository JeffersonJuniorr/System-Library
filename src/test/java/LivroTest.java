import org.junit.Test;
import static org.junit.Assert.*;

public class LivroTest {

    @Test
    public void testLivro() {
        Livro livro = new Livro("Livro A", "Autor A", "12345");
        assertEquals("Livro A", livro.getTitulo());
        assertEquals("Autor A", livro.getAutor());
        assertEquals("12345", livro.getISBN());
    }

    @Test
    public void testToString() {
        Livro livro = new Livro("Livro A", "Autor A", "12345");
        String expected = "Livro{titulo='Livro A', autor='Autor A', ISBN='12345'}";
        assertEquals(expected, livro.toString());
    }
}