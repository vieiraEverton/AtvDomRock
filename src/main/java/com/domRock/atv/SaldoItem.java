package com.domRock.atv;

import java.util.Date;

public class SaldoItem {

    private String item;

    private Date dataInicio;

    private double qtdInicio;

    private double valorInicio;

    private Date dataFinal;

    private double qtdFinal;

    private double valorFinal;

    public SaldoItem(String item, Date dataInicio, double qtdInicio, double valorInicio, Date dataFinal, double qtdFinal, double valorFinal) {
        this.item = item;
        this.dataInicio = dataInicio;
        this.qtdInicio = qtdInicio;
        this.valorInicio = valorInicio;
        this.dataFinal = dataFinal;
        this.qtdFinal = qtdFinal;
        this.valorFinal = valorFinal;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getQtdInicio() {
        return qtdInicio;
    }

    public void setQtdInicio(double qtdInicio) {
        this.qtdInicio = qtdInicio;
    }

    public double getValorInicio() {
        return valorInicio;
    }

    public void setValorInicio(double valorInicio) {
        this.valorInicio = valorInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getQtdFinal() {
        return qtdFinal;
    }

    public void setQtdFinal(double qtdFinal) {
        this.qtdFinal = qtdFinal;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
}
