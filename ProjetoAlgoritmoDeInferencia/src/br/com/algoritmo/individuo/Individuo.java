package br.com.algoritmo.individuo;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="Individuo")
public class Individuo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="idIndividuo")
	private Integer idIndividuo;
	
	@NaturalId
	private BigInteger idLattes;
	
	private String nome;

	public Integer getIdIndividuo() {
		return idIndividuo;
	}

	public void setIdIndividuo(Integer idIndividuo) {
		this.idIndividuo = idIndividuo;
	}

	public BigInteger getIdLattes() {
		return idLattes;
	}

	public void setIdLattes(BigInteger idLattes) {
		this.idLattes = idLattes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idLattes == null) ? 0 : idLattes.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individuo other = (Individuo) obj;
		if (idLattes == null) {
			if (other.idLattes != null)
				return false;
		} else if (!idLattes.equals(other.idLattes))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
