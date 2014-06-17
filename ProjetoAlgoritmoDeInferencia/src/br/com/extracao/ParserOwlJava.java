package br.com.extracao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import edu.stanford.smi.protege.exception.OntologyLoadException;

	public class ParserOwlJava {
	public static int tamanhoListaBase = 0;
	public static int tamanhoListaDestino = 0;
	public static ArrayList<String> listaBase = null;
	public static ArrayList<String> listaDestino = null;
	
	public static void main(String args[]) throws OntologyLoadException {

		new ParserOwlJava().consultaBase();
		new ParserOwlJava().consultaDestino1();
		//new Teste2().consultaDestino2();
		//new Teste2().consultaDestino3();
		//System.out.println("Artigo(s) publicados destino 3: " +listaDestino.get(2));
		System.out.println("TAMANHO DA LISTA BASE: " +tamanhoListaBase);
		System.out.println("TAMANHO DA LISTA DESTINO1: " +tamanhoListaDestino);
		System.out.println("TAMANHO LISTA DESTINO1 " +listaDestino.size());
		int totalCombinacao = tamanhoListaBase * tamanhoListaDestino;
		
		if (listaDestino.isEmpty()){
			System.out.println("Lista destino vazia, não há nada para se comparar. ");
			return;
		}
	
		boolean flagNomeDoCursoBase = false, flagNomeDoCursoDestino = false;
		int flagValorNomeDoCurso = 0, flagValorNomeFaculdade = 0, flagValorTituloArtigo = 0, flagValorAnoDadosBasicos = 0, 
			flagValorTituloLivroDetalhe = 0, flagValorTituloPeriodicoOuRevista = 0, 
			flagValorFasciculo = 0, flagValorVolume = 0;
		
		int flagValorNomeDoCursoTotal = 5, flagValorNomeFaculdadeTotal = 5, flagValorTituloArtigoTotal = 5, flagValorAnoDadosBasicosTotal = 5, 
			flagValorTituloLivroDetalheTotal = 5, flagValorTituloPeriodicoOuRevistaTotal = 5, flagValorFasciculoTotal = 5,
		    flagValorVolumeTotal = 5;
		
		int totalValorPossivel = flagValorTituloArtigoTotal + flagValorAnoDadosBasicosTotal +
								 flagValorTituloLivroDetalheTotal + flagValorTituloPeriodicoOuRevistaTotal + flagValorFasciculoTotal + 
								 flagValorVolumeTotal;
		
		int totalGeralPossivel = (totalCombinacao*totalValorPossivel) + (flagValorNomeDoCursoTotal + flagValorNomeFaculdadeTotal);
		
		if(listaBase.get(0).equals(listaDestino.get(0))){
			flagValorNomeDoCurso = 5; 
		}else {
			
					if (listaBase.get(0).contains(listaDestino.get(0))	|| listaDestino.get(0).contains(listaBase.get(0))){
						flagNomeDoCursoBase = true;
					}else{
						flagNomeDoCursoBase = false;
					}
			
					if (flagNomeDoCursoBase && flagNomeDoCursoDestino){
						flagValorNomeDoCurso = 3;
					}else {
						flagValorNomeDoCurso = 0;
					}
					System.out.println("Valor para o nome do curso: " +flagValorNomeDoCurso + " de um total de: " +flagValorNomeDoCursoTotal);
			}
		
		if(listaBase.get(1).equals(listaDestino.get(1))){
			flagValorNomeFaculdade = 3;
		}else{
			flagValorNomeFaculdade = 0;
		}
		
		System.out.println("Valor para o nome da faculdade: " +flagValorNomeFaculdade + " de um total de: " +flagValorNomeFaculdadeTotal);

		/**
		 * [0] - Nome do curso
		 * [1] - Nome Faculdade
		 * [2] - Titulo dados básicos 
		 * [3] - Ano dados básicos
		 * [4] - Titulo do livro detalhe
		 * [5] - Titulo do periodico ou revista detalhe
		 * [6] - Fasciculo detalhe
		 * [7] - Volume detalhe
		 */
		
		/**
		 * Laço mais externo de todos para realizar o controle da quantidade de destinos possíveis
		 * 
		 */
		//int controlePosicaoQtdIndividuos = 0; //controle o laço entre os individuos destinos 
		/*for (int k = 1; k<=10; k++){
		}*/

		/**
		 * laço externo para a comparação dos elementos do primeiro artigo do base com o primeiro 
		 * artigo do destino 1, quando acabar incrementa o i, apos isso vai para o segundo
		 * artigo do base com o demais artigos do destino 1, acabou o destino 1 passa para o 2...
		 *
		 */
		int controlePosicaoVetorBase = 0;
		List<Integer> soma = new ArrayList<Integer>();
		int totalGeral = 0; 
		for (int i = 1; i<=tamanhoListaBase; i++){
			System.out.println("Individuo base i, artigo: " +i);
			
			/**
			 * laço interno para passar por todos os artigos do destino1
			 */
			int controlePosicaoVetorDestino = 0;
			for (int j = 1; j<=tamanhoListaDestino; j++){
				System.out.println("Individuo destino j, artigo: " +j);
				if (listaBase.get(2+controlePosicaoVetorBase).equals(listaDestino.get(2+controlePosicaoVetorDestino))){
					flagValorTituloArtigo = 30;
				}else if (listaBase.get(5+controlePosicaoVetorBase).equals(listaDestino.get(5+controlePosicaoVetorDestino))){
					flagValorTituloPeriodicoOuRevista = 20;
					System.out.println("Valor para o título do periódico ou revista: " +flagValorTituloPeriodicoOuRevista + " de um total de: " +flagValorTituloPeriodicoOuRevistaTotal);
				
					System.out.println("Valor para o titulo básico do artigo: " +flagValorTituloArtigo + " de um total de: " +flagValorTituloArtigoTotal);
					
					if (listaBase.get(3+controlePosicaoVetorBase).equals(listaDestino.get(3+controlePosicaoVetorDestino))){
						flagValorAnoDadosBasicos = 3;
					}else{
						flagValorAnoDadosBasicos = 0;
					}
					System.out.println("Valor para o ano de publicação: " +flagValorAnoDadosBasicos + " de um total de: " +flagValorAnoDadosBasicosTotal);
					
					if (listaBase.get(4+controlePosicaoVetorBase).equals(listaDestino.get(4+controlePosicaoVetorDestino))){
						flagValorTituloLivroDetalhe = 5;
					}else{
						flagValorTituloLivroDetalhe = 0;
					}
					System.out.println("Valor para o titulo detalhado do artigo: " +flagValorTituloLivroDetalhe + " de um total de: " +flagValorTituloLivroDetalheTotal);
					
					if (listaBase.get(6+controlePosicaoVetorBase).equals(listaDestino.get(6+controlePosicaoVetorDestino))){
						flagValorFasciculo = 5;
					}else{
						flagValorFasciculo = 0;
					}
					System.out.println("Valor para o fascículo do artigo: " +flagValorFasciculo + " de um total de: " +flagValorFasciculoTotal);
					
					if (listaBase.get(7+controlePosicaoVetorBase).equals(listaDestino.get(7+controlePosicaoVetorDestino))){
						flagValorVolume = 5;
					}else{
						flagValorVolume = 0;
					}
					System.out.println("Valor para o volume do artigo: " +flagValorVolume + " de um total de: " +flagValorVolumeTotal);
				}else{
					flagValorTituloArtigo = 0;
				}
				
				controlePosicaoVetorDestino = controlePosicaoVetorDestino + 8;
//				/System.out.println("Titulo artigo destino no laço: " + j + " : "+ listaDestino.get(2+controlePosicaoVetorDestino));
				soma.add(flagValorNomeDoCurso);
				soma.add(flagValorNomeFaculdade);
				soma.add(flagValorTituloArtigo);
				soma.add(flagValorAnoDadosBasicos);
				soma.add(flagValorTituloLivroDetalhe);
				soma.add(flagValorTituloPeriodicoOuRevista);
				soma.add(flagValorFasciculo);
				soma.add(flagValorVolume);
			}
			controlePosicaoVetorBase = controlePosicaoVetorBase + 8;
			for (int k=0; k<soma.size(); k++){
				totalGeral = totalGeral + (soma.get(i));
			}
		}
		System.out.println("Total Geral Possível: "+totalGeralPossivel);
		System.out.println("Total geral comparado: " +totalGeral);
	}

	public ArrayList<String> consultaBase() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste2.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica ?temDadosBasicos ?temDetalhamentoDoArtigo ?tituloDadosBasicos " 
					+		"?anoDadosBasicos ?tituloDoLivroDetalheArtigo ?tituloDoPeriodicoOuRevistaDetalheArtigo ?fasciculoDetalheArtigo "
					+   	"?volumeDetalheArtigo ?realizadoNaInstituicao ?nomeInstituicaoEmpresa ?nomeCurso "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad99e1ac12-40e1-4266-b6b3-1565bfd121bb-luiz-carlos-miyadaira-ribeiro-junior> onto:nivel ?nomeCurso ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-faculdade-de-informática-de-lins> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa ."
					+ "			?realizadoNaInstituicao1 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa ."
					+ "		<http://www.ime.usp.br/ontolattes#cv-luiz-carlos-miyadaira-ribeiro-junior> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "		 	 ?temProducaoBibliografica onto:temDadosBasicos ?temDadosBasicos ."
					+ "			 ?temProducaoBibliografica onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo ."
					+ "		     ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "			 ?temDadosBasicos onto:ano ?anoDadosBasicos ."
					+ "			 ?temDetalhamentoDoArtigo onto:tituloDoLivro ?tituloDoLivroDetalheArtigo ."
					+ "			 ?temDetalhamentoDoArtigo onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo ."
					+ "  		 ?temDetalhamentoDoArtigo onto:fasciculo ?fasciculoDetalheArtigo ."
					+ "	   		 ?temDetalhamentoDoArtigo onto:volume ?volumeDetalheArtigo ."
					+ "	        		 } ";

			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaBase = new ArrayList<String>();
			tamanhoListaBase = 0;
			String strNomeCurso = null, strNomeInstituicaoEmpresa = null, strTituloDadosBasicos = null, strAnoDadosBasicos = null, 
				   strTituloDoLivroDetalheArtigo = null, strTituloDoPeriodicoOuRevistaDetalheArtigo = null, strFasciculoDetalheArtigo = null, 
				   strVolumeDetalheArtigo = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				
				strNomeCurso = soln.getLiteral("nomeCurso").toString();
				strNomeInstituicaoEmpresa = soln.getLiteral("nomeInstituicaoEmpresa").toString();
				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos").toString();
				strAnoDadosBasicos = soln.getLiteral("anoDadosBasicos").toString();
				strTituloDoLivroDetalheArtigo = soln.getLiteral("tituloDoLivroDetalheArtigo").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo").toString();
				strFasciculoDetalheArtigo = soln.getLiteral("fasciculoDetalheArtigo").toString();
				strVolumeDetalheArtigo = soln.getLiteral("volumeDetalheArtigo").toString();

				listaBase.add(strNomeCurso);
				listaBase.add(strNomeInstituicaoEmpresa);
				listaBase.add(strTituloDadosBasicos);
				listaBase.add(strAnoDadosBasicos);
				listaBase.add(strTituloDoLivroDetalheArtigo);
				listaBase.add(strTituloDoPeriodicoOuRevistaDetalheArtigo);
				listaBase.add(strFasciculoDetalheArtigo);
				listaBase.add(strVolumeDetalheArtigo);
				tamanhoListaBase ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaBase;
	}

	public ArrayList<String> consultaDestino1() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste2.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica1 ?temDadosBasicos1 ?temDetalhamentoDoArtigo1 ?tituloDadosBasicos1 " 
					+ "						?anoDadosBasicos1 ?tituloDoLivroDetalheArtigo1 ?tituloDoPeriodicoOuRevistaDetalheArtigo1 " 
					+ "						?fasciculoDetalheArtigo1 ?volumeDetalheArtigo1 ?realizadoNaInstituicao1 ?nomeInstituicaoEmpresa1 " 
					+ "						?nomeCurso1 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad6561ffc2-2b7d-4125-9d96-74b1066fbd76-luís-domingues-tomé-jardim-tarrataca> onto:nivel ?nomeCurso1 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-instituto-superior-técnico---universidade-de-lisboa> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "			?realizadoNaInstituicao1 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-luís-domingues-tomé-jardim-tarrataca> onto:temProducaoBibliografica ?temProducaoBibliografica1 ."
					+ "			 ?temProducaoBibliografica1 onto:temDadosBasicos ?temDadosBasicos1 ."
					+ "			 ?temProducaoBibliografica1 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo1 ."
					+ "			 ?temDadosBasicos1 onto:titulo ?tituloDadosBasicos1 ."
					+ "			 ?temDadosBasicos1 onto:ano ?anoDadosBasicos1. "
					+ "          ?temDetalhamentoDoArtigo1 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo1 ."
					+ "			 ?temDetalhamentoDoArtigo1 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo1 ."
					+ "		     ?temDetalhamentoDoArtigo1 onto:fasciculo ?fasciculoDetalheArtigo1 ."
					+ "			 ?temDetalhamentoDoArtigo1 onto:volume ?volumeDetalheArtigo1.}";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso1 = null, strNomeInstituicaoEmpresa1 = null, strTituloDadosBasicos1 = null, strAnoDadosBasicos1 = null, 
				   strTituloDoLivroDetalheArtigo1 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo1 = null, strFasciculoDetalheArtigo1 = null,
				   strVolumeDetalheArtigo1 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso1 = soln.getLiteral("nomeCurso1").toString();
				strNomeInstituicaoEmpresa1 = soln.getLiteral("nomeInstituicaoEmpresa1").toString();
				strTituloDadosBasicos1 = soln.getLiteral("tituloDadosBasicos1").toString();
				strAnoDadosBasicos1 = soln.getLiteral("anoDadosBasicos1").toString();
				strTituloDoLivroDetalheArtigo1 = soln.getLiteral("tituloDoLivroDetalheArtigo1").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo1 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo1").toString();
				strFasciculoDetalheArtigo1 = soln.getLiteral("fasciculoDetalheArtigo1").toString();
				strVolumeDetalheArtigo1 = soln.getLiteral("volumeDetalheArtigo1").toString();

				listaDestino.add(strNomeCurso1);
				listaDestino.add(strNomeInstituicaoEmpresa1);
				listaDestino.add(strTituloDadosBasicos1);
				listaDestino.add(strAnoDadosBasicos1);
				listaDestino.add(strTituloDoLivroDetalheArtigo1);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo1);
				listaDestino.add(strFasciculoDetalheArtigo1);
				listaDestino.add(strVolumeDetalheArtigo1);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
