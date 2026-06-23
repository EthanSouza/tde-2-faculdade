
public class Mensagem {
    private int id; // Identificador da mensagem
    private String remetente; // Nome do remetente da mensagem
    private String destinatario; // Nome do destinatário da mensagem
    private String conteudo; // Conteúdo da mensagem
    private boolean criptografada; // Indica se a mensagem está criptografada ou não. "True" significa que está criptografada, "False" significa que está em texto claro

    public Mensagem(int id, String remetente, String destinatario, String conteudo, boolean criptografada) {

        // Responsável por inicializar todos os atributos da mensagem no momento da criação

        this.id = id; 
        this.remetente = remetente; 
        this.destinatario = destinatario; 
        this.conteudo = conteudo; 
        this.criptografada = criptografada; 
    }

    // Getters e Setters para acessar e modificar os atributos da mensagem
    // Para leitura (get) e alteração (set) de cada campo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    // Getters de booleanos usam prefixo "is" em vez de "get"
    public boolean isCriptografada() {
        return criptografada;
    }

    public void setCriptografada(boolean criptografada) {
        this.criptografada = criptografada;
    }

    // Redefine o método toString() da classe Object para fonercer uma representação legível da mensagem, incluindo ID, remetente, destinatário, status de criptografia e conteúdo

    @Override
    public String toString() {
        String status = criptografada ? "CRIPTOGRAFADA" : "em texto claro";
        return "[ID " + id + "] De: " + remetente+ " | Para: " + destinatario + " | Status: " + status + "\n   Conteudo: " + conteudo;
    }
}
