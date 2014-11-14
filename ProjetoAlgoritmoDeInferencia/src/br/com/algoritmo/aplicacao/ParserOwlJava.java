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
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
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
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "  <http://www.ime.usp.br/ontolattes#cv-sergio-antônio-andrade-de-freitas> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-andre-luiz-aquere-de-cerqueira-e-souza> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
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

	public ArrayList<String> consultaDestino4() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
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

	public ArrayList<String> consultaDestino5() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
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
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
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

	public ArrayList<String> consultaDestino7() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
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

	public ArrayList<String> consultaDestino8() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-cristiane-soares-ramos> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-fabricio-ataides-braz> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-alexandre-sérgio-de-araújo-bezerra> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino11() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-eduardo-stockler-tognetti> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino12() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-jan-mendonça-correa> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino13() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-fabiana-freitas-mendes> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino14() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-augusto-césar-de-mendonça-brasil> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino15() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-fábio-macêdo-mendes> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino16() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-jose-felicio-da-silva> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino17() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-tiago-ribeiro-duarte> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino18() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-agatha-pitombo-bacelar> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino19() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-gláucio-de-castro-júnior> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino20() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-andrea-felippe-cabello> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	
	public ArrayList<String> consultaDestino21() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-marcelo-de-oliveira-torres> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino22() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-tiago-ribeiro-duarte> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino23() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-carla-silva-rocha-aguiar> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino24() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-edna-dias-canedo> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino25() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-hilmer-rodrigues-neri> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino26() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-marcelino-monteiro-de-andrade> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino27() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-paulo-roberto-miranda-meirelles> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino28() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-ricardo-matos-chaim> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino29() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-vinícius-de-carvalho-rispoli> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino30() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-suzana-moreira-avila> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino31() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-rita-de-cássia-silva> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino32() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-paula-lucia-ferrucio-da-rocha> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino33() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-marcus-santos-mota> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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
	public ArrayList<String> consultaDestino34() {
		InputStream in = null;
		ResultSet results = null;
		try {
			in = new FileInputStream(new File("Ontologias/Ontologia.owl"));
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-cesar-lignelli> onto:temProducaoBibliografica ?temProducaoBibliografica ."
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