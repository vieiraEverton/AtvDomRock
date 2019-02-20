package com.domRock.atv;

import java.util.Date;

public class MovtoItem {

    private String item;

    private TipoMovimento tipoMovimento;

    private Date dataLancamento;

    private double quantidade;

    private double valor;

    public MovtoItem(String item, TipoMovimento tipoMovimento, Date dataLancamento, double quantidade, double valor) {
        this.item = item;
        this.tipoMovimento = tipoMovimento;
        this.dataLancamento = dataLancamento;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
