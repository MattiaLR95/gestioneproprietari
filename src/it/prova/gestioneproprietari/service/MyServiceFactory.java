package it.prova.gestioneproprietari.service;

import it.prova.gestioneproprietari.dao.MyDaoFactory;
import it.prova.gestioneproprietari.service.automobile.*;
import it.prova.gestioneproprietari.service.proprietario.*;

public class MyServiceFactory {

	// rendiamo le istanze restituite SINGLETON
	private static AutomobileService automobileServiceInstance = null;
	private static ProprietarioService proprietarioServiceInstance = null;

	public static AutomobileService getAutomobileServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

	public static ProprietarioService getProprietarioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}

}
