package br.com.algoritmo.extracao.desktop.En_US;

import java.util.*;

public class MeaningEnUS {
	public static final int UNKNOWN = -1;
	public static final int NOUN = 0;
	public static final int ADJ = 1;
	public static final int ADV = 2;
	public static final int VERB = 3;

	private int type;
	private TermEnUS[] terms;
	public static ArrayList<String> listaSinonimosThesaurusEnUS;

	@SuppressWarnings("unchecked")
	public MeaningEnUS(String line,
			@SuppressWarnings("rawtypes") ArrayList[] dumbList) {
		String[] pieces = line.split("\\|");
		// set the type
		String typeString = pieces[0].split("\\(")[1];

		if (typeString.startsWith("noun")) {
			type = NOUN;
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
		terms = new TermEnUS[pieces.length - 1];
		listaSinonimosThesaurusEnUS = new ArrayList<String>();
		for (int i = 1; i < pieces.length; i++) {
			terms[i - 1] = new TermEnUS(pieces[i]);
			dumbList[type].add(terms[i - 1].getValue());
			listaSinonimosThesaurusEnUS.add(terms[i - 1].getValue());
		}
	}

	public MeaningEnUS() {
		if (listaSinonimosThesaurusEnUS == null
				|| listaSinonimosThesaurusEnUS.size() > 0) {
			listaSinonimosThesaurusEnUS = new ArrayList<String>();
		} else {
			return;
		}
	}

	public int getType() {
		return type;
	}
}
