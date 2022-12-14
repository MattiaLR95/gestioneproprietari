package it.prova.gestioneproprietari.dao.automobile;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		automobileInstance = entityManager.merge(automobileInstance);
	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(automobileInstance);
	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(automobileInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Automobile> proprietariConCodiceFiscaleThatStartsWith(String iniziale) throws Exception {
		TypedQuery<Automobile> query = entityManager.createQuery(
				"select a from Proprietario p inner join p.automobili a where p.codiceFiscale like ?1",
				Automobile.class);
		return query.setParameter(1, iniziale + "%").getResultList();
	}

	@Override
	public List<Automobile> proprietariMinorenni() {
		TypedQuery<Automobile> query = entityManager.createQuery(
				"select a from Proprietario p inner join p.automobili a where year(CURRENT_DATE)-year(p.dataDiNascita) < 18", Automobile.class);
		return query.getResultList();
	}

}
