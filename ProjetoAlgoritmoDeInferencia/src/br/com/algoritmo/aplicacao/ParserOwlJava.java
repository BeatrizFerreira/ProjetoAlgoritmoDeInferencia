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
	public static ArrayList<String> lista = null;
	public static int tamanhoLista = 0;
	
	public ArrayList<String> consultaProducaoBibliografica(String nomeIndividuo) {
		InputStream in = null;
		ResultSet results = null;
		lista = new ArrayList<String>();
		try {
			in = new FileInputStream(new File("Curriculos/saida/cvs_lattes.owl"));
			
			Model model = ModelFactory.createMemModelMaker()
					.createDefaultModel();
			model.read(in, null);

			String queryString = " PREFIX  onto: <http://www.ime.usp.br/ontolattes#> "
					+ "	SELECT DISTINCT ?tituloDadosBasicos "
					+ "WHERE "
					+ "  { "
					+ "   <http://www.ime.usp.br/ontolattes#cv-"
					+ nomeIndividuo.toLowerCase().replace(' ', '-')
					+ "> onto:temProducaoBibliografica ?temProducaoBibliografica ."
					+ "    ?temProducaoBibliografica onto:temDadosBasicos  ?temDadosBasicos ."
					+ "    ?temDadosBasicos onto:titulo ?tituloDadosBasicos ."
					+ "  } ";

			com.hp.hpl.jena.query.Query query = QueryFactory
					.create(queryString);
			System.out.println("QUERY: " + query);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			tamanhoLista = 0;
			String strTituloDadosBasicos = null;
			System.out.println("RESULTADO: " + query);
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();

				strTituloDadosBasicos = soln.getLiteral("tituloDadosBasicos")
						.toString();

				lista.add(strTituloDadosBasicos);

				tamanhoLista++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return lista;
	}

}