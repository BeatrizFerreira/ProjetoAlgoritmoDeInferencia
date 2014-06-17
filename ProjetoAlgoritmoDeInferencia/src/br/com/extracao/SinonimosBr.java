package br.com.extracao;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SinonimosBr {
	
	public static void main(String[] args) throws IOException {
		String url = "http://www.sinonimos.com.br/informacao/";
		Document document = Jsoup.connect(url).get();
		Elements links = document.select("p[class]");
	
		for (Element link : links) {
			System.out.println(link.text());
        }
	}

}
