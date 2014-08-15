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
		//new ParserOwlJava().consultaDestino3();
/*		new ParserOwlJava().consultaDestino2();
		new ParserOwlJava().consultaDestino3();*/
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
			in = new FileInputStream(new File("Teste5.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
			+ "	SELECT DISTINCT ?tituloDadosBasicos "
			+   	"WHERE "
			+   	"  { "
			+   	"   <http://www.ime.usp.br/ontolattes#cv-luiz-carlos-miyadaira-ribeiro-junior> onto:temProducaoBibliografica ?temProducaoBibliografica ."
			+   	"    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
			+   	"    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
			+   	"  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			System.out.println("QUERY: "+query);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaBase = new ArrayList<String>();
			tamanhoListaBase = 0;
			String strTituloDadosBasicos = null;
			System.out.println("RESULTADO: "+query);
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				
				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos").toString();

				listaBase.add(strTituloDadosBasicos);

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
			in = new FileInputStream(new File("Teste5.owl"));
			Model model = ModelFactory.createMemModelMaker().createDefaultModel();
			model.read(in, null); 

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
			+ "	SELECT DISTINCT ?tituloDadosBasicos "
			+   	"WHERE "
			+   	"  { "
			+   	"   <http://www.ime.usp.br/ontolattes#cv-sergio-antônio-andrade-de-freitas> onto:temProducaoBibliografica ?temProducaoBibliografica ."
			+   	"    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
			+   	"    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
			+   	"  } ";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;
			
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos").toString();

				listaDestino.add(strTituloDadosBasicos);
				
				tamanhoListaDestino ++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
	
	public ArrayList<String> consultaDestino2() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso2 ?nomeCompleto2 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-sergio-antônio-andrade-de-freitas> onto:nomeCompleto ?nomeCompleto2 ."	
					+ "		<http://www.ime.usp.br/ontolattes#form-acad22043d29-b67c-48e0-8e0b-1cf8c9934930-sergio-antônio-andrade-de-freitas> onto:nivel ?nomeCurso2 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-federal-de-uberlândia,-ufu,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa2 ."
					+ "			?realizadoNaInstituicao2 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa2 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-sergio-antônio-andrade-de-freitas> onto:temProducaoBibliografica ?temProducaoBibliografica2 ."
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
			String strNomeCompleto2 = null, strNomeCurso2 = null, strNomeInstituicaoEmpresa2 = null, strTituloDadosBasicos2 = null, strAnoDadosBasicos2 = null, 
				   strTituloDoLivroDetalheArtigo2 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo2 = null, strFasciculoDetalheArtigo2 = null,
				   strVolumeDetalheArtigo2 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto2 = soln.getLiteral("nomeCompleto2").toString();
				strNomeCurso2 = soln.getLiteral("nomeCurso2").toString();
				strNomeInstituicaoEmpresa2 = soln.getLiteral("nomeInstituicaoEmpresa2").toString();
				strTituloDadosBasicos2 = soln.getLiteral("tituloDadosBasicos2").toString();
				strAnoDadosBasicos2 = soln.getLiteral("anoDadosBasicos2").toString();
				strTituloDoLivroDetalheArtigo2 = soln.getLiteral("tituloDoLivroDetalheArtigo2").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo2 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo2").toString();
				strFasciculoDetalheArtigo2 = soln.getLiteral("fasciculoDetalheArtigo2").toString();
				strVolumeDetalheArtigo2 = soln.getLiteral("volumeDetalheArtigo2").toString();

				listaDestino.add(strNomeCompleto2);
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

	public ArrayList<String> consultaDestino3() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso1 ?nomeCompleto1 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-edgard-costa-oliveira> onto:nomeCompleto ?nomeCompleto1 ."	
					+ "		<http://www.ime.usp.br/ontolattes#form-acaddc594282-62c1-4583-b871-452d3471ae86-edgard-costa-oliveira> onto:nivel ?nomeCurso1 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-centro-de-ensino-unificado-de-brasília> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "			?realizadoNaInstituicao1 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-edgard-costa-oliveira> onto:temProducaoBibliografica ?temProducaoBibliografica1 ."
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
			String strNomeCompleto1 = null, strNomeCurso1 = null, strNomeInstituicaoEmpresa1 = null, strTituloDadosBasicos1 = null, strAnoDadosBasicos1 = null, 
				   strTituloDoLivroDetalheArtigo1 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo1 = null, strFasciculoDetalheArtigo1 = null,
				   strVolumeDetalheArtigo1 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto1 = soln.getLiteral("nomeCompleto1").toString();
				strNomeCurso1 = soln.getLiteral("nomeCurso1").toString();
				strNomeInstituicaoEmpresa1 = soln.getLiteral("nomeInstituicaoEmpresa1").toString();
				strTituloDadosBasicos1 = soln.getLiteral("tituloDadosBasicos1").toString();
				strAnoDadosBasicos1 = soln.getLiteral("anoDadosBasicos1").toString();
				strTituloDoLivroDetalheArtigo1 = soln.getLiteral("tituloDoLivroDetalheArtigo1").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo1 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo1").toString();
				strFasciculoDetalheArtigo1 = soln.getLiteral("fasciculoDetalheArtigo1").toString();
				strVolumeDetalheArtigo1 = soln.getLiteral("volumeDetalheArtigo1").toString();

				listaDestino.add(strNomeCompleto1);
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
	
	public ArrayList<String> consultaDestino4() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso4 ?nomeCompleto4 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-adson-ferreira-da-rocha> onto:nomeCompleto ?nomeCompleto4 ."	
					+ "		<http://www.ime.usp.br/ontolattes#form-acadc88aa5ad-8e8e-459b-9011-2e301a7a14e9-adson-ferreira-da-rocha> onto:nivel ?nomeCurso4 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa4 ."
					+ "			?realizadoNaInstituicao4 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa4 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-adson-ferreira-da-rocha> onto:temProducaoBibliografica ?temProducaoBibliografica4 ."
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
			String strNomeCompleto4 = null, strNomeCurso4 = null, strNomeInstituicaoEmpresa4 = null, strTituloDadosBasicos4 = null, strAnoDadosBasicos4 = null, 
				   strTituloDoLivroDetalheArtigo4 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo4 = null, strFasciculoDetalheArtigo4 = null,
				   strVolumeDetalheArtigo4 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto4 = soln.getLiteral("nomeCompleto4").toString();
				strNomeCurso4 = soln.getLiteral("nomeCurso4").toString();
				strNomeInstituicaoEmpresa4 = soln.getLiteral("nomeInstituicaoEmpresa4").toString();
				strTituloDadosBasicos4 = soln.getLiteral("tituloDadosBasicos4").toString();
				strAnoDadosBasicos4 = soln.getLiteral("anoDadosBasicos4").toString();
				strTituloDoLivroDetalheArtigo4 = soln.getLiteral("tituloDoLivroDetalheArtigo4").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo4 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo4").toString();
				strFasciculoDetalheArtigo4 = soln.getLiteral("fasciculoDetalheArtigo4").toString();
				strVolumeDetalheArtigo4 = soln.getLiteral("volumeDetalheArtigo4").toString();

				listaDestino.add(strNomeCompleto4);
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
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?anoDadosBasicos5 ?tituloDoLivroDetalheArtigo5 ?tituloDoPeriodicoOuRevistaDetalheArtigo5 " 
					+ "						?fasciculoDetalheArtigo5 ?volumeDetalheArtigo5 ?realizadoNaInstituicao5 ?nomeInstituicaoEmpresa5 " 
					+ "						?nomeCurso5 ?nomeCompleto5 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-edson-mintsu-hung> onto:nomeCompleto ?nomeCompleto5 ."	
					+ "		<http://www.ime.usp.br/ontolattes#form-acad210912f6-d690-4a65-b9ef-76bfba7ef0be-edson-mintsu-hung> onto:nivel ?nomeCurso5 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa5 ."
					+ "			?realizadoNaInstituicao5 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa5 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-edson-mintsu-hung> onto:temProducaoBibliografica ?temProducaoBibliografica5 ."
					+ "			 ?temProducaoBibliografica5 onto:temDadosBasicos ?temDadosBasicos5 ."
					+ "			 ?temProducaoBibliografica5 onto:temDetalhamentoDoArtigo ?temDetalhamentoDoArtigo5 ."
					+ "			 ?temDadosBasicos5 onto:titulo ?tituloDadosBasicos5 ."
					+ "			 ?temDadosBasicos5 onto:ano ?anoDadosBasicos5. "
					+ "          ?temDetalhamentoDoArtigo5 onto:tituloDoLivro ?tituloDoLivroDetalheArtigo5 ."
					+ "			 ?temDetalhamentoDoArtigo5 onto:tituloDoPeriodicoOuRevista ?tituloDoPeriodicoOuRevistaDetalheArtigo5 ."
					+ "		     ?temDetalhamentoDoArtigo5 onto:fasciculo ?fasciculoDetalheArtigo5 ."
					+ "			 ?temDetalhamentoDoArtigo5 onto:volume ?volumeDetalheArtigo5.}";
			
			com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strNomeCompleto5 = null, strNomeCurso5 = null, strNomeInstituicaoEmpresa5 = null, strTituloDadosBasicos5 = null, strAnoDadosBasicos5 = null, 
				   strTituloDoLivroDetalheArtigo5 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo5 = null, strFasciculoDetalheArtigo5 = null,
				   strVolumeDetalheArtigo5 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto5 = soln.getLiteral("nomeCompleto5").toString();
				strNomeCurso5 = soln.getLiteral("nomeCurso5").toString();
				strNomeInstituicaoEmpresa5 = soln.getLiteral("nomeInstituicaoEmpresa5").toString();
				strTituloDadosBasicos5 = soln.getLiteral("tituloDadosBasicos5").toString();
				strAnoDadosBasicos5 = soln.getLiteral("anoDadosBasicos5").toString();
				strTituloDoLivroDetalheArtigo5 = soln.getLiteral("tituloDoLivroDetalheArtigo5").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo5 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo5").toString();
				strFasciculoDetalheArtigo5 = soln.getLiteral("fasciculoDetalheArtigo5").toString();
				strVolumeDetalheArtigo5 = soln.getLiteral("volumeDetalheArtigo5").toString();

				listaDestino.add(strNomeCompleto5);
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
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso6 ?nomeCompleto6 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-edson-alves-da-costa-júnior> onto:nomeCompleto ?nomeCompleto6 ."	
					+ "		<http://www.ime.usp.br/ontolattes#form-acad9f219c08-c304-4d0a-8d0e-3b72a0d1ad3b-edson-alves-da-costa-júnior> onto:nivel ?nomeCurso6 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa6 ."
					+ "			?realizadoNaInstituicao6 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa6 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-edson-alves-da-costa-júnior> onto:temProducaoBibliografica ?temProducaoBibliografica6 ."
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
			String strNomeCompleto6 = null, strNomeCurso6 = null, strNomeInstituicaoEmpresa6 = null, strTituloDadosBasicos6 = null, strAnoDadosBasicos6 = null, 
				   strTituloDoLivroDetalheArtigo6 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo6 = null, strFasciculoDetalheArtigo6 = null,
				   strVolumeDetalheArtigo6 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto6 = soln.getLiteral("nomeCompleto6").toString();
				strNomeCurso6 = soln.getLiteral("nomeCurso6").toString();
				strNomeInstituicaoEmpresa6 = soln.getLiteral("nomeInstituicaoEmpresa6").toString();
				strTituloDadosBasicos6 = soln.getLiteral("tituloDadosBasicos6").toString();
				strAnoDadosBasicos6 = soln.getLiteral("anoDadosBasicos6").toString();
				strTituloDoLivroDetalheArtigo6 = soln.getLiteral("tituloDoLivroDetalheArtigo6").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo6 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo6").toString();
				strFasciculoDetalheArtigo6 = soln.getLiteral("fasciculoDetalheArtigo6").toString();
				strVolumeDetalheArtigo6 = soln.getLiteral("volumeDetalheArtigo6").toString();

				listaDestino.add(strNomeCompleto6);
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
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso1 ?nomeCompleto1 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-alessandro-borges-de-sousa-oliveira> onto:nomeCompleto ?nomeCompleto1 ."	
					+ "		<http://www.ime.usp.br/ontolattes#form-acad583c871a-09aa-429f-875e-5f8f18dbe52a-alessandro-borges-de-sousa-oliveira> onto:nivel ?nomeCurso1 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-brasília,-unb,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "			?realizadoNaInstituicao1 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa1 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-alessandro-borges-de-sousa-oliveira> onto:temProducaoBibliografica ?temProducaoBibliografica1 ."
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
			String strNomeCompleto1 = null, strNomeCurso1 = null, strNomeInstituicaoEmpresa1 = null, strTituloDadosBasicos1 = null, strAnoDadosBasicos1 = null, 
				   strTituloDoLivroDetalheArtigo1 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo1 = null, strFasciculoDetalheArtigo1 = null,
				   strVolumeDetalheArtigo1 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto1 = soln.getLiteral("nomeCompleto1").toString();
				strNomeCurso1 = soln.getLiteral("nomeCurso1").toString();
				strNomeInstituicaoEmpresa1 = soln.getLiteral("nomeInstituicaoEmpresa1").toString();
				strTituloDadosBasicos1 = soln.getLiteral("tituloDadosBasicos1").toString();
				strAnoDadosBasicos1 = soln.getLiteral("anoDadosBasicos1").toString();
				strTituloDoLivroDetalheArtigo1 = soln.getLiteral("tituloDoLivroDetalheArtigo1").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo1 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo1").toString();
				strFasciculoDetalheArtigo1 = soln.getLiteral("fasciculoDetalheArtigo1").toString();
				strVolumeDetalheArtigo1 = soln.getLiteral("volumeDetalheArtigo1").toString();

				listaDestino.add(strNomeCompleto1);
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
	
	public ArrayList<String> consultaDestino8() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso8 ?nomeCompleto8 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-suélia-de-siqueira-rodrigues-fleury-rosa> onto:nomeCompleto ?nomeCompleto8 ."	
					+ "		<http://www.ime.usp.br/ontolattes#form-acad716e5537-6ae2-40fe-8ab4-acffda730a40-suélia-de-siqueira-rodrigues-fleury-rosa> onto:nivel ?nomeCurso8 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-estadual-paulista-júlio-de-mesquita-filho,-unesp,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa8 ."
					+ "			?realizadoNaInstituicao8 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa8 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-suélia-de-siqueira-rodrigues-fleury-rosa> onto:temProducaoBibliografica ?temProducaoBibliografica8 ."
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
			String strNomeCompleto8 = null, strNomeCurso8 = null, strNomeInstituicaoEmpresa8 = null, strTituloDadosBasicos8 = null, strAnoDadosBasicos8 = null, 
				   strTituloDoLivroDetalheArtigo8 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo8 = null, strFasciculoDetalheArtigo8 = null,
				   strVolumeDetalheArtigo8 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto8= soln.getLiteral("nomeCompleto8").toString();
				strNomeCurso8 = soln.getLiteral("nomeCurso8").toString();
				strNomeInstituicaoEmpresa8 = soln.getLiteral("nomeInstituicaoEmpresa8").toString();
				strTituloDadosBasicos8 = soln.getLiteral("tituloDadosBasicos8").toString();
				strAnoDadosBasicos8 = soln.getLiteral("anoDadosBasicos8").toString();
				strTituloDoLivroDetalheArtigo8 = soln.getLiteral("tituloDoLivroDetalheArtigo8").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo8 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo8").toString();
				strFasciculoDetalheArtigo8 = soln.getLiteral("fasciculoDetalheArtigo8").toString();
				strVolumeDetalheArtigo8 = soln.getLiteral("volumeDetalheArtigo8").toString();

				listaDestino.add(strNomeCompleto8);
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
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso9 ?nomeCompleto9 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-andré-barros-de-sales> onto:nomeCompleto ?nomeCompleto9 ."
					+ "		<http://www.ime.usp.br/ontolattes#form-acadd4e48b85-e40c-4caa-b5e2-00091b4e1cf3-andré-barros-de-sales> onto:nivel ?nomeCurso9 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-em-tecnologia-em-processamento-de-dados> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa9 ."
					+ "			?realizadoNaInstituicao9 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa9 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-andré-barros-de-sales> onto:temProducaoBibliografica ?temProducaoBibliografica9 ."
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
			String strNomeCompleto9 = null, strNomeCurso9 = null, strNomeInstituicaoEmpresa9 = null, strTituloDadosBasicos9 = null, strAnoDadosBasicos9 = null, 
				   strTituloDoLivroDetalheArtigo9 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo9 = null, strFasciculoDetalheArtigo9 = null,
				   strVolumeDetalheArtigo9 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto9 = soln.getLiteral("nomeCompleto9").toString();
				strNomeCurso9 = soln.getLiteral("nomeCurso9").toString();
				strNomeInstituicaoEmpresa9 = soln.getLiteral("nomeInstituicaoEmpresa9").toString();
				strTituloDadosBasicos9 = soln.getLiteral("tituloDadosBasicos9").toString();
				strAnoDadosBasicos9 = soln.getLiteral("anoDadosBasicos9").toString();
				strTituloDoLivroDetalheArtigo9 = soln.getLiteral("tituloDoLivroDetalheArtigo9").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo9 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo9").toString();
				strFasciculoDetalheArtigo9 = soln.getLiteral("fasciculoDetalheArtigo9").toString();
				strVolumeDetalheArtigo9 = soln.getLiteral("volumeDetalheArtigo9").toString();

				listaDestino.add(strNomeCompleto9);
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
			in = new FileInputStream(new File("Teste5.owl"));
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
					+ "						?nomeCurso10 ?nomeCompleto10 "
					+ "WHERE {	"
					+ "		<http://www.ime.usp.br/ontolattes#dg-andré-gustavo-de-melo-araújo> onto:nomeCompleto ?nomeCompleto10 ."
					+ "		<http://www.ime.usp.br/ontolattes#form-acad4aa0e193-e73c-4a51-b21e-d6373d5b678a-andré-gustavo-de-melo-araújo> onto:nivel ?nomeCurso10 ."
					+ "		<http://www.ime.usp.br/ontolattes#inst-universidade-de-são-paulo,-usp,-brasil> onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa10 ."
					+ "			?realizadoNaInstituicao10 onto:nomeInstituicaoEmpresa ?nomeInstituicaoEmpresa10 ."
					+ "		 <http://www.ime.usp.br/ontolattes#cv-andré-gustavo-de-melo-araújo> onto:temProducaoBibliografica ?temProducaoBibliografica10 ."
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
			String strNomeCompleto10 = null, strNomeCurso10 = null, strNomeInstituicaoEmpresa10 = null, strTituloDadosBasicos10 = null, strAnoDadosBasicos10 = null, 
				   strTituloDoLivroDetalheArtigo10 = null, strTituloDoPeriodicoOuRevistaDetalheArtigo10 = null, strFasciculoDetalheArtigo10 = null,
				   strVolumeDetalheArtigo10 = null;
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strNomeCompleto10 = soln.getLiteral("nomeCompleto10").toString();
				strNomeCurso10 = soln.getLiteral("nomeCurso10").toString();
				strNomeInstituicaoEmpresa10 = soln.getLiteral("nomeInstituicaoEmpresa10").toString();
				strTituloDadosBasicos10 = soln.getLiteral("tituloDadosBasicos10").toString();
				strAnoDadosBasicos10 = soln.getLiteral("anoDadosBasicos10").toString();
				strTituloDoLivroDetalheArtigo10 = soln.getLiteral("tituloDoLivroDetalheArtigo10").toString();
				strTituloDoPeriodicoOuRevistaDetalheArtigo10 = soln.getLiteral("tituloDoPeriodicoOuRevistaDetalheArtigo10").toString();
				strFasciculoDetalheArtigo10 = soln.getLiteral("fasciculoDetalheArtigo10").toString();
				strVolumeDetalheArtigo10 = soln.getLiteral("volumeDetalheArtigo10").toString();

				listaDestino.add(strNomeCompleto10);
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