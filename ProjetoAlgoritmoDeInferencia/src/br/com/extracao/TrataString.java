package br.com.extracao;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class TrataString {
	
    private static String key = "LY884krlgATbK1nDgYSn";
    private static String language = "en_US";
    private static String output = "json";
    private static ArrayList<String> listaCompararBase;
    private static ArrayList<String> listaCompararDestino;
    private static ArrayList<String> listaSinonimoBase;
    private static ArrayList<String> listaSinonimoDestino;
    private static ArrayList<String> listaStrSelecionadas;
    private static int valorTotalSomado = 0;
    private static double valorPercentualAderende = 0.0;
	
	public static void main (String args []) {
		ParserOwlJava t = new ParserOwlJava();
		t.consultaBase();
		t.consultaDestino1();
		
		int controlePosicaoVetorBase = 0;
		int controlePosicaoVetorDestino = 0;
		TrataString trata = new TrataString();
		
		if (ParserOwlJava.listaDestino.isEmpty()){
			System.out.println("O indivíduo não possui publicação para ser comparada.");
			return;
		}
		
		System.err.println("INDIVÍDUO BASE: "+ParserOwlJava.listaBase.get(0));
		System.err.println("INDIVÍDUO DESTINO 1: "+ParserOwlJava.listaDestino.get(0));
		System.err.println("TAMANHO DA LISTA BASE: "+ParserOwlJava.tamanhoListaBase);
		System.err.println("TAMANHO DA LISTA DESTINO: "+ParserOwlJava.tamanhoListaDestino);
		
		System.out.println("LISTA BASE: "+ParserOwlJava.listaBase.get(1));
		System.out.println("LISTA DESTINO: "+ParserOwlJava.listaDestino.get(1));
		trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(1).toUpperCase(), ParserOwlJava.listaDestino.get(1).toUpperCase());
		trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(2).toUpperCase(), ParserOwlJava.listaDestino.get(2).toUpperCase());

		for (int i = 0; i< ParserOwlJava.tamanhoListaBase; i++){
			//System.out.println(Teste2.listaBase.get(2+controlePosicaoVetorBase));
			//System.out.println(t.consultaBase().get(controlePosicaoVetorBase+2));
			//System.out.println("CONTROLE VETOR BASE: "+controlePosicaoVetorBase+2);
			for (int j = 0; j< ParserOwlJava.tamanhoListaDestino; j++){
				//System.out.println(ParserOwlJava.listaBase.get(3+controlePosicaoVetorBase));
				trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(3+controlePosicaoVetorBase).toUpperCase(), ParserOwlJava.listaDestino.get(3+controlePosicaoVetorDestino).toUpperCase());
				//System.out.println(Teste2.listaBase.get(2+controlePosicaoVetorBase));
				controlePosicaoVetorDestino = controlePosicaoVetorDestino + 9;
			}
			controlePosicaoVetorDestino = 0;
			controlePosicaoVetorBase = controlePosicaoVetorBase + 9;
		}
		 controlePosicaoVetorDestino = 0;
/*		for (int i = 1; i<=Teste2.tamanhoListaDestino; i++){
//			/System.out.println(t.consultaDestino1().get(controlePosicaoVetorDestino+2));
			for (int j = 1; j<=Teste2.tamanhoListaBase; j++){
				//System.out.println(t.consultaBase().get(controlePosicaoVetorBase+2));
				trata.tratarString(Teste2.listaDestino.get(2+controlePosicaoVetorDestino).toUpperCase(), Teste2.listaBase.get(2+controlePosicaoVetorBase).toUpperCase());
				System.out.println(Teste2.listaDestino.get(2+controlePosicaoVetorDestino));
				controlePosicaoVetorBase = controlePosicaoVetorBase + 8;
			}
			controlePosicaoVetorBase = 0;
			controlePosicaoVetorDestino = controlePosicaoVetorDestino + 8;
		}*/
		 System.err.println("---------------------------------------------------------------------------");
		 System.err.println("VALOR TOTAL SOMADO: "+valorTotalSomado);
		 System.err.println("-------------------------VALOR PERCENTUAL ADERENTE-------------------------");
		 System.err.println("-------------------------INDIVÍDUO BASE: "+ParserOwlJava.listaBase.get(0));
		 System.err.println("-------------------------INDIVÍDUO DESTINO 1: "+ParserOwlJava.listaDestino.get(0));
		 valorPercentualAderende = (((double)ParserOwlJava.tamanhoListaBase * (double)ParserOwlJava.tamanhoListaDestino) / ((double)valorTotalSomado)) * 100;
		 System.err.println("-------------------------VALOR: " + valorPercentualAderende);
		 System.err.println("---------------------------------------------------------------------------");
	}
	/**
	 * Limpar a string do nome do artigo, para a retirada de elementos semanticamente inuteis;
	 * @param str
	 * @return
	 */
	public String tratarStr (String str){
		 //System.out.println("Antes: "+str);
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
	    //System.out.println("Depois: "+str);
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
	     //System.out.println("Nomes cadastrados: " + token.countTokens());
	     
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
	     //System.out.println("Nomes cadastrados: " + token.countTokens());
	     
	     while(token.hasMoreTokens()) {  
	            String local = token.nextToken();  
	            listaSinonimoDestino.add(local);
	            //System.out.println(local);  
	    } 
	     return listaSinonimoDestino;
	}
	public void compararArraysSeparadado (ArrayList<String> listaStrBase, ArrayList<String> listaStrDestino){
		for (String v : listaStrBase) {
		    if (listaStrDestino.contains(v)) {
		        //System.out.println("Itens iguais: " +v);
		    } else {
		        //System.out.println();
		    }
		}
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
	     if (listaStrTmpSelecionadas.size() <= 4){
	    	 listaStrSelecionadas.addAll(listaStrTmpSelecionadas);
	    	 //System.out.println("LISTA DE PALAVRAS SELECIONADAS: " +listaStrSelecionadas);
	    	 return listaStrSelecionadas;
	     }else{
	    	 while (listaTmp.size() < 5) {
		         numeroTmp=radom.nextInt(listaStrTmpSelecionadas.size());
		         if (listaTmp.contains(numeroTmp) == false){
		        	 listaTmp.add(numeroTmp);
		        	 //System.out.println(listaStrTmpSelecionadas.get(numeroTmp));
		        	 listaStrSelecionadas.add(listaStrTmpSelecionadas.get(numeroTmp));
		         }
		     }
	    	 //System.out.println("LISTA DE PALAVRAS SELECIONADAS: " +listaStrSelecionadas);
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
						valorObtidoNaComparacao = 5;
						System.out.println("Valor Adquirido: "+valorObtidoNaComparacao);
						valorTotalSomado = valorTotalSomado + valorObtidoNaComparacao;
					}
				}
			}
