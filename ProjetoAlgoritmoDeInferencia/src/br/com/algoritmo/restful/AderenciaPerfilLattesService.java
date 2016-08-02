package br.com.algoritmo.restful;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		System.out.println(dados);
		JSONParser parser = new JSONParser();
		JSONObject dados_requisicao = (JSONObject) parser.parse(dados);
		
		String individuo_base_nome = dados_requisicao.get("individuo_base_nome").toString();
		String individuos_destino_nomes = dados_requisicao.get("individuos_destino_nomes").toString();

		//receber nome dos individuos
		//tratar arquivo recebido via json
		//transformar aquivos em rdf
		
		ArrayList<String> destinos = new ArrayList<String>();
		destinos.add("Fábio Macêdo Mendes");
		String individuoBase = "Luiz Carlos Miyadaira Ribeiro Junior";
		TrataString trataString = new TrataString();
		trataString.calculaFatorAderencia(individuoBase, destinos);
				
		JSONObject resposta = new JSONObject();
		resposta.put((String) "valor_total_somado", trataString.getQuantidadeProducoesAderentes());
		resposta.put("percentual_aderencia", trataString.getValoresPercentuaisAderencia());
		resposta.put("equivalencias", trataString.getEquivalencias());
		resposta.put("individuo_base_nome", individuo_base_nome);
		resposta.put("individuos_destino_nomes", individuos_destino_nomes);
		
		System.out.println(resposta.toString());

		return resposta.toString();
	}

}