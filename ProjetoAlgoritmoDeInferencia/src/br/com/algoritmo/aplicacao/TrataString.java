package br.com.algoritmo.aplicacao;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static String language = "en_US";
    private static String output = "json";
    private static ArrayList<String> listaCompararBase;
    private static ArrayList<String> listaCompararDestino;
    private static ArrayList<String> listaSinonimoBase;
    private static ArrayList<String> listaSinonimoDestino;
    private static ArrayList<String> listaStrSelecionadas;
    private static int valorTotalSomado = 0;
    private static int auxIListaBase;
    private static int auxJListaDestino;
    ArrayList<Double> listaValores = new ArrayList<Double>();
	private static StopWords carrega = new StopWords();
	private static ArrayList<String> listaBase;
	private static ArrayList<String> listaDestino;
	private static Map<String, ArrayList<String>> equivalencias = new HashMap<String, ArrayList<String>>();
	private static ArrayList<String> listaEq;
	
	public TrataString(){}
	
	public int getQuantidadeProducoesAderentes(){
		return valorTotalSomado;
	}
	
	public ArrayList<Double> getValoresPercentuaisAderencia(){
		return listaValores;
	}
	
	public Map<String, ArrayList<String>> getEquivalencias(){
		return equivalencias;
	}

	/**
     * Deverá calcular o fator de aderência e 
    	retornar pares (fator de aderencia, lista de produções bibliográficas similares).
	 * @throws IOException 
	 */
	
	public void calculaFatorAderencia(String nome_base, ArrayList<String> nome_cvs_destinos) throws IOException{
		ParserOwlJava parse = new ParserOwlJava();
		listaBase = parse.consultaProducaoBibliografica(nome_base);
		// carregar lista destino em um loop com o array passado
		
		// carrega o array de palavras do StopWords
		carrega.carregaStopWords();
		
		if(listaBase.isEmpty()){
			System.err.println("Nao ha como realizar comparacao. Lista base vazia.");
			return;
		}
		
		if(nome_cvs_destinos.isEmpty()){
			System.err.println("Nao ha perfis destinos para serem comparados.");
			return;
		}
		
		double valorPercentualAderende = 0.0;
		//d de destino (cv destino)
		for(int d = 0; d < nome_cvs_destinos.size(); d++){
			listaDestino = parse.consultaProducaoBibliografica(nome_cvs_destinos.get(d));
			if (listaDestino.isEmpty()){
				System.err.println("Lista destino " + nome_cvs_destinos.get(d) + " esta vazia.");
				continue;
			}
			// agora compara a lista base com a lista destino do individuo 'd'
			comparaListasBaseDestino();
			
			// calcular o valor do percentual de aderencia e inserir na lista de valores 
			valorPercentualAderende = ((double)valorTotalSomado) / (((double)listaBase.size() * (double)listaDestino.size())*5) * 100;
			listaValores.add(valorPercentualAderende);
		}
	}
	
	
	private static void comparaListasBaseDestino(){
		System.out.println("TAMANHO DA LISTA BASE: "+ listaBase.size());
		System.out.println("TAMANHO DA LISTA DESTINO: "+ listaDestino.size());
		
		for (int i = 0; i< listaBase.size(); i++){
			auxIListaBase = i;
			listaEq = new ArrayList<String>();
			for (int j = 0; j< listaDestino.size(); j++){
				System.out.println("ITEM: "+i+" LISTA BASE -- "+ listaBase.get(i) +"");
				System.out.println("ITEM: "+j+" LISTA DESTINO -- "+ listaDestino.get(j) +"");
				auxJListaDestino = j;
				carregarSinonimosDesktop(listaBase.get(i).toUpperCase(), listaDestino.get(j).toUpperCase());				
			}
			equivalencias.put(listaBase.get(i), listaEq);
		}
		
	}
	
	/**
	 * Limpar a string do nome do artigo, para a retirada de elementos semanticamente inuteis;
	 * @param str
	 * @return
	 */
	public String tratarStr (String str){
		 String strAux = "";
		 str = str.replace("-", " ");
		 String[] prep = {"CURSO", "UNIVERSIDADE", "GRADUAÇÃO", "AU", "TO", "AND", "BSC", "MSC", "DR", "UM", "DOS", "FOR", "AT", "E", "A", "O", "DA", "DE", "NA", "A", "AN", "OS", "ON", "IN", "THE", "DO", "OF", "WITH", "AS", "PARA", "COM", "NO", "EM"}; //Lista de termos para serem retirados da string original do titulo do artigo

		 for (int i = 0; i < prep.length; i++) {
			 
			 StringTokenizer token = new StringTokenizer(str, " ");
		     
		     if(prep[i].contains(token.nextToken())){
		    	 strAux = prep[i].toString();
		    	  if(str.contains(strAux)){
		    		    str = " "+str;
			  	    	str = str.replaceAll(" "+prep[i].toString()+ " ", " ");
			  			
			  	    	}
		          } 
		     str = str.replaceAll(" " +prep[i].toString()+ " ", " ");
		     }
		return str.trim();
	}
	/**
	 * A string eh quebrada para facilitar o calculo randomico das palavras que vao compor a lista de sinonimos
	 * @param str
	 * @return
	 */
	public ArrayList<String> quebrarStrBase (String str){
		 listaSinonimoBase = new ArrayList<String>();
		    
		 StringTokenizer token = new StringTokenizer(str, ",");
	     
	     while(token.hasMoreTokens()) {  
	            String local = token.nextToken();  
	            listaSinonimoBase.add(local);
	            //System.out.println(local);  
	    } 
	     return listaSinonimoBase;
	}
	
	public ArrayList<String> quebrarStrDestino (String str){
		 listaSinonimoDestino = new ArrayList<String>();;
		 StringTokenizer token = new StringTokenizer(str, " ");
	     while(token.hasMoreTokens()) {
	            String local = token.nextToken();  
	            listaSinonimoDestino.add(local);  
	    } 
	     return listaSinonimoDestino;
	}
	/**
	 * Apos a limpeza da string com a retira das informacoes desncessarias, esse metodo retorna aleatoriamente 
	 * 3 palavras dessa string, com o objetivo de serem usuadas para a construca da lista de sinonimos...
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
	
	public static boolean compararListaSinonimos (ArrayList<String> listaSinonimosBase, ArrayList<String> listaSinonimosDestino){

		//int valorObtidoNaComparacao = 0;
		boolean saoEquivalentes = false;

		if(listaSinonimosBase.size() > 0 && listaSinonimosDestino.size() > 0){
			for (int i = 0; i< listaSinonimosBase.size(); i++){
				for (int j = 0; j< listaSinonimosDestino.size(); j++){
					if(listaSinonimosBase.get(i).toUpperCase().equalsIgnoreCase(listaSinonimosDestino.get(j).toUpperCase())){
						System.out.println(listaSinonimosBase.get(i) + " = " + listaSinonimosDestino.get(j));
						//valorObtidoNaComparacao = 1;
						//System.out.println("Valor Adquirido: "+valorObtidoNaComparacao);
						System.err.println("EQUIVALENTE "+ listaBase.get(auxIListaBase) + " A " + listaDestino.get(auxJListaDestino) );
						valorTotalSomado += 1;
						saoEquivalentes = true;
					}
				}
			}
		}else{
			System.out.println("NENHUMA CORRESPONDENCIA ENCONTRADA! VALOR 0!");
		}
		return saoEquivalentes;
	}
	/**
	 * Metodo que retorna a lista de sinonimos do Thesauros em Ingles e portugues.
	 * @param strASerTratadaBase
	 * @param strASerTratadaDestino
	 * @throws IOException 
	 */
	public void carregarSinonimosWeb(String strASerTratadaBase, String strASerTratadaDestino){
		TrataString strBase = new TrataString();
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
	    	lisEnBase.sendRequest(listaStrSelecionadas.get(i), language, "lMsJYeVm6u7rAH05pX2w", output);
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
	    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaDestino)).get(i));
	    	lisEnDestino.sendRequest(listaStrSelecionadas.get(i), language, "lMsJYeVm6u7rAH05pX2w", output);
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
	public static void carregarSinonimosDesktop(String strASerTratadaBase, String strASerTratadaDestino){
		TrataString strBase = new TrataString();
	    ThesaurusEnUS lisEnBase = new ThesaurusEnUS();
	    ThesaurusPtBR lisBrBase = new ThesaurusPtBR();
	    List<String> listaCompararBaseEn = new ArrayList<String>();
	    List<String> listaCompararBaseBr = new ArrayList<String>();
	    List<String> listaCompararDestinoEn = new ArrayList<String>();
	    List<String> listaCompararDestinoBr = new ArrayList<String>();
	    MeaningEnUS meaningEnUS = new MeaningEnUS();
	    MeaningPtBR meaningPtBR = new MeaningPtBR();
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
	    
	    if (compararListaSinonimos(listaCompararBase, listaCompararDestino)){
	    	listaEq.add(strASerTratadaDestino);
	    	
	    }
	    //compararListaSinonimos(listaCompararBase, listaCompararDestino);
	}
}