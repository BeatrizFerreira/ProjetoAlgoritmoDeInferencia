package br.com.algoritmo.restful;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.algoritmo.aplicacao.TrataString;

@Path("/AderenciaPerfilLattes")
public class AderenciaPerfilLattesService {
	@SuppressWarnings("unchecked")
	@POST
	@Path("/aderencia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getValorAderencia(String dados) throws IOException, ParseException{
		
		JSONParser parser = new JSONParser();
		JSONObject dados_requisicao = (JSONObject) parser.parse(dados);
		
		JSONObject individuo_base = (JSONObject) parser.parse(dados_requisicao.get("individuo_base").toString());
		JSONObject individuo_destino = (JSONObject) parser.parse(dados_requisicao.get("individuo_destino").toString());
		
		String cv_base_conteudo = dados_requisicao.get("cv_base").toString();
		this.salvarArquivoHTML(individuo_base.get("id_base").toString(), cv_base_conteudo);
		this.salvarArquivoHTML(individuo_destino.get("id_destino").toString(), dados_requisicao.get("cv_destino").toString());
				
		File lista_cvs_lattes = new File("Curriculos/cvs.list");
		if (!lista_cvs_lattes.exists()) {
			lista_cvs_lattes.createNewFile();
		}
		
		FileWriter escritor = new FileWriter(lista_cvs_lattes.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(escritor);
		bw.write(individuo_base.get("id_base").toString() + ", " + individuo_base.get("nome_base").toString() + "\n");
		bw.write(individuo_destino.get("id_destino").toString() + ", " + individuo_destino.get("nome_destino").toString() + "\n");
		bw.close();
		
		//TODO: conversao de arquivos para RDF - executar comando no scriptLattes
		//this.gerarOntologiaScriptLattes();
		
		TrataString trataString = new TrataString();
		ArrayList<String> destinos = new ArrayList<String>();
		destinos.add(individuo_destino.get("nome_destino").toString());
		trataString.calculaFatorAderencia(individuo_base.get("nome_base").toString(), destinos);

		JSONObject resposta = new JSONObject();
		resposta.put((String) "valor_total_somado", trataString.getQuantidadeProducoesAderentes());
		resposta.put("percentual_aderencia", trataString.getValoresPercentuaisAderencia());
		resposta.put("equivalencias", trataString.getEquivalencias());
		resposta.put("individuo_base", individuo_base);
		resposta.put("individuo_destino", individuo_destino);

		System.out.println(resposta.toString());
		
		//DELETAR TODOS OS ARQUIVOS GERADOS NO FINAL

		return resposta.toString();
	}
	
	private void salvarArquivoHTML(String nome_arquivo, String conteudo) throws IOException{
		File arquivo_base = new File("Curriculos/" + nome_arquivo);
		if (!arquivo_base.exists()) {
			arquivo_base.createNewFile();
		}
		
		Writer out = new OutputStreamWriter(new FileOutputStream(arquivo_base.getAbsoluteFile()), "cp1252");
		out.write(conteudo);
		out.close();
	}
	
	private void gerarOntologiaScriptLattes() throws IOException{
		ProcessBuilder pb = new ProcessBuilder("./scriptLattes.py", "padrao.config");
		pb.directory(new File("/home/beatriz/ScriptLattes"));
		pb.start();
		System.out.println("heyyy");
	}

}