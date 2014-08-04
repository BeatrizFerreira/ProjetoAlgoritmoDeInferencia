package br.com.extracao;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SinonimosBr {
	public static ArrayList<String> listaSinonimosBr;
	
	public static void main(String[] args) throws IOException {
		String url = "http://www.sinonimos.com.br/informacao/";
		Document document = Jsoup.connect(url).get();
		Elements links = document.select("p[class]");
		listaSinonimosBr = new ArrayList<String>();
		for (Element link : links) {
			System.out.println(link.text());
			link.text().replace(".", " ");
			listaSinonimosBr.add(link.text().replace(".", "").replaceAll("[0-9]", ""));
        }
		System.out.println("LISTA DE SINONIMOS BR: " +listaSinonimosBr);
	}

}
