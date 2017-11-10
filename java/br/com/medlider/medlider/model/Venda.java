package br.com.medlider.medlider.model;

/**
 * Created by Filipe Firmino on 09/11/2017.
 */

public class Venda {

    private Long id;
    private String vendaNomeRemedio;
    private String vendaTipoRemedio;
    private String vendaPrecoRemedio;
    private int    vendaQuantidadeRemedio;
    private String vendaNomeCliente;
    private String vendaCPFCliente;
    private String vendaTotalVenda;

    public Venda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendaNomeRemedio() {
        return vendaNomeRemedio;
    }

    public void setVendaNomeRemedio(String vendaNomeRemedio) {
        this.vendaNomeRemedio = vendaNomeRemedio;
    }

    public String getVendaTipoRemedio() {
        return vendaTipoRemedio;
    }

    public void setVendaTipoRemedio(String vendaTipoRemedio) {
        this.vendaTipoRemedio = vendaTipoRemedio;
    }

    public String getVendaPrecoRemedio() {
        return vendaPrecoRemedio;
    }

    public void setVendaPrecoRemedio(String vendaPrecoRemedio) {
        this.vendaPrecoRemedio = vendaPrecoRemedio;
    }

    public String getVendaNomeCliente() {
        return vendaNomeCliente;
    }

    public void setVendaNomeCliente(String vendaNomeCliente) {
        this.vendaNomeCliente = vendaNomeCliente;
    }

    public String getVendaCPFCliente() {
        return vendaCPFCliente;
    }

    public void setVendaCPFCliente(String vendaCPFCliente) {
        this.vendaCPFCliente = vendaCPFCliente;
    }

    public String getVendaTotalVenda() {
        return vendaTotalVenda;
    }

    public void setVendaTotalVenda(String vendaTotalVenda) {
        this.vendaTotalVenda = vendaTotalVenda;
    }

    public int getVendaQuantidadeRemedio() {
        return vendaQuantidadeRemedio;
    }

    public void setVendaQuantidadeRemedio(int vendaQuantidadeRemedio) {
        this.vendaQuantidadeRemedio = vendaQuantidadeRemedio;
    }
}
