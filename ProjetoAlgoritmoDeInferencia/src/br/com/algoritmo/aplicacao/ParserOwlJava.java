package br.com.algoritmo.aplicacao;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ParserOwlJava {
	public static int tamanhoListaBase = 0;
	public static int tamanhoListaDestino = 0;
	public static ArrayList<String> listaBase = null;
	public static ArrayList<String> listaDestino = null;

	public ArrayList<String> consultaBase() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-luiz-carlos-miyadaira-ribeiro-junior> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			System.out.println("QUERY: " + query);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaBase = new ArrayList<String>();
			tamanhoListaBase = 0;
			String strTituloDadosBasicos = null;
			System.out.println("RESULTADO: " + query);
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaBase.add(strTituloDadosBasicos);

				tamanhoListaBase++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "  <http://www.ime.usp.br/ontolattes#cv-rejane-maria-da-costa-figueiredo> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-sergio-antônio-andrade-de-freitas> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-edgard-costa-oliveira> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-edson-mintsu-hung> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-edson-alves-da-costa-júnior> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-alessandro-borges-de-sousa-oliveira> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-suélia-de-siqueira-rodrigues-fleury-rosa> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-andré-barros-de-sales> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-andré-gustavo-de-melo-araújo> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
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
			in = new FileInputStream(new File("Teste6.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-giovanni-almeida-santos> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			listaDestino = new ArrayList<String>();
			tamanhoListaDestino = 0;
			String strTituloDadosBasicos = null;

			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				listaDestino.add(strTituloDadosBasicos);

				tamanhoListaDestino++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return listaDestino;
	}
}