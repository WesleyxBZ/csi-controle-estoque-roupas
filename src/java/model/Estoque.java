package model;

public class Estoque {

    private int id_estoque;
    private Produto produto;
    private int qtdProd;
    private String atualizado_em;

    public Estoque(int id_estoque, Produto produto, int qtdProd) {
        this.id_estoque = id_estoque;
        this.produto = produto;
        this.qtdProd = qtdProd;
    }

    public Estoque() {

    }

    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(int qtdProd) {
        this.qtdProd = qtdProd;
    }

    public String getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(String atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

}
