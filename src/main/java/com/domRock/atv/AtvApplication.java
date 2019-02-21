package com.domRock.atv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class AtvApplication {

	public static void main(String[] args) {
		ArrayList<Item> items = agruparPorDia(ReadWriteXlsx.lerMovtoItem(), ReadWriteXlsx.lerSaldoItem());
		ReadWriteXlsx.escreverMovDiarioITEM(items);
	}

	private static ArrayList<Item> agruparPorDia(ArrayList<MovtoItem> movtoItems, ArrayList<SaldoItem> saldoItems){
		ArrayList<Item> items = new ArrayList<Item>();
		//Ordenar os itens
		Collections.sort(movtoItems, new Comparator<MovtoItem>(){
			public int compare(MovtoItem m1, MovtoItem m2){
				if(m1.getItem().equals(m2.getItem())) {
					if (m1.getDataLancamento().equals(m2.getDataLancamento())){
						return 0;
					}
					return (m1.getDataLancamento().before(m2.getDataLancamento())) ? -1 : 1;
				}
				return (m1.getItem().compareTo (m2.getItem()) < 0) ? -1 : 1;
			}
		});
		//obter o saldo inicial atraves da saldoItem
		final MovtoItem movtoItem = movtoItems.get(0);
		Optional<SaldoItem> saldoItem = saldoItems.stream().filter(item -> item.getItem().equals(movtoItem.getItem())).findFirst();

		double quantidadeInicial = saldoItem.map(SaldoItem::getQtdInicio).orElse(0.0);
		double valorInicial = saldoItem.map(SaldoItem::getValorInicio).orElse(0.0);
		//Gerar a lista final
		for(int i = 0; i < movtoItems.size(); i++){
			Item item = new Item(movtoItems.get(i).getItem(), movtoItems.get(i).getDataLancamento(),  0, 0, 0, 0, quantidadeInicial, valorInicial, 0, 0);
			//Agrupar os Itens
			for(int j = i; j < movtoItems.size(); j++){
				if (movtoItems.get(j).getTipoMovimento().equals(TipoMovimento.Ent)){
					item.setQuantidadeEntrada(item.getQuantidadeEntrada() + movtoItems.get(j).getQuantidade());
					item.setValorEntrada(item.getValorEntrada() + movtoItems.get(j).getValor());
				}
				else if(movtoItems.get(j).getTipoMovimento().equals(TipoMovimento.Sai)){
					item.setQuantidadeSaida(item.getQuantidadeSaida() + movtoItems.get(j).getQuantidade());
					item.setValorSaida(item.getValorSaida() + movtoItems.get(j).getValor());
				}
				if(movtoItems.size() != j+1) {
					//se o Item mudou tem que obter o novo valor inicial
					if (!movtoItems.get(i).getItem().equals(movtoItems.get(j + 1).getItem())) {
						//saldo final = saldo inicial + entrada – saída
						//quantidade e valor final de um é a quantidade final do outro
						quantidadeInicial = quantidadeInicial + item.getQuantidadeEntrada() - item.getQuantidadeSaida();
						valorInicial = valorInicial + item.getValorEntrada() - item.getValorSaida();
						item.setQuantidadeFinal(quantidadeInicial);
						item.setValorFinal(valorInicial);

						//obter o saldo inicial atraves da saldoItem
						final MovtoItem movtoItem1 = movtoItems.get(j + 1);
						Optional<SaldoItem> saldoItem1 = saldoItems.stream().filter(item1 -> item1.getItem().equals(movtoItem1.getItem())).findFirst();

						quantidadeInicial = saldoItem1.map(SaldoItem::getQtdInicio).orElse(0.0);
						valorInicial = saldoItem1.map(SaldoItem::getValorInicio).orElse(0.0);
						break;
					} else if (!movtoItems.get(i).getDataLancamento().equals(movtoItems.get(j + 1).getDataLancamento())) {
						//saldo final = saldo inicial + entrada – saída
						//quantidade e valor final de um é a quantidade final do outro
						quantidadeInicial = quantidadeInicial + item.getQuantidadeEntrada() - item.getQuantidadeSaida();
						valorInicial = valorInicial + item.getValorEntrada() - item.getValorSaida();
						item.setQuantidadeFinal(quantidadeInicial);
						item.setValorFinal(valorInicial);
						break;
					}
				}
				else {
					quantidadeInicial = quantidadeInicial + item.getQuantidadeEntrada() - item.getQuantidadeSaida();
					valorInicial = valorInicial + item.getValorEntrada() - item.getValorSaida();
					item.setQuantidadeFinal(quantidadeInicial);
					item.setValorFinal(valorInicial);
				}

				i++;
			}

			items.add(item);
		}

		return items;
	}

}