package it.prova.gestioneproprietari.service.automobile;

import java.util.Date;
import java.util.List;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;


public interface AutomobileService {
	public List<Automobile> listAllAbitanti() throws Exception;

	public Automobile caricaSingoloAbitante(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Long idAutomobileInstance) throws Exception;

	public List<Automobile> proprietariConCodiceFiscaleCheInizianoCon(String iniziale) throws Exception;
	
	public List<Automobile> proprietariMinorenni() throws Exception;
	
	public void setAutomobileDAO(AutomobileDAO automobileDAO);
}
