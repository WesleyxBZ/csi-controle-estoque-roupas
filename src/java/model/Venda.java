package model;

public class Venda {

    private int id_venda;
    private Usuario usuario;
    private double valorTotal;
    private double desconto;
    private String criado_em;

    public Venda(int id_venda, Usuario usuario, double valorTotal) {
        this.id_venda = id_venda;
        this.usuario = usuario;
        this.valorTotal = valorTotal;
    }

    public Venda() {

    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(String criado_em) {
        this.criado_em = criado_em;
    }

}
