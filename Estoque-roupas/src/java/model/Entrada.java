package model;

public class Entrada {

    private int id_entrada;
    private Estoque estoque;
    private int quantidade;
    private String criado_em;

    public Entrada(Estoque estoque, int quantidade) {
        this.id_entrada = id_entrada;
        this.estoque = estoque;
        this.quantidade = quantidade;
    }

    public Entrada() {

    }

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrada) {
        this.id_entrada = id_entrada;
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
