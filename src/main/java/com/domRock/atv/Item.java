package com.domRock.atv;

import java.util.Date;

public class Item {

    private String Item;

    private Date dataLancamento;

    private double quantidadeEntrada;

    private double valorEntrada;

    private double quantidadeSaida;

    private double valorSaida;

    private double quantidadeInicial;

    private double valorInicial;

    private double quantidadeFinal;

    private double valorFinal;

    public Item(String item, Date dataLancamento, double quantidadeEntrada, double valorEntrada, double quantidadeSaida, double valorSaida, double quantidadeInicial, double valorInicial, double quantidadeFinal, double valorFinal) {
        Item = item;
        this.dataLancamento = dataLancamento;
        this.quantidadeEntrada = quantidadeEntrada;
        this.valorEntrada = valorEntrada;
        this.quantidadeSaida = quantidadeSaida;
        this.valorSaida = valorSaida;
        this.quantidadeInicial = quantidadeInicial;
        this.valorInicial = valorInicial;
        this.quantidadeFinal = quantidadeFinal;
        this.valorFinal = valorFinal;
    }

    public Item(){}

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(double quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public double getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(double quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public double getValorSaida() {
        return valorSaida;
    }

    public void setValorSaida(double valorSaida) {
        this.valorSaida = valorSaida;
    }

    public double getQuantidadeInicial() {
        return quantidadeInicial;
    }

    public void setQuantidadeInicial(double quantidadeInicial) {
        this.quantidadeInicial = quantidadeInicial;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getQuantidadeFinal() {
        return quantidadeFinal;
    }

    public void setQuantidadeFinal(double quantidadeFinal) {
        this.quantidadeFinal = quantidadeFinal;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
}