/*			for (int i = 0; i< listaSinonimosDestino.size(); i++){
				for (int j = 0; j< listaSinonimosBase.size(); j++){
					if(listaSinonimosDestino.get(i).toUpperCase().contains(listaSinonimosBase.get(j).toUpperCase())){
						System.out.println(listaSinonimosDestino.get(i) + " = " + listaSinonimosBase.get(j));
						valorObtidoNaComparacao = 5;
					}
					}
				}*/
		}else{
			return;
		}
	}
	/**
	 * Metodo que retorna a lista de sinonimos do Thesauros em Ingles.
	 * @param strASerTratadaBase
	 * @param strASerTratadaDestino
	 * @throws IOException 
	 */
	public void selecionarSinonimosThesaurosEn(String strASerTratadaBase, String strASerTratadaDestino){
		TrataString strBase = new TrataString();
	    
	    //str.selecionarStr(str.tratarStr(strASerTratadaDestino));
	    SinonimosEn tbase = new SinonimosEn();
	    List<String> listaCompararBaseEn = new ArrayList<String>();
	    List<String> listaCompararBaseBr = new ArrayList<String>();
	    List<String> listaCompararDestinoEn = new ArrayList<String>();
	    List<String> listaCompararDestinoBr = new ArrayList<String>();
	    listaCompararBase = new ArrayList<String>();
	    listaCompararDestino = new ArrayList<String>();
	    strBase.selecionarStr(strBase.tratarStr(strASerTratadaBase.toUpperCase()));
			
/*			for (int i = 0; i<listaStrSelecionadas.size(); i++){
		    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaBase)).get(i));

				lisBr.listaSinonimosBr(listaStrSelecionadas.get(i));
		    	if(SinonimosBr.listaSinonimosBr.size() > 0)
		    		listaCompararBase.add(listaStrSelecionadas.get(i).toUpperCase());
		    	listaCompararBase.addAll(SinonimosBr.listaSinonimosBr);
		    	//listaCompararBase.add("/");//caractere para separar os nomes raizes com seus sinonimos		    	
		    }*/
	    SinonimosBr lisBrBase = new SinonimosBr();
	    
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaBase)).get(i));
	    	tbase.sendRequest(listaStrSelecionadas.get(i), language, key, output);
	    	if(SinonimosEn.listaSinonimosEn.size() > 0){
	    		listaCompararBaseEn.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < SinonimosEn.listaSinonimosEn.size(); j++){
	    		listaCompararBaseEn.add(SinonimosEn.listaSinonimosEn.get(j));
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
	    //System.out.println("LISTA COMPARA BASE: "+listaCompararBase);
	    System.out.println("LISTA DE SINONIMOS BASE INGLES/PORTUGUES: "+listaCompararBase);
	    
/*	    if(strASerTratadaDestino.contains("-"))
	    	strASerTratadaDestino = strASerTratadaDestino.replace("PROBLEM-SOLVING AND QUANTUM COMPUTATION", "PROBLEM SOLVING QUANTUM COMPUTATION");
	    */
	    //System.out.println(strASerTratadaDestino.toUpperCase());
	    SinonimosEn tdestino = new SinonimosEn();
	    SinonimosBr lisBrDestino= new SinonimosBr();
	    TrataString strDestino = new TrataString();
	    strDestino.selecionarStr(strDestino.tratarStr(strASerTratadaDestino.toUpperCase()));
	    //System.out.println(listaStrSelecionadas.size());
	    System.out.println("lista de strings SELECIONADAS destino: " +listaStrSelecionadas);
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaDestino)).get(i));
	    	tdestino.sendRequest(listaStrSelecionadas.get(i), language, key, output);
	    	if(SinonimosEn.listaSinonimosEn.size() > 0){
	    		listaCompararDestinoEn.add(listaStrSelecionadas.get(i).toUpperCase());
	    	}
	    	for (int j = 0; j < SinonimosEn.listaSinonimosEn.size(); j++){
	    		listaCompararDestinoEn.add(SinonimosEn.listaSinonimosEn.get(j));
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
	    
	    compararListaSinonimos(listaCompararBase, listaCompararDestino);
	    
	    //str.compararListaSinonimos(listaCompararBase, listaCompararDestino);
	    //System.out.println(str.tratarStr(strASerTratadaDestino));
	    //str.quebrarStrDestino(str.tratarStr(strASerTratadaDestino));
	    
	    //str.compararArraysSeparadado(str.quebrarStrBase(str.tratarStr(strASerTratadaBase)), str.quebrarStrDestino(str.tratarStr(strASerTratadaDestino)));

	}
}