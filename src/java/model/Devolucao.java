package model;

public class Devolucao {

    private int id_devolucao;
    private Estoque estoque;
    private int quantidade;
    private String criado_em;

    public Devolucao(Estoque estoque, int quantidade) {
        this.estoque = estoque;
        this.quantidade = quantidade;
    }

    public Devolucao() {
    }

    public int getId_devolucao() {
        return id_devolucao;
    }

    public void setId_devolucao(int id_devolucao) {
        this.id_devolucao = id_devolucao;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(String criado_em) {
        this.criado_em = criado_em;
    }

}
