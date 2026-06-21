
public class Mensagem {
    private int id;
    private String remetente;
    private String destinatario;
    private String conteudo;
    private boolean criptografada;

    public Mensagem(int id, String remetente, String destinatario, String conteudo, boolean criptografada) {
        this.id = id;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.criptografada = criptografada;
    }

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

    public boolean isCriptografada() {
        return criptografada;
    }

    public void setCriptografada(boolean criptografada) {
        this.criptografada = criptografada;
    }

    @Override
    public String toString() {
        String status = criptografada ? "CRIPTOGRAFADA" : "em texto claro";
        return "[ID " + id + "] De: " + remetente+ " | Para: " + destinatario + " | Status: " + status + "\n   Conteudo: " + conteudo;
    }
}