/*	public ArrayList<String> consultaDestino1() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica1 ?temDadosBasicos1 ?temDetalhamentoDoArtigo1 ?tituloDadosBasicos1 " 
					+ "						?anoDadosBasicos1 ?tituloDoLivroDetalheArtigo1 ?tituloDoPeriodicoOuRevistaDetalheArtigo1 " 
					+ "						?fasciculoDetalheArtigo1 ?volumeDetalheArtigo1 ?realizadoNaInstituicao1 ?nomeInstituicaoEmpresa1 " 
					+ "						?nomeCurso1 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acadc6fc53e5-b8c1-4895-a1ec-8a08c1d43e43-andré-gustavo-de-melo-araújo> onto:nivel ?nomeCurso1 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-são-paulo,-usp,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "			?realizadoNaInstituicao1 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-andré-gustavo-de-melo-araújo> onto:temProducaoBibliografica ?temProducaoBibliografica1 ."
					+ "			 ?temProducaoBibliografica1 onto:temDadosBasicos ?temDadosBasicos1 ."
					+ "			 ?temProducaoBibliografica1 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo1 ."
					+ "			 ?temDadosBasicos1 onto:titulo ?tituloDadosBasicos1 ."
					+ "			 ?temDadosBasicos1 onto:ano ?anoDadosBasicos1. "
					+ "          ?temDetalhamentoDoArtigo1 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo1 ."
					+ "			 ?temDetalhamentoDoArtigo1 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo1 ."
					+ "		     ?temDetalhamentoDoArtigo1 onto:fasciculo ?fasciculoDetalheArtigo1 ."
					+ "			 ?temDetalhamentoDoArtigo1 onto:volume ?volumeDetalheArtigo1.}";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso1 = null, strNomeInstituicaoEmpresa1 = null, strTituloDadosBasicos1 = null, strAnoDadosBasicos1 = null, 
				   strTituloDoLivroDetalheArtigo1 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo1 = null, strFasciculoDetalheArtigo1 = null,
				   strVolumeDetalheArtigo1 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso1 = soln.getLiteral("nomeCurso1").toString();
				strNomeInstituicaoEmpresa1 = soln.getLiteral("nomeInstituicaoEmpresa1").toString();
				strTituloDadosBasicos1 = soln.getLiteral("tituloDadosBasicos1").toString();
				strAnoDadosBasicos1 = soln.getLiteral("anoDadosBasicos1").toString();
				strTituloDoLivroDetalheArtigo1 = soln.getLiteral("tituloDoLivroDetalheArtigo1").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo1 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo1").toString();
				strFasciculoDetalheArtigo1 = soln.getLiteral("fasciculoDetalheArtigo1").toString();
				strVolumeDetalheArtigo1 = soln.getLiteral("volumeDetalheArtigo1").toString();

				listaDestino.add(strNomeCurso1);
				listaDestino.add(strNomeInstituicaoEmpresa1);
				listaDestino.add(strTituloDadosBasicos1);
				listaDestino.add(strAnoDadosBasicos1);
				listaDestino.add(strTituloDoLivroDetalheArtigo1);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo1);
				listaDestino.add(strFasciculoDetalheArtigo1);
				listaDestino.add(strVolumeDetalheArtigo1);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	*/
