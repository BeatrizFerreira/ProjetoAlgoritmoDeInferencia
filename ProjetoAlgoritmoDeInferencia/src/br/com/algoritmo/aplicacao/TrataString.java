package br.com.algoritmo.aplicacao;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import br.com.algoritmo.extracao.desktop.En_US.MeaningEnUS;
import br.com.algoritmo.extracao.desktop.En_US.ThesaurusEnUS;
import br.com.algoritmo.extracao.desktop.En_US.WordNotFoundExceptionEnUS;
import br.com.algoritmo.extracao.desktop.Pt_BR.MeaningPtBR;
import br.com.algoritmo.extracao.desktop.Pt_BR.ThesaurusPtBR;
import br.com.algoritmo.extracao.desktop.Pt_BR.WordNotFoundExceptionPtBR;
import br.com.algoritmo.extracao.web.SinonimosBr;
import br.com.algoritmo.extracao.web.SinonimosEn;

public class TrataString {
	
    private static String key = "LY884krlgATbK1nDgYSn";
    private static String language = "en_US";
    private static String output = "json";
    private static ArrayList<String> listaCompararBase;
    private static ArrayList<String> listaCompararDestino;
    private static ArrayList<String> listaStrSelecionadas;
    private static int valorTotalSomado = 0;
    private static int auxIListaBase;
    private static int auxJListaDestino;
    private static double valorPercentualAderende = 0.0;
	private static StopWords carrega = new StopWords();
	
	public static void main (String args []) throws IOException {
		ParserOwlJava t = new ParserOwlJava();
		t.consultaBase();
		t.consultaDestino10();
		
		
		carrega.carregaStopWords();

		TrataString trata = new TrataString();
		
		if (ParserOwlJava.listaDestino.isEmpty()){
			System.out.println("O indivíduo não possui publicação para ser comparada.");
			return;
		}

		System.err.println("TAMANHO DA LISTA BASE: "+ParserOwlJava.tamanhoListaBase);
		System.err.println("TAMANHO DA LISTA DESTINO: "+ParserOwlJava.tamanhoListaDestino);

		for (int i = 0; i< ParserOwlJava.tamanhoListaBase; i++){

			auxIListaBase = i;
			for (int j = 0; j< ParserOwlJava.tamanhoListaDestino; j++){
				System.out.println("ITEM: "+i+" LISTA BASE -- "+ ParserOwlJava.listaBase.get(i) +"");
				System.out.println("ITEM: "+j+" LISTA DESTINO -- "+ ParserOwlJava.listaDestino.get(j) +"");
				auxJListaDestino = j;
				trata.carregarSinonimosDesktop(ParserOwlJava.listaBase.get(i).toUpperCase(), ParserOwlJava.listaDestino.get(j).toUpperCase());

			}

		}

		 System.err.println("---------------------------------------------------------------------------");
		 System.err.println("VALOR TOTAL SOMADO: "+valorTotalSomado+"-----------------------------------");
		 System.err.println("-------------------------VALOR PERCENTUAL ADERENTE-------------------------");
		 valorPercentualAderende = ((double)valorTotalSomado) / (((double)ParserOwlJava.tamanhoListaBase * (double)ParserOwlJava.tamanhoListaDestino)*5) * 100;
		 DecimalFormat df = new DecimalFormat("0.00");  
		 String str = df.format(valorPercentualAderende); 
		 System.err.println("-------------------------VALOR: "+str+ " ----------------------------------");
		 System.err.println("---------------------------------------------------------------------------");
		
	}

