
public class CifraCesar {

    public static String criptografar(String texto, int chave) {
        return criptografar(texto, chave, false);
    }

    public static String descriptografar(String texto, int chave) {
        return descriptografar(texto, chave, false);
    }

    public static String criptografar(String texto, int chave, boolean mostrarPassos) {
        StringBuilder resultado = new StringBuilder();

        int deslocamento = ((chave % 26) + 26) % 26;

        if (mostrarPassos) {
            System.out.println("--- Iniciando CRIPTOGRAFIA (chave = " + chave + ", deslocamento efetivo = " + deslocamento + ") ---");
        }

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                int posicaoOriginal = c - 'A';
                int novaPosicao = (posicaoOriginal + deslocamento) % 26;
                char novoChar = (char) ('A' + novaPosicao);
                resultado.append(novoChar);
                if (mostrarPassos) {
                    System.out.println("  '" + c + "' (posicao " + posicaoOriginal + ") + " + deslocamento + " -> posicao " + novaPosicao + " (mod 26) = '" + novoChar + "'");
                }
            } else if (c >= 'a' && c <= 'z') {
                int posicaoOriginal = c - 'a';
                int novaPosicao = (posicaoOriginal + deslocamento) % 26;
                char novoChar = (char) ('a' + novaPosicao);
                resultado.append(novoChar);
                if (mostrarPassos) {
                    System.out.println("  '" + c + "' (posicao " + posicaoOriginal+ ") + " + deslocamento + " -> posicao " + novaPosicao+ " (mod 26) = '" + novoChar + "'");
                }
            } else {
                resultado.append(c);
                if (mostrarPassos) {
                    System.out.println("  '" + c + "' nao e letra -> mantido sem alteracao");
                }
            }
        }

        if (mostrarPassos) {
            System.out.println("--- Resultado da criptografia: \"" + resultado + "\" ---");
        }

        return resultado.toString();
    }

    public static String descriptografar(String texto, int chave, boolean mostrarPassos) {
        int deslocamentoInverso = 26 - (((chave % 26) + 26) % 26);

        if (mostrarPassos) {
            System.out.println("--- Iniciando DESCRIPTOGRAFIA (chave = " + chave
                    + ", deslocamento inverso aplicado = " + deslocamentoInverso + ") ---");
        }

        return criptografar(texto, deslocamentoInverso, mostrarPassos);
    }
}
