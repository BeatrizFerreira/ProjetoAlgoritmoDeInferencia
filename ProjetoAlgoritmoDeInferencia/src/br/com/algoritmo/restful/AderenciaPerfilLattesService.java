package br.com.algoritmo.restful;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public static final String TMP_DIR = System.getProperty("java.io.tmpdir");
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/aderenciaSimples")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getValorAderenciaSimples(String dados) throws IOException, ParseException, InterruptedException{
		
		JSONParser parser = new JSONParser();
		JSONObject dados_requisicao = (JSONObject) parser.parse(dados);
		
		JSONObject individuo_base = (JSONObject) parser.parse(dados_requisicao.get("individuo_base").toString());
		JSONObject individuo_destino = (JSONObject) parser.parse(dados_requisicao.get("individuo_destino").toString());
		
		String cv_base_conteudo = dados_requisicao.get("cv_base").toString();
		this.salvarArquivoHTML(individuo_base.get("id_base").toString(), cv_base_conteudo);
		this.salvarArquivoHTML(individuo_destino.get("id_destino").toString(), dados_requisicao.get("cv_destino").toString());
				
		// File lista_cvs_lattes = new File("Curriculos/cvs.list");
		File cvs_dir = new File(TMP_DIR + "/Curriculos/");
		cvs_dir.mkdirs();
		File lista_cvs_lattes = new File(cvs_dir, "cvs.list");
		if (!lista_cvs_lattes.exists()) {
			lista_cvs_lattes.createNewFile();
		}
		
		FileWriter escritor = new FileWriter(lista_cvs_lattes.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(escritor);
		bw.write(individuo_base.get("id_base").toString() + ", " + individuo_base.get("nome_base").toString() + "\n");
		bw.write(individuo_destino.get("id_destino").toString() + ", " + individuo_destino.get("nome_destino").toString() + "\n");
		bw.close();
		
		this.gerarOntologiaScriptLattes();
		
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
		
		this.removerArquivos(new File(TMP_DIR + "/Curriculos/"));
		this.removerArquivos(new File(TMP_DIR + "/Curriculos/saida/"));

		System.out.println(resposta.toString());
		return resposta.toString();
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/aderenciaComposta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getValorAderenciaComposta(String dados) throws IOException, ParseException, InterruptedException{
		JSONObject resposta = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONObject dados_requisicao = (JSONObject) parser.parse(dados);
		
		JSONObject individuo_base = (JSONObject) parser.parse(dados_requisicao.get("individuo_base").toString());
		JSONObject individuos_destino = (JSONObject) parser.parse(dados_requisicao.get("individuos_destino").toString());
		String cv_base_conteudo = dados_requisicao.get("cv_base").toString();
		JSONObject cvs_destino_conteudo = (JSONObject) parser.parse(dados_requisicao.get("cvs_destino").toString());
		
		ArrayList<String> destinos = new ArrayList<String>(individuos_destino.keySet());
		
		this.salvarArquivoHTML(individuo_base.get("id_base").toString(), cv_base_conteudo);

		// para cada individuo destino, escrever um cvs.list e mandar executar o restante das coisas
		// File lista_cvs_lattes = new File("Curriculos/cvs.list");
		File cvs_dir = new File(TMP_DIR + "/Curriculos/");
		cvs_dir.mkdirs();
		File lista_cvs_lattes = new File(cvs_dir, "cvs.list");
		if (!lista_cvs_lattes.exists()) {
			lista_cvs_lattes.createNewFile();
		}
		
		for (int i = 0; i < cvs_destino_conteudo.size(); i++){
			FileWriter escritor = new FileWriter(lista_cvs_lattes.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(escritor);
			bw.write(individuo_base.get("id_base").toString() + ", " + individuo_base.get("nome_base").toString() + "\n");
			bw.write(individuos_destino.get(destinos.get(i)) + ", " + destinos.get(i) + "\n");
			bw.close();
			this.salvarArquivoHTML(individuos_destino.get(destinos.get(i)).toString(), cvs_destino_conteudo.get(destinos.get(i)).toString());
			this.gerarOntologiaScriptLattes();
			TrataString trataString = new TrataString();
			trataString.calculaFatorAderencia(individuo_base.get("nome_base").toString(), destinos);
			JSONObject resultadoIndividualAderencia = new JSONObject();
			resultadoIndividualAderencia.put("valor_total_somado", trataString.getQuantidadeProducoesAderentes());
			resultadoIndividualAderencia.put("percentual_aderencia", trataString.getValoresPercentuaisAderencia());
			resultadoIndividualAderencia.put("equivalencias", trataString.getEquivalencias());
			resultadoIndividualAderencia.put("individuo_destino", destinos.get(i));
			resposta.put(individuo_base.get("id_base").toString() + '_' + individuos_destino.get(destinos.get(i)).toString(), resultadoIndividualAderencia);
		}
		
		resposta.put("individuo_base", individuo_base.get("nome_base").toString());
		
		this.removerArquivos(new File(TMP_DIR + "/Curriculos/"));
		this.removerArquivos(new File(TMP_DIR + "/Curriculos/saida/"));

		return resposta.toString();
	}

	
	private void salvarArquivoHTML(String nome_arquivo, String conteudo) throws IOException{
		// File arquivo_base = new File("Curriculos/" + nome_arquivo);
		File cvs_dir = new File(TMP_DIR + "/Curriculos/");
		cvs_dir.mkdirs();
		File arquivo_base = new File(cvs_dir,nome_arquivo);

		if (!arquivo_base.exists()) {
			arquivo_base.createNewFile();
		}
		
		Writer out = new OutputStreamWriter(new FileOutputStream(arquivo_base.getAbsoluteFile()), "cp1252");
		out.write(conteudo);
		out.close();
	}

	private void gerarOntologiaScriptLattes() throws IOException, InterruptedException{
		ProcessBuilder pb = new ProcessBuilder("./scriptLattes.py", "padrao.config");
		pb.directory(new File("/home/beatriz/ScriptLattes"));	
		final Process p = pb.start();
		BufferedReader erro_reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String buffer_erro = "";
		final File arquivo_lattes = new File(TMP_DIR + "/Curriculos/saida/cvs_lattes.owl");
		Thread thread = new Thread(){

			@Override
			public void run() {
				while (!arquivo_lattes.exists()){
					System.out.println("verificando existencia de arquivo");
					System.out.println(arquivo_lattes.getAbsolutePath());
				}
				System.out.println("arquivo criado, destruindo processo");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.destroy();
			}
		};
		thread.start();
		
		
		while (((buffer_erro = (erro_reader.readLine())) != null)) {
			System.out.println(buffer_erro);
		}
		p.waitFor();
	}
	
	public void removerArquivos(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
            	file.delete();
            }
        }
	}
}