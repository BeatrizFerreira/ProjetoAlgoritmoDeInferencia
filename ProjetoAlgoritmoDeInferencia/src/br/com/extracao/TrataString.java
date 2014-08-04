package br.com.extracao;
import java.util.ArrayList;
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
	
	public static void main (String args []) {
		ParserOwlJava t = new ParserOwlJava();
		t.consultaBase();
		t.consultaDestino1();
		int controlePosicaoVetorBase = 0;
		int controlePosicaoVetorDestino = 0;
		TrataString trata = new TrataString();
		
		trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(0).toUpperCase(), ParserOwlJava.listaDestino.get(0).toUpperCase());
		trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(1).toUpperCase(), ParserOwlJava.listaDestino.get(1).toUpperCase());
		
/*		//trata.tratarString("PROPOSAL FOR A MEASUREMENT MODEL FOR SOFTWARE TESTS WITH A FOCUS ON THE MANAGEMENT OF OUTSOURCED SERVICES", 
				"Intricacies of quantum computational paths");*/
		//System.out.println(Teste2.listaBase);
		System.out.println("--------------Tamanho de possibilidade: " +ParserOwlJava.tamanhoListaBase * ParserOwlJava.tamanhoListaDestino +" --------------");
		for (int i = 0; i< ParserOwlJava.tamanhoListaBase; i++){
			//System.out.println(Teste2.listaBase.get(2+controlePosicaoVetorBase));
			//System.out.println(t.consultaBase().get(controlePosicaoVetorBase+2));
			//System.out.println("CONTROLE VETOR BASE: "+controlePosicaoVetorBase+2);
			for (int j = 0; j< ParserOwlJava.tamanhoListaDestino; j++){
				//System.out.println(Teste2.listaDestino.get(2+controlePosicaoVetorBase));
				trata.selecionarSinonimosThesaurosEn(ParserOwlJava.listaBase.get(2+controlePosicaoVetorBase).toUpperCase(), ParserOwlJava.listaDestino.get(2+controlePosicaoVetorDestino).toUpperCase());
				//System.out.println(Teste2.listaBase.get(2+controlePosicaoVetorBase));
				controlePosicaoVetorDestino = controlePosicaoVetorDestino + 8;
			}
			controlePosicaoVetorDestino = 0;
			controlePosicaoVetorBase = controlePosicaoVetorBase + 8;
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
		 String[] prep = {"MSC", "DR", "FOR", "AT", "E", "DA", "DE", "NA", "A", "AN", "OS", "ON", "IN", "THE", "DO", "OF", "WITH", "AS", "PARA", "COM", "NO", "EM"}; //Lista de termos para serem retirados da string original do titulo do artigo

		 for (int i = 0; i < prep.length; i++) {
			 
			 StringTokenizer token = new StringTokenizer(str, " ");
		     
		     if(prep[i].contains(token.nextToken())){
		    	 strAux = prep[i].toString();
		    	  if(str.contains(strAux)){
		    		    str = " "+str;
			  	    	str = str.replaceAll(" "+prep[i].toString()+ " ", "");
			  	    	}
		          }
		     str = str.replaceAll(" " +prep[i].toString()+ " ", " ");
		     }
	    //System.out.println("Depois: "+str);
		return str;
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
		        System.out.println("Itens iguais: " +v);
		    } else {
		        System.out.println();
		    }
		}
	}
	/**
	 * Apos a limpeza da string com a retira das informacoes desncessarias, esse metodo retorna aleatoriamente 
	 * 3 palavras dessa string, com o objetivo de serem usuadas para a construca da lista de sinonimos...
	 * cada uma das tres palavras sera utilizada para obter o sinonimo, apois adicionadas em outra lista que comporta
	 * sinonimos de cada palavra, sera uma lista pro destino e uma pro base;
	 * @param str
	 * @return
	 */
	public ArrayList<String> selecionarStr (String str){
		 listaStrSelecionadas = new ArrayList<String>();
		 ArrayList<String> listaStrTmpSelecionadas = new ArrayList<String>();
		 ArrayList<Integer> listaTmp = new ArrayList<Integer>();
		 StringTokenizer token = new StringTokenizer(str, " ");
	     while(token.hasMoreTokens()) {  
	            String local = token.nextToken(); 
	            listaStrTmpSelecionadas.add(local);
	    }
	     Random radom  = new Random();
	     int numeroTmp = 0;
	     //System.out.println(listaStrTmpSelecionadas.);
	     if (listaStrTmpSelecionadas.size() <= 5){
	    	 listaStrSelecionadas.addAll(listaStrTmpSelecionadas);
	    	 return listaStrSelecionadas;
	     }else{
		     while (listaTmp.size() < 6) {
		         numeroTmp=radom.nextInt(listaStrTmpSelecionadas.size());
		         if (listaTmp.contains(numeroTmp) == false){
		        	 listaTmp.add(numeroTmp);
		        	 //System.out.println(listaStrTmpSelecionadas.get(numeroTmp));
		        	 listaStrSelecionadas.add(listaStrTmpSelecionadas.get(numeroTmp));
		         }
		     }
		     //System.out.println("LISTAAAAAAA: "+listaStrSelecionadas);
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
					}
				}
			}
			for (int i = 0; i< listaSinonimosDestino.size(); i++){
				for (int j = 0; j< listaSinonimosBase.size(); j++){
					if(listaSinonimosDestino.get(i).toUpperCase().contains(listaSinonimosBase.get(j).toUpperCase())){
						System.out.println(listaSinonimosDestino.get(i) + " = " + listaSinonimosBase.get(j));
						valorObtidoNaComparacao = 5;
					}
					}
				}
		}else{
			return;
		}
		System.out.println("Valor Adquirido: "+valorObtidoNaComparacao);
	}
	/**
	 * Metodo que retorna a lista de sinonimos do Thesauros em Ingles.
	 * @param strASerTratadaBase
	 * @param strASerTratadaDestino
	 */
	public void selecionarSinonimosThesaurosEn(String strASerTratadaBase, String strASerTratadaDestino){
		TrataString str = new TrataString();
	    
	    //str.selecionarStr(str.tratarStr(strASerTratadaDestino));
	    SinonimosEn tbase = new SinonimosEn();
	    listaCompararBase = new ArrayList<String>();
	    listaCompararDestino = new ArrayList<String>();
	    str.selecionarStr(str.tratarStr(strASerTratadaBase.toUpperCase()));
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaBase)).get(i));
	    	tbase.sendRequest(listaStrSelecionadas.get(i), language, key, output);
	    	if(SinonimosEn.listaSinonimosEn.size() > 0)
	    		listaCompararBase.add(listaStrSelecionadas.get(i).toUpperCase());
	    	listaCompararBase.addAll(SinonimosEn.listaSinonimosEn);
	    	//listaCompararBase.add("/");//caractere para separar os nomes raizes com seus sinonimos
	    	
	    }
	    System.out.println("LISTA DE SINONIMOS BASE: "+listaCompararBase);
	    
	    if(strASerTratadaDestino.contains("-"))
	    	strASerTratadaDestino = strASerTratadaDestino.replace("PROBLEM-SOLVING AND QUANTUM COMPUTATION", "PROBLEM SOLVING QUANTUM COMPUTATION");
	    
	    System.out.println(strASerTratadaDestino.toUpperCase());
	    str.selecionarStr(str.tratarStr(strASerTratadaDestino.toUpperCase()));
	    SinonimosEn tdestino = new SinonimosEn();
	    System.out.println(listaStrSelecionadas.size());
	    for (int i = 0; i<listaStrSelecionadas.size(); i++){
	    	//System.out.println(str.selecionarStr(str.tratarStr(strASerTratadaDestino)).get(i));
	    	tdestino.sendRequest(listaStrSelecionadas.get(i), language, key, output);
	    	if(SinonimosEn.listaSinonimosEn.size() > 0)
	    		listaCompararDestino.add(listaStrSelecionadas.get(i).toUpperCase());
	    	listaCompararDestino.addAll(SinonimosEn.listaSinonimosEn);
	    	//listaCompararDestino.add("/");//caractere para separar os nomes raizes com seus sinonimos
	    }
	    
	    System.out.println("LISTA DE SINONIMOS DESTINO: "+listaCompararDestino);
	    
	    str.compararListaSinonimos(listaCompararBase, listaCompararDestino);
	    
	    //str.compararListaSinonimos(listaCompararBase, listaCompararDestino);
	    //System.out.println(str.tratarStr(strASerTratadaDestino));
	    //str.quebrarStrDestino(str.tratarStr(strASerTratadaDestino));
	    
	    //str.compararArraysSeparadado(str.quebrarStrBase(str.tratarStr(strASerTratadaBase)), str.quebrarStrDestino(str.tratarStr(strASerTratadaDestino)));

	}
}