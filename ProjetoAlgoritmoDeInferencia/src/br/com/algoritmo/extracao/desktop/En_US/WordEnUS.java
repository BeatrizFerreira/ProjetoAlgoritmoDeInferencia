package br.com.algoritmo.extracao.desktop.En_US;

import java.util.*;

public class WordEnUS {
	private MeaningEnUS[] meanings;
	@SuppressWarnings("rawtypes")
	private ArrayList[] dumbSynonyms;
	private int count;

	public WordEnUS(String value, int numMeanings) {
		meanings = new MeaningEnUS[numMeanings];
		dumbSynonyms = new ArrayList[5];
		for (int i = 0; i < 5; i++) {
			dumbSynonyms[i] = new ArrayList<String>();
		}
		count = 0;
	}

	public void addMeaning(String line) {
		meanings[count] = new MeaningEnUS(line, dumbSynonyms);
		count++;
	}
}
