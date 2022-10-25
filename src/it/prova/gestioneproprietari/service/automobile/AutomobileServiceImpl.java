package it.prova.gestioneproprietari.service.automobile;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileServiceImpl implements AutomobileService {

	private AutomobileDAO automobileDAO;

	public void setAutomobileDAO(AutomobileDAO automobileDAO) {
		this.automobileDAO = automobileDAO;
	}

	@Override
	public List<Automobile> listAllAbitanti() throws Exception {

		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobileDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Automobile caricaSingoloAbitante(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobileDAO.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Automobile automobileInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			automobileDAO.update(automobileInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Automobile automobileInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			automobileDAO.insert(automobileInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idAutomobileInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			automobileDAO.delete(automobileDAO.get(idAutomobileInstance));

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Automobile> proprietariConCodiceFiscaleCheInizianoCon(String iniziale) throws Exception {

		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobileDAO.proprietariConCodiceFiscaleThatStartsWith(iniziale);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Automobile> proprietariMinorenni() throws Exception {

		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			automobileDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobileDAO.proprietariMinorenni();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
