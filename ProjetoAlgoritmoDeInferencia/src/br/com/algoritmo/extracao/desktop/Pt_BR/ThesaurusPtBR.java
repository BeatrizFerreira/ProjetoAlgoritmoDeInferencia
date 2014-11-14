package br.com.algoritmo.extracao.desktop.Pt_BR;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

/**
 * A Thesaurus built using OpenOffice.org's Thesaurus index and data files.
 * <P>
 * These files are usually called something like 'th_en_US_new.idx' for the
 * index and 'th_en_US_new.dat' for the data.
 * </P>
 */
public class ThesaurusPtBR {
	private HashMap<String, Integer> index;
	private RandomAccessFile data;

	public ThesaurusPtBR() {
		this("resources/th_pt_BR.idx", "resources/th_pt_BR.dat");
	}

	public ThesaurusPtBR(String indexFile, String dataFile) {
		index = new HashMap<String, Integer>();

		// open the files
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(indexFile));
			loadIndex(br);
			data = new RandomAccessFile(dataFile, "r");
			//br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void loadIndex(BufferedReader br) {
		String line;
		String[] segments;
		try {
			while ((line = br.readLine()) != null) {
				segments = line.split("\\|");
				// only consider the actual index lines
				if (segments.length == 2) {
					index.put(segments[0], Integer.parseInt(segments[1]));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WordPtBR getWord(String w) throws WordNotFoundExceptionPtBR {
		// find the word in the index
		Integer offset = index.get(w);
		if (offset == null) {
			// the word does not exist in the thesaurus
			// throw new WordNotFoundException(w);
			MeaningPtBR meaningPtBR = new MeaningPtBR();
			return null;
		}

		// find the word in the data
		try {
			data.seek(offset);
			String line = data.readLine();
			String[] segments = line.split("\\|");
			int meaningCount = Integer.parseInt(segments[1]);
			WordPtBR word = new WordPtBR(segments[0], meaningCount);

			// read in all the meanings
			for (int i = 0; i < meaningCount; i++) {

				word.addMeaning(data.readLine());

			}
			data.close();
			return word;
		} catch (IOException e) {
			e.getMessage();
		}

		// something fucked up... this should never happen
		return null;
	}

}