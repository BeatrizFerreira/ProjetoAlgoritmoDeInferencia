package br.com.algoritmo.extracao.desktop.Pt_BR;

import java.text.Normalizer;
import java.util.*;

public class MeaningPtBR {
	public static final int UNKNOWN = -1;
	public static final int NOUN = 0;
	public static final int SUBS = 1;
	public static final int ADJ = 2;
	public static final int ADV = 3;
	public static final int VERB = 4;

	private int type;
	private TermPtBR[] terms;
	public static ArrayList<String> listaSinonimosThesaurusPtBR;

	@SuppressWarnings("unchecked")
	public MeaningPtBR(String line,
			@SuppressWarnings("rawtypes") ArrayList[] dumbList) {
		String[] pieces = line.split("\\|");
		// set the type
		ArrayList<String> l = new ArrayList<String>(Arrays.asList(pieces));
		l.remove(0);
		if (l.size() == 0) {
			return;
		}
		String typeString = l.get(0).split("\\(")[1];

		if (typeString.startsWith("noun")) {
			type = NOUN;
		} else if (typeString.startsWith("subs")) {
			type = SUBS;
		} else if (typeString.startsWith("adj")) {
			type = ADJ;
		} else if (typeString.startsWith("adv")) {
			type = ADV;
		} else if (typeString.startsWith("verb")) {
			type = VERB;
		} else {
			System.out.println("PROBLEM with meaning > " + typeString);
		}

		// add the terms
		terms = new TermPtBR[l.size()];
		listaSinonimosThesaurusPtBR = new ArrayList<String>();
		for (int i = 0; i < l.size(); i++) {

			terms[i] = new TermPtBR(l.get(i));
			dumbList[type].add(terms[i].getValue());
			String texto = terms[i].getValue();
			texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
			texto = texto.replaceAll("Ã§", "ç").replaceAll("Ã£", "ã")
					.replaceAll("Ã¢", "â").replaceAll("Ãª", "ê")
					.replaceAll("Ã³", "ó").replaceAll("Ã¡", "á")
					.replaceAll("Ã­", "í").replaceAll("Ã©", "é");
			listaSinonimosThesaurusPtBR.add(texto);
		}
	}

	public MeaningPtBR() {
		if (listaSinonimosThesaurusPtBR == null
				|| listaSinonimosThesaurusPtBR.size() > 0) {
			listaSinonimosThesaurusPtBR = new ArrayList<String>();
		} else {
			return;
		}
	}

	public int getType() {
		return type;
	}
}
