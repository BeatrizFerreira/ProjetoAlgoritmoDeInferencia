package br.com.algoritmo.aplicacao;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.ibm.icu.text.DecimalFormat;

import br.com.algoritmo.extracao.desktop.En_US.MeaningEnUS;
import br.com.algoritmo.extracao.desktop.En_US.ThesaurusEnUS;
import br.com.algoritmo.extracao.desktop.En_US.WordNotFoundExceptionEnUS;
import br.com.algoritmo.extracao.desktop.Pt_BR.MeaningPtBR;
import br.com.algoritmo.extracao.desktop.Pt_BR.ThesaurusPtBR;
import br.com.algoritmo.extracao.desktop.Pt_BR.WordNotFoundExceptionPtBR;
import br.com.algoritmo.extracao.web.SinonimosBr;
import br.com.algoritmo.extracao.web.SinonimosEn;

public class TrataString {
	
    //private static String key = "LY884krlgATbK1nDgYSn";
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
    private static double valorPercentualAderende = 0.0;
    private static ArrayList<Double> listaValor = new ArrayList<Double>();
	private static StopWords carrega = new StopWords();
	
	public static void main (String args []) throws IOException {
		ParserOwlJava t = new ParserOwlJava();
		carrega.carregaStopWords();
		t.consultaBase();
		t.consultaDestino10();
		/*for (int iSelecionaConsultaDestino = 17; iSelecionaConsultaDestino <= 17;){
		switch (iSelecionaConsultaDestino){
		case 1: iSelecionaConsultaDestino = 17;
			t.consultaDestino17();
			break;
		case 2: iSelecionaConsultaDestino = 18;
			t.consultaDestino18();
			break;
		
		case 3: iSelecionaConsultaDestino = 19;
			t.consultaDestino19();
			break;
		
		case 4: iSelecionaConsultaDestino = 20;
			t.consultaDestino20();
			break;
		
		case 5: iSelecionaConsultaDestino = 21;
			t.consultaDestino21();
			break;
		
		case 6: iSelecionaConsultaDestino = 22;
			t.consultaDestino22();
			break;
		
		case 7: iSelecionaConsultaDestino = 23;
			t.consultaDestino23();
			break;
		
		case 8: iSelecionaConsultaDestino = 24;
			t.consultaDestino24();
			break;
		
		case 9: iSelecionaConsultaDestino = 25;
			t.consultaDestino25();
			break;
			
		case 10: iSelecionaConsultaDestino = 26;
			t.consultaDestino26();
			break;
			
		case 11: iSelecionaConsultaDestino = 27;
			t.consultaDestino27();
			break;
			
		case 12: iSelecionaConsultaDestino = 28;
			t.consultaDestino28();
			break;
			
		case 13: iSelecionaConsultaDestino = 29;
			t.consultaDestino29();
			break;
			
		case 14: iSelecionaConsultaDestino = 30;
			t.consultaDestino30();
			break;
			
		case 15: iSelecionaConsultaDestino = 31;
			t.consultaDestino31();
			break;
			
		case 16: iSelecionaConsultaDestino = 32;
			t.consultaDestino32();
			break;
			
		case 17: iSelecionaConsultaDestino = 33;
			t.consultaDestino33();
			break;
			
		case 18: iSelecionaConsultaDestino = 34;
			t.consultaDestino34();
			break;
			
		}*/

		
/*		int controlePosicaoVetorBase = 0;
		int controlePosicaoVetorDestino = 0;*/
		TrataString trata = new TrataString();
		
		if (ParserOwlJava.listaDestino.isEmpty()){
			System.out.println("O indivíduo não possui publicação para ser comparada.");
			return;
		}
		
		/*System.err.println("INDIVÍDUO BASE: "+ParserOwlJava.listaBase.get(0));
		System.err.println("INDIVÍDUO DESTINO 1: "+ParserOwlJava.listaDestino.get(0));*/
		System.err.println("TAMANHO DA LISTA BASE: "+ParserOwlJava.tamanhoListaBase);
		System.err.println("TAMANHO DA LISTA DESTINO: "+ParserOwlJava.tamanhoListaDestino);
		
/*		System.out.println("LISTA BASE: "+ParserOwlJava.listaBase.get(1));
		System.out.println("LISTA DESTINO: "+ParserOwlJava.listaDestino.get(1));*/
		//trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(1).toUpperCase(), ParserOwlJava.listaDestino.get(1).toUpperCase());
		//trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(2).toUpperCase(), ParserOwlJava.listaDestino.get(2).toUpperCase());

		for (int i = 0; i< ParserOwlJava.tamanhoListaBase; i++){
			//System.out.println(t.consultaBase().get(controlePosicaoVetorBase+2));
			//System.out.println("CONTROLE VETOR BASE: "+controlePosicaoVetorBase+2);
			auxIListaBase = i;
			for (int j = 0; j< ParserOwlJava.tamanhoListaDestino; j++){
				System.out.println("ITEM: "+i+" LISTA BASE -- "+ ParserOwlJava.listaBase.get(i) +"");
				System.out.println("ITEM: "+j+" LISTA DESTINO -- "+ ParserOwlJava.listaDestino.get(j) +"");
				//System.out.println(ParserOwlJava.listaBase.get(3+controlePosicaoVetorBase));
				auxJListaDestino = j;
				trata.carregarSinonimosDesktop(ParserOwlJava.listaBase.get(i).toUpperCase(), ParserOwlJava.listaDestino.get(j).toUpperCase());
				//System.out.println(Teste2.listaBase.get(2+controlePosicaoVetorBase));
				//controlePosicaoVetorDestino = controlePosicaoVetorDestino + 9;
				
			}

			//controlePosicaoVetorDestino = 0;
			//controlePosicaoVetorBase = controlePosicaoVetorBase + 9;
		}
		 //controlePosicaoVetorDestino = 0;
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
		 valorPercentualAderende = ((double)valorTotalSomado) / (((double)ParserOwlJava.tamanhoListaBase * (double)ParserOwlJava.tamanhoListaDestino)*5) * 100;
		 DecimalFormat df = new DecimalFormat("0.00");  
		 String valorPercentualAderendeFormatado = df.format(valorPercentualAderende); 
		 System.err.println("-------------------------VALOR: " + valorPercentualAderende);
		 System.err.println("---------------------------------------------------------------------------");
		 listaValor.add(valorPercentualAderende);
		 //iSelecionaConsultaDestino ++;
		//}
		 System.out.println("LISTA FINAL DE VALORES: "+listaValor);
		
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
	     if (listaStrTmpSelecionadas.size() <= 5){
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
					if(listaSinonimosBase.get(i).toUpperCase().equalsIgnoreCase(listaSinonimosDestino.get(j).toUpperCase())){
						System.out.println(listaSinonimosBase.get(i) + " = " + listaSinonimosDestino.get(j));
						valorObtidoNaComparacao = 1;
						System.out.println("Valor Adquirido: "+valorObtidoNaComparacao);
						System.err.println("EQUIVALENTE "+ ParserOwlJava.listaBase.get(auxIListaBase) + " A " + ParserOwlJava.listaDestino.get(auxJListaDestino) );
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
			
/*			for (int i = 0; i<listaStrSelecionadas.size(); i++){
		    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaBase)).get(i));

				lisBr.listaSinonimosBr(listaStrSelecionadas.get(i));
		    	if(SinonimosBr.listaSinonimosBr.size() > 0)
		    		listaCompararBase.add(listaStrSelecionadas.get(i).toUpperCase());
		    	listaCompararBase.addAll(SinonimosBr.listaSinonimosBr);
		    	//listaCompararBase.add("/");//caractere para separar os nomes raizes com seus sinonimos		    	
		    }*/
	
	    
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaBase)).get(i));
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
	    //System.out.println("LISTA COMPARA BASE: "+listaCompararBase);
	    