/*	
	public ArrayList<String> consultaDestino2() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica2 ?temDadosBasicos2 ?temDetalhamentoDoArtigo2 ?tituloDadosBasicos2 " 
					+ "    					?anoDadosBasicos2 ?tituloDoLivroDetalheArtigo2 ?tituloDoPeriodicoOuRevistaDetalheArtigo2 " 
					+ "						?fasciculoDetalheArtigo2 ?volumeDetalheArtigo2 ?realizadoNaInstituicao2 ?nomeInstituicaoEmpresa2 " 
					+ "						?nomeCurso2 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad279c2d0c-8de9-4364-9f6c-5fcccafabf1e-fabricio-ataides-braz> onto:nivel ?nomeCurso2 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-pontifícia-universidade-católica-de-goiás,-puc-goiás,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa2 ."
					+ "			?realizadoNaInstituicao2 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa2 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-fabricio-ataides-braz> onto:temProducaoBibliografica ?temProducaoBibliografica2 ."
					+ "			 ?temProducaoBibliografica2 onto:temDadosBasicos ?temDadosBasicos2 ."
					+ "			 ?temProducaoBibliografica2 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo2 ."
					+ "			 ?temDadosBasicos2 onto:titulo ?tituloDadosBasicos2 ."
					+ "			 ?temDadosBasicos2 onto:ano ?anoDadosBasicos2 ."
					+ "          ?temDetalhamentoDoArtigo2 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo2 ."
					+ "			 ?temDetalhamentoDoArtigo2 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo2 ."
					+ "		     ?temDetalhamentoDoArtigo2 onto:fasciculo ?fasciculoDetalheArtigo2 ."
					+ "			 ?temDetalhamentoDoArtigo2 onto:volume ?volumeDetalheArtigo2 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso2 = null, strNomeInstituicaoEmpresa2 = null, strTituloDadosBasicos2 = null, strAnoDadosBasicos2 = null, 
				   strTituloDoLivroDetalheArtigo2 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo2 = null, strFasciculoDetalheArtigo2 = null,
				   strVolumeDetalheArtigo2 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso2 = soln.getLiteral("nomeCurso2").toString();
				strNomeInstituicaoEmpresa2 = soln.getLiteral("nomeInstituicaoEmpresa2").toString();
				strTituloDadosBasicos2 = soln.getLiteral("tituloDadosBasicos2").toString();
				strAnoDadosBasicos2 = soln.getLiteral("anoDadosBasicos2").toString();
				strTituloDoLivroDetalheArtigo2 = soln.getLiteral("tituloDoLivroDetalheArtigo2").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo2 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo2").toString();
				strFasciculoDetalheArtigo2 = soln.getLiteral("fasciculoDetalheArtigo2").toString();
				strVolumeDetalheArtigo2 = soln.getLiteral("volumeDetalheArtigo2").toString();

				listaDestino.add(strNomeCurso2);
				listaDestino.add(strNomeInstituicaoEmpresa2);
				listaDestino.add(strTituloDadosBasicos2);
				listaDestino.add(strAnoDadosBasicos2);
				listaDestino.add(strTituloDoLivroDetalheArtigo2);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo2);
				listaDestino.add(strFasciculoDetalheArtigo2);
				listaDestino.add(strVolumeDetalheArtigo2);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	*/
	
	public ArrayList<String> consultaDestino2() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste2.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica2 ?temDadosBasicos2 ?temDetalhamentoDoArtigo2 ?tituloDadosBasicos2 " 
					+ "    					?anoDadosBasicos2 ?tituloDoLivroDetalheArtigo2 ?tituloDoPeriodicoOuRevistaDetalheArtigo2 " 
					+ "						?fasciculoDetalheArtigo2 ?volumeDetalheArtigo2 ?realizadoNaInstituicao2 ?nomeInstituicaoEmpresa2 " 
					+ "						?nomeCurso2 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad35f70f31-2520-422c-8251-7924b1b97727-jan-mendonça-correa> onto:nivel ?nomeCurso2 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa2 ."
					+ "			?realizadoNaInstituicao2 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa2 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-jan-mendonça-correa> onto:temProducaoBibliografica ?temProducaoBibliografica2 ."
					+ "			 ?temProducaoBibliografica2 onto:temDadosBasicos ?temDadosBasicos2 ."
					+ "			 ?temProducaoBibliografica2 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo2 ."
					+ "			 ?temDadosBasicos2 onto:titulo ?tituloDadosBasicos2 ."
					+ "			 ?temDadosBasicos2 onto:ano ?anoDadosBasicos2 ."
					+ "          ?temDetalhamentoDoArtigo2 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo2 ."
					+ "			 ?temDetalhamentoDoArtigo2 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo2 ."
					+ "		     ?temDetalhamentoDoArtigo2 onto:fasciculo ?fasciculoDetalheArtigo2 ."
					+ "			 ?temDetalhamentoDoArtigo2 onto:volume ?volumeDetalheArtigo2 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso2 = null, strNomeInstituicaoEmpresa2 = null, strTituloDadosBasicos2 = null, strAnoDadosBasicos2 = null, 
				   strTituloDoLivroDetalheArtigo2 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo2 = null, strFasciculoDetalheArtigo2 = null,
				   strVolumeDetalheArtigo2 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso2 = soln.getLiteral("nomeCurso2").toString();
				strNomeInstituicaoEmpresa2 = soln.getLiteral("nomeInstituicaoEmpresa2").toString();
				strTituloDadosBasicos2 = soln.getLiteral("tituloDadosBasicos2").toString();
				strAnoDadosBasicos2 = soln.getLiteral("anoDadosBasicos2").toString();
				strTituloDoLivroDetalheArtigo2 = soln.getLiteral("tituloDoLivroDetalheArtigo2").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo2 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo2").toString();
				strFasciculoDetalheArtigo2 = soln.getLiteral("fasciculoDetalheArtigo2").toString();
				strVolumeDetalheArtigo2 = soln.getLiteral("volumeDetalheArtigo2").toString();

				listaDestino.add(strNomeCurso2);
				listaDestino.add(strNomeInstituicaoEmpresa2);
				listaDestino.add(strTituloDadosBasicos2);
				listaDestino.add(strAnoDadosBasicos2);
				listaDestino.add(strTituloDoLivroDetalheArtigo2);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo2);
				listaDestino.add(strFasciculoDetalheArtigo2);
				listaDestino.add(strVolumeDetalheArtigo2);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
