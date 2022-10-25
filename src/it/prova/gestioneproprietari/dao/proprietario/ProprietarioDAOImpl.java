package it.prova.gestioneproprietari.dao.proprietario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO {

	private EntityManager entityManager;

	@Override
	public List<Proprietario> list() throws Exception {
		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		return entityManager.find(Proprietario.class, id);
	}

	@Override
	public void update(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		proprietarioInstance = entityManager.merge(proprietarioInstance);
	}

	@Override
	public void insert(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(proprietarioInstance);
	}

	@Override
	public void delete(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(proprietarioInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public int countProprietariConAutoImmatricolateDaUnCertoAnno(int input) throws Exception {
		if (input < 0) {
			throw new Exception("Attenzione! Valore inserito non valido");
		}
		TypedQuery<Integer> query = entityManager.createQuery(
				"select count (distinct proprietario_id) from Automobile where annoimmatricolazione >= ?1",
				Integer.class);
		query.setParameter(1, input);
		return query.getSingleResult().intValue();
	}

}