	/**
	 * Apos a limpeza da string com a retira das informacoes desncessarias, esse metodo retorna aleatoriamente 
	 * 5 palavras dessa string, com o objetivo de serem usuadas para a construca da lista de sinonimos...
	 * cada uma das tres palavras sera utilizada para obter o sinonimo, apos adicionadas em outra lista que comporta
	 * sinonimos de cada palavra, sera uma lista pro destino e uma pro base;
	 * @param str
	 * @return
	 */
	public ArrayList<String> selecionarStr (String str){
		 listaStrSelecionadas = new ArrayList<String>();
		 ArrayList<String> listaStrTmpSelecionadas = new ArrayList<String>();
		 ArrayList<Integer> listaTmp = new ArrayList<Integer>();
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "").replace(":", "").replace(".", "");
		 StringTokenizer token = new StringTokenizer(str, " ");
	     while(token.hasMoreTokens()) {  
	            String local = token.nextToken(); 
	            listaStrTmpSelecionadas.add(local);
	    }
	     Random radom  = new Random();
	     int numeroTmp = 0;
	     if (listaStrTmpSelecionadas.size() <= 5){
	    	 listaStrSelecionadas.addAll(listaStrTmpSelecionadas);
	    	 return listaStrSelecionadas;
	     }else{
	    	 while (listaTmp.size() < 5) {
		         numeroTmp=radom.nextInt(listaStrTmpSelecionadas.size());
		         if (listaTmp.contains(numeroTmp) == false){
		        	 listaTmp.add(numeroTmp);
		        	 listaStrSelecionadas.add(listaStrTmpSelecionadas.get(numeroTmp));
		         }
		     }
		     return listaStrSelecionadas;
	     }
	}
	
	public void compararListaSinonimos (ArrayList<String> listaSinonimosBase, ArrayList<String> listaSinonimosDestino){

		int valorObtidoNaComparacao = 0;

		if(listaSinonimosBase.size() > 0 && listaSinonimosDestino.size() > 0){
			for (int i = 0; i< listaSinonimosBase.size(); i++){
				for (int j = 0; j< listaSinonimosDestino.size(); j++){
					if(listaSinonimosBase.get(i).toUpperCase().contains(listaSinonimosDestino.get(j).toUpperCase())){
						System.out.println(listaSinonimosBase.get(i) + " = " + listaSinonimosDestino.get(j));
						valorObtidoNaComparacao = 1;
						System.out.println("Valor Adquirido: "+valorObtidoNaComparacao);
						System.err.println("EQUIVALENTE "+ ParserOwlJava.listaBase.get(auxIListaBase) + " A " + ParserOwlJava.listaDestino.get(auxJListaDestino) );
						valorTotalSomado = valorTotalSomado + valorObtidoNaComparacao;
					}
				}
			}
		}else{
			System.out.println("NENHUMA CORRESPONDENCIA ENCONTRADA! VALOR 0!");
			return;
		}
	}
	/**
	 * Metodo que retorna a lista de sinonimos do Thesauros em Ingles e portugues.
	 * @param strASerTratadaBase
	 * @param strASerTratadaDestino
	 * @throws IOException 
	 */
	public void carregarSinonimosWeb(String strASerTratadaBase, String strASerTratadaDestino){
		TrataString strBase = new TrataString();
	    
	    //str.selecionarStr(str.tratarStr(strASerTratadaDestino));
	    SinonimosEn lisEnBase = new SinonimosEn();
	    SinonimosBr lisBrBase = new SinonimosBr();
	    List<String> listaCompararBaseEn = new ArrayList<String>();
	    List<String> listaCompararBaseBr = new ArrayList<String>();
	    List<String> listaCompararDestinoEn = new ArrayList<String>();
	    List<String> listaCompararDestinoBr = new ArrayList<String>();
	    listaCompararBase = new ArrayList<String>();
	    listaCompararDestino = new ArrayList<String>();
	    strBase.selecionarStr(carrega.tratarStr(strASerTratadaBase.toLowerCase()));
	
	    
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	lisEnBase.sendRequest(listaStrSelecionadas.get(i), language, key, output);
	    	if(lisEnBase.listaSinonimosEn.size() > 0){
	    		listaCompararBaseEn.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < lisEnBase.listaSinonimosEn.size(); j++){
	    		listaCompararBaseEn.add(lisEnBase.listaSinonimosEn.get(j));
	    	}
	    }
	    listaCompararBase.addAll(listaCompararBaseEn);
	    
	    System.out.println("lista de strings SELECIONADAS base: " +listaStrSelecionadas);
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	lisBrBase.listaSinonimosBr(listaStrSelecionadas.get(i));
            
	    	if(SinonimosBr.listaSinonimosBr.size() > 0){
	    		listaCompararBaseBr.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < SinonimosBr.listaSinonimosBr.size(); j++){
	    		listaCompararBaseBr.add(SinonimosBr.listaSinonimosBr.get(j));
	    	}
	    	
	    }
	    listaCompararBase.addAll(listaCompararBaseBr);
	    
	    System.out.println("LISTA DE SINONIMOS BASE INGLES/PORTUGUES: "+listaCompararBase);
	    if (listaCompararBase.isEmpty()){
        	System.out.println("LISTA DE SINONIMOS EM PT VAZIA.");
        }

	    SinonimosEn lisEnDestino = new SinonimosEn();
	    SinonimosBr lisBrDestino= new SinonimosBr();
	    TrataString strDestino = new TrataString();
	    strDestino.selecionarStr(carrega.tratarStr(strASerTratadaDestino.toLowerCase()));

	    System.out.println("lista de strings SELECIONADAS destino: " +listaStrSelecionadas);
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	
	    	lisEnDestino.sendRequest(listaStrSelecionadas.get(i), language, key, output);
	    	if(lisEnDestino.listaSinonimosEn.size() > 0){
	    		listaCompararDestinoEn.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < lisEnDestino.listaSinonimosEn.size(); j++){
	    		listaCompararDestinoEn.add(lisEnDestino.listaSinonimosEn.get(j));
	    	}
	    }
	    listaCompararDestino.addAll(listaCompararDestinoEn);
	    
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){	
	    	lisBrDestino.listaSinonimosBr(listaStrSelecionadas.get(i));
	    	if(SinonimosBr.listaSinonimosBr.size() > 0){
	    		listaCompararDestinoBr.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < SinonimosBr.listaSinonimosBr.size(); j++){
	    		listaCompararDestinoBr.add(SinonimosBr.listaSinonimosBr.get(j));
	    	}
	    }
	    listaCompararDestino.addAll(listaCompararDestinoBr);
	    
	    System.out.println("LISTA DE SINONIMOS DESTINO INGLES/PORTUGUES: "+listaCompararDestino);
	    
	    if (listaCompararDestino.isEmpty()){
        	System.out.println("LISTA DE SINONIMOS EM PT VAZIA.");
        }  
	     
	    compararListaSinonimos(listaCompararBase, listaCompararDestino);

	}
	/**
	 * Metodo que retorna a lista de sinonimos do Thesauros em Ingles e portugues.
	 * @param strASerTratadaBase
	 * @param strASerTratadaDestino
	 * @throws IOException 
	 */
	public void carregarSinonimosDesktop(String strASerTratadaBase, String strASerTratadaDestino) throws IOException{
		TrataString strBase = new TrataString();
	    ThesaurusEnUS lisEnBase = new ThesaurusEnUS();
	    ThesaurusPtBR lisBrBase = new ThesaurusPtBR();
	    List<String> listaCompararBaseEn = new ArrayList<String>();
	    List<String> listaCompararBaseBr = new ArrayList<String>();
	    @SuppressWarnings("unused")
		MeaningEnUS meaningEnUS = new MeaningEnUS();
	    @SuppressWarnings("unused")
		MeaningPtBR meaningPtBR = new MeaningPtBR();
	    List<String> listaCompararDestinoEn = new ArrayList<String>();
	    List<String> listaCompararDestinoBr = new ArrayList<String>();
	    listaCompararBase = new ArrayList<String>();
	    listaCompararDestino = new ArrayList<String>();
	    strBase.selecionarStr(carrega.tratarStr(strASerTratadaBase.toLowerCase()));
			
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	try {
				lisEnBase.getWord(listaStrSelecionadas.get(i));
			} catch (br.com.algoritmo.extracao.desktop.En_US.WordNotFoundExceptionEnUS e) {
				e.printStackTrace();
			}

	    	
	    	if(MeaningEnUS.listaSinonimosThesaurusEnUS.size() > 0){
	    		listaCompararBaseEn.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < MeaningEnUS.listaSinonimosThesaurusEnUS.size(); j++){
	    		listaCompararBaseEn.add(MeaningEnUS.listaSinonimosThesaurusEnUS.get(j));
	    	}
	    }
	    listaCompararBase.addAll(listaCompararBaseEn);
	    
	    System.out.println("lista de strings SELECIONADAS base: " +listaStrSelecionadas);
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	try {
				lisBrBase.getWord(listaStrSelecionadas.get(i));
			} catch (WordNotFoundExceptionPtBR e) {
				e.printStackTrace();
			}
            
	    	if(MeaningPtBR.listaSinonimosThesaurusPtBR.size() > 0){
	    		listaCompararBaseBr.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < MeaningPtBR.listaSinonimosThesaurusPtBR.size(); j++){
	    		listaCompararBaseBr.add(MeaningPtBR.listaSinonimosThesaurusPtBR.get(j));
	    	}
	    	
	    }
	    listaCompararBase.addAll(listaCompararBaseBr);
	    
	    System.out.println("LISTA DE SINONIMOS BASE INGLES/PORTUGUES: "+listaCompararBase);
	    if (listaCompararBase.isEmpty()){
        	System.out.println("LISTA DE SINONIMOS EM PT VAZIA.");
        }

	    TrataString strDestino = new TrataString();
	    ThesaurusEnUS lisEnDestino = new ThesaurusEnUS();
	    ThesaurusPtBR lisBrDestino = new ThesaurusPtBR();
	    
	    strDestino.selecionarStr(carrega.tratarStr(strASerTratadaDestino.toLowerCase()));
	    System.out.println("lista de strings SELECIONADAS destino: " +listaStrSelecionadas);
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	try {
				lisEnDestino.getWord(listaStrSelecionadas.get(i));
			} catch (WordNotFoundExceptionEnUS e) {
				e.printStackTrace();
			}
	    	
	    	if(MeaningEnUS.listaSinonimosThesaurusEnUS.size() > 0){
	    		listaCompararDestinoEn.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < MeaningEnUS.listaSinonimosThesaurusEnUS.size(); j++){
	    		listaCompararDestinoEn.add(MeaningEnUS.listaSinonimosThesaurusEnUS.get(j));
	    	}
	    }
	    listaCompararDestino.addAll(listaCompararDestinoEn);
	    
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){	
	    	try {
				lisBrDestino.getWord(listaStrSelecionadas.get(i));
			} catch (WordNotFoundExceptionPtBR e) {
				e.printStackTrace();
			}
	    	
	    	if(MeaningPtBR.listaSinonimosThesaurusPtBR.size() > 0){
	    		listaCompararDestinoBr.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < MeaningPtBR.listaSinonimosThesaurusPtBR.size(); j++){
	    		listaCompararDestinoBr.add(MeaningPtBR.listaSinonimosThesaurusPtBR.get(j));
	    	}
	    }
	    listaCompararDestino.addAll(listaCompararDestinoBr);
	    
	    System.out.println("LISTA DE SINONIMOS DESTINO INGLES/PORTUGUES: "+listaCompararDestino);
	    
	    if (listaCompararDestino.isEmpty()){
        	System.out.println("LISTA DE SINONIMOS EM PT VAZIA.");
        }  
	     
	    compararListaSinonimos(listaCompararBase, listaCompararDestino);
	}
}