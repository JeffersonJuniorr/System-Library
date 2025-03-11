import java.io.IOException;
import java.util.Scanner;

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcao;

        do {
            System.out.println("\n--- Sistema de Gestão da Biblioteca ---");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Registrar Membro");
            System.out.println("4. Registrar Empréstimo");
            System.out.println("5. Devolver Livro");
            System.out.println("6. Listar Livros");
            System.out.println("7. Listar Membros");
            System.out.println("8. Listar Empréstimos");
            System.out.println("9. Salvar Dados");
            System.out.println("10. Carregar Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite o ISBN do livro: ");
                    String ISBN = scanner.nextLine();
                    Livro novoLivro = new Livro(titulo, autor, ISBN);
                    biblioteca.adicionarLivro(novoLivro);
                    break;

                case 2:
                    System.out.print("Digite o ISBN do livro a ser removido: ");
                    String isbnRemover = scanner.nextLine();
                    Livro livroRemover = biblioteca.getLivros().stream()
                            .filter(l -> l.getISBN().equals(isbnRemover))
                            .findFirst()
                            .orElse(null);
                    if (livroRemover != null) {
                        biblioteca.removerLivro(livroRemover);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do membro: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o ID do membro: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o email do membro: ");
                    String email = scanner.nextLine();
                    Membro novoMembro = new Membro(nome, id, email);
                    biblioteca.registrarMembro(novoMembro);
                    break;

                case 4:
                    System.out.print("Digite o ISBN do livro a ser emprestado: ");
                    String isbnEmprestimo = scanner.nextLine();
                    System.out.print("Digite o ID do membro: ");
                    int idMembro = scanner.nextInt();
                    scanner.nextLine();

                    String livroEmprestimo = String.valueOf(biblioteca.getLivros().stream()
                            .filter(l -> l.getISBN().equals(isbnEmprestimo))
                            .findFirst()
                            .orElse(null));
                    Membro membroEmprestimo = biblioteca.getMembros().stream()
                            .filter(m -> m.getId() == idMembro)
                            .findFirst()
                            .orElse(null);

                    if (livroEmprestimo != null && membroEmprestimo != null) {
                        biblioteca.registrarEmprestimo(livroEmprestimo, membroEmprestimo.getId());
                    } else {
                        System.out.println("Livro ou membro não encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Digite o ISBN do livro a ser devolvido: ");
                    String isbnDevolucao = scanner.nextLine();
                    Emprestimo emprestimoDevolucao = biblioteca.getEmprestimos().stream()
                            .filter(e -> e.getLivro().getISBN().equals(isbnDevolucao))
                            .findFirst()
                            .orElse(null);
                    if (emprestimoDevolucao != null) {
                        biblioteca.devolverLivro(emprestimoDevolucao.toString());
                    } else {
                        System.out.println("Empréstimo não encontrado.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- Lista de Livros ---");
                    biblioteca.getLivros().forEach(System.out::println);
                    break;

                case 7:
                    System.out.println("\n--- Lista de Membros ---");
                    biblioteca.getMembros().forEach(System.out::println);
                    break;

                case 8:
                    System.out.println("\n--- Lista de Empréstimos ---");
                    biblioteca.getEmprestimos().forEach(System.out::println);
                    break;

                case 9:
                    try {
                        biblioteca.salvarDadosEmArquivo("biblioteca.txt");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar os dados: " + e.getMessage());
                    }
                    break;

                case 10:
                    try {
                        biblioteca.carregarDadosDeArquivo("biblioteca.txt");
                    } catch (IOException e) {
                        System.out.println("Erro ao carregar os dados: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}