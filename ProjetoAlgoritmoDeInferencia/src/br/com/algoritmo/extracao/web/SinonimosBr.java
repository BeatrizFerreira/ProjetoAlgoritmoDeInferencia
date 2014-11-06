package br.com.algoritmo.extracao.web;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SinonimosBr {
	public static ArrayList<String> listaSinonimosBr;

	public void listaSinonimosBr(String palavra) {
		listaSinonimosBr = new ArrayList<String>();
		String url = "http://www.sinonimos.com.br/" + palavra + "/";
		Document document;
		try {
			document = Jsoup.connect(url).get();

			Elements links = document.select("p[class]");

			for (Element link : links) {
				listaSinonimosBr.add(link.text().replace(".", "")
						.replaceAll("[0-9]", ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
