package model;

public class Produto {

    private int id_produto;
    private Categoria categoria;
    private String marca;
    private String cor;
    private String tamanho;
    private String descricao;
    private String codigoBarras;
    private double preco;
    private String caminhoImg;
    private boolean visivelCliente;
    private String criado_em;
    private String atualizado_em;

    public Produto(int id_produto, Categoria categoria, String marca, String cor, String tamanho, String descricao, String codigoBarras, double preco, String caminhoImg, boolean visivelCliente) {
        this.id_produto = id_produto;
        this.categoria = categoria;
        this.marca = marca;
        this.cor = cor;
        this.tamanho = tamanho;
        this.descricao = descricao;
        this.codigoBarras = codigoBarras;
        this.preco = preco;
        this.caminhoImg = caminhoImg;
        this.visivelCliente = visivelCliente;
    }

    public Produto() {

    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCaminhoImg() {
        return caminhoImg;
    }

    public void setCaminhoImg(String caminhoImg) {
        this.caminhoImg = caminhoImg;
    }

    public boolean isVisivelCliente() {
        return visivelCliente;
    }

    public void setVisivelCliente(boolean visivelCliente) {
        this.visivelCliente = visivelCliente;
    }

    public String getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(String criado_em) {
        this.criado_em = criado_em;
    }

    public String getAtualizado_em() {
        return atualizado_em;
    }

    public void setAtualizado_em(String atualizado_em) {
        this.atualizado_em = atualizado_em;
    }

}
