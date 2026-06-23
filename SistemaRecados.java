import java.util.Scanner;

public class SistemaRecados {

    static final int MAX_MENSAGENS = 10;              // capacidade máxima do "banco de dados" em memória
    static Mensagem[] mensagens = new Mensagem[MAX_MENSAGENS];  // armazenamento simples via array
    static int totalMensagens = 0;                      // contador de mensagens cadastradas até agora
    static Scanner scanner = new Scanner(System.in);   // leitura de entrada do usuário (reutilizado em todo o programa)

    public static void main(String[] args) {
        // faz uma demonstração automática antes de entrar no menu principal

        demonstracaoAutomatica();
        
        int opcao;

        // loop principal do menu. 
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
        } while (opcao != 5); // Repete até o usuário escolher sair (opção 5)

        scanner.close();
    }

    // Opções do menu principal
    static void exibirMenu() {
        System.out.println("===== SISTEMA DE RECADOS =====");
        System.out.println("1 | Escrever nova mensagem");
        System.out.println("2 | Listar todas as mensagens");
        System.out.println("3 | Criptografar mensagem (ID + chave )");
        System.out.println("4 | Descriptografar mensagem (ID + chave )");
        System.out.println("5 | Sair");
    }

    // Função para cadastrar uma nova mensagem
    static void escreverMensagem() {
        if (totalMensagens >= MAX_MENSAGENS) {
            System.out.println("Limite de " + MAX_MENSAGENS + " mensagens atingido.");
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

    // Função para listar todas as mensagens
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

    // Função para criptografar ou descriptografar uma mensagem existente
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
            System.out.println("Mensagem já esta criptografada!");
            return;
        }
        if (!criptografando && !alvo.isCriptografada()) {
            System.out.println("Esta mensagem já está descriptografada.");
            return;
        }

        int chave  = lerInteiro("Informe a chave : ");
        System.out.println();

        // "True" no final ativa o modo mostrarPassos da Cifra de Cesar, exebindo letra por letra o processo de criptografia/descriptografia
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

    // Demonstração antes do menu principal, para mostrar como funciona a Cifra de Cesar. Com comparação do texto original, criptografado e descriptografado.
    static void demonstracaoAutomatica() {
        System.out.println("===== DEMONSTRACAO DA CIFRA DE CESAR =====");
        System.out.println("Antes de entrar no menu, vamos testar o algoritmo com um exemplo seu.");

        System.out.print("Digite um texto para criptografar: ");
        String original = scanner.nextLine();
        int chaveDemo = lerInteiro("Digite uma chave (ex: 4): ");
        scanner.nextLine();

        System.out.println("\nTexto original digitado: \"" + original + "\"\n");

        // Passo 1: Criptografar o texto original com a chave fornecida
        String criptografado = CifraCesar.criptografar(original, chaveDemo, true);

        // Passo 2: Descriptografar o texto criptografado com a mesma chave
        System.out.println();
        String descriptografado = CifraCesar.descriptografar(criptografado, chaveDemo, true);

        System.out.println("\n----- RESUMO DA DEMONSTRACAO -----");
        System.out.println("Texto original        : " + original);
        System.out.println("Chave utilizada        : " + chaveDemo);
        System.out.println("Texto criptografado    : " + criptografado);
        System.out.println("Texto descriptografado : " + descriptografado);
        System.out.println("===========================================\n");
    }

    // Função auxiliar para ler um número inteiro do usuário, para evitar erros, assim repetindo a solicitação até que o usuário digite um valor válido
    static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("Valor invalido. Digite um numero inteiro: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
