import java.io.*;
import java.util.*;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Membro> membros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adicionarLivro(Livro livro) {
        if (!livros.contains(livro)) {
            livros.add(livro);
            System.out.println("Livro adicionado: " + livro);
        } else {
            System.out.println("Livro já existe na biblioteca: " + livro);
        }
    }

    public void removerLivro(Livro livro) {
        if (!livros.contains(livro)) {
            livros.remove(livro);
            System.out.println("Livro removido: " + livro);
        } else {
            System.out.println("O livro não encontrado: " + livro);
        }
    }

    public void registrarMembro(Membro membro) {
        if (!membros.contains(livros)) {
            membros.add(membro);
            System.out.println("Membro registrado: " + membro);
        } else {
            System.out.println("Membro já registrado: " + membro);
        }
    }

    public void registrarEmprestimo(String isbnLivro, int idMembro) {

        Livro livro = livros.stream()
                .filter(l -> l.getISBN().equals(isbnLivro))
                .findFirst()
                .orElse(null);

        Membro membro = membros.stream()
                .filter(m -> m.getId() == idMembro)
                .findFirst()
                .orElse(null);

        if (livro == null) {
            System.out.println("Erro: Livro com ISBN " + isbnLivro + " não encontrado.");
            return;
        }
        if (membro == null) {
            System.out.println("Erro: Membro com ID " + idMembro + " não encontrado.");
            return;
        }

        boolean livroJaEmprestado = emprestimos.stream()
                .anyMatch(e -> e.getLivro().getISBN().equals(isbnLivro));
        if (livroJaEmprestado) {
            System.out.println("Erro: O livro " + livro.getTitulo() + " já está emprestado.");
            return;
        }

        // Registrar o empréstimo
        Emprestimo emprestimo = new Emprestimo(livro, membro, new Date());
        emprestimos.add(emprestimo);
        System.out.println("Empréstimo registrado: " + emprestimo);
    }

    public void devolverLivro(String isbnLivro) {

        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getLivro().getISBN().equals(isbnLivro))
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            System.out.println("Erro: Empréstimo do livro com ISBN " + isbnLivro + " não encontrado.");
            return;
        }

        emprestimos.remove(emprestimo);
        System.out.println("Livro devolvido: " + emprestimo.getLivro().getTitulo());
    }

    public void salvarDadosEmArquivo(String nomeArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {

            for (Livro livro : livros) {
                writer.write("Livro:" + livro.getTitulo() + "," + livro.getAutor() + "," + livro.getISBN() + "\n");
            }
            for (Membro membro : membros) {
                writer.write("Membro:" + membro.getNome() + "," + membro.getId() + "," + membro.getEmail() + "\n");
            }
            for (Emprestimo emprestimo : emprestimos) {
                writer.write("Emprestimo:" + emprestimo.getLivro().getISBN() + "," + emprestimo.getMembro().getId() + "," + emprestimo.getDataEmprestimo().getTime() + "\n");
            }
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void carregarDadosDeArquivo(String nomeArquivo) throws IOException {

        livros.clear();
        membros.clear();
        emprestimos.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {

                if (linha.startsWith("Livro:")) {
                    String[] dados = linha.substring(6).split(",");
                    Livro livro = new Livro(dados[0], dados[1], dados[2]);
                    livros.add(livro);

                } else if (linha.startsWith("Membro:")) {
                    String[] dados = linha.substring(7).split(",");
                    Membro membro = new Membro(dados[0], Integer.parseInt(dados[1]), dados[2]);
                    membros.add(membro);

                } else if (linha.startsWith("Emprestimo:")) {
                    String[] dados = linha.substring(11).split(",");
                    String isbn = dados[0];
                    int idMembro = Integer.parseInt(dados[1]);
                    long dataEmprestimo = Long.parseLong(dados[2]);

                    Livro livro = livros.stream()
                            .filter(l -> l.getISBN().equals(isbn))
                            .findFirst()
                            .orElse(null);
                    Membro membro = membros.stream()
                            .filter(m -> m.getId() == idMembro)
                            .findFirst()
                            .orElse(null);

                    if (livro != null && membro != null) {
                        Emprestimo emprestimo = new Emprestimo(livro, membro, new Date(dataEmprestimo));
                        emprestimos.add(emprestimo);
                    }
                }
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}