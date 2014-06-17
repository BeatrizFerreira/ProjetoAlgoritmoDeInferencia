package br.com.algoritmo.web;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

import br.com.algoritmo.individuo.Individuo;
import br.com.algoritmo.individuo.IndividuoRN;

@ManagedBean (name="individuoBean")
@RequestScoped

public class IndividuoBean {
	
	private Individuo individuo = new Individuo();
	private List<Individuo> listaIndividuo;
	private String destino; 
	
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String novo () {
		destino = "individuoCadastrado";
		individuo = new Individuo();
		return "individuo";
	}
	
	public String editar () {
		return "individuo";
	}
	
	public String delete (){
		
		IndividuoRN user = new IndividuoRN();
		user.excluir(individuo);
		listaIndividuo = null;
		return null;
	}
	
	public String salvar(){
		IndividuoRN individuoRN = new IndividuoRN();
		individuoRN.salvar(individuo);
		return "individuoCadastrado";
	}
	
	public List <Individuo> getListaIndividuo () {
		if(listaIndividuo == null) {
			IndividuoRN individuoRN = new IndividuoRN();
			listaIndividuo = individuoRN.listar();
		}
		return listaIndividuo;
	}
	
	public Individuo getIndividuo() {
		return individuo;
	}
	public void setIndividuo(Individuo individuo) {
		this.individuo = individuo;
	}

	public void handleFileUpload (FileUploadEvent event){
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName()+ "is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}