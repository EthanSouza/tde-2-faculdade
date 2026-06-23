public class CifraCesar {

    // criptografa sem exibir os passos
    public static String criptografar(String texto, int chave) {
        return criptografar(texto, chave, false);
    }

    // descriptografa sem exibir os passos
    public static String descriptografar(String texto, int chave) {
        return descriptografar(texto, chave, false);
    }

    //Método principal, que pode mostrar cada passo
    // `texto` é a mensagem a ser processada, `chave` é o deslocamento, e `mostrarPassos` indica se cada passo deve ser exibido
    public static String criptografar(String texto, int chave, boolean mostrarPassos) {
        // Normaliza o deslocamento para garantir que fique sempre entre 0 e 25,
        // mesmo que seja negativo ou maior que 26 (evita comportamento inesperado do operador % em Java)
        int deslocamento = ((chave % 26) + 26) % 26;

        if (mostrarPassos) {
            System.out.println("--- Iniciando CRIPTOGRAFIA (deslocamento = " + chave + ", deslocamento efetivo = " + deslocamento + ") ---");
        }

        // Aplica o deslocamento "para frente" no alfabeto (ex: A -> D, se deslocamento = 3)
        return aplicarDeslocamento(texto, deslocamento, mostrarPassos);
    }

    // Versão completa do método de descriptografia, com opção de mostrar o passo a passo
    public static String descriptografar(String texto, int chave, boolean mostrarPassos) {
        // Calcula o deslocamento inverso (complementar a 26) para "desfazer" a criptografia
        // usando o mesmo método aplicarDeslocamento, sem precisar de uma lógica de subtração separada
        int deslocamentoInverso = 26 - (((chave % 26) + 26) % 26);

        if (mostrarPassos) {
            System.out.println("--- Iniciando DESCRIPTOGRAFIA (deslocamento = " + chave + ", deslocamento inverso aplicado = " + deslocamentoInverso + ") ---");
        }

        // Aplica o deslocamento inverso, "desfazendo" o deslocamento original letra por letra
        return aplicarDeslocamento(texto, deslocamentoInverso, mostrarPassos);
    }

    // Método central que realiza o deslocamento de cada caractere do texto
    // É reutilizado tanto pela criptografia quanto pela descriptografia, mudando apenas o valor do deslocamento
    private static String aplicarDeslocamento(String texto, int deslocamento, boolean mostrarPassos) {
        StringBuilder resultado = new StringBuilder();

        // Percorre o texto caractere por caractere
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // Caso o caractere seja uma letra MAIÚSCULA (A-Z)
            if (c >= 'A' && c <= 'Z') {
                // Calcula a posição da letra dentro do alfabeto (0 = A, 1 = B, ..., 25 = Z)
                int posicaoOriginal = c - 'A';
                // Aplica o deslocamento e usa módulo 26 para "dar a volta" no alfabeto quando necessário
                int novaPosicao = (posicaoOriginal + deslocamento) % 26;
                // Converte a nova posição numérica de volta para o caractere correspondente
                char novoChar = (char) ('A' + novaPosicao);
                resultado.append(novoChar);
                if (mostrarPassos) {
                    System.out.println("  '" + c + "' (posicao " + posicaoOriginal + ") + " + deslocamento + " -> posicao " + novaPosicao + " (mod 26) = '" + novoChar + "'");
                }

            // Caso o caractere seja uma letra minúscula (a-z) - mesma lógica, mas com base 'a'
            } else if (c >= 'a' && c <= 'z') {
                int posicaoOriginal = c - 'a';
                int novaPosicao = (posicaoOriginal + deslocamento) % 26;
                char novoChar = (char) ('a' + novaPosicao);
                resultado.append(novoChar);
                if (mostrarPassos) {
                    System.out.println("  '" + c + "' (posicao " + posicaoOriginal + ") + " + deslocamento + " -> posicao " + novaPosicao + " (mod 26) = '" + novoChar + "'");
                }

            // Qualquer caractere que não seja letra (números, espaços, pontuação, acentos, etc.)
            // é mantido exatamente como está, sem nenhum deslocamento
            } else {
                resultado.append(c);
                if (mostrarPassos) {
                    System.out.println("  '" + c + "' nao e letra -> mantido sem alteracao");
                }
            }
        }

        if (mostrarPassos) {
            System.out.println("--- Resultado: \"" + resultado + "\" ---");
        }

        // Retorna o texto final já processado
        return resultado.toString();
    }
}