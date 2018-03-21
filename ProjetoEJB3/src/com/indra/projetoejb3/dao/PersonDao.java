package com.indra.projetoejb3.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.indra.ejb3.model.Person;

public class PersonDao {
	private static PersonDao instance;
	protected EntityManager entityManager;

	public static PersonDao getInstance() {
		if (instance == null) {
			instance = new PersonDao();
		}

		return instance;
	}

	public PersonDao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("person");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	// Salvar cliente no banco

	public Person save(Person person) throws Exception {
		EntityManager entity = getEntityManager();
		try {
			entity.getTransaction().begin();
			if (person.getCpf() != null) {
				entity.persist(person); // execute insert
			} else {
				if (entity.contains(person)) {
					if (entity.find(Person.class, person.getCpf()) != null) {
						throw new Exception(" Error updating ");
					}
					person = entity.merge(person); // run update
				}
				entity.getTransaction().commit();
			}

		} finally {
			entity.close();
		}
		return person;
	}

	// Metodo de busca por cpf
	public Person getByCpf(final String cpf) {

		Person person = new Person();
		try {
			if (cpf != null && !cpf.isEmpty()) {
				String jpql = "select p from Person p where p.cpf =: cpf ";
				Query query = entityManager.createQuery(jpql);
				query.setParameter("cpf", cpf);

				@SuppressWarnings("unchecked")
				List<Person> personResult = query.getResultList();

				person = personResult.get(0);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"CPF inexistente!!! ");
		}
		return person;
	}

	public void remove(Long cpf) {
		
		EntityManager entity = getEntityManager();
		Person person = entity.find(Person.class, cpf);
		
		try {
			entity.getTransaction().begin();
			entity.remove(person); // execute remove
			entity.getTransaction().commit();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error");
			entityManager.getTransaction().rollback();
		}
	}

}
