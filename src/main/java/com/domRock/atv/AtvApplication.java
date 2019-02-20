package com.domRock.atv;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//@SpringBootApplication
public class AtvApplication {

	public static void main(String[] args) {
//		SpringApplication.run(AtvApplication.class, args);
//		System.out.println(ReadXlsx.lerSaldoItem());
		agruparPorDia(ReadXlsx.lerMovtoItem());
	}

	public static ArrayList<Item> agruparPorDia(ArrayList<MovtoItem> movtoItems){

		//Ordenar os itens
		Collections.sort(movtoItems, new Comparator<MovtoItem>(){
			public int compare(MovtoItem m1, MovtoItem m2){
				if(m1.getItem().equals(m2.getItem()))
					//todo: ordenar pela data aki
					return 0;
				return (m1.getItem().compareTo (m2.getItem()) < 0) ? -1 : 1;
			}
		});

		for(int i = 0; i < movtoItems.size(); i++){
			Item item = new Item(movtoItems.get(i).getItem(), movtoItems.get(i).getDataLancamento(), 0, 0, 0, 0, 0, 0, 0, 0);

			for(int j = i; j < movtoItems.size(); j++){
				if(!movtoItems.get(i).getItem().equals(movtoItems.get(j).getItem())
						|| !movtoItems.get(i).getDataLancamento().equals(movtoItems.get(j).getDataLancamento())){
					break;
				}
				if (movtoItems.get(j).getTipoMovimento().equals(TipoMovimento.Ent)){
					item.setQuantidadeEntrada(item.getQuantidadeEntrada() + movtoItems.get(j).getQuantidade());
					item.setValorEntrada(item.getValorEntrada() + movtoItems.get(j).getValor());
				}
				else if(movtoItems.get(j).getTipoMovimento().equals(TipoMovimento.Sai)){
					item.setQuantidadeSaida(item.getQuantidadeSaida() + movtoItems.get(j).getQuantidade());
					item.setValorSaida(item.getValorSaida() + movtoItems.get(j).getValor());
				}
			}



		}

		return null;
	}

}