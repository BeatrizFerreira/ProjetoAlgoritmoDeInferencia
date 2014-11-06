package br.com.algoritmo.extracao.web;

import java.io.*;
import java.util.*;

public class StopWords {
	public static List<String> listaStopWords = new ArrayList<String>();

	public void carregaStopWords() throws IOException {
		File texto = new File("stopwords");

		// criar arquivo
		texto.createNewFile();

		FileReader fr = new FileReader(texto);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		// equanto houver mais linhas
		while (br.ready()) {

			// lê a proxima linha
			String linha = br.readLine();
			listaStopWords.add(linha);
		}
	}

	public String tratarStr(String str) {

		ArrayList<String> tokens = new ArrayList<String>();

		@SuppressWarnings("resource")
		Scanner tokenize = new Scanner(str);
		while (tokenize.hasNext()) {
			tokens.add(tokenize.next());
		}
		for (int i = 0; i < tokens.size(); i++) {
			if (listaStopWords.contains(tokens.get(i).toLowerCase())) {

				str = " " + str;
				str = str.toLowerCase().replace(
						" " + tokens.get(i).toLowerCase() + " ", " ");
			}
		}
		return str.trim();
	}
}
