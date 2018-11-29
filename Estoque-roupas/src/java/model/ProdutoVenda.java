package model;

public class ProdutoVenda {

    private int id_produtoVenda;
    private Estoque estoque;
    private Venda venda;
    private int quantidade;

    public ProdutoVenda(int id_produtoVenda, Estoque estoque, Venda venda, int quantidade) {
        this.id_produtoVenda = id_produtoVenda;
        this.estoque = estoque;
        this.venda = venda;
        this.quantidade = quantidade;
    }

    public ProdutoVenda() {

    }

    public int getId_produtoVenda() {
        return id_produtoVenda;
    }

    public void setId_produtoVenda(int id_produtoVenda) {
        this.id_produtoVenda = id_produtoVenda;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
