package br.com.controle.veiculos.consumer;

import javax.persistence.Id;

public class Fipe {

	@Id
	private Long id;	

	private String marcas;
	private String modelos;
	private String anos;
	private String valor;
	
	public String getMarcas() {
		return marcas;
	}
	public void setMarcas(String marcas) {
		this.marcas = marcas;
	}
	public String getModelos() {
		return modelos;
	}
	public void setModelos(String modelos) {
		this.modelos = modelos;
	}
	public String getAnos() {
		return anos;
	}
	public void setAnos(String anos) {
		this.anos = anos;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Fipe other = (Fipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
