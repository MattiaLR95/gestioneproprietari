package it.prova.gestioneproprietari.model;

import java.util.Date;

public class Automobile {
	private Long id;
	private String marca;
	private String modello;
	private String targa;
	private Date annoImmatricolazione;
	private Proprietario proprietario;

	public Automobile() {
		super();
	}

	public Automobile(String marca, String modello, String targa, Date annoImmatricolazione) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
	}

	public Automobile(String marca, String modello, String targa, Date annoImmatricolazione,
			Proprietario proprietario) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.annoImmatricolazione = annoImmatricolazione;
		this.proprietario = proprietario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Date getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}

	public void setAnnoImmatricolazione(Date annoImmatricolazione) {
		this.annoImmatricolazione = annoImmatricolazione;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

}
