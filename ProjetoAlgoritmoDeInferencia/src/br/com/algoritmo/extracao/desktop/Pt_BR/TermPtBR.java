package br.com.algoritmo.extracao.desktop.Pt_BR;

import java.util.ArrayList;
import java.util.Arrays;

public class TermPtBR {
	public static enum TYPE {
		NORMAL, GENERIC, SIMILAR, RELATED, ANTONYM
	};

	private String value;
	private TYPE type;

	public TermPtBR(String segment) {
		String[] pieces = segment.split("\\)");
		ArrayList<String> l = new ArrayList<String>(Arrays.asList(pieces));
		l.remove(0);
		value = l.get(0).trim();
		// set the type
		if (l.size() > 1) {
			if (l.get(0).startsWith("generic")) {
				type = TYPE.GENERIC;
			} else if (l.get(0).startsWith("similar")) {
				type = TYPE.SIMILAR;
			} else if (l.get(0).startsWith("related")) {
				type = TYPE.RELATED;
			} else if (l.get(0).startsWith("antonym")) {
				type = TYPE.ANTONYM;
			} else {
				System.out.println("PROBLEM with " + value + " > " + pieces[1]);
			}
		} else {
			type = TYPE.NORMAL;
		}
	}

	public String getValue() {
		return value;
	}

	public TYPE getType() {
		return type;
	}
}