	    System.out.println("LISTA DE SINONIMOS BASE INGLES/PORTUGUES: "+listaCompararBase);
	    if (listaCompararBase.isEmpty()){
        	System.out.println("LISTA DE SINONIMOS EM PT VAZIA.");
        }
/*	    if(strASerTratadaDestino.contains("-"))
	    	strASerTratadaDestino = strASerTratadaDestino.replace("PROBLEM-SOLVING AND QUANTUM COMPUTATION", "PROBLEM SOLVING QUANTUM COMPUTATION");
	    */
	    //System.out.println(strASerTratadaDestino.toUpperCase());
	    SinonimosEn lisEnDestino = new SinonimosEn();
	    SinonimosBr lisBrDestino= new SinonimosBr();
	    TrataString strDestino = new TrataString();
	    strDestino.selecionarStr(carrega.tratarStr(strASerTratadaDestino.toLowerCase()));
	    //System.out.println(listaStrSelecionadas.size());
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
	    
	    //str.compararListaSinonimos(listaCompararBase, listaCompararDestino);
	    //System.out.println(str.tratarStr(strASerTratadaDestino));
	    //str.quebrarStrDestino(str.tratarStr(strASerTratadaDestino));
	    
	    //str.compararArraysSeparadado(str.quebrarStrBase(str.tratarStr(strASerTratadaBase)), str.quebrarStrDestino(str.tratarStr(strASerTratadaDestino)));

	}
	/**
	 * Metodo que retorna a lista de sinonimos do Thesauros em Ingles e portugues.
	 * @param strASerTratadaBase
	 * @param strASerTratadaDestino
	 * @throws IOException 
	 */
	public void carregarSinonimosDesktop(String strASerTratadaBase, String strASerTratadaDestino){
		TrataString strBase = new TrataString();
	    ThesaurusEnUS lisEnBase = new ThesaurusEnUS();
	    ThesaurusPtBR lisBrBase = new ThesaurusPtBR();
	    List<String> listaCompararBaseEn = new ArrayList<String>();
	    List<String> listaCompararBaseBr = new ArrayList<String>();
	    MeaningEnUS meaningEnUS = new MeaningEnUS();
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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