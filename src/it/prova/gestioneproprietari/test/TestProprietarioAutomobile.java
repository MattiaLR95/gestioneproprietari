package it.prova.gestioneproprietari.test;

import java.util.Date;
import java.util.List;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;
import it.prova.gestioneproprietari.service.MyServiceFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {
	public static void main(String[] args) {
		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {
			testCrude(proprietarioService, automobileService);
			System.out.println();
			
			testProprietarioConCodiceFiscaleCheIniziaPer(proprietarioService, automobileService);
			System.out.println();
			
			testTutteAutomobiliConCodiceFiscaleProprietarioIniziaCon(automobileService);
			System.out.println();
			
			testTutteLeAutoConErrori(automobileService);
			System.out.println();

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

	private static void testCrude(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testInserisciMunicipio inizio.............");

		List<Proprietario> listaProprietari = proprietarioService.listAllProprietario();
		if (listaProprietari.isEmpty())
			throw new Exception("Attenzione! Database vuoto");
		// creo nuovo proprietario
		Proprietario nuovoProprietario = new Proprietario("Mattia", "La Rocca", "lrcmtg95a06a494x", new Date());
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");
		proprietarioService.inserisciNuovo(nuovoProprietario);

		Automobile nuovaAutomobile = new Automobile("Audi", "R8", "BC838PM", 2012);
		nuovaAutomobile.setProprietario(nuovoProprietario);
		automobileService.inserisciNuovo(nuovaAutomobile);

		// salvo
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciMunicipio fallito ");

		nuovoProprietario.setNome("Giovanni");
		nuovaAutomobile.setMarca("Ferrari");

		proprietarioService.aggiorna(nuovoProprietario);
		automobileService.aggiorna(nuovaAutomobile);

		if (!proprietarioService.caricaSingoloAbitante(nuovoProprietario.getId()).getNome().equals("Giovanni")) {
			throw new Exception("Update proprietario: FALLITO");
		}

		if (!automobileService.caricaSingoloAbitante(nuovaAutomobile.getId()).getMarca().equals("Ferrari")) {
			throw new Exception("Update automobile: FALLITO");
		}

		automobileService.rimuovi(nuovaAutomobile.getId());
		proprietarioService.rimuovi(nuovoProprietario.getId());

		System.out.println(".......testInserisciMunicipio fine: PASSED.............");
	}

	private static void testProprietarioConCodiceFiscaleCheIniziaPer(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {

		System.out.println("Inizio test proprietarioConCodiceFiscaleCheIniziaPer");

		List<Automobile> listaProprietari = automobileService.listAllAbitanti();
		if (listaProprietari.isEmpty())
			throw new Exception("Attenzione! Database vuoto");

		Proprietario nuovoProprietario = new Proprietario("Ajeje", "Brazorf", "ajjbrza05d24svsx", new Date());
		Automobile nuovaAutomobile = new Automobile("BMW", "X5", "Acjsif", 2015);

		proprietarioService.inserisciNuovo(nuovoProprietario);
		automobileService.inserisciNuovo(nuovaAutomobile);

		String inizioCF = "ajj";

		if (automobileService.proprietariConCodiceFiscaleCheInizianoCon(inizioCF).size() != 1)
			throw new Exception("Test proprietariConCodiceFiscaleCheInizaCon: FALLITO");
		
		proprietarioService.rimuovi(nuovoProprietario.getId());
		automobileService.rimuovi(nuovaAutomobile.getId());
		System.out.println("Test proprietariConCodiceFiscaleCheIniziaCon: COMPLETATO");

	}
	
	private static void testTutteAutomobiliConCodiceFiscaleProprietarioIniziaCon(AutomobileService automobileService)
			throws Exception {
		System.out.println("__inizio testTutteAutomobiliConCodiceFiscaleProprietarioIniziaCon...");
		if (automobileService.listAllAbitanti().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		String inizioCodiceFiscale = "CF";
		List<Automobile> result = automobileService
				.proprietariConCodiceFiscaleCheInizianoCon(inizioCodiceFiscale);
		if (result.isEmpty())
			throw new RuntimeException("FAILED : la ricerca non ha dato i risultati attesi.");
		System.out.println("__fine testTutteAutomobiliConCodiceFiscaleProprietarioIniziaCon : PASSED");
	}

	private static void testTutteLeAutoConErrori(AutomobileService automobileService) throws Exception {
		System.out.println("__inizio testTutteLeAutoConErrori...");
		if (automobileService.listAllAbitanti().isEmpty())
			throw new RuntimeException("FAILED : database vuoto.");
		List<Automobile> result = automobileService.proprietariMinorenni();
		if (result.isEmpty())
			throw new RuntimeException("FAILED : la ricerca non ha dato i risultati attesi.");
		System.out.println("__fine testTutteLeAutoConErrori : PASSED");
	}

}
