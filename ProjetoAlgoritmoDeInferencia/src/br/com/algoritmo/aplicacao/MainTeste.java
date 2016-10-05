package br.com.algoritmo.aplicacao;

import java.io.IOException;
import java.util.ArrayList;

public class MainTeste {

	public static void main(String[] args) throws IOException {
		String individuoDestino = "Fábio Macêdo Mendes";
		String individuoBase = "Luiz Carlos Miyadaira Ribeiro Junior";
		TrataString trataString = new TrataString();
		trataString.calculaFatorAderencia(individuoBase, individuoDestino);
		
		System.err.println("---------------------------------------------------------------------------");
		System.err.println("Quantidade de producoes aderentes: "+ trataString.getQuantidadeProducoesAderentes());
		System.err.println("-------------------------VALOR PERCENTUAL ADERENTE-------------------------");
		System.err.println("-------------------------INDIVÍDUO BASE: "+ "Luiz Carlos Miyadaira Ribeiro Junior".toLowerCase().replace(' ', '-'));
		System.err.println("-------------------------INDIVÍDUO DESTINO: "+ "Fábio Macêdo Mendes".toLowerCase().replace(' ', '-'));		
		System.err.println("-------------------------VALOR: " + trataString.getValoresPercentuaisAderencia());
		System.err.println("---------------------------------------------------------------------------");
	}
}