/*	
	public ArrayList<String> consultaDestino3() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica3 ?temDadosBasicos3 ?temDetalhamentoDoArtigo3 ?tituloDadosBasicos3 " 
					+ "    					?anoDadosBasicos3 ?tituloDoLivroDetalheArtigo3 ?tituloDoPeriodicoOuRevistaDetalheArtigo3 " 
					+ "						?fasciculoDetalheArtigo3 ?volumeDetalheArtigo3 ?realizadoNaInstituicao3 ?nomeInstituicaoEmpresa3 " 
					+ "						?nomeCurso3 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad47093e66-625f-489f-8f4e-63b46a759bee-edson-mintsu-hung> onto:nivel ?nomeCurso3 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa3 ."
					+ "			?realizadoNaInstituicao3 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa3 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-edson-mintsu-hung> onto:temProducaoBibliografica ?temProducaoBibliografica3 ."
					+ "			 ?temProducaoBibliografica3 onto:temDadosBasicos ?temDadosBasicos3 ."
					+ "			 ?temProducaoBibliografica3 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo3 ."
					+ "			 ?temDadosBasicos3 onto:titulo ?tituloDadosBasicos3 ."
					+ "			 ?temDadosBasicos3 onto:ano ?anoDadosBasicos3 ."
					+ "          ?temDetalhamentoDoArtigo3 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo3 ."
					+ "			 ?temDetalhamentoDoArtigo3 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo3 ."
					+ "		     ?temDetalhamentoDoArtigo3 onto:fasciculo ?fasciculoDetalheArtigo3 ."
					+ "			 ?temDetalhamentoDoArtigo3 onto:volume ?volumeDetalheArtigo3 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso3 = null, strNomeInstituicaoEmpresa3 = null, strTituloDadosBasicos3 = null, strAnoDadosBasicos3 = null, 
				   strTituloDoLivroDetalheArtigo3 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo3 = null, strFasciculoDetalheArtigo3 = null,
				   strVolumeDetalheArtigo3 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso3 = soln.getLiteral("nomeCurso3").toString();
				strNomeInstituicaoEmpresa3 = soln.getLiteral("nomeInstituicaoEmpresa3").toString();
				strTituloDadosBasicos3 = soln.getLiteral("tituloDadosBasicos3").toString();
				strAnoDadosBasicos3 = soln.getLiteral("anoDadosBasicos3").toString();
				strTituloDoLivroDetalheArtigo3 = soln.getLiteral("tituloDoLivroDetalheArtigo3").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo3 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo3").toString();
				strFasciculoDetalheArtigo3 = soln.getLiteral("fasciculoDetalheArtigo3").toString();
				strVolumeDetalheArtigo3 = soln.getLiteral("volumeDetalheArtigo3").toString();

				listaDestino.add(strNomeCurso3);
				listaDestino.add(strNomeInstituicaoEmpresa3);
				listaDestino.add(strTituloDadosBasicos3);
				listaDestino.add(strAnoDadosBasicos3);
				listaDestino.add(strTituloDoLivroDetalheArtigo3);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo3);
				listaDestino.add(strFasciculoDetalheArtigo3);
				listaDestino.add(strVolumeDetalheArtigo3);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	*/
	public ArrayList<String> consultaDestino3() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica3 ?temDadosBasicos3 ?temDetalhamentoDoArtigo3 ?tituloDadosBasicos3 " 
					+ "    					?anoDadosBasicos3 ?tituloDoLivroDetalheArtigo3 ?tituloDoPeriodicoOuRevistaDetalheArtigo3 " 
					+ "						?fasciculoDetalheArtigo3 ?volumeDetalheArtigo3 ?realizadoNaInstituicao3 ?nomeInstituicaoEmpresa3 " 
					+ "						?nomeCurso3 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad47093e66-625f-489f-8f4e-63b46a759bee-edson-mintsu-hung> onto:nivel ?nomeCurso3 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa3 ."
					+ "			?realizadoNaInstituicao3 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa3 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-edson-mintsu-hung> onto:temProducaoBibliografica ?temProducaoBibliografica3 ."
					+ "			 ?temProducaoBibliografica3 onto:temDadosBasicos ?temDadosBasicos3 ."
					+ "			 ?temProducaoBibliografica3 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo3 ."
					+ "			 ?temDadosBasicos3 onto:titulo ?tituloDadosBasicos3 ."
					+ "			 ?temDadosBasicos3 onto:ano ?anoDadosBasicos3 ."
					+ "          ?temDetalhamentoDoArtigo3 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo3 ."
					+ "			 ?temDetalhamentoDoArtigo3 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo3 ."
					+ "		     ?temDetalhamentoDoArtigo3 onto:fasciculo ?fasciculoDetalheArtigo3 ."
					+ "			 ?temDetalhamentoDoArtigo3 onto:volume ?volumeDetalheArtigo3 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso3 = null, strNomeInstituicaoEmpresa3 = null, strTituloDadosBasicos3 = null, strAnoDadosBasicos3 = null, 
				   strTituloDoLivroDetalheArtigo3 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo3 = null, strFasciculoDetalheArtigo3 = null,
				   strVolumeDetalheArtigo3 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso3 = soln.getLiteral("nomeCurso3").toString();
				strNomeInstituicaoEmpresa3 = soln.getLiteral("nomeInstituicaoEmpresa3").toString();
				strTituloDadosBasicos3 = soln.getLiteral("tituloDadosBasicos3").toString();
				strAnoDadosBasicos3 = soln.getLiteral("anoDadosBasicos3").toString();
				strTituloDoLivroDetalheArtigo3 = soln.getLiteral("tituloDoLivroDetalheArtigo3").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo3 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo3").toString();
				strFasciculoDetalheArtigo3 = soln.getLiteral("fasciculoDetalheArtigo3").toString();
				strVolumeDetalheArtigo3 = soln.getLiteral("volumeDetalheArtigo3").toString();

				listaDestino.add(strNomeCurso3);
				listaDestino.add(strNomeInstituicaoEmpresa3);
				listaDestino.add(strTituloDadosBasicos3);
				listaDestino.add(strAnoDadosBasicos3);
				listaDestino.add(strTituloDoLivroDetalheArtigo3);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo3);
				listaDestino.add(strFasciculoDetalheArtigo3);
				listaDestino.add(strVolumeDetalheArtigo3);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino4() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste2.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica4 ?temDadosBasicos4 ?temDetalhamentoDoArtigo4 ?tituloDadosBasicos4 " 
					+ "    					?anoDadosBasicos4 ?tituloDoLivroDetalheArtigo4 ?tituloDoPeriodicoOuRevistaDetalheArtigo4 " 
					+ "						?fasciculoDetalheArtigo4 ?volumeDetalheArtigo4 ?realizadoNaInstituicao4 ?nomeInstituicaoEmpresa4 " 
					+ "						?nomeCurso4 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad77dfae12-1794-4b78-99ca-351a9385e3aa-andré-gustavo-de-melo-araújo> onto:nivel ?nomeCurso4 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-são-paulo,-usp,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa4 ."
					+ "			?realizadoNaInstituicao4 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa4 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-andré-gustavo-de-melo-araújo> onto:temProducaoBibliografica ?temProducaoBibliografica4 ."
					+ "			 ?temProducaoBibliografica4 onto:temDadosBasicos ?temDadosBasicos4 ."
					+ "			 ?temProducaoBibliografica4 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo4 ."
					+ "			 ?temDadosBasicos4 onto:titulo ?tituloDadosBasicos4 ."
					+ "			 ?temDadosBasicos4 onto:ano ?anoDadosBasicos4 ."
					+ "          ?temDetalhamentoDoArtigo4 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo4 ."
					+ "			 ?temDetalhamentoDoArtigo4 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo4 ."
					+ "		     ?temDetalhamentoDoArtigo4 onto:fasciculo ?fasciculoDetalheArtigo4 ."
					+ "			 ?temDetalhamentoDoArtigo4 onto:volume ?volumeDetalheArtigo4 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso4 = null, strNomeInstituicaoEmpresa4 = null, strTituloDadosBasicos4 = null, strAnoDadosBasicos4 = null, 
				   strTituloDoLivroDetalheArtigo4 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo4 = null, strFasciculoDetalheArtigo4 = null,
				   strVolumeDetalheArtigo4 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso4 = soln.getLiteral("nomeCurso4").toString();
				strNomeInstituicaoEmpresa4 = soln.getLiteral("nomeInstituicaoEmpresa4").toString();
				strTituloDadosBasicos4 = soln.getLiteral("tituloDadosBasicos4").toString();
				strAnoDadosBasicos4 = soln.getLiteral("anoDadosBasicos4").toString();
				strTituloDoLivroDetalheArtigo4 = soln.getLiteral("tituloDoLivroDetalheArtigo4").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo4 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo4").toString();
				strFasciculoDetalheArtigo4 = soln.getLiteral("fasciculoDetalheArtigo4").toString();
				strVolumeDetalheArtigo4 = soln.getLiteral("volumeDetalheArtigo4").toString();

				listaDestino.add(strNomeCurso4);
				listaDestino.add(strNomeInstituicaoEmpresa4);
				listaDestino.add(strTituloDadosBasicos4);
				listaDestino.add(strAnoDadosBasicos4);
				listaDestino.add(strTituloDoLivroDetalheArtigo4);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo4);
				listaDestino.add(strFasciculoDetalheArtigo4);
				listaDestino.add(strVolumeDetalheArtigo4);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino5() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica5 ?temDadosBasicos5 ?temDetalhamentoDoArtigo5 ?tituloDadosBasicos5 " 
					+ "    					?anoDadosBasicos5 ?tituloDoLivroDetalheArtigo5 ?tituloDoPeriodicoOuRevistaDetalheArtigo5 " 
					+ "						?fasciculoDetalheArtigo5 ?volumeDetalheArtigo5 ?realizadoNaInstituicao5 ?nomeInstituicaoEmpresa5 " 
					+ "						?nomeCurso5 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acadb4cfbd34-775c-4a6e-8895-cc3a7b22b34e-paolo-gessini> onto:nivel ?nomeCurso5 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-università-degli-studi-di-roma-la-sapienza,-urs,-itália> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa5 ."
					+ "			?realizadoNaInstituicao5 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa5 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-paolo-gessini> onto:temProducaoBibliografica ?temProducaoBibliografica5 ."
					+ "			 ?temProducaoBibliografica5 onto:temDadosBasicos ?temDadosBasicos5 ."
					+ "			 ?temProducaoBibliografica5 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo5 ."
					+ "			 ?temDadosBasicos5 onto:titulo ?tituloDadosBasicos5 ."
					+ "			 ?temDadosBasicos5 onto:ano ?anoDadosBasicos5 ."
					+ "          ?temDetalhamentoDoArtigo5 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo5 ."
					+ "			 ?temDetalhamentoDoArtigo5 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo5 ."
					+ "		     ?temDetalhamentoDoArtigo5 onto:fasciculo ?fasciculoDetalheArtigo5 ."
					+ "			 ?temDetalhamentoDoArtigo5 onto:volume ?volumeDetalheArtigo5 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso5 = null, strNomeInstituicaoEmpresa5 = null, strTituloDadosBasicos5 = null, strAnoDadosBasicos5 = null, 
				   strTituloDoLivroDetalheArtigo5 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo5 = null, strFasciculoDetalheArtigo5 = null,
				   strVolumeDetalheArtigo5 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso5 = soln.getLiteral("nomeCurso5").toString();
				strNomeInstituicaoEmpresa5 = soln.getLiteral("nomeInstituicaoEmpresa5").toString();
				strTituloDadosBasicos5 = soln.getLiteral("tituloDadosBasicos5").toString();
				strAnoDadosBasicos5 = soln.getLiteral("anoDadosBasicos5").toString();
				strTituloDoLivroDetalheArtigo5 = soln.getLiteral("tituloDoLivroDetalheArtigo5").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo5 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo5").toString();
				strFasciculoDetalheArtigo5 = soln.getLiteral("fasciculoDetalheArtigo5").toString();
				strVolumeDetalheArtigo5 = soln.getLiteral("volumeDetalheArtigo5").toString();

				listaDestino.add(strNomeCurso5);
				listaDestino.add(strNomeInstituicaoEmpresa5);
				listaDestino.add(strTituloDadosBasicos5);
				listaDestino.add(strAnoDadosBasicos5);
				listaDestino.add(strTituloDoLivroDetalheArtigo5);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo5);
				listaDestino.add(strFasciculoDetalheArtigo5);
				listaDestino.add(strVolumeDetalheArtigo5);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino6() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica6 ?temDadosBasicos6 ?temDetalhamentoDoArtigo6 ?tituloDadosBasicos6 " 
					+ "    					?anoDadosBasicos6 ?tituloDoLivroDetalheArtigo6 ?tituloDoPeriodicoOuRevistaDetalheArtigo6 " 
					+ "						?fasciculoDetalheArtigo6 ?volumeDetalheArtigo6 ?realizadoNaInstituicao6 ?nomeInstituicaoEmpresa6 " 
					+ "						?nomeCurso6 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acadb4cfbd34-775c-4a6e-8895-cc3a7b22b34e-paolo-gessini> onto:nivel ?nomeCurso6 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-università-degli-studi-di-roma-la-sapienza,-urs,-itália> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa6 ."
					+ "			?realizadoNaInstituicao6 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa6 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-fábio-macêdo-mendes> onto:temProducaoBibliografica ?temProducaoBibliografica6 ."
					+ "			 ?temProducaoBibliografica6 onto:temDadosBasicos ?temDadosBasicos6 ."
					+ "			 ?temProducaoBibliografica6 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo6 ."
					+ "			 ?temDadosBasicos6 onto:titulo ?tituloDadosBasicos6 ."
					+ "			 ?temDadosBasicos6 onto:ano ?anoDadosBasicos6 ."
					+ "          ?temDetalhamentoDoArtigo6 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo6 ."
					+ "			 ?temDetalhamentoDoArtigo6 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo6 ."
					+ "		     ?temDetalhamentoDoArtigo6 onto:fasciculo ?fasciculoDetalheArtigo6 ."
					+ "			 ?temDetalhamentoDoArtigo6 onto:volume ?volumeDetalheArtigo6 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso6 = null, strNomeInstituicaoEmpresa6 = null, strTituloDadosBasicos6 = null, strAnoDadosBasicos6 = null, 
				   strTituloDoLivroDetalheArtigo6 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo6 = null, strFasciculoDetalheArtigo6 = null,
				   strVolumeDetalheArtigo6 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso6 = soln.getLiteral("nomeCurso6").toString();
				strNomeInstituicaoEmpresa6 = soln.getLiteral("nomeInstituicaoEmpresa6").toString();
				strTituloDadosBasicos6 = soln.getLiteral("tituloDadosBasicos6").toString();
				strAnoDadosBasicos6 = soln.getLiteral("anoDadosBasicos6").toString();
				strTituloDoLivroDetalheArtigo6 = soln.getLiteral("tituloDoLivroDetalheArtigo6").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo6 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo6").toString();
				strFasciculoDetalheArtigo6 = soln.getLiteral("fasciculoDetalheArtigo6").toString();
				strVolumeDetalheArtigo6 = soln.getLiteral("volumeDetalheArtigo6").toString();

				listaDestino.add(strNomeCurso6);
				listaDestino.add(strNomeInstituicaoEmpresa6);
				listaDestino.add(strTituloDadosBasicos6);
				listaDestino.add(strAnoDadosBasicos6);
				listaDestino.add(strTituloDoLivroDetalheArtigo6);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo6);
				listaDestino.add(strFasciculoDetalheArtigo6);
				listaDestino.add(strVolumeDetalheArtigo6);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino7() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica7 ?temDadosBasicos7 ?temDetalhamentoDoArtigo7 ?tituloDadosBasicos7 " 
					+ "    					?anoDadosBasicos7 ?tituloDoLivroDetalheArtigo7 ?tituloDoPeriodicoOuRevistaDetalheArtigo7 " 
					+ "						?fasciculoDetalheArtigo7 ?volumeDetalheArtigo7 ?realizadoNaInstituicao7 ?nomeInstituicaoEmpresa7 " 
					+ "						?nomeCurso7 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad4f171eb8-0cbb-4e7a-a029-a63975eff948-gilmar-silva-beserra> onto:nivel ?nomeCurso7 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa7 ."
					+ "			?realizadoNaInstituicao7 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa7 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-gilmar-silva-beserra> onto:temProducaoBibliografica ?temProducaoBibliografica7 ."
					+ "			 ?temProducaoBibliografica7 onto:temDadosBasicos ?temDadosBasicos7 ."
					+ "			 ?temProducaoBibliografica7 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo7 ."
					+ "			 ?temDadosBasicos7 onto:titulo ?tituloDadosBasicos7 ."
					+ "			 ?temDadosBasicos7 onto:ano ?anoDadosBasicos7 ."
					+ "          ?temDetalhamentoDoArtigo7 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo7 ."
					+ "			 ?temDetalhamentoDoArtigo7 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo7 ."
					+ "		     ?temDetalhamentoDoArtigo7 onto:fasciculo ?fasciculoDetalheArtigo7 ."
					+ "			 ?temDetalhamentoDoArtigo7 onto:volume ?volumeDetalheArtigo7 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso7 = null, strNomeInstituicaoEmpresa7 = null, strTituloDadosBasicos7 = null, strAnoDadosBasicos7 = null, 
				   strTituloDoLivroDetalheArtigo7 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo7 = null, strFasciculoDetalheArtigo7 = null,
				   strVolumeDetalheArtigo7 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso7 = soln.getLiteral("nomeCurso7").toString();
				strNomeInstituicaoEmpresa7 = soln.getLiteral("nomeInstituicaoEmpresa7").toString();
				strTituloDadosBasicos7 = soln.getLiteral("tituloDadosBasicos7").toString();
				strAnoDadosBasicos7 = soln.getLiteral("anoDadosBasicos7").toString();
				strTituloDoLivroDetalheArtigo7 = soln.getLiteral("tituloDoLivroDetalheArtigo7").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo7 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo7").toString();
				strFasciculoDetalheArtigo7 = soln.getLiteral("fasciculoDetalheArtigo7").toString();
				strVolumeDetalheArtigo7 = soln.getLiteral("volumeDetalheArtigo7").toString();

				listaDestino.add(strNomeCurso7);
				listaDestino.add(strNomeInstituicaoEmpresa7);
				listaDestino.add(strTituloDadosBasicos7);
				listaDestino.add(strAnoDadosBasicos7);
				listaDestino.add(strTituloDoLivroDetalheArtigo7);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo7);
				listaDestino.add(strFasciculoDetalheArtigo7);
				listaDestino.add(strVolumeDetalheArtigo7);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino8() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica8 ?temDadosBasicos8 ?temDetalhamentoDoArtigo8 ?tituloDadosBasicos8 " 
					+ "    					?anoDadosBasicos8 ?tituloDoLivroDetalheArtigo8 ?tituloDoPeriodicoOuRevistaDetalheArtigo8 " 
					+ "						?fasciculoDetalheArtigo8 ?volumeDetalheArtigo8 ?realizadoNaInstituicao8 ?nomeInstituicaoEmpresa8 " 
					+ "						?nomeCurso8 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad88a0c106-0b75-4596-afa9-1db70f91dfd1-alexandre-sérgio-de-araújo-bezerra> onto:nivel ?nomeCurso8 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa8 ."
					+ "			?realizadoNaInstituicao8 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa8 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-alexandre-sérgio-de-araújo-bezerra> onto:temProducaoBibliografica ?temProducaoBibliografica8 ."
					+ "			 ?temProducaoBibliografica8 onto:temDadosBasicos ?temDadosBasicos8 ."
					+ "			 ?temProducaoBibliografica8 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo8 ."
					+ "			 ?temDadosBasicos8 onto:titulo ?tituloDadosBasicos8 ."
					+ "			 ?temDadosBasicos8 onto:ano ?anoDadosBasicos8 ."
					+ "          ?temDetalhamentoDoArtigo8 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo8 ."
					+ "			 ?temDetalhamentoDoArtigo8 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo8 ."
					+ "		     ?temDetalhamentoDoArtigo8 onto:fasciculo ?fasciculoDetalheArtigo8 ."
					+ "			 ?temDetalhamentoDoArtigo8 onto:volume ?volumeDetalheArtigo8 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso8 = null, strNomeInstituicaoEmpresa8 = null, strTituloDadosBasicos8 = null, strAnoDadosBasicos8 = null, 
				   strTituloDoLivroDetalheArtigo8 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo8 = null, strFasciculoDetalheArtigo8 = null,
				   strVolumeDetalheArtigo8 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso8 = soln.getLiteral("nomeCurso8").toString();
				strNomeInstituicaoEmpresa8 = soln.getLiteral("nomeInstituicaoEmpresa8").toString();
				strTituloDadosBasicos8 = soln.getLiteral("tituloDadosBasicos8").toString();
				strAnoDadosBasicos8 = soln.getLiteral("anoDadosBasicos8").toString();
				strTituloDoLivroDetalheArtigo8 = soln.getLiteral("tituloDoLivroDetalheArtigo8").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo8 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo8").toString();
				strFasciculoDetalheArtigo8 = soln.getLiteral("fasciculoDetalheArtigo8").toString();
				strVolumeDetalheArtigo8 = soln.getLiteral("volumeDetalheArtigo8").toString();

				listaDestino.add(strNomeCurso8);
				listaDestino.add(strNomeInstituicaoEmpresa8);
				listaDestino.add(strTituloDadosBasicos8);
				listaDestino.add(strAnoDadosBasicos8);
				listaDestino.add(strTituloDoLivroDetalheArtigo8);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo8);
				listaDestino.add(strFasciculoDetalheArtigo8);
				listaDestino.add(strVolumeDetalheArtigo8);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino9() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica9 ?temDadosBasicos9 ?temDetalhamentoDoArtigo9 ?tituloDadosBasicos9 " 
					+ "    					?anoDadosBasicos9 ?tituloDoLivroDetalheArtigo9 ?tituloDoPeriodicoOuRevistaDetalheArtigo9 " 
					+ "						?fasciculoDetalheArtigo9 ?volumeDetalheArtigo9 ?realizadoNaInstituicao9 ?nomeInstituicaoEmpresa9 " 
					+ "						?nomeCurso9 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad190023a3-ac8d-48c6-b4da-f6759ecc00fa-eduardo-stockler-tognetti> onto:nivel ?nomeCurso9 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-são-paulo,-usp,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa9 ."
					+ "			?realizadoNaInstituicao9 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa9 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-eduardo-stockler-tognett> onto:temProducaoBibliografica ?temProducaoBibliografica9 ."
					+ "			 ?temProducaoBibliografica9 onto:temDadosBasicos ?temDadosBasicos9 ."
					+ "			 ?temProducaoBibliografica9 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo9 ."
					+ "			 ?temDadosBasicos9 onto:titulo ?tituloDadosBasicos9 ."
					+ "			 ?temDadosBasicos9 onto:ano ?anoDadosBasicos9 ."
					+ "          ?temDetalhamentoDoArtigo9 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo9 ."
					+ "			 ?temDetalhamentoDoArtigo9 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo9 ."
					+ "		     ?temDetalhamentoDoArtigo9 onto:fasciculo ?fasciculoDetalheArtigo9 ."
					+ "			 ?temDetalhamentoDoArtigo9 onto:volume ?volumeDetalheArtigo9 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso9 = null, strNomeInstituicaoEmpresa9 = null, strTituloDadosBasicos9 = null, strAnoDadosBasicos9 = null, 
				   strTituloDoLivroDetalheArtigo9 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo9 = null, strFasciculoDetalheArtigo9 = null,
				   strVolumeDetalheArtigo9 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso9 = soln.getLiteral("nomeCurso9").toString();
				strNomeInstituicaoEmpresa9 = soln.getLiteral("nomeInstituicaoEmpresa9").toString();
				strTituloDadosBasicos9 = soln.getLiteral("tituloDadosBasicos9").toString();
				strAnoDadosBasicos9 = soln.getLiteral("anoDadosBasicos9").toString();
				strTituloDoLivroDetalheArtigo9 = soln.getLiteral("tituloDoLivroDetalheArtigo9").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo9 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo9").toString();
				strFasciculoDetalheArtigo9 = soln.getLiteral("fasciculoDetalheArtigo9").toString();
				strVolumeDetalheArtigo9 = soln.getLiteral("volumeDetalheArtigo9").toString();

				listaDestino.add(strNomeCurso9);
				listaDestino.add(strNomeInstituicaoEmpresa9);
				listaDestino.add(strTituloDadosBasicos9);
				listaDestino.add(strAnoDadosBasicos9);
				listaDestino.add(strTituloDoLivroDetalheArtigo9);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo9);
				listaDestino.add(strFasciculoDetalheArtigo9);
				listaDestino.add(strVolumeDetalheArtigo9);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino10() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = "	PREFIX lattes: <http://www.semanticlattes.com.br/curriculo#>"
					+ "	PREFIX onto:   <http://www.ime.usp.br/ontolattes#>"
					+ "	PREFIX qualis: <http://qualis.capes.gov.br/qualis-capes.owl#>"
					+ "	PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#>"
					+ "	PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "	PREFIX owl:     <http://www.w3.org/2002/07/owl#>"
					+ "	PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#>"
					+ "		SELECT DISTINCT ?temProducaoBibliografica10 ?temDadosBasicos10 ?temDetalhamentoDoArtigo10 ?tituloDadosBasicos10 " 
					+ "    					?anoDadosBasicos10 ?tituloDoLivroDetalheArtigo10 ?tituloDoPeriodicoOuRevistaDetalheArtigo10 " 
					+ "						?fasciculoDetalheArtigo10 ?volumeDetalheArtigo10 ?realizadoNaInstituicao10 ?nomeInstituicaoEmpresa10 " 
					+ "						?nomeCurso10 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#form-acad811eb2f9-db42-4940-a463-b95d8223f1d8-jan-mendonça-correa> onto:nivel ?nomeCurso10 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa10 ."
					+ "			?realizadoNaInstituicao10 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa10 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-jan-mendonça-correa> onto:temProducaoBibliografica ?temProducaoBibliografica10 ."
					+ "			 ?temProducaoBibliografica10 onto:temDadosBasicos ?temDadosBasicos10 ."
					+ "			 ?temProducaoBibliografica10 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo10 ."
					+ "			 ?temDadosBasicos10 onto:titulo ?tituloDadosBasicos10 ."
					+ "			 ?temDadosBasicos10 onto:ano ?anoDadosBasicos10 ."
					+ "          ?temDetalhamentoDoArtigo10 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo10 ."
					+ "			 ?temDetalhamentoDoArtigo10 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo10 ."
					+ "		     ?temDetalhamentoDoArtigo10 onto:fasciculo ?fasciculoDetalheArtigo10 ."
					+ "			 ?temDetalhamentoDoArtigo10 onto:volume ?volumeDetalheArtigo10 . } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCurso10 = null, strNomeInstituicaoEmpresa10 = null, strTituloDadosBasicos10 = null, strAnoDadosBasicos10 = null, 
				   strTituloDoLivroDetalheArtigo10 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo10 = null, strFasciculoDetalheArtigo10 = null,
				   strVolumeDetalheArtigo10 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCurso10 = soln.getLiteral("nomeCurso10").toString();
				strNomeInstituicaoEmpresa10 = soln.getLiteral("nomeInstituicaoEmpresa10").toString();
				strTituloDadosBasicos10 = soln.getLiteral("tituloDadosBasicos10").toString();
				strAnoDadosBasicos10 = soln.getLiteral("anoDadosBasicos10").toString();
				strTituloDoLivroDetalheArtigo10 = soln.getLiteral("tituloDoLivroDetalheArtigo10").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo10 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo10").toString();
				strFasciculoDetalheArtigo10 = soln.getLiteral("fasciculoDetalheArtigo10").toString();
				strVolumeDetalheArtigo10 = soln.getLiteral("volumeDetalheArtigo10").toString();

				listaDestino.add(strNomeCurso10);
				listaDestino.add(strNomeInstituicaoEmpresa10);
				listaDestino.add(strTituloDadosBasicos10);
				listaDestino.add(strAnoDadosBasicos10);
				listaDestino.add(strTituloDoLivroDetalheArtigo10);
				listaDestino.add(strTituloDoPeriodicoOuRevistaDetalheArtigo10);
				listaDestino.add(strFasciculoDetalheArtigo10);
				listaDestino.add(strVolumeDetalheArtigo10);
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
}