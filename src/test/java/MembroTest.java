import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembroTest {

    @Test
    void testRegistrarMembro() {
        Membro membro = new Membro("João Teste", 1, "joaotest@fiap.com");

        assertEquals("João Teste", membro.getNome());
        assertEquals(1, membro.getId());
        assertEquals("joaotest@fiap.com", membro.getEmail());
    }

    @Test
    void testEqualsAndHashCode() {
        Membro membro1 = new Membro("João Silva", 1, "joao@email.com");
        Membro membro2 = new Membro("Outro Nome", 1, "outro@email.com");
        Membro membro3 = new Membro("Maria Souza", 2, "maria@email.com");

        assertEquals(membro1, membro2, "Membros com o mesmo ID devem ser iguais");
        assertNotEquals(membro1, membro3, "Membros com IDs diferentes devem ser diferentes");
        assertEquals(membro1.hashCode(), membro2.hashCode(), "HashCode deve ser igual para membros com mesmo ID");
    }

    @Test
    void testToString() {
        Membro membro = new Membro("João Silva", 1, "joao@email.com");

        String expected = "Membro{nome='João Silva', id=1, email='joao@email.com'}";
        assertEquals(expected, membro.toString(), "O método toString() deve retornar a representação correta");
    }
}