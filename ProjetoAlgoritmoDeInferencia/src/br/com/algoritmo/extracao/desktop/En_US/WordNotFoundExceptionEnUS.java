package br.com.algoritmo.extracao.desktop.En_US;

public class WordNotFoundExceptionEnUS extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WordNotFoundExceptionEnUS(String word) {
		super("Word '" + word + "' not found!");
	}
}
