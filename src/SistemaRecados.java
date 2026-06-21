import java.util.Scanner;

public class SistemaRecados {

    static final int MAX_MENSAGENS = 10;
    static Mensagem[] mensagens = new Mensagem[MAX_MENSAGENS];
    static int totalMensagens = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        demonstracaoAutomatica();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    escreverMensagem();
                    break;
                case 2:
                    listarMensagens();
                    break;
                case 3:
                    operarMensagem(true); // criptografar
                    break;
                case 4:
                    operarMensagem(false); // descriptografar
                    break;
                case 5:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
            System.out.println();
        } while (opcao != 5);

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("===== SISTEMA DE RECADOS =====");
        System.out.println("1 | Escrever nova mensagem");
        System.out.println("2 | Listar todas as mensagens");
        System.out.println("3 | Criptografar mensagem (ID + chave)");
        System.out.println("4 | Descriptografar mensagem (ID + chave)");
        System.out.println("5 | Sair");
    }

    static void escreverMensagem() {
        if (totalMensagens >= MAX_MENSAGENS) {
            System.out.println("Limite de " + MAX_MENSAGENS + " mensagens atingido!");
            return;
        }

        scanner.nextLine();
        System.out.print("Remetente: ");
        String remetente = scanner.nextLine();
        System.out.print("Destinatario: ");
        String destinatario = scanner.nextLine();
        System.out.print("Conteudo da mensagem: ");
        String conteudo = scanner.nextLine();

        int novoId = totalMensagens + 1;
        Mensagem nova = new Mensagem(novoId, remetente, destinatario, conteudo, false);
        mensagens[totalMensagens] = nova;
        totalMensagens++;

        System.out.println("Mensagem cadastrada com ID " + novoId + "!");
    }

    static void listarMensagens() {
        if (totalMensagens == 0) {
            System.out.println("Nenhuma mensagem cadastrada ainda.");
            return;
        }

        System.out.println("----- LISTA DE MENSAGENS -----");
        for (int i = 0; i < totalMensagens; i++) {
            System.out.println(mensagens[i]);
        }
    }

    static void operarMensagem(boolean criptografando) {
        if (totalMensagens == 0) {
            System.out.println("Nenhuma mensagem cadastrada ainda.");
            return;
        }

        int id = lerInteiro("Informe o ID da mensagem: ");
        Mensagem alvo = buscarPorId(id);

        if (alvo == null) {
            System.out.println("Mensagem com ID " + id + " nao encontrada.");
            return;
        }

        if (criptografando && alvo.isCriptografada()) {
            System.out.println("Esta mensagem ja esta criptografada!");
            return;
        }
        if (!criptografando && !alvo.isCriptografada()) {
            System.out.println("Esta mensagem ja esta em texto claro!");
            return;
        }

        int chave = lerInteiro("Informe a senha: ");
        System.out.println();

        if (criptografando) {
            String resultado = CifraCesar.criptografar(alvo.getConteudo(), chave, true);
            alvo.setConteudo(resultado);
            alvo.setCriptografada(true);
            System.out.println("\nMensagem criptografada com sucesso!");
        } else {
            String resultado = CifraCesar.descriptografar(alvo.getConteudo(), chave, true);
            alvo.setConteudo(resultado);
            alvo.setCriptografada(false);
            System.out.println("\nMensagem descriptografada com sucesso!");
        }

        System.out.println(alvo);
    }

    static Mensagem buscarPorId(int id) {
        for (int i = 0; i < totalMensagens; i++) {
            if (mensagens[i].getId() == id) {
                return mensagens[i];
            }
        }
        return null;
    }

    static void demonstracaoAutomatica() {
        System.out.println("===== DEMONSTRACAO DA CIFRA DE CESAR =====");
        System.out.println("Antes de entrar no menu, vamos testar o algoritmo com um exemplo seu.");

        System.out.print("Digite um texto para criptografar: ");
        String original = scanner.nextLine();
        int chaveDemo = lerInteiro("Digite a chave numerica (ex: 3): ");
        scanner.nextLine();

        System.out.println("\nTexto original digitado: \"" + original + "\"\n");

        String criptografado = CifraCesar.criptografar(original, chaveDemo, true);

        System.out.println();
        String descriptografado = CifraCesar.descriptografar(criptografado, chaveDemo, true);

        System.out.println("\n----- RESUMO DA DEMONSTRACAO -----");
        System.out.println("Texto original        : " + original);
        System.out.println("Senha utilizada        : " + chaveDemo);
        System.out.println("Texto criptografado    : " + criptografado);
        System.out.println("Texto descriptografado : " + descriptografado);
        System.out.println("===========================================\n");
    }

    static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor invalido. Digite um numero inteiro: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
